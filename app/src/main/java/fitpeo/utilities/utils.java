package fitpeo.utilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Utility methods.
 */
public class Utils {
    /**
     * Scrolls the viewport to make the given element visible.
     *
     * @param driver  The WebDriver instance.
     * @param element The WebElement to scroll to.
     */
    public static void scrollToViewport(WebDriver driver, WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(element));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView();", element);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Captures a screenshot.
     *
     * @param driver  The WebDriver instance.
     * @return The absolute file path of the saved screenshot.
     */
    public static String capture(WebDriver driver) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destFile = new File(System.getProperty("user.dir") + File.separator + "reports" + File.separator + "error_" + System.currentTimeMillis() + ".png");
        String errorFilePath = destFile.getAbsolutePath();
        try {
            FileUtils.copyFile(srcFile, destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return errorFilePath;
    }
}
