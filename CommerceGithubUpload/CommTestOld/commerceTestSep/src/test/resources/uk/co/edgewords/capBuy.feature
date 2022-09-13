Feature: Buying a cap

  Scenario: logging in
    Given I am on "https://www.edgewordstraining.co.uk/demo-site/"
    When I enter my account
    And Enter username
    And Enter password
    Then I will see "scott.hamilton" greeting

  Scenario: getting cap
    Given I am on "https://www.edgewordstraining.co.uk/demo-site/"
    When I search for "cap"
    Then I arrive at the "Cap" page

  Scenario: adding cap to basket
    Given I am on "https://www.edgewordstraining.co.uk/demo-site/product/cap/"
    When I add to basket
    Then I can see "Cap" in the basket
    Then The price will be "£19.95"

  Scenario: getting discount
    Given I am on "https://www.edgewordstraining.co.uk/demo-site/cart/"
    And I can see "Cap" in the basket
    When I apply discount code "edgewords"
    Then The price will be "£17.55"

  Scenario: placing order
    Given I am on "https://www.edgewordstraining.co.uk/demo-site/cart/"
    When I proceed to checkout
    And Select Cash
    And Place Order
    Then Order number will be known between pages

