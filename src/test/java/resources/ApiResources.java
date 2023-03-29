package resources;
//enum is special class in java which has collection of constants or methods
public enum ApiResources {
	
//In enum if we have set of methods we can seperate it by comma(,)
	AddPlaceAPI("/maps/api/place/add/json"),
	getPlaceAPI("/maps/api/place/get/json"),
	deletePlaceAPI("/maps/api/place/delete/json");
	
	private String resource;
	
//In enum if we method is having argument then constructor should have some argument	
	ApiResources(String resource){
		this.resource = resource;
	}
	
	public String getResource() {
		return resource;
	}
	
}
