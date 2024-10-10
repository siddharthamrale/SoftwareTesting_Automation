Feature: Validate completion percentage of users in FanCode city

  Scenario: Users from FanCode city have completed more than 50% of their tasks
    Given the users with todos tasks
    And the users belong to the city FanCode
    Then the completion percentage for each user should be greater than 50%
    