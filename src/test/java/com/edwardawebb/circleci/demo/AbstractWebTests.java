package com.edwardawebb.circleci.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public abstract class AbstractWebTests {

    @LocalServerPort
    private int serverPort;
    @Autowired
    WebDriver webDriver;

    protected String getBaseUrl() {
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
