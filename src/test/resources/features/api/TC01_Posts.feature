@api
Feature: Posts API validation
@post
  Scenario: TC01_Post(a) - Validate posts API total count
    Given User hits the "posts" endpoint
    Then User validates response status code as 200
    And User prints total items count
@post
  Scenario: TC01_Post(b) - Validate posts containing keyword
    Given User hits the "posts" endpoint
    Then User validates response status code as 200
    And User prints posts where title or body contains keyword from test data