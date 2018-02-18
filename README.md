# Circle CI Challenge

Deploy a simple webapp using CircleCI and Heroku
[![CircleCI](https://circleci.com/gh/eddiewebb/circleci-challenge.svg?style=svg)](https://circleci.com/gh/eddiewebb/circleci-challenge)

##  Testing
To test simple UI functionality we're using Spring Boot's test starter and PhantomJS binaries driven through Selenium's `WebDriver` interface.

## Deploying
First time using Heroku, but I disliked the idea of Heroku rebuilding deployable from source.

Since spring boot creates runnable jar, we override the `Procfile` to use this

```
web: java -jar target/demo-0.0.1-SNAPSHOT.jar
```

And then the in [`config.yml`](.circleci/config.yml) we pass .jar and Procfile as only artifacts to Heroku.

## Viewing

The latest version should be visible on [https://boiling-falls-60288.herokuapp.com/about]


## Running locally

Spring boot!

```
mvn spring-boot:run
```


## Summary of Learning & Setup

- Getting initial build to compile and pass tests in circleci was straightforward [build-3](https://circleci.com/gh/eddiewebb/circleci-challenge/3) 
- Subsequently setup heroku account and local CLI, which was easy enough.
- Adding heroku as isolated job in circleci workflow broke a few builds but back on track by [build 12](https://circleci.com/gh/eddiewebb/circleci-challenge/12)
- When it came to deploying I really disliked heroku rebuilding my code after it was tested. So I deviated frm circleci tutorial, and instead of pushing git repo, I used CLI solely to push my jar file.
- By [build 17](https://circleci.com/workflow-run/231448cf-2486-4a5a-821f-dfe2d623f427) Heroku deployment of jar, the only artifact passed in was succeeding. This eliminated need for SSH key and hack in tutorial.