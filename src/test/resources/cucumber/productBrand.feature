Feature: the get call to list all productBrands
  Scenario: client makes call to GET /productBrands
#    Given the client init a call
    When the client calls /productBrands
    Then the client receives status code of 200
    And the client receives content type application/hal+json
    And the client receives body like a json file productBrands
