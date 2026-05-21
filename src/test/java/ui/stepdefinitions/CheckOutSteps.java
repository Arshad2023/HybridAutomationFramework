package ui.stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import ui.Base.BaseTest;
import ui.hooks.Hooks;
import ui.pages.CartPage;
import ui.pages.CheckoutPage;
import ui.pages.InventoryPage;
import ui.utils.ActionMethod;
import java.util.Map;

public class CheckOutSteps extends BaseTest{
    InventoryPage inventoryPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    @When("User adds Backpack and Bike Light products to cart")
    public void user_adds_backpack_and_bike_light_products_to_cart() {
        inventoryPage = new InventoryPage(BaseTest.getDriver());
        inventoryPage.addBackpackAndBikeLightToCart();
        Hooks.getTest().pass("Backpack and Bike Light added to cart");
    }
    @When("User clicks on checkout button")
    public void user_clicks_on_checkout_button() {
        cartPage = new CartPage(BaseTest.getDriver());
        cartPage.clickCheckoutButton();
        Hooks.getTest().pass("Clicked on checkout button");
    }
    @When("User enters checkout details")
    public void user_enters_checkout_details() {
        checkoutPage = new CheckoutPage(BaseTest.getDriver());
        Map<String, String> data = Hooks.getTestData();
        checkoutPage.enterCheckoutInformation(
                data.get("FirstName"),
                data.get("LastName"),
                data.get("PostalCode")
        );
        Hooks.getTest().pass("Checkout information entered");
    }
    @Then("Checkout item total should match sum of product prices")
    public void checkout_item_total_should_match_sum_of_product_prices() {
        double expectedTotal = checkoutPage.getCalculatedProductTotal();
        double actualTotal = checkoutPage.getDisplayedItemTotal();
        Assert.assertEquals(actualTotal, expectedTotal);
        Hooks.getTest().pass("Expected total: " + expectedTotal + " | Actual total: " + actualTotal);
        ActionMethod.captureScreenshot("Checkout_Overview", "Checkout overview validated");
    }
    @When("User clicks on finish button")
    public void user_clicks_on_finish_button() {
        checkoutPage.clickFinishButton();
    }

    @Then("Order confirmation message should be visible")
    public void order_confirmation_message_should_be_visible() {
        String confirmationMessage = checkoutPage.getConfirmationMessage();
        Assert.assertTrue(confirmationMessage.contains("Thank you for your order"));
        Hooks.getTest().pass("Order confirmation validated: " + confirmationMessage);
        ActionMethod.captureScreenshot("Order_Confirmation", "Order placed");
    }

}
