@ui
  Feature: Locked_Out user
@login @TC02
Scenario Outline: TC_02 - Locked-out user validation
Given User launches the application for test case "<TestCaseID>"
And User enters login credentials and submits
Then User should remain on login page
And Error message should contain "locked out"
And Username and password fields should be visible

Examples:
| TestCaseID |
| TC_02 - Locked-out user validation |