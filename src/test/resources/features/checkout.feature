
Feature: Admin tests add to cart functionalty

  Background: Login
    Given Admin goes to login page "loginPage"
    And Admin fills with positive datas and click login button
      | username      | password     |
      | standard_user | secret_sauce |
    And Admin adds items to the cart
    Then Admin verifies the page navigates to cart
    Then Admin verifies Admin can see items which he added to cart

  @positiveCartPage
  Scenario: Admin tests add to cart positive test

    And Admin navigates checkout page and enters positive datas
    Then Admin verifies the items and total prices
    Then Admin verifies order is completed
    And Admin closes the browser

  @negativeCartPage
  Scenario Outline: Admin tests add to cart negative test

    And Admin navigates checkout page and enters negative datas "<firstname>", "<lastname>", "<zipCode>"
    Then Admin shouldnt pass next step
    Then Admin verifies the items and total prices
    Then Admin verifies order is completed
    And Admin closes the browser

    Examples:
      | firstname | lastname | zipCode |
      | .         | .        | .       |
      | 1         | 1        | 1       |
      |           |          |         |
      | w         | w        | w       |
      | *         | *        | *       |

