@api
Feature: Albums API validation
@albums
  Scenario: TC_03_Albums(a) - Validate albums API total count
    Given User hits the "albums" endpoint
    Then User validates response status code as 200
    And User prints total items count
@albums
  Scenario:  TC_03_Albums(b) - Validate albums where id is not divisible by userId
    Given User hits the "albums" endpoint
    Then User validates response status code as 200
    And User filters albums where id is not divisible by userId