Feature: Cv Generator

  @e2e @regression
  Scenario: User is able to fill out form with default data
    Given user is on the cv generator page
    When user fills out the form with default data
    And I click generate
    Then Action buttons are displayed
    And Cv is correctly generated

  @e2e @smoke
  Scenario: Cv form is correctly displayed
    When user is on the cv generator page
    Then user fills out the form with default data
    And Generate button is enabled


