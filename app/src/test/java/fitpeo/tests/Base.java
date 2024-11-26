package fitpeo.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

/**
 * Base class for setting up and tearing down the WebDriver.
 */
public class Base {
    static WebDriver driver;

    @BeforeSuite
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
    }

    @AfterSuite
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
