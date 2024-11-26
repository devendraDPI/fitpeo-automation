package fitpeo.utilities;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Utility methods.
 */
public class utils {
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
}
