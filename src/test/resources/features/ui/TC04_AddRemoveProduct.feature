@ui
Feature: Add Remove product from the cart
@cart @TC04
Scenario Outline: TC_04 - Cart add, remove, and count validation
  Given User launches the application for test case "<TestCaseID>"
  When User enters login credentials and submits
  And User adds three products to the cart
  Then Cart badge count should be "3"
  When User opens the cart page
  And User removes one product from the cart
  Then Cart badge count should be "2"
  And Remaining cart item count should be 2
  And Removed product should not be present in cart

Examples:
  | TestCaseID |
  | TC_04 - Cart add, remove, and count validation |