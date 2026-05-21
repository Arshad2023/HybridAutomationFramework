@api
Feature: Users API validation
@user
  Scenario: TC_05 - Validate user details by company name
    Given User hits the "users" endpoint
    Then User validates response status code as 200
    And User prints user details whose company name is from test data