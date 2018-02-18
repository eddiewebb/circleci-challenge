package com.edwardawebb.circleci.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class HomePageTests {



    @org.springframework.boot.test.context.TestConfiguration
    static class TestConfiguration {

        @Bean
        public WebDriver webDriver(){
            return new PhantomJSDriver();
        }
    }


    @LocalServerPort
    private int serverPort;

    @Autowired
    WebDriver webDriver;


    @Test
    public void testButtonShowsInfoDialog() throws Exception {
            webDriver.get(getUrl());
            assertThat("Did not find welcome text",webDriver.findElement(By.id("welcome")).getText(),is("Hello Circle CI!"));
    }

    private String getUrl() {
        return "http://localhost:" + serverPort;
    }



}
