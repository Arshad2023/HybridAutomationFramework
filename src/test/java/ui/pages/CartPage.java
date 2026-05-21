package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui.Base.BasePage;
import ui.Base.BaseTest;
import ui.hooks.Hooks;
import ui.utils.ActionMethod;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {
    String removedProductName;
    public CartPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='cart_item']")
    List<WebElement> cartItems;

    @FindBy(xpath = "//div[@class='cart_item']/descendant::div[@class='inventory_item_name']")
    List<WebElement> cartItemNames;

    @FindBy(xpath = "(//button[text()='Remove'])[1]")
    WebElement firstRemoveButton;

    @FindBy(id = "checkout")
    WebElement checkoutButton;

    public void removeOneProductFromCart() {
        removedProductName = action.getText(cartItemNames.get(0), "Cart");
        action.clickElement(firstRemoveButton, "Remove Button");
        action.captureScreenshot("RemoveProduct", "Screenshot captured after removing products");
    }
    public int getRemainingCartItemCount() {
        return cartItems.size();
    }
    public boolean isRemovedProductAbsent() {
        List<String> remainingItems = new ArrayList<>();

        for (WebElement item : cartItemNames) {
            remainingItems.add(action.getText(item, "Cart"));
        }
        return !remainingItems.contains(removedProductName);
    }
    public String getRemovedProductName() {
        return removedProductName;
    }
    public void clickCheckoutButton() {

        action.clickElement(checkoutButton, "Checkout Button");
    }
}