package ui.utils;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import ui.Base.BaseTest;
import ui.hooks.Hooks;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import static ui.hooks.Hooks.setStepException;

public class ActionMethod {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public ActionMethod(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickElement(WebElement element, String elementName) {
        try {
            WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(element));

            try {
                ele.click();
                TestLogger.logPass("Clicked on: " + elementName);
            } catch (Exception jsException) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", ele);
                TestLogger.logPass("Clicked on using JS: " + elementName);
            }

        } catch (Exception e) {
            setStepException(e);
            TestLogger.logFail("Failed to click on: " + elementName + " | Reason: " + e.getMessage());
            throw new RuntimeException("Failed to click on: " + elementName, e);
        }
    }

    public void enterText(WebElement element, String value, String elementName) {
        try {
            WebElement ele = wait.until(ExpectedConditions.visibilityOf(element));
            ele.clear();
            ele.sendKeys(value);
            TestLogger.logPass("Entered value in: " + elementName);

        } catch (Exception e) {
            setStepException(e);
            TestLogger.logFail("Failed to enter value in: " + elementName + " | Reason: " + e.getMessage());
            throw new RuntimeException("Failed to enter value in: " + elementName, e);
        }
    }

    public boolean isElementDisplayed(WebElement element, String elementName) {
        try {
            boolean isDisplayed = wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();

            if (isDisplayed) {
                TestLogger.logPass(elementName + " is displayed");
                return true;
            }

        } catch (Exception e) {
            setStepException(e);
            TestLogger.logFail(elementName + " is not displayed | Reason: " + e.getMessage());
            return false;
        }

        return false;
    }

    public void selectByVisibleText(WebElement element, String value, String elementName) {
        try {
            WebElement ele = wait.until(ExpectedConditions.visibilityOf(element));
            Select select = new Select(ele);
            select.selectByVisibleText(value);

            TestLogger.logPass("Selected '" + value + "' from: " + elementName);

        } catch (Exception e) {
            setStepException(e);
            TestLogger.logFail("Failed to select '" + value + "' from: " + elementName + " | Reason: " + e.getMessage());
            throw new RuntimeException("Failed to select value from: " + elementName, e);
        }
    }

    public String getText(WebElement element, String elementName) {
        try {
            WebElement ele = wait.until(ExpectedConditions.visibilityOf(element));
            String text = ele.getText();

            TestLogger.logPass("Captured text from: " + elementName + " | Text: " + text);
            return text;

        } catch (Exception e) {
            setStepException(e);
            TestLogger.logFail("Failed to get text from: " + elementName + " | Reason: " + e.getMessage());
            throw new RuntimeException("Failed to get text from: " + elementName, e);
        }
    }

    public static void captureAndAttachScreenshot(
            WebDriver driver,
            ExtentTest extentTest,
            String screenshotName,
            String logMessage,
            String status
    ) {
        try {
            if (driver == null || extentTest == null) return;

            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String finalScreenshotName = screenshotName + "_" + timestamp;

            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            String screenshotsDir = System.getProperty("user.dir") + "/reports/screenshots/";
            new File(screenshotsDir).mkdirs();

            File destFile = new File(screenshotsDir + finalScreenshotName + ".png");

            Files.copy(srcFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

            String relativePath = "screenshots/" + finalScreenshotName + ".png";

            if (status.equalsIgnoreCase("pass")) {
                extentTest.pass(logMessage,
                        MediaEntityBuilder.createScreenCaptureFromPath(relativePath).build());
            } else if (status.equalsIgnoreCase("fail")) {
                extentTest.fail(logMessage,
                        MediaEntityBuilder.createScreenCaptureFromPath(relativePath).build());
            } else {
                extentTest.info(logMessage,
                        MediaEntityBuilder.createScreenCaptureFromPath(relativePath).build());
            }

        } catch (Exception e) {
            setStepException(e);
            extentTest.warning("Could not capture screenshot: " + e.getMessage());
        }
    }

    public static void captureScreenshot(String screenshotName, String message) {
        captureAndAttachScreenshot(
                BaseTest.getDriver(),
                Hooks.getTest(),
                screenshotName,
                message,
                "pass"
        );
    }

    public void waitForElementVisible(WebElement element, String elementName) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            TestLogger.logPass(elementName + " is visible");

        } catch (Exception e) {
            setStepException(e);
            TestLogger.logFail(elementName + " is not visible | Reason: " + e.getMessage());
            throw new RuntimeException(elementName + " is not visible", e);
        }
    }

    public void waitForElementClickable(WebElement element, String elementName) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            TestLogger.logPass(elementName + " is clickable");

        } catch (Exception e) {
            setStepException(e);
            TestLogger.logFail(elementName + " is not clickable | Reason: " + e.getMessage());
            throw new RuntimeException(elementName + " is not clickable", e);
        }
    }
}