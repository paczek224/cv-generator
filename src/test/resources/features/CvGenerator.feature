Feature: Cv Generator

  Scenario: User is able to fill out form with default data
    Given user is on the cv generator page
    When user fills out the form with default data
    And I click generate
    Then Action buttons are displayed
    And Cv is correctly generated


