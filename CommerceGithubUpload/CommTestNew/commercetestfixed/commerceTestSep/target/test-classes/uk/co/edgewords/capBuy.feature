Feature: Buying a cap

  Scenario: get the cap
    Given I am on "https://www.edgewordstraining.co.uk/demo-site/"
    And I am logged in
    When I navigate to "cap" page
    And I add to basket
    Then I can see "Cap" in the basket
    And The price will be "19.95"

  Scenario: placing order
    Given I am on "https://www.edgewordstraining.co.uk/demo-site/"
    And I can see "Cap" in the basket
    When I apply discount code "edgewords"
    Then The price will be "£17.55"
    When I proceed to checkout
    And Place Order
    Then Order number will be known between pages

