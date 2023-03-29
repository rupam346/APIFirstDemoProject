package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.ApiResources;
import resources.TestDataBuild;
import resources.Utils;

public class PlaceValidationStepDefinition extends Utils{
	
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	static String place_id;//it will create once and will refer same to everywhere
	
	TestDataBuild data = new TestDataBuild();
   
	@Given("Add place payload {string} {string} {string}")
	public void add_place_payload(String name, String language, String address ) throws IOException {
		res = given().spec(requestSpecification())// this spec is accepting argument of RequestSpecification type
				.body(data.addPlacePayLoad(name, language, address));
	}

	@When("User calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource , String method) {
   //constructor will be called with value of resource which you pass	
		
		ApiResources resourceAPI = ApiResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		
		resspec = new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
		
		if(method.equalsIgnoreCase("POST"))
		response = res.when().post(resourceAPI.getResource());
		else if(method.equalsIgnoreCase("GET"))
		response = res.when().get(resourceAPI.getResource());
		//	    .then().spec(resspec).extract().response();this validation will do in further steps
 }

	@Then("The Api call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
             assertEquals(response.getStatusCode(),200);
	}

	@And("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {
               
        assertEquals(getJsonPath(response,keyValue),expectedValue);
	}

	@And("verify place Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
	
		//requestSpec;
		place_id =  getJsonPath(response, "place_id");
		res = given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_http_request(resource, "GET");
		String actualName = getJsonPath(response , "name");
		assertEquals(actualName ,expectedName);		
	}
	
	@Given("DeletePlace Payload")
	public void deleteplace_payload() throws IOException {
	   res = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
	}
}
