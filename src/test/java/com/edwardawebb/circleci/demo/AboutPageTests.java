package com.edwardawebb.circleci.demo;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class AboutPageTests extends AbstractWebTests{

    @Test
    public void testAboutPageLoads() throws Exception {
        webDriver.get(BASE_URL + "/about");
        assertThat("Did not find correct aboutPage title",webDriver.getTitle(),is("About Circle CI Challenge"));
    }



}
