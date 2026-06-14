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

public class AmazonIphoneTest extends BaseTest
{
	
	@Test
	public void verifyIphone()
	{
		amazonPage.navigateToAmazon();
		amazonPage.productSearch("Iphone");
		amazonPage.clickFirstProduct();
		System.out.println("Window Count Before Switch: "+driver.getWindowHandles().size());
		SwitchWindow.switchToNewWindow(driver);
		String price = amazonPage.getProductPrice();
		System.out.println("iPhone price: "+ price);
		
		//Assertion
		Assert.assertNotNull(price, "Price should not be null");
		Assert.assertFalse(price.isEmpty(), "Price should not be empty");
		
		amazonPage.clickAddToCart();
	}
	
}
