package fitpeo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import fitpeo.pages.Home;
import fitpeo.pages.RevenueCalculator;
import fitpeo.utilities.reports.ExtentTestManager;

public class Testcase01 extends Base {
    @Test
    public void testcase01() {
        Home home = new Home(driver);
        RevenueCalculator revenueCalculator = new RevenueCalculator(driver);

        // Navigate to the FitPeo Homepage
        ExtentTestManager.testLogger(LogStatus.INFO, "Navigating to the FitPeo Homepage");
        home.navigateToHomePage();

        // Navigate to the Revenue Calculator Page
        ExtentTestManager.testLogger(LogStatus.INFO, "Navigating to the Revenue Calculator Page");
        home.navigateToRevenueCalculatorPage();

        // Scroll down to the slider section
        ExtentTestManager.testLogger(LogStatus.INFO, "Scrolling down to the slider section");
        revenueCalculator.scrollToMedicareEligiblePatients();

        // Adjust the slider and verify
        ExtentTestManager.testLogger(LogStatus.INFO, "Adjusting the slider");
        revenueCalculator.adjustPatientSlider(820);
        Assert.assertTrue(revenueCalculator.verifySliderAndTextValue());
        ExtentTestManager.testLogger(LogStatus.PASS, "Verify if slider and text box is updated");

        // Update the text field and verify
        ExtentTestManager.testLogger(LogStatus.INFO, "Updating patient text box");
        revenueCalculator.updatePatientTextBox(560);
        Assert.assertTrue(revenueCalculator.verifySliderAndTextValue());
        ExtentTestManager.testLogger(LogStatus.PASS, "Verify if slider and text box is updated");

        // Adjust the slider and verify
        ExtentTestManager.testLogger(LogStatus.INFO, "Adjust the slider");
        revenueCalculator.adjustPatientSlider(820);
        Assert.assertTrue(revenueCalculator.verifySliderAndTextValue());
        ExtentTestManager.testLogger(LogStatus.PASS, "Verify if slider and text box is updated");

        // Select CPT codes
        String[] cptCodes = {"CPT-99091", "CPT-99453", "CPT-99454", "CPT-99474"};
        ExtentTestManager.testLogger(LogStatus.INFO, "Selecting CPT codes");
        revenueCalculator.selectCPTCodes(cptCodes);

        // Verify that the header displaying Total Recurring Reimbursement for all Patients Per Month
        Assert.assertTrue(revenueCalculator.verifyTotalRecurringReimbursementPatientsPerMonth(110700));
        ExtentTestManager.testLogger(LogStatus.PASS, "Verify that the header displaying Total Recurring Reimbursement for all Patients Per Month");
    }
}
