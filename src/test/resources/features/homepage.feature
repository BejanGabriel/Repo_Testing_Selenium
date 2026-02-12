@LaStampaHomePage
Feature: HomePage

  Scenario Outline: Homepage check
    Given Land on homepage "<urlHomepage>"
    Then I remove cookies screen
    And I remove ads
    Then I research for "<elementoRicerca>"

    Examples:
      | urlHomepage | elementoRicerca |
      | https://www.lastampa.it/ | Milano |

