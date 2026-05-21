@api
Feature: Comments API validation
@Comments
  Scenario: TC02_Comments(a) - Validate comments API total count
    Given User hits the "comments" endpoint
    Then User validates response status code as 200
    And User prints total items count
@Comments
  Scenario: TC02_Comments(b) - Validate comments email grouping by TLD
    Given User hits the "comments" endpoint
    Then User validates response status code as 200
    And User groups emails by top level domain