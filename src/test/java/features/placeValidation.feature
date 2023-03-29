Feature: Validating place API's
@AddPlace
  Scenario Outline: Verify if place is being Succesfully added using AddPlaceAPI
    Given Add place payload "<Name>" "<Language>" "<Address>"
    When User calls "AddPlaceAPI" with "POST" http request
    Then The Api call got success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place Id created maps to "<Name>" using "getPlaceAPI"
    Examples: 
      | Name     | Language | Address            |
      | AAhouse  | English  | World cross center |
 #     | BBhouse | Spanish  | Sea cross center   |
 
@DeletePlace 
 Scenario: Verify if Delete Place functionality is working
    Given DeletePlace Payload
    When  User calls "deletePlaceAPI" with "POST" http request
    Then The Api call got success with status code 200
    And  "status" in response body is "OK"
    
