@api
Feature: Photos API validation
@photos
  Scenario: TC04_Photos(a) - Validate photos API total count
    Given User hits the "photos" endpoint
    Then User validates response status code as 200
    And User prints total items count
@photos
  Scenario: TC04_Photos(b) - Validate random photo URLs
    Given User hits the "photos" endpoint
    Then User validates response status code as 200
    And User validates random photo URLs