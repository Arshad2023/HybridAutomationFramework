package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ui.Base.BasePage;
import org.openqa.selenium.support.FindBy;
import ui.Base.BaseTest;
import ui.utils.ActionMethod;

import java.util.List;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(name = "user-name")
    private WebElement usernameInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(xpath = "//span[@class='title' and text()='Products']")
    private WebElement productTitle;

    @FindBy(xpath = "//div[@id='shopping_cart_container']")
    private WebElement cartIcon;

    @FindBy(xpath = "//div[@class='inventory_list']")
    List<WebElement> productList;

    public void login(String username, String password){
        action.enterText(usernameInput, username, "Username");
        action.enterText(passwordInput, password, "Password");
        action.clickElement(loginButton, "Login button");
        ActionMethod.captureScreenshot("LoginPage", "Login Page");
    }

    public int getVisibleProductCount() {
        return productList.size();
    }
    public boolean productHeadingVisibilty(){
        return action.isElementDisplayed(productTitle, "Product Title");
    }

    public boolean cartIconVisibility(){
        ActionMethod.captureScreenshot("CartVisibilty", "Cart Icon is visible on screen");
        return action.isElementDisplayed(cartIcon, "Cart Icon");
    }
}