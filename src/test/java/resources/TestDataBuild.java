package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.BodyGoogleMap;
import pojo.Location;

public class TestDataBuild {

	
	public BodyGoogleMap addPlacePayLoad(String name , String language ,String address) {
		
		BodyGoogleMap bgm = new BodyGoogleMap();
		bgm.setAccuracy(50);
		bgm.setAddress(address);
		bgm.setLanguage(language);
		bgm.setPhone_number("(+91) 9738990055");
		bgm.setName(name);

		List<String> myList = new ArrayList<String>();
		myList.add("show park");
		myList.add("shop");
		bgm.setTypes(myList);// It was expecting List return type

		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		bgm.setLocation(l);
		return bgm;
	}
	
	public String deletePlacePayload(String placeId) {
		return "{\"place_id\":\""+placeId+"\"}";
	}
}
