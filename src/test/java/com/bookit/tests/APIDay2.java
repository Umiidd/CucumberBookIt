package com.bookit.tests;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBodyExtractionOptions;
import io.restassured.response.ValidatableResponse;

public class APIDay2 {
	@Test
	public void empWP() {
		given().accept(ContentType.JSON).and().params("limit", 100).when()
				.get("http://3.89.218.1:1000/ords/hr/employees").then().assertThat().statusCode(200).and()
				.contentType(ContentType.JSON).and().body("items.employee_id", Matchers.hasSize(100));
	}

	@Test
	public void empWPath() {
		given().accept(ContentType.JSON).and().pathParam("id", 110).when()
				.get("http://3.89.218.1:1000/ords/hr/employees/{id}").then().assertThat().statusCode(200).and()
				.assertThat().body("first_name", Matchers.equalTo("John"), "email", Matchers.equalTo("JCHEN"));
	}

	@Test
	public void testWJSONPath() {
		Response response = given().accept(ContentType.JSON).and().pathParam("id", 110).when()
				.get("http://3.89.218.1:1000/ords/hr/employees/{id}");
		JsonPath json = response.jsonPath();
		System.out.println(json.getString("first_name"));
		System.out.println(json.getString("job_id"));
		System.out.println(json.getInt("employee_id"));

		String acf = json.getString("first_name");
		String exf = "John";
		assertEquals(acf, exf);
	}

	@Test
	public void testWJSONPathList() {
		Map<String, Integer> paramMap = new HashMap<>();
		paramMap.put("limit", 100);
		Response response = given().accept(ContentType.JSON).and().params(paramMap).when()
				.get("http://3.89.218.1:1000/ords/hr/employees/");
		JsonPath json = response.jsonPath();
		assertEquals(response.statusCode(), 200);
		List<Integer> empIds = json.getList("items.employee_id");
		System.out.println(empIds.toString());
		assertEquals(empIds.size(), 100);

		List<Integer> empId2s = json.getList("items.findAll{it.employee_id > 150}.employee_id");
		System.out.println(empId2s);

		List<String> empLN = json.getList("items.findAll{it.salary> 7000}.last_name");
		System.out.println(empLN);
		System.out.println(empLN.size());
	}

	@Test
	public void testEmailList() {
		Map<String, Integer> paramMap = new HashMap<>();
		paramMap.put("limit", 100);
		Response response = given().accept(ContentType.JSON).and().params(paramMap).when()
				.get("http://3.89.218.1:1000/ords/hr/employees/");
		JsonPath json = response.jsonPath();
		assertEquals(response.statusCode(), 200);
		List<Integer> empEmail = json.getList("items.email");
		System.out.println(empEmail.toString());

	}

	@Test
	public void DeSerialization() {
		Response response = given().accept(ContentType.JSON).and().when()
				.get("http://3.89.218.1:1000/ords/hr/employees/140");

		Map<String, Object> JsonMap = response.as(HashMap.class);
		System.out.println(JsonMap);
		System.out.println(JsonMap.get("first_name"));

	}
	
	@Test
	public void convertJSONtoListofMaps() {
		Response response = given().accept(ContentType.JSON).and().when()
				.get("http://3.89.218.1:1000/ords/hr/departments");

		JsonPath Json = response.jsonPath();
		
		List<Map> result = Json.getList("items",Map.class);
		System.out.println(result.get(0).get("department_name"));
		
		String actDN= (String) result.get(4).get("department_name");
		String ExDN= "Shipping";
		assertEquals(actDN,ExDN);
		
		int actDN1= (int) result.get(4).get("department_id");
		int ExDN1= 50;
		assertEquals(actDN1,ExDN1);
		
	}
	
	@Test
	public void Task01() {
		Response response = given().accept(ContentType.JSON).and().when()
				.get("http://3.89.218.1:1000/ords/hr/regions");

		JsonPath Json =  response.jsonPath();
		
		assertEquals(response.statusCode(),200);
		
		
		List<Map> result = Json.getList("items",Map.class);
				
		String actRN1 = (String) result.get(0).get("region_name");
		String ExRN1 = "Europe";
		assertEquals(actRN1,ExRN1);
		
		String actRN2 = (String) result.get(1).get("region_name");
		String ExRN2 = "Americas";
		assertEquals(actRN2,ExRN2);
		
		String actRN3 = (String) result.get(2).get("region_name");
		String ExRN3 = "Asia";
		assertEquals(actRN3,ExRN3);
		
		String actRN4 = (String) result.get(3).get("region_name");
		String ExRN4 = "Middle East and Africa";
		assertEquals(actRN4,ExRN4);

	}
	
	@Test
	public void Task01SecondWay() {
		given().accept(ContentType.JSON).and().param("limit", 10).when()
				.get("http://3.89.218.1:1000/ords/hr/regions").then().statusCode(200).and().
				assertThat().body("items.region_name", Matchers.hasItems("Europe", "Americas", "Asia", "Middle East and Africa"));

	}
	
	@Test
	public void Task01ThirdWay() {
		Response response = given().accept(ContentType.JSON).and().when()
				.get("http://3.89.218.1:1000/ords/hr/regions");

		JsonPath Json =  response.jsonPath();
		
		assertEquals(response.statusCode(),200);
		
		assertEquals(Json.getString("items[0].region_name"), "Europe");
		assertEquals(Json.getString("items[1].region_name"), "Americas");
		assertEquals(Json.getString("items[2].region_name"), "Asia");
		assertEquals(Json.getString("items[3].region_name"), "Middle East and Africa");
	
	}
}
