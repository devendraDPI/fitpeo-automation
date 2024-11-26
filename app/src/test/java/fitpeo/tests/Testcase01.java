package fitpeo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import fitpeo.pages.Home;
import fitpeo.pages.RevenueCalculator;

public class Testcase01 extends Base {
    @Test
    public void testcase01() {
        Home home = new Home(driver);
        RevenueCalculator revenueCalculator = new RevenueCalculator(driver);

        // Navigate to the FitPeo Homepage
        home.navigateToHomePage();

        // Navigate to the Revenue Calculator Page
        home.navigateToRevenueCalculatorPage();

        // Scroll down to the slider section
        revenueCalculator.scrollToMedicareEligiblePatients();

        // Adjust the slider and verify
        revenueCalculator.adjustPatientSlider(820);
        Assert.assertTrue(revenueCalculator.verifySliderAndTextValue());

        // Update the text field and verify
        revenueCalculator.updatePatientTextBox(560);
        Assert.assertTrue(revenueCalculator.verifySliderAndTextValue());

        // Adjust the slider and verify
        revenueCalculator.adjustPatientSlider(820);
        Assert.assertTrue(revenueCalculator.verifySliderAndTextValue());

        // Select CPT codes
        String[] cptCodes = {"CPT-99091", "CPT-99453", "CPT-99454", "CPT-99474"};
        revenueCalculator.selectCPTCodes(cptCodes);

        // Verify that the header displaying Total Recurring Reimbursement for all Patients Per Month
        Assert.assertTrue(revenueCalculator.verifyTotalRecurringReimbursementPatientsPerMonth(110700));
    }
}
