package common.framework;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener, ISuiteListener {

    private static final Logger log =
            LogManager.getLogger(TestListener.class);

    @Override
    public void onStart(ISuite suite) {
        log.info("Starting test suite: {}", suite.getName());
    }

    @Override
    public void onFinish(ISuite suite) {
        log.info("Finished test suite: {}", suite.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info("Total Passed: {}", context.getPassedTests().size());
        log.info("Total Failed: {}", context.getFailedTests().size());
        log.info("Total Skipped: {}", context.getSkippedTests().size());
    }

    @Override
    public void onTestStart(ITestResult result) {
        String scenarioName = getScenarioName(result);
        log.info("Started: {}", scenarioName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String scenarioName = getScenarioName(result);
        long executionTime = result.getEndMillis() - result.getStartMillis();
        log.info("Passed: {} | Time: {} ms", scenarioName, executionTime);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String scenarioName = getScenarioName(result);
        long executionTime = result.getEndMillis() - result.getStartMillis();
        log.error("Failed: {} | Time: {} ms", scenarioName, executionTime);
        if (result.getThrowable() != null) {
            log.error("Failure Reason: ", result.getThrowable());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String scenarioName = getScenarioName(result);
        log.warn("Skipped: {}", scenarioName);
        if (result.getThrowable() != null) {
            log.warn("Skip Reason: ", result.getThrowable());
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        log.warn("Test partly failed but within success percentage: {}", getScenarioName(result));
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        log.error("Timeout: {}", getScenarioName(result));
        onTestFailure(result);
    }

    private String getScenarioName(ITestResult result) {
        try {
            Object[] parameters = result.getParameters();
            if (parameters != null && parameters.length > 0) {
                return parameters[0].toString();
            }
        } catch (Exception e) {
            log.warn("Could not extract scenario name: {}", e.getMessage());
        }
        return result.getMethod().getMethodName();
    }
}
