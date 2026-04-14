Feature: Sorting Visualizer

  Scenario Outline: Full combinations

    Given user opens sorting page
    When user runs all combinations for algorithm "<algo>"
    Then sorting should complete

  Examples:
    | algo |
    | 1 |
    | 2 |
    | 3 |
    | 4 |
    | 5 |