package tatoc;

import org.openqa.selenium.WebDriver;

public class TactocAdvanced  {

	public static void main(String[] args) throws Exception {
		WebDriver driver = Driver.setDriver("Chrome");
		Task1_Hover t1 = new Task1_Hover();
		Task2_Database t2 = new Task2_Database();
		Task3_VideoPlayer t3 = new Task3_VideoPlayer();
		Task4_RestAssured t4 = new Task4_RestAssured();
		Task5_FIleHandle t5 = new Task5_FIleHandle();
		
		Driver.launchTatocAdvancedCourse();
		t1.hoverMenu(driver);
		t2.createConnection(driver);
		t3.videoPlayer(driver);
		t4.restAssuredTask(driver);
		t5.fileHandlingTask(driver);
		
		
		
		
		
		
		
//		TatocAdvancedTasks tatoc = new TatocAdvancedTasks();
//		tatoc.launchTatocAdvancedCourse("Firefox");
//		tatoc.hoverMenu();
//		tatoc.automateDatabaseTask();
//		tatoc.createConnection();
//		tatoc.videoPlayer();
//		tatoc.restAssuredTask();
//		tatoc.fileHandlingTask();
	}
}
