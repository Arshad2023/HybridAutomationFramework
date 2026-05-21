package ui.utils;
import com.aventstack.extentreports.ExtentTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestLogger {
    private static final Logger log = LogManager.getLogger(TestLogger.class);
    public static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    public static void setTest(ExtentTest test) {
        testThread.set(test);
    }

    public static ExtentTest getTest() {
        return testThread.get();
    }

    public static void logInfo(String message) {
        log.info(message);
        if (getTest() != null)
            getTest().info(message);

    }

    public static void logPass(String message) {
        log.info(message);
        if (getTest() != null)
            getTest().pass(message);
    }

    public static void logFail(String message) {
        log.error(message);
        if (getTest() != null)
            getTest().fail(message);

    }
    public static void logFailWithScreenshot(String message, String base64Image) {
        log.error(message);
        getTest().fail(message)
                .addScreenCaptureFromBase64String(base64Image);
    }
}
