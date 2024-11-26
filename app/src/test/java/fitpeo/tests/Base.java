package fitpeo.tests;

import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.LogStatus;

import fitpeo.utilities.Utils;
import fitpeo.utilities.reports.ExtentReportManager;
import fitpeo.utilities.reports.ExtentTestManager;

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

    @BeforeMethod
    public void beforeTest(Method method) {
        ExtentTestManager.startTest(method.getName());
    }

    @AfterMethod
    public void afterTest(ITestResult result) throws IOException {
        switch (result.getStatus()) {
            case ITestResult.SUCCESS:
                ExtentTestManager.testLogger(LogStatus.PASS, "Test step PASS");
                break;
            case ITestResult.FAILURE:
                ExtentTestManager.testLogger(LogStatus.FAIL, ExtentTestManager.getTest().addScreenCapture(Utils.capture(driver)) + "Test FAIL");
                break;
            default:
                ExtentTestManager.testLogger(LogStatus.SKIP, "Test step SKIP");
        }
        ExtentTestManager.endTest();
    }

    @AfterSuite
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
        ExtentReportManager.getReports().flush();
    }
}
