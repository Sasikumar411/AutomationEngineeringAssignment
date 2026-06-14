package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AmazonPage 
{
	WebDriver driver;
	
	public AmazonPage(WebDriver driver) {
		
		this.driver=driver;
	}
	
	public void navigateToAmazon() 
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.amazon.in/?ref_=icp_country_from_us");
		driver.manage().window().maximize();
	}
	
	//Locators
	
	By searchBox=By.id("twotabsearchtextbox");
	By SearchButton=By.id("nav-search-submit-button");
	By firstProduct=By.xpath("(//div[@data-cy='title-recipe']//h2/parent::a)[1]");		
	By addToProduct=By.xpath("//div[@class='a-section a-spacing-none a-padding-none']//input[@id='add-to-cart-button']");
	By productPrice=By.xpath("//span[contains(@class,'priceToPay')]//span[@class='a-price-whole']");
	
	
	//Action methods
	public void productSearch(String productName)
	{
		driver.findElement(searchBox).sendKeys(productName);
		driver.findElement(SearchButton).click();
	}
	
	public void clickFirstProduct()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(firstProduct)).click();
	}
	
	public void clickAddToCart()
	{
		driver.findElement(addToProduct).click();
	}
	
	public String getProductPrice()
	{
		return driver.findElement(productPrice).getText();
	}

	public String captureScreen(String name) {
		
		return null;
	}
}




















