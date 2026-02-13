@LaStampaHomePage #// cosa fa questo??
Feature: HomePage

  Scenario Outline: Homepage check
    Given Land on homepage
    When I click on Accetta button
    And I scroll to bottom
    And I remove ads and take screenshot
    Then I research for "<elementoRicerca>"
  #  Then I quit page

    #Creare un interfaccia che contenga i metodi che verrano usati da page.
    # alcuni metodi devono essere implementati altri no


#    prendi il sito dal file txt e non dalla data table

#   Examples:
#     | urlHomepage | elementoRicerca |
#     | https://www.lastampa.it/ | Milano |

  Examples:
    | elementoRicerca |
    | Milano |
  |        |
  |        |
  |        |
  |        |


# @Prov
# Feature: Provably
#  Scenario:
#    Given Primo given di prova
