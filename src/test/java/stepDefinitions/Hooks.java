package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeScenario() throws IOException
	{
		//execute this code only when place id is null as while executing only delete scenario independently placeId is null so at that tym we can use
		//write a code that will give you place id
		PlaceValidationStepDefinition pvsd = new PlaceValidationStepDefinition();
		
		if(pvsd.place_id == null) //warning cause place_id is static we can call it by class name also
		{
		pvsd.add_place_payload("Shetty", "French", "Asia");
	    pvsd.user_calls_with_http_request("AddPlaceAPI", "POST");
	    pvsd.verify_place_id_created_maps_to_using("Shetty", "getPlaceAPI");
		}
	}
}
