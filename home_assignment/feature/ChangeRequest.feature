Feature: Create and validate Change Request 

Background: 

	Given Set the Endpoint 
	When Set the auth 
	
Scenario Outline: Create a Change Request and validate the status code 

	Then Define the request body fields <description> and <short_description> 
	And Call post method 
	And Validate status code <statusCode>
	And Validate response body 
	
	Examples: 
	
		|description||short_description||statusCode|
		|'CreateChangeRequest1'||'post request1'||201|
		|'CreateChangeRequest2'||'post request2'||201|