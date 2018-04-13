package com.edwardawebb.circleci.demo;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.StringContains.containsString;

public class HomePageTests extends AbstractWebTests{


    @Test
    public void testHomePageLoads() throws Exception {

        WebDriver webDriver = createWebDriver();
        webDriver.get(getBaseUrl());
        assertThat("Did not find welcome text",webDriver.findElement(By.id("welcome")).getText(),containsString("Hello Circle CI!"));
    }

    @Test
    public void testButtonDisplaysInfoDialog() throws Exception {

        WebDriver webDriver = createWebDriver();
        webDriver.get(getBaseUrl());
        WebElement infoDetails = webDriver.findElement(By.id("msgBox"));
        WebElement infoButton = webDriver.findElement(By.id("msgButton"));
        assertThat("Hidden element visible by default.", infoDetails.isDisplayed(),is(false));
        infoButton.click();
        assertThat("Additional info not displayed on button click.",infoDetails.isDisplayed(),is(true));
    }



}
