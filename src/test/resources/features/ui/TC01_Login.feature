@ui
Feature: Login functionality

  @login @TC01
  Scenario Outline: TC_01 - Successful login and inventory landing
    Given User launches the application for test case "<TestCaseID>"
    And User enters login credentials and submits
    Then User should see login page and URL should contain inventory.html
    And Product Heading should be visible
    And Product list should be visible and count should be greater than 0
    And Cart Icon is visible on screen
    Examples:
      | TestCaseID |
      | TC_01 - Successful login and inventory landing |