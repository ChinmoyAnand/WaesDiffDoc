# Assignment for Finding Difference Between Two Documents
The Project solves the problem of adding Doc in Db and finding the difference between 2 documents. 

### Technology Used
	* Java 8
	* Spring Boot 2
	* Lombok
	* Swagger 2
	* MongoDB
	* JUnit,Mockito,MockMvc
	
# How to run the project

We need to install and run the mongodb shell. Other configuration will be auto configured after we start the SpringBoot Application.

# API's

We can check the Api Endpoints in Swageer UI too at @link <host>/swagger-ui.html

TWO WAYS TO FIND THE DIFFFERENCE IN DOC
 
	* To Get the Difference based on ID and ByteArray, use  <host>/diff/{id}
	* To GET difference based on ID and TEXT content, use <host>/diff/{id}?criteria="TEXT"
		This is an Optional Criteria.

SAVE THE DOC BASED ON DIFF ID AND SIDE

	* To Save the doc, use <host>/v1/diff/<ID>/right and <host>/v1/diff/<ID>/left

#UNIT & INTEGRATION TEST

The Unit test is definced for the Service Layer with Mocking to Repo
Integration Test is written in COntroller

#Improvements
If the File is huge we can use GridFS to store in MongoDB.
We can provide a different POST API to upload Multipart file and save it.

