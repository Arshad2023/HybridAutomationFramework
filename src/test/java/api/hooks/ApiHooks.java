package api.hooks;

import com.aventstack.extentreports.ExtentTest;
import api.stepdefinitions.CommonSteps;
import common.report.ExtentManager;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class ApiHooks {
    public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    @Before
    public void beforeScenario(Scenario scenario) {
        ExtentTest test = ExtentManager.createOrGetTest(scenario.getName());
        extentTest.set(test);
        test.info("Scenario Started: " + scenario.getName());
    }
    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            extentTest.get().fail("Scenario Failed: " + scenario.getName());
        } else {
            extentTest.get().pass("Scenario Passed: " + scenario.getName());
        }
        extentTest.remove();
        CommonSteps.clearResponse();
    }
    @AfterAll
    public static void afterAll() {
        ExtentManager.flushReports();
    }
}
