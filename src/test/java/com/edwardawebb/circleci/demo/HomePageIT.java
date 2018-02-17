package com.edwardawebb.circleci.demo;

import org.fluentlenium.adapter.FluentTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class HomePageIT extends FluentTest {
    @Value("${local.server.port}")
    private int serverPort;
    private WebDriver webDriver = new PhantomJSDriver();

    @Test
    public void testButtonShowsInfoDialog() throws Exception {
            goTo(getUrl());
            assertThat("Did not find welcome text",find("#welcome"),is("Hello Circle CI!"));
    }

    private String getUrl() {
        return "http://localhost:" + serverPort;
    }
}
