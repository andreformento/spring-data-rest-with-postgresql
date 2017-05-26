Feature: the get call to list all products
  Scenario: client makes call to GET /products
#    Given the client init a call
    When the client calls /products
    Then the client receives status code of 200
    And the client receives content type application/hal+json
    And the client receives body like a json file products

  Scenario: client makes call to GET /products/1
    When the client calls /products/1
    Then the client receives status code of 200
    And the client receives content type application/hal+json
    And the client receives body like a json file product-1

