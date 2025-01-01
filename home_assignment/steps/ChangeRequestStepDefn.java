package week4.home_assignment.steps;

import org.testng.Assert;

import com.google.gson.Gson;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class ChangeRequestStepDefn extends ProjectSpecificMethod{
	
	@Given("Set the Endpoint")
	public void set_the_endpoint() {
	   
		url = "https://dev203574.service-now.com";
		
			
	}
	@When("Set the auth")
	public void set_the_auth() {
		token = RestAssured.given()
				.contentType(ContentType.URLENC)
				.formParam("grant_type", "password")
				.formParam("client_id", "e429d49e2ef6417f9e7f2eaf95fef162")
				.formParam("client_secret", "##URqvI]Yk")
				.formParam("username", "admin")
				.formParam("password", "h5Ta9*hFW/tA")
				.log().all()
			.when()
				.post(url+"/oauth_token.do")
			.then()
				.log().all()
				.extract()
				.jsonPath()
				.getString("access_token");
		
		
	}
	
	@Then("Define the request body fields {string} and {string}")
	public void define_the_request_body_fields_and(String desc, String shortDesc) {
	    
		payload = new Create_ChangeRequest_Payload();
		payload.setDescription(desc);
		payload.setShort_description(shortDesc);
					
	}
	
	@Then("Call post method")
	public void call_post_method() {
	   
		//String url = "https://dev203574.service-now.com/api/now/table/{tableName}";
		System.out.println("Access Token:"+token);
	response =	RestAssured.given()
			.contentType(ContentType.JSON)
			.header("Authorization","Bearer "+token)
			.pathParam("tableName","change_request")
			.log().all()
		.when()
			.body(new Gson().toJson(payload))
			.post(url+"/api/now/table/{tableName}")
			
		.then()
			.log().all()
			.extract();			
		
	}
	
	@Then("Validate status code {int}")
	public void validate_status_code(int statCode) {
	  
		Assert.assertEquals(statCode,response.statusCode());
	}
	
	@Then("Validate response body")
	public void validate_response_body() {
	  
		String desc = response.jsonPath().getString("result.description");
		Assert.assertEquals(desc, payload.getDescription());
				
		String shortDesc = response.jsonPath().getString("result.short_description");
		Assert.assertEquals(shortDesc, payload.getShort_description());
		
			
	}

}
