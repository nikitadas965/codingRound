package Automation.abc;
import com.sun.javafx.PlatformUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class HotelBookingTest {
	static WebDriver driver;
	HotelBookingTest object;








	public HotelBookingTest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HotelBookingTest(WebDriver driver){

		this.driver = driver;

		//This initElements method will create all WebElements

		PageFactory.initElements(driver, this);

	}

	@FindBy(how=How.XPATH,using="//li[contains(@class,'hotelApp')]//a[contains(text(),'Hotels')]")
	public WebElement hotelLink;

	@FindBy(id = "Tags")
	public WebElement localityTextBox;

	@FindBy(id = "SearchHotelsButton")
	public  WebElement searchButton;

	@FindBy(id = "travellersOnhome")
	public WebElement travellerSelection;

	@Test
	public void shouldBeAbleToSearchForHotels() throws InterruptedException {
		setDriverPath();
		driver = new ChromeDriver();
		driver.get("https://www.cleartrip.com/");

		object = new HotelBookingTest(driver);
		object.hotelLink.click();

		object.localityTextBox.sendKeys("Indiranagar, Bangalore");

		waitFor(1000);
		object.localityTextBox.sendKeys(Keys.ENTER);


		Select select = new Select(object.travellerSelection);
		select.selectByVisibleText("1 room, 2 adults");


		object.searchButton.click();



		driver.quit();

	}
	private void waitFor(int durationInMilliSeconds) {
		try {
			Thread.sleep(durationInMilliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
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
