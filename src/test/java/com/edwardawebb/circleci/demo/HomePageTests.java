package com.edwardawebb.circleci.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.StringContains.containsString;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class HomePageTests {

    @LocalServerPort
    private int serverPort;
    @Autowired
    WebDriver webDriver;


    @Test
    public void testHomePageLoads() throws Exception {
        webDriver.get(getUrl());
        assertThat("Did not find welcome text",webDriver.findElement(By.id("welcome")).getText(),containsString("Hello Circle CI!"));
    }

    @Test
    public void testButtonDisplaysInfoDialog() throws Exception {
        webDriver.get(getUrl());
        WebElement infoDetails = webDriver.findElement(By.id("msgBox"));
        WebElement infoButton = webDriver.findElement(By.id("msgButton"));
        assertThat("Hidden element visible by default.", infoDetails.isDisplayed(),is(false));
        infoButton.click();
        assertThat("Additional info not displayed on button click.",infoDetails.isDisplayed(),is(true));
    }

    private String getUrl() {
        return "http://localhost:" + serverPort;
    }


    @org.springframework.boot.test.context.TestConfiguration
    static class TestConfiguration {
        @Bean
        public WebDriver webDriver(){
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setJavascriptEnabled(true);
            return new PhantomJSDriver(capabilities);
        }
    }


}
