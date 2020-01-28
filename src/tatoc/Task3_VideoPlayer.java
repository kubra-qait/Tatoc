package tatoc;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class Task3_VideoPlayer {
	
	public void videoPlayer(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.played = true;");
		driver.findElement(By.xpath("//a[text() = 'Proceed']")).click();
	}
}
