Feature: Create Order
  Scenario: Green path
    Given a valid order
    When the order is posted to the entry endpoint
    Then a success message is returned
    And the order is placed in the vendor interface

  Scenario: Invalid input
    Given an invalid order
    When the order is posted to the entry endpoint
    Then a validation error message is returned
