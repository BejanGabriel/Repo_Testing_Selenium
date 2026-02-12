@LaStampaHomePage
Feature: HomePage

  Scenario Outline: Homepage check
    Given Land on homepage "<urlHomepage>"
    Then I remove cookies screen
    And I scroll to bottom
    And I remove ads and take screenshot
    Then I research for "<elementoRicerca>"
    Then I quit page


    Examples:
      | urlHomepage | elementoRicerca |
      | https://www.lastampa.it/ | Milano |

