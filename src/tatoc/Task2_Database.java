package tatoc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Task2_Database {
	
	String symbol = null;
	int id = 0;
	String name = null;
	String passkey = null;
	

	public void createConnection(WebDriver driver) {
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
			symbol = driver.findElement(By.id("symboldisplay")).getText();
			// System.out.println(symbol);
			symbol = symbol.toLowerCase();
			// System.out.println(symbol);
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
}
