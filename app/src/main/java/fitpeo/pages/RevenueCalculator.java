package fitpeo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import fitpeo.utilities.utils;

/**
 * FitPeo Revenue Calculator page and contains Revenue Calculator methods.
 */
public class RevenueCalculator {
    private WebDriver driver;

    @FindBy(xpath = "//div[contains(@class, 'MuiGrid-root MuiGrid-container')]")
    private WebElement revenueCalculatorSection;

    @FindBy(xpath = "//span[contains(@class, 'MuiSlider-thumb')]/input")
    private WebElement sliderHandle;

    @FindBy(xpath = "//span[contains(@class, 'MuiSlider-rail')]")
    private WebElement sliderTrack;

    @FindBy(xpath = "//input[contains(@class, 'MuiInputBase-input')]")
    private WebElement patientTextBox;

    @FindBy(xpath = "//p[contains(text(), 'Total Recurring Reimbursement')]/p")
    private WebElement totalRecurringReimbursement;

    public RevenueCalculator(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    /**
     * Scrolls to the Medicare Eligible Patients section.
     */
    public void scrollToMedicareEligiblePatients() {
        utils.scrollToViewport(driver, revenueCalculatorSection);
    }

    /**
     * Adjusts the slider to the specified value.
     *
     * @param value The target value.
     */
    public void adjustPatientSlider(int value) {
        try {
            int sliderWidth = sliderTrack.getSize().width;
            int currentValue = Integer.parseInt(sliderHandle.getAttribute("aria-valuenow"));
            int minValue = Integer.parseInt(sliderHandle.getAttribute("aria-valuemin"));
            int maxValue = Integer.parseInt(sliderHandle.getAttribute("aria-valuemax"));
            int offset = (int) (((double) (value - currentValue) / (maxValue - minValue)) * sliderWidth);
            Actions actions = new Actions(driver);
            actions.dragAndDropBy(sliderHandle, offset, 0).perform();
            currentValue = Integer.parseInt(sliderHandle.getAttribute("aria-valuenow"));
            while (currentValue != value) {
                if (currentValue < value) {
                    sliderHandle.sendKeys(Keys.RIGHT);
                } else {
                    sliderHandle.sendKeys(Keys.LEFT);
                }
                currentValue = Integer.parseInt(sliderHandle.getAttribute("aria-valuenow"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Verifies if the slider and the text field display the same value.
     *
     * @return True if values match, false otherwise.
     */
    public Boolean verifySliderAndTextValue() {
        try {
            int sliderValue = Integer.parseInt(sliderHandle.getAttribute("aria-valuenow"));
            int textValue = Integer.parseInt(patientTextBox.getAttribute("value"));
            return sliderValue == textValue;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Updates the patient count in the text box.
     *
     * @param value The target value.
     */
    public void updatePatientTextBox(int value) {
        try {
            patientTextBox.click();
            int val = patientTextBox.getAttribute("value").length();
            for (int i=0; i<=val; i++) {
                patientTextBox.sendKeys(Keys.BACK_SPACE);
            }
            patientTextBox.sendKeys(String.valueOf(value));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Selects the given CPT codes by clicking their corresponding checkboxes.
     *
     * @param cptCodes An array of CPT codes to select.
     */
    public void selectCPTCodes(String[] cptCodes) {
        try {
            for (String cptCode : cptCodes) {
                WebElement element = driver.findElement(By.xpath("//p[contains(text(), '"+ cptCode +"')]/../label//input"));
                if (!element.isSelected()) {
                    element.click();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Verifies if the total recurring reimbursement matches the expected value.
     *
     * @param expectedReimbursement The expected reimbursement value.
     * @return True if values match, false otherwise.
     */
    public Boolean verifyTotalRecurringReimbursementPatientsPerMonth(int expectedReimbursement) {
        try {
            int recurringReimbursement = Integer.parseInt(totalRecurringReimbursement.getText().replaceAll("[^0-9]", ""));
            return recurringReimbursement == expectedReimbursement;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
