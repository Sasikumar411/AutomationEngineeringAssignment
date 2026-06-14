package tests;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;
import java.net.MalformedURLException;

import factory.DriverFactory;
import pages.AmazonPage;

public class BaseTest {
	
		protected WebDriver driver;
	    protected AmazonPage amazonPage;

	    @BeforeMethod
	    public void setup(Method method) throws MalformedURLException 
	    {
	    	String executionType = System.getProperty("executionType", "local");
	    	System.out.println("Execution Type= "+ executionType);
	        driver = DriverFactory.initializeDriver(executionType, method.getName());
	        amazonPage = new AmazonPage(driver);
	    	
	    }

	    @AfterMethod
	    public void tearDown() {
	    	
	        if (driver != null) {
	        	driver.quit();
	        }
	    }

	    public WebDriver getDriver() {

	        return driver;
	    }

	    public String captureScreen(String testName) throws IOException {

	        String targetFilePath =
	                System.getProperty("user.dir")
	                        + "/screenshots/"
	                        + testName
	                        + ".png";

	        TakesScreenshot ts = (TakesScreenshot) driver;

	        File sourceFile =
	                ts.getScreenshotAs(OutputType.FILE);

	        File targetFile =
	                new File(targetFilePath);

	        FileUtils.copyFile(sourceFile, targetFile);

	        return targetFilePath;
	    }
}
