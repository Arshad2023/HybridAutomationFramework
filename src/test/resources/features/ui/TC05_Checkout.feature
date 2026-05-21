@ui
Feature: Checkout
@checkout @TC05
Scenario Outline: TC_05 - Complete checkout with item total validation
  Given User launches the application for test case "<TestCaseID>"
When User enters login credentials and submits
And User adds Backpack and Bike Light products to cart
And User opens the cart page
And User clicks on checkout button
And User enters checkout details
Then Checkout item total should match sum of product prices
When User clicks on finish button
Then Order confirmation message should be visible

Examples:
| TestCaseID |
| TC_05 - Complete checkout with item total validation |