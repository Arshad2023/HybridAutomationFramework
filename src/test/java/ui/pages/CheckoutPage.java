package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui.Base.BasePage;
import ui.utils.ActionMethod;

import java.util.ArrayList;
import java.util.List;

public class CheckoutPage extends BasePage {
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "first-name")
    WebElement firstNameInput;

    @FindBy(id = "last-name")
    WebElement lastNameInput;

    @FindBy(id = "postal-code")
    WebElement postalCodeInput;

    @FindBy(id = "continue")
    WebElement continueButton;

    @FindBy(className = "inventory_item_price")
    List<WebElement> productPrices;

    @FindBy(className = "summary_subtotal_label")
    WebElement itemTotal;

    @FindBy(id = "finish")
    WebElement finishButton;

    @FindBy(className = "complete-header")
    WebElement confirmationMessage;

    public void enterCheckoutInformation(
            String firstName,
            String lastName,
            String postalCode
    ) {

        action.enterText(firstNameInput, firstName, "First Name");

        action.enterText(lastNameInput, lastName,"Last Name");

        action.enterText(postalCodeInput, postalCode, "Postal Code");

        action.captureScreenshot("UserDetails", "User Details Page");

        action.clickElement(continueButton, "Continue button");

    }

    public double getCalculatedProductTotal() {
        List<Double> prices = new ArrayList<>();
        for (WebElement price : productPrices) {
            String amount = action.getText(price, "Price").replace("$", "");
            prices.add(Double.parseDouble(amount));
        }
        return prices.stream().mapToDouble(Double::doubleValue).sum();
    }

    public double getDisplayedItemTotal() {
        String total = action.getText(itemTotal, "Total Displayed Item").replace("Item total: $", "");
        return Double.parseDouble(total);
    }
    public void clickFinishButton() {
        action.clickElement(finishButton, "Finish");
    }
    public String getConfirmationMessage() {
        return action.getText(confirmationMessage, "Confirmation Message");
    }
}