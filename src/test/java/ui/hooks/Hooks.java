package ui.hooks;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.*;
import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import ui.Base.BaseTest;
import ui.utils.ActionMethod;
import common.report.ExtentManager;
import ui.utils.TestLogger;
import ui.utils.ExcelUtils;
import java.util.Map;

public class Hooks {

    private static ExtentReports extent = ExtentManager.getInstance();

    private static ThreadLocal<Throwable> stepException = new ThreadLocal<>();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static ThreadLocal<Map<String, String>> testData = new ThreadLocal<>();
    private static ThreadLocal<Boolean> screenshotCaptured = new ThreadLocal<>();
    private static ThreadLocal<Integer> stepCounter = new ThreadLocal<>();
    private static ThreadLocal<Boolean> scenarioSkipped = new ThreadLocal<>();

    public static Map<String, String> getTestData() {
        return testData.get();
    }

    public static void setStepException(Throwable e) {
        stepException.set(e);
    }

    public static ExtentTest getTest() {
        return test.get();
    }

    @Before
    public void beforeScenario(Scenario scenario) {

        String scenarioName = scenario.getName();
        ExtentTest extentTest = ExtentManager.createOrGetTest(scenarioName);

        test.set(extentTest);
        TestLogger.setTest(extentTest);

        screenshotCaptured.set(false);
        stepCounter.set(0);
        scenarioSkipped.set(false);
        stepException.remove();

        Map<String, String> data =
                ExcelUtils.getMergedTestData(scenarioName);

        testData.set(data);

        if (data == null ||
                !data.getOrDefault("RunMode", "N").equalsIgnoreCase("Y")) {

            scenarioSkipped.set(true);
            extentTest.skip("Scenario Skipped due to RunMode = N");

            throw new SkipException("Skipping due to RunMode = N");
        }

        BaseTest.initDriver();
    }

    @AfterStep
    public void afterStep(Scenario scenario) {

        ExtentTest extentTest = test.get();
        Map<String, String> data = testData.get();

        if (extentTest == null) {
            return;
        }

        int currentStep = stepCounter.get() == null
                ? 1
                : stepCounter.get() + 1;

        stepCounter.set(currentStep);

        if (scenario.isFailed()
                && !Boolean.TRUE.equals(screenshotCaptured.get())) {

            WebDriver driver = BaseTest.getDriver();

            String testCaseId =
                    scenario.getName().replaceAll("\\s+", "_");

            String screenshotName =
                    testCaseId + "_Step" + currentStep;

            String testDescription = "No Description";

            if (data != null) {
                testDescription =
                        data.getOrDefault("Description", "No Description");
            }

            Throwable e = stepException.get();

            String failureReason = "Unknown Error";

            if (e != null) {
                failureReason =
                        e.getClass().getSimpleName()
                                + " : "
                                + e.getMessage();
            }

            ActionMethod.captureAndAttachScreenshot(
                    driver,
                    extentTest,
                    screenshotName,
                    "Step "
                            + currentStep
                            + " Failed - "
                            + testDescription
                            + "<br><b>Reason:</b> "
                            + failureReason,
                    "fail"
            );

            screenshotCaptured.set(true);
        }
    }

    @After
    public void afterScenario(Scenario scenario) {

        ExtentTest extentTest = test.get();

        if (extentTest != null) {

            if (Boolean.TRUE.equals(scenarioSkipped.get())) {

                extentTest.skip("Scenario Skipped: " + scenario.getName());

            } else if (scenario.isFailed()) {

                extentTest.fail("Scenario Failed: " + scenario.getName());

            } else {

                extentTest.pass("Scenario Passed: " + scenario.getName());
            }
        }

        BaseTest.quitDriver();

        test.remove();
        testData.remove();
        screenshotCaptured.remove();
        stepCounter.remove();
        stepException.remove();
        scenarioSkipped.remove();
        TestLogger.testThread.remove();
    }

    @AfterAll
    public static void tearDown() {
        extent.flush();
    }
}
