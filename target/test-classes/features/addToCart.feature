@addToCart
Feature: Admin tests add to cart functionalty
Background: Login
  Given Admin goes to login page "loginPage"
  And Admin fills with positive datas and click login button
    | username        | password     |
    | standard_user   | secret_sauce |

  Scenario: Admin test add to cart test

    Given Admin adds staffs to the cart
    Then Admin verifies staffs added to the cart
    And Admin closes the browser