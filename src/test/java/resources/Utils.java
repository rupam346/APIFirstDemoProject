package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils { //we r using this for reusability ,like RequestSpecification, ResponseSpecification() etc

	public static RequestSpecification req; //static so that it will not create another instance instead it will create instance once and used that in entire execution,  
	                                        //if its not static it means while executing for 2nd data it will be null	
	
	public RequestSpecification requestSpecification() throws IOException 
		{
			if(req==null)  //so that it will not replace data mentioned in feature file instead it will add data
			{
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));  //this will push all logs/ output in this file as we r taking data from output so we used FileOutputStream
		req = new RequestSpecBuilder().setBaseUri(Utils.getGlobalValue("baseUrl"))
		.addQueryParam("key", "qaclick123")
		.addFilter(RequestLoggingFilter.logRequestTo(log))//this filter will be applied to object sp that it will have knowledge of loggingfilter and it will log everything
		.addFilter(ResponseLoggingFilter.logResponseTo(log))//here we r logging both request and response
		.setContentType(ContentType.JSON).build(); 
		return req;
	 }
			return req;
  }
		
		public static String getGlobalValue(String key) throws IOException {
			Properties prop = new Properties();
			FileInputStream fis = new FileInputStream("C:\\Users\\RKUMA346\\eclipse-workspace\\RahulShettyApiCucumberFramework\\src\\test\\java\\resources\\global.properties");  //here we have to read value present inside in file so we r using FileInputStream
		    prop.load(fis);
		    return prop.getProperty(key);
		}
		
		
		public String getJsonPath(Response response, String key) 
		{
			String resp = response.asString();
			JsonPath jp = new JsonPath(resp);
	        return jp.get(key).toString();
		}
}
