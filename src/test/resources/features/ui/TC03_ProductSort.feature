@ui
Feature: Product price validation after sort
  @login @TC03
  Scenario Outline: TC_03 - Product sorting by price: low to high
    Given User launches the application for test case "<TestCaseID>"
    And User enters login credentials and submits
    When User selects product sort option as "Price (low to high)"
    Then Products should be sorted from low to high price
    Examples:
      | TestCaseID |
      | TC_03 - Product sorting by price: low to high      |