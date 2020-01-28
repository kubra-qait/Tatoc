package tatoc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class Task1_Hover {
	
	public void hoverMenu(WebDriver driver) {

		Actions hoverMenu2 = new Actions(driver);
		hoverMenu2.moveToElement(driver.findElement(By.className("menutitle"))).perform();
		driver.findElement(By.xpath("//span[text() = 'Go Next']")).click();
	}

}
