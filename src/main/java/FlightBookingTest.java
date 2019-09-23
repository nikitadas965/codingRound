package Automation.abc;
import com.sun.javafx.PlatformUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class FlightBookingTest {
	static	WebDriver driver ;

	public FlightBookingTest() {
		super();
		// TODO Auto-generated constructor stub
	}




	public  FlightBookingTest(WebDriver driver){

		this.driver = driver;

		//This initElements method will create all WebElement

	}


	@Test
	public  void testThatResultsAppearForAOneWayJourney() {

		setDriverPath();
		driver = new ChromeDriver();
		driver.get("https://www.cleartrip.com/");
		waitFor(2000);
		driver.findElement(By.id("OneWay")).click();

		driver.findElement(By.id("FromTag")).clear();
		driver.findElement(By.id("FromTag")).sendKeys("Bangalore");


		//wait for the auto complete options to appear for the origin

		waitFor(2000);
		driver.findElement(By.id("FromTag")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.id("FromTag")).sendKeys(Keys.ENTER);

		driver.findElement(By.id("ToTag")).clear();
		driver.findElement(By.id("ToTag")).sendKeys("Delhi");

		//wait for the auto complete options to appear for the destination

		waitFor(2000);
		//select the first item from the destination auto complete list

		driver.findElement(By.id("ToTag")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.id("ToTag")).sendKeys(Keys.ENTER);
		driver.findElement(By.id("DepartDate")).click();

		driver.findElement(By.xpath("//a[contains(@class,'ui-state-default ui-state-highlight ui-state-active')]")).click();

		//all fields filled in. Now click on search
		driver.findElement(By.id("SearchBtn")).click();

		waitFor(5000);
		//verify that result appears for the provided journey search
		Assert.assertTrue(isElementPresent(By.className("searchSummary")));

		//close the browser
		driver.quit();

	}



	private void waitFor(int durationInMilliSeconds) {
		try {
			Thread.sleep(durationInMilliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}
	}


	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private void setDriverPath() {
		if (PlatformUtil.isMac()) {
			System.setProperty("webdriver.chrome.driver", "chromedriver");
		}
		if (PlatformUtil.isWindows()) {
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		}
		if (PlatformUtil.isLinux()) {
			System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
		}
	}
}
