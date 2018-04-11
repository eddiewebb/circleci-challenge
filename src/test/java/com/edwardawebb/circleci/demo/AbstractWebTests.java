package com.edwardawebb.circleci.demo;

import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;


@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public abstract class AbstractWebTests {

    @LocalServerPort
    private int serverPort;
    WebDriver webDriver;

    // Enable Sauce Labs browser testing, compliment Open Source license.  SauceLabs.com
    final String BASE_URL;
    private static String SAUCE_USER = System.getenv("sauceUser");
    private static String SAUCE_ACCESS_KEY = System.getenv("sauceApiKey");
    public static final String SAUCE_URL = "http://" + SAUCE_USER + ":" + SAUCE_ACCESS_KEY + "@localhost:4445/wd/hub";

    public AbstractWebTests() {
        BASE_URL = "http://localhost:" + serverPort;
        try {
            webDriver = createWebDriver();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


    public WebDriver  createWebDriver() throws MalformedURLException {
        DesiredCapabilities caps = DesiredCapabilities.chrome();
        caps.setCapability("platform", "Windows 10");
        caps.setCapability("version", "57.0");
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.INFO);
        caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        System.out.println("Testing against BaseURL: " + BASE_URL);
        return new RemoteWebDriver(new URL(SAUCE_URL), caps);
    }


}
