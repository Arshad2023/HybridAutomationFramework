package ui.Base;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.hooks.Hooks;
import ui.utils.ActionMethod;

import java.time.Duration;
import java.util.Map;

public class BasePage {
    protected Map<String, String> data = Hooks.getTestData();
    protected WebDriver driver;
    protected ActionMethod action;
    protected WebDriverWait wait;
    protected JavascriptExecutor js;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.action = new ActionMethod(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }
}
