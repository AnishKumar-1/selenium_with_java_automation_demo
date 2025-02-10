package seleniumTest;

import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DatePickerDemo {

	public WebDriver driver;
	
	
	@Test
	public void dateDemo() {
		driver=new ChromeDriver();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Implicit wait to handle element load times
	    driver.manage().window().maximize();
		driver.get("https://www.redbus.in/");		//click on date icon
		////div[@id='onwardCal']//i
		driver.findElement(By.xpath("//div[@id='onwardCal']//i")).click();
	}
}
