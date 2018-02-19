# Circle CI Challenge

Build, Test and Deploy a simple webapp using CircleCI and Heroku.
[![CircleCI](https://circleci.com/gh/eddiewebb/circleci-challenge.svg?style=svg)](https://circleci.com/gh/eddiewebb/circleci-challenge)


##  Testing
To test simple UI functionality we're using Spring Boot's test starter and PhantomJS binaries driven through Selenium's `WebDriver` interface.

You can see these simple tests in [HomePagesTests.java](src/test/java/com/edwardawebb/circleci/demo/HomePageTests.java)

## Deploying
**This was my first time using Heroku**, and have more experience with Cloud Foundry as an abstracted application PaaS.
I disliked the idea of Heroku rebuilding deployable from source, being a firm believer in **"Build once, run anywhere."**

Since spring boot creates a self-contained runnable jar, that the tests above validate, it was important to find a non `git push` way to deploy this project in which only the precompiled artifact was passed.
And in fact the Heroku CLI supports just that with 
```
heroku deploy:jar self-contained.jar --app $HEROKU_APP_NAME
```
Which can be seen in [config.yml](.circleci/config.yml) 


## Viewing

The latest version should be visible on [https://boiling-falls-60288.herokuapp.com]


## Running locally

This project uses spring boot, so run as you would any other like project to start on port :8080 locally.

```
mvn spring-boot:run
```


## Summary of Learning and Setup

- Getting initial build to compile and pass tests in circleci was straightforward [build-3](https://circleci.com/gh/eddiewebb/circleci-challenge/3) 
  I am by no means a UI developer, and recalling the setup required to use selenium/phantomjs for Javascript testing was the biggest challenge. 
  Failures were related to phantomjs binary install.
- Subsequently setup heroku account and local CLI, which was easy enough.
  Following Heroku tutorial locally used the `git push heroku master` approach, which seems fine for exploratory development. 
  But for production use cases the idea of rebuilding the binary with `-DskipTests` seemed unnecessary risk. More about that later.
- Adding heroku as isolated job in circleci workflow broke a few builds but back on track by [build 12](https://circleci.com/gh/eddiewebb/circleci-challenge/12).
  CircleCI tutorials combine build and deploy in a single job, with entire source directory checked out.  Breaking build/test and Deploy into seperate jobs took some tweaking.
- When it came to deploying I really disliked heroku rebuilding my code after it was tested. 
  So I deviated frm circleci tutorial, and instead of pushing git repo, I used CLI solely to push my jar file.
  This eliminated need for SSH key (git.heroku.com) and `verifyHosts` hack in tutorial, while also appeasing my **"build once run anywhere"** desires.
- By [build 17](https://circleci.com/workflow-run/231448cf-2486-4a5a-821f-dfe2d623f427) the end to end workflow with seperate build and deploy jobs was passing.
  Heroku cli deploys the jar which is passed via circleCI's `workspace` support. 
  
  
## Previous CircleCI Experience

Eddie has used CircleCI previously to deploy his [personal website](https://edwardawebb.com) built with Hugo. 
[That project's config.yml](https://github.com/eddiewebb/json-resume/blob/master/.circleci/config.yml) deploys a test domain for all branches, and a prod domain for `master` branch only.
```
workflows:
  version: 2
  build-deploy:
    jobs:
      - build
      - deploy-stage:
          requires:
            - build
      - deploy-prod:
          requires:
            - deploy-stage
          filters:
            branches:
              only: master
```

