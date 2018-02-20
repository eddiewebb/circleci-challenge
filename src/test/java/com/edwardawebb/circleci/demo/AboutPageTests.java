package com.edwardawebb.circleci.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class AboutPageTests extends AbstractWebTests{

    @Test
    public void testAboutPageLoads() throws Exception {
        webDriver.get(getBaseUrl() + "/about");
        assertThat("Did not find correct aboutPage title",webDriver.getTitle(),is("About Circle CI Challenge"));
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
