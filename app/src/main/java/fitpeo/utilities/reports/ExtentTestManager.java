package fitpeo.utilities.reports;

import java.util.HashMap;
import java.util.Map;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * Utility class to manage ExtentReports and log test information during execution.
 * Provides methods for starting, logging, and ending tests.
 */
public class ExtentTestManager {
    private static ExtentReports extentReports = ExtentReportManager.getReports();
    private static Map<Object, Object> extentMap = new HashMap<>();

    /**
     * Starts a new test with the given test name and associates it with the current thread.
     *
     * @param testName The name of the test to start.
     * @return The ExtentTest instance for the current test.
     */
    public static ExtentTest startTest(String testName) {
        ExtentTest extentTest = extentReports.startTest(testName);
        extentMap.put((int) (long) Thread.currentThread().threadId(), extentTest);
        return extentTest;
    }

    /**
     * Retrieves the current ExtentTest instance associated with the current thread.
     *
     * @return The current ExtentTest instance.
     */
    public static ExtentTest getTest() {
        return (ExtentTest) extentMap.get((int) (long) Thread.currentThread().threadId());
    }

    /**
     * Logs a message to the current test with the specified status and description.
     *
     * @param status The status of the log (e.g., PASS, FAIL, INFO).
     * @param description The description of the log.
     */
    public static void testLogger(LogStatus status, String description) {
        getTest().log(status, description);
    }

    /**
     * Ends the current test and updates the ExtentReports instance.
     */
    public static void endTest() {
        extentReports.endTest(getTest());
    }
}
