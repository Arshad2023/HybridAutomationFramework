package common.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import common.config.ConfigReader;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class ExtentManager {

    private static final Map<String, ExtentTest> testMap =
            Collections.synchronizedMap(new LinkedHashMap<>());

    private static volatile ExtentReports extent;
    public static ExtentTest createOrGetTest(String testName) {
        return testMap.computeIfAbsent(testName, name -> getInstance().createTest(name));
    }
    public static ExtentReports getInstance() {
        if (extent == null) {
            synchronized (ExtentManager.class) {
                if (extent == null) {
                    extent = createInstance();
                }
            }
        }
        return extent;
    }
    private static ExtentReports createInstance() {

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String reportPath = System.getProperty("user.dir")
                + "/reports/ExtentReport_" + timestamp + ".html";

        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);

        reporter.config().setDocumentTitle("Automation Report");
        reporter.config().setReportName("Selenium BDD Execution");
        reporter.config().setTheme(Theme.STANDARD);
        reporter.config().setTimelineEnabled(true);

        ExtentReports extentReports = new ExtentReports();
        extentReports.attachReporter(reporter);

        extentReports.setSystemInfo("Tester", "Arshad Rangrez");
        extentReports.setSystemInfo("Environment", "QA");
        extentReports.setSystemInfo("Browser", ConfigReader.getProperty("browser"));
        extentReports.setSystemInfo("OS", System.getProperty("os.name"));
        extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));

        return extentReports;
    }

    public static void flushReports() {
        if (extent != null) {
            extent.flush();
        }
    }
}
