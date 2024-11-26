package fitpeo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

/**
 * FitPeo Home page and contains Home page methods.
 */
public class Home {
    private WebDriver driver;
    private final String homeURL = "https://www.fitpeo.com/";
    private final String revenueCalculatorURL = "https://www.fitpeo.com/revenue-calculator";

    @FindBy(xpath = "//div[contains(text(), 'Revenue Calculator')]")
    private WebElement revenueCalculatorLink;

    public Home(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    /**
     * Navigates to the FitPeo home page.
     *
     * @return True if navigation is successful, false otherwise.
     */
    public Boolean navigateToHomePage() {
        try {
            if (!driver.getCurrentUrl().equals(homeURL)) {
                driver.get(homeURL);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Navigates to the Revenue Calculator page from the home page.
     *
     * @return True if navigation is successful, false otherwise.
     */
    public Boolean navigateToRevenueCalculatorPage() {
        try {
            if (!driver.getCurrentUrl().equals(revenueCalculatorURL)) {
                revenueCalculatorLink.click();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
