package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.AmazonPage;
import utils.SwitchWindow;

public class AmazonGalaxyTest extends BaseTest
{
	
	@Test
	public void verifyGalaxy()
	{
		amazonPage.navigateToAmazon();
		amazonPage.productSearch("Galaxy");
		amazonPage.clickFirstProduct();
		System.out.println("Window Count Before Switch: "+driver.getWindowHandles().size());
		SwitchWindow.switchToNewWindow(driver);
		String price = amazonPage.getProductPrice();
		System.out.println("Samsung Galaxy price: "+ price);
		
		//Assertion
		Assert.assertNotNull(price, "Price should not be null");
		Assert.assertFalse(price.isEmpty(), "Price should not be empty");
		
		amazonPage.clickAddToCart();
	}
	
}
