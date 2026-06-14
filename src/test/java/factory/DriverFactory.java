package factory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory 
{
	public static WebDriver initializeDriver(String executionType, String testName) throws MalformedURLException 
	{
		WebDriver driver;
		
		System.out.println("Execution Type received: "+executionType);
		
		if ("cloud".equalsIgnoreCase(executionType)) {
			
			System.out.println("Launching on LambdaTest");
			
	        String username = System.getenv("LT_USERNAME");
	        String accessKey = System.getenv("LT_ACCESS_KEY");
	        
	        if (username == null || accessKey == null) {
	            throw new RuntimeException(
	                "Please set LT_USERNAME and LT_ACCESS_KEY environment variables.");
	        }
	       
	        ChromeOptions browserOptions = new ChromeOptions();

	        browserOptions.setBrowserVersion("latest");
	        
	        HashMap<String, Object> ltOptions = new HashMap<>();
	        
	        ltOptions.put("platformName", "Windows 11");
	        ltOptions.put("build", "Amazon Assignment");
	        ltOptions.put("name", testName);
	        
	        browserOptions.setCapability("LT:Options", ltOptions);
	        
	        String gridURL = "https://" + username + ":" + accessKey + "@hub.lambdatest.com/wd/hub";
	        
	        driver = new RemoteWebDriver (new URL(gridURL), browserOptions);

	        } 
		else {
			
			System.out.println("Launching locally");
			
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			
	       }
		return driver;
		}
	}
