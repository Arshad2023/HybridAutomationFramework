package ui.stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import ui.Base.BaseTest;
import ui.hooks.Hooks;
import ui.pages.CartPage;
import ui.pages.InventoryPage;
import ui.utils.ActionMethod;

public class AddRemoveProduct {
    InventoryPage inventoryPage;
    CartPage cartPage;

    @When("User adds three products to the cart")
    public void user_adds_three_products_to_the_cart() {
        inventoryPage = new InventoryPage(BaseTest.getDriver());
        inventoryPage.addThreeProductsToCart();
        Hooks.getTest().pass("Three products added to cart");
    }

    @Then("Cart badge count should be {string}")
    public void cart_badge_count_should_be(String expectedCount) {
        String actualCount = inventoryPage.getCartBadgeCount();
        Assert.assertEquals(actualCount, expectedCount);
        Hooks.getTest().pass("Cart badge count: " + actualCount);
    }
    @When("User opens the cart page")
    public void user_opens_the_cart_page() {
        inventoryPage = new InventoryPage(BaseTest.getDriver());
        inventoryPage.openCartPage();
        cartPage = new CartPage(BaseTest.getDriver());
        ActionMethod.captureScreenshot("CartPage", "Cart page opened");
        }
    @When("User removes one product from the cart")
    public void user_removes_one_product_from_the_cart() {
        cartPage.removeOneProductFromCart();
        Hooks.getTest().pass("Removed product: " + cartPage.getRemovedProductName());
    }
    @Then("Remaining cart item count should be {int}")
    public void remaining_cart_item_count_should_be(Integer expectedCount) {
        int actualCount = cartPage.getRemainingCartItemCount();
        Assert.assertEquals(actualCount, expectedCount.intValue());
        Hooks.getTest().pass("Remaining cart item count: " + actualCount);
    }
    @Then("Removed product should not be present in cart")
    public void removed_product_should_not_be_present_in_cart() {
        Assert.assertTrue(cartPage.isRemovedProductAbsent());
        Hooks.getTest().pass("Removed product is not present in cart");

        ActionMethod.captureScreenshot("Cart_After_Removal", "Cart validated after removing one product");
    }

}
