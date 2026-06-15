package listener;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import tests.BaseTest;

public class ExtentReportManager implements ITestListener, ISuiteListener {
	private static ExtentSparkReporter sparkReporter;
	public static ExtentReports extent;
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	private static String repName;
	
	@Override
	public void onStart(ISuite testContext) {
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
		repName = "Test-Report-" + timeStamp + ".html";
		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"//reports//"+repName); 

		sparkReporter.config().setDocumentTitle("Amazon Automation Report"); 
		sparkReporter.config().setReportName("Amazon Functional Testing"); 
		sparkReporter.config().setTheme(Theme.DARK);
		
		if(extent==null)
		{
			synchronized (ExtentReportManager.class)
		
			    {
				 if(extent == null)
				 
				 {
					extent = new ExtentReports();
					extent.attachReporter(sparkReporter);
					extent.setSystemInfo("Application", "Amazon");
					extent.setSystemInfo("Module", "Admin");
					extent.setSystemInfo("Sub Module", "Customers");
					extent.setSystemInfo("User Name", System.getProperty("user.name"));
					extent.setSystemInfo("Environment", "QA");
					
					String os = testContext.getXmlSuite().getParameter("os");
					if(os!=null)
					{
						extent.setSystemInfo("Operating System", os);
				    }
					
					String browser = testContext.getXmlSuite().getParameter("browser");
					if(browser!=null)
				    {
						extent.setSystemInfo("Browser", browser);
				    }
				 }
			    }
		}
	}
				
	@Override
	public void onTestSuccess(ITestResult result) {
	
		test.set(extent.createTest(result.getTestClass().getRealClass().getSimpleName() + " - " +result.getMethod().getMethodName()));
		test.get().assignCategory(result.getMethod().getGroups());
		test.get().log(Status.PASS,result.getMethod().getMethodName() +" successfully executed");
		test.remove();
		
	}
	
	@Override
	public void onTestFailure(ITestResult result) {

		test.set(extent.createTest(result.getTestClass().getRealClass().getSimpleName() + " - " +result.getMethod().getMethodName()));
		test.get().assignCategory(result.getMethod().getGroups());
	    test.get().log(Status.FAIL, result.getMethod().getMethodName() + " got failed");
	    test.get().log(Status.INFO, result.getThrowable().getMessage());
	    test.remove();
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		test.set(extent.createTest(result.getTestClass().getRealClass().getSimpleName() + " - " +result.getMethod().getMethodName()));
		test.get().assignCategory(result.getMethod().getGroups());
		test.get().log(Status.SKIP, result.getMethod().getMethodName()+" got skipped");
		test.get().log(Status.INFO, result.getThrowable().getMessage());
		test.remove();
	}
	
	@Override
	public void onFinish(ISuite testContext) {
		
		if (extent != null) {
			extent.flush();
			}
		
		String pathOfExtentReport = System.getProperty("user.dir")+"//reports//"+ repName;
		File extentReport = new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());   
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	 
	}

}


