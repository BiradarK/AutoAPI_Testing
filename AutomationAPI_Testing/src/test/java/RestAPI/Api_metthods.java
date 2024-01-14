package RestAPI;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.concurrent.TimeUnit;

import  io.restassured.http.ContentType;
import  io.restassured.http.Header;
import io.restassured.response.Response;
import  io.restassured.http.Cookies;


public class Api_metthods {
	
	@Test (priority=1)
	public void get_status() {
		baseURI="https://simple-grocery-store-api.glitch.me";
		Response response=
		 
		given()
		.contentType("application/json")
		.baseUri(baseURI)
		.when()
		.get("/status")
		.then()
		.assertThat()
		.statusCode(200)
		.time(lessThan(5000L))   // Validating Response timing in milliseconds 
		.time(lessThan(5L),TimeUnit.SECONDS)   // Validating Response timing in seconds
		.statusLine("HTTP/1.1 200 OK")
		.header("Content-Type", "application/json; charset=utf-8") // Validating Response header
		.log().all()
		.body("status",equalTo("UP"))
	.extract()
		.response();
		Assert.assertTrue(response.getBody().asString().contains("status"));
		System.out.println("Response Time in milliseconds: " + response.getTime());
		System.out.println("Response Time in seconds: " + response.getTimeIn(TimeUnit.SECONDS));
		
	}
	//@Test (priority=2)
	public void get_products() {
		baseURI="https://simple-grocery-store-api.glitch.me";
		given()
		.contentType("application.json")
		.baseUri(baseURI)
		.when()
		.get("/products") 
		.then()
		.statusCode(200)
		.log().all();
	}
	
	//@Test (priority=3)
	public void get_aproduct() {
		baseURI="https://simple-grocery-store-api.glitch.me";
		given()
		.contentType("application.json")
		.baseUri(baseURI)
		.when()
		.get("products/4643")
		.then()
		.statusCode(200)
		.log().body();
		
	}
	
	@Test
	public void cookies() {
		Response res=
		given()
		.when()
		.get("https:www.google.com");
		System.out.println(res.cookie("1P_JAR"));
//		.then().assertThat().and().
//	   cookie("NID","511%3DahHAidplu8zSSUlzhWOHtaRhjq8xkyykhxXxzZZqNVo_SmRb-BatU6zG00IFQ2yN7_f5P35XQdwzfyJxi98MR2TlxdEdv1Q4CNykUgYzvXvanGz9vAP-jb15NvqnBikM1sOYk1EAF94vY3r-6rKhYeCqmDKn5QU7963S290o33c");
	}

}
