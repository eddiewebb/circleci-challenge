# Circle CI Challenge

Deploy a simple webapp using CircleCI and Heroku
[![CircleCI](https://circleci.com/gh/eddiewebb/circleci-challenge.svg?style=svg)](https://circleci.com/gh/eddiewebb/circleci-challenge)

##  Testing
To test simple UI functionality we're using Spring Boot's test starter and PhantomJS binaries driven through Selenium's `WebDriver` interface.

## Deploying
First time using Heroku, since spring boot creates runnable jar, we override the `Procfile`

```
web: java -jar target/demo-0.0.1-SNAPSHOT.jar
```

## Viewing

The latest version should be visible on [https://boiling-falls-60288.herokuapp.com/about]


## Running locally

Spring boot!

```
mvn spring-boot:run
```