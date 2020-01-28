package tatoc;

import static io.restassured.RestAssured.given;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Task4_RestAssured {
	
	public void restAssuredTask(WebDriver driver) {
		String sessionID = driver.findElement(By.id("session_id")).getText();
		String[] sessionIDText = sessionID.split(": ");
		String ID = sessionIDText[1];
		System.out.println(sessionIDText[0]);
		System.out.println("'" + ID + "'");
		RestAssured.baseURI = "http://10.0.1.86/tatoc/advanced/rest/service/token";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET, ID);
		JsonPath responseBody = response.getBody().jsonPath();
		String token = responseBody.getString("token");
		System.out.println("'" + token + "'");
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);

		RestAssured.baseURI = "http://10.0.1.86/tatoc/advanced/rest/service";
		Response res = given()
				// .header("Content-Type", "application/json")
				.param("id", ID).param("signature", token).param("allow_access", "1").when().post("/register").then()
				.extract().response();
		int status_code = res.getStatusCode();
		System.out.println(status_code);

		driver.findElement(By.xpath("//a[text() = 'Proceed']")).click();
	}

}
