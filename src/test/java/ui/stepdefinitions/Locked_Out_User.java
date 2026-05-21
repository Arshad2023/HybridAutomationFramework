package ui.stepdefinitions;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import ui.Base.BaseTest;
import ui.hooks.Hooks;
import ui.utils.ActionMethod;

public class Locked_Out_User extends BaseTest {

    @Then("User should remain on login page")
    public void user_should_remain_on_login_page() {
        String currentUrl = BaseTest.getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("saucedemo"));
        Hooks.getTest().pass("User remained on login page");
    }
    @Then("Error message should contain {string}")
    public void error_message_should_contain(String expectedText) {
        ui.pages.Locked_Out_User lockedOutUser = new ui.pages.Locked_Out_User(BaseTest.getDriver());
        String actualError = lockedOutUser.getErrorMessage();
        Assert.assertTrue(actualError.toLowerCase().contains(expectedText.toLowerCase()));
        Hooks.getTest().pass("Error message validated: " + actualError);
        ActionMethod.captureScreenshot("Locked_User_Error", "Locked user error validated");
    }
    @Then("Username and password fields should be visible")
    public void username_and_password_fields_should_be_visible() {
        ui.pages.Locked_Out_User lockedOutUser = new ui.pages.Locked_Out_User(BaseTest.getDriver());
        Assert.assertTrue(lockedOutUser.isUsernameFieldVisible());
        Assert.assertTrue(lockedOutUser.isPasswordFieldVisible());
        Hooks.getTest().pass("Username and Password fields are visible");
    }
}
