package tatoc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Driver {
	  
	static WebDriver driver;
	public  static WebDriver setDriver(String browser) {
		
		if (browser.equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\kubraabbas\\Downloads\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if (browser.equals("Firefox")) {
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\kubraabbas\\Downloads\\geckodriver-v0.26.0-win64\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		return driver;
	}
	
	public static void launchTatocAdvancedCourse() {
		driver.get("http://10.0.1.86/tatoc");
		driver.findElement(By.xpath("//a[text() = 'Advanced Course']")).click();
		
	}
}


