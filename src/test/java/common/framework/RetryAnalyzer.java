package common.framework;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private static final Logger log = LogManager.getLogger(RetryAnalyzer.class);
    private static final int maxRetryCount = 3;
    private int count = 0;
    @Override
    public boolean retry(ITestResult result) {
        if (count < maxRetryCount) {
            count++;
            log.info("Retrying test: {} | Attempt {}", result.getName(), count + 1);
            return true;
        }
        return false;
    }
}
