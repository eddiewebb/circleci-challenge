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