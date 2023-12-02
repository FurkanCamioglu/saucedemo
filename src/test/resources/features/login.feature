
Feature: Admin tests login page functionalty

  @positiveLogin
  Scenario: Admin test login page as positive test

    Given Admin goes to login page "loginPage"
    And Admin fills with positive datas and click login button
      | username        | password     |
      | standard_user   | secret_sauce |
      | locked_out_user | secret_sauce |
    Then Admin verifys the page navigates to inventory
    And Admin closes the browser

  @negativeLogin

  Scenario Outline: Admin test login page as negative test

    Given Admin goes to login page "loginPage"
    And Admin fills with negative datas "<username>", "<password>" and click login button
    Then Admin verifys the page doesnt navigates to inventory
    And Admin closes the browser

    Examples:
      | username      | password     |
      |               |              |
      | standard_user | wrong        |
      | wrong         | secret_sauce |
      | wrong         | wrong        |