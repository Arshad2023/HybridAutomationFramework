package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.Base.BasePage;

public class Locked_Out_User extends BasePage {
    public Locked_Out_User(WebDriver driver) {
        super(driver);
    }

    @FindBy(name = "user-name")
    private WebElement usernameInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(xpath = "//h3[@data-test=\"error\"]")
    private WebElement errorMessage;

    public String getErrorMessage() {
        return action.getText(errorMessage, "Error Message");
    }

    public boolean isUsernameFieldVisible() {

        return action.isElementDisplayed(usernameInput, "Username field");
    }

    public boolean isPasswordFieldVisible() {

        return action.isElementDisplayed(usernameInput, "Password field");
    }
}