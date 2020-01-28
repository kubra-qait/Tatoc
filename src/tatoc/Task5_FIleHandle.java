package tatoc;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Task5_FIleHandle {
	
	public void fileHandlingTask(WebDriver driver) throws Exception {
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
