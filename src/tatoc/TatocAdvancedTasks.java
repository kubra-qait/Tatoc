package tatoc;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class TatocAdvancedTasks {

	WebDriver driver;
	String symbol = null;
	int id = 0;
	String name = null;
	String passkey = null;
	
	

	public void launchTatocAdvancedCourse(String browser) {
		if (browser.equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\kubraabbas\\Downloads\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if (browser.equals("Firefox"))
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\kubraabbas\\Downloads\\geckodriver-v0.26.0-win64\\geckodriver.exe");
			driver = new FirefoxDriver();	
		driver.get("http://10.0.1.86/tatoc");
		driver.findElement(By.xpath("//a[text() = 'Advanced Course']")).click();
	}

	public void hoverMenu() {
		Actions hoverMenu2 = new Actions(driver);
		hoverMenu2.moveToElement(driver.findElement(By.className("menutitle"))).perform();
		driver.findElement(By.xpath("//span[text() = 'Go Next']")).click();
	}

	public void createConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // register JDBC Driver
			Connection con = DriverManager.getConnection("jdbc:mysql://10.0.1.86/tatoc", "tatocuser", "tatoc01"); // open
																													// Connection
			Statement stmt = con.createStatement(); // execute statement
			ResultSet rs = stmt.executeQuery("select * from credentials");
			while (rs.next()) {
				// System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " +
				// rs.getString(3));
			}
			rs = stmt.executeQuery("select * from identity");
			while (rs.next()) {
				// System.out.println(rs.getInt(1) + " " + rs.getString(2));
			}
			rs = stmt.executeQuery("select id from identity where symbol = '" + symbol + "'");
			while (rs.next()) {
				id = rs.getInt(1);
				// System.out.println(id);
			}
			rs = stmt.executeQuery("select name , passkey from credentials where id = '" + id + "'");
			while (rs.next()) {
				name = rs.getString(1);
				passkey = rs.getString(2);
				// System.out.println(name);
				// System.out.println(passkey);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		driver.findElement(By.id("name")).sendKeys(name);
		driver.findElement(By.id("passkey")).sendKeys(passkey);
		driver.findElement(By.id("submit")).click();
	}

	public void automateDatabaseTask() {
		symbol = driver.findElement(By.id("symboldisplay")).getText();
		// System.out.println(symbol);
		symbol = symbol.toLowerCase();
		// System.out.println(symbol);
	}

	public void videoPlayer() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.played = true;");
		driver.findElement(By.xpath("//a[text() = 'Proceed']")).click();
	}

	public void restAssuredTask() {
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

	public void fileHandlingTask() throws Exception {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//a[text() = 'Download File']")).click();
		Thread.sleep(3000);
		File file = new File("C:\\Users\\kubraabbas\\Downloads\\file_handle_test.dat");
		Path pathToFile = Paths.get("C:\\Users\\kubraabbas\\Downloads\\file_handle_test.dat");
		System.out.println(pathToFile);
		List<String> signatureLine = Files.readAllLines(pathToFile.toAbsolutePath());
		String signatureLine1 = signatureLine.get(2);
		System.out.println(signatureLine1);
		String signatureID = signatureLine1.split(": ")[1];
		System.out.println(signatureID);
		driver.findElement(By.id("signature")).sendKeys(signatureID);
		driver.findElement(By.cssSelector("input.submit")).click();
		file.delete();
	}
}