package ui.stepdefinitions;

import io.cucumber.java.en.Then;
import org.testng.Assert;
import ui.Base.BaseTest;
import ui.pages.LoginPage;
import ui.utils.TestLogger;
import ui.hooks.Hooks;

public class LoginSteps extends BaseTest{
    LoginPage loginPage = new LoginPage(BaseTest.getDriver());
    @Then("User should see login page and URL should contain inventory.html")
    public void user_should_see_login_page() {
        String currentUrl = BaseTest.getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("inventory.html"));
    }

    @Then("Product Heading should be visible")
    public void product_list_visibility_and_count(){
        Assert.assertTrue(loginPage.productHeadingVisibilty());
    }
    @Then("Product list should be visible and count should be greater than 0")
    public void product_list_should_be_visible_and_count_should_be_greater_than_zero() {
        int productCount = loginPage.getVisibleProductCount();
        Assert.assertTrue(productCount > 0);
        Hooks.getTest().pass("Product list is visible. Count: " + productCount);
        TestLogger.logPass("Login completed. Product count: " + productCount);
    }
    @Then("Cart Icon is visible on screen")
    public void cartIconVisibility(){
        Assert.assertTrue(loginPage.cartIconVisibility());

    }

}
