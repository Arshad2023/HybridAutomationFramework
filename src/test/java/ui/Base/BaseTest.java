package ui.Base;

import common.config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import ui.hooks.Hooks;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {

    private static ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();
    protected Map<String, String> data = Hooks.getTestData();

    public static WebDriver getDriver() {
        return driverThread.get();
    }

    public static void initDriver() {

        if (driverThread.get() != null) {
            return;
        }

        String browser = ConfigReader.getProperty("browser").toLowerCase();
        boolean isHeadless = Boolean.parseBoolean(ConfigReader.getProperty("headless"));

        WebDriver driver;

        switch (browser) {

            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();

                chromeOptions.addArguments("--disable-notifications");
                chromeOptions.addArguments("--disable-popup-blocking");
                chromeOptions.addArguments("--disable-save-password-bubble");
                chromeOptions.addArguments("--disable-features=PasswordLeakDetection");
                chromeOptions.addArguments("--disable-extensions");
                chromeOptions.addArguments("--disable-background-networking");
                chromeOptions.addArguments("--disable-sync");
                chromeOptions.addArguments("--no-first-run");
                chromeOptions.addArguments("--no-default-browser-check");

                Map<String, Object> prefs = new HashMap<>();
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                prefs.put("profile.password_manager_leak_detection", false);

                chromeOptions.setExperimentalOption("prefs", prefs);

                if (isHeadless) {
                    chromeOptions.addArguments("--headless=new");
                    chromeOptions.addArguments("--window-size=1920,1080");
                    chromeOptions.addArguments("--disable-gpu");
                    chromeOptions.addArguments("--no-sandbox");
                    chromeOptions.addArguments("--disable-dev-shm-usage");
                } else {
                    chromeOptions.addArguments("--start-maximized");
                }

                driver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();

                if (isHeadless) {
                    firefoxOptions.addArguments("-headless");
                }

                driver = new FirefoxDriver(firefoxOptions);

                if (!isHeadless) {
                    driver.manage().window().maximize();
                }

                break;

            default:
                throw new RuntimeException("Browser not supported: " + browser);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(ConfigReader.getProperty("uiBaseUrl"));
        driverThread.set(driver);
    }

    public static void quitDriver() {

        WebDriver driver = driverThread.get();

        if (driver != null) {
            try {
                driver.quit();
            } finally {
                driverThread.remove();
            }
        }
    }
}
