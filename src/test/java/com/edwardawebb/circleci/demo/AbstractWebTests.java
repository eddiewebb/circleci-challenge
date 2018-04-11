package com.edwardawebb.circleci.demo;

import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.InterfaceAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;

import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AbstractWebTests {

    @LocalServerPort
    private int port;

    // Enable Sauce Labs browser testing, compliment Open Source license.  SauceLabs.com
    private static String SAUCE_USER = System.getenv("SAUCELABS_USER"); //these must match env variables
    private static String SAUCE_ACCESS_KEY = System.getenv("SAUCELABS_KEY"); //these must match env variables
    public static final String SAUCE_URL = "http://" + SAUCE_USER + ":" + SAUCE_ACCESS_KEY + "@localhost:4445/wd/hub";


    public WebDriver  createWebDriver() throws MalformedURLException {
        DesiredCapabilities caps = DesiredCapabilities.chrome();
        caps.setCapability("platform", "Windows 10");
        caps.setCapability("version", "57.0");
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.INFO);
        caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        return new RemoteWebDriver(new URL(SAUCE_URL), caps);
    }

    String getBaseUrl(){
        String baseUrl = "http://localhost:" + port;
        return baseUrl;
    }

}
