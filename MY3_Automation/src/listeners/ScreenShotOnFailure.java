package listeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
 
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;

/*
 * @Class for Capturing screenshot on Failure,
 
 * @Date:12-07-2014.
 */

public class ScreenShotOnFailure extends TestListenerAdapter {
	 
	  @Override
		public void onTestFailure(ITestResult tr) {
		    WebDriver driver = WebDriverManager.getDriverInstance();
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh_mm_ssaa");
			String destDir = "html/screenshots";
			new File(destDir).mkdirs();
			String destFile = dateFormat.format(new Date()) + ".png";
	 
	        try {
				FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
			} catch (IOException e) {
				e.printStackTrace();
			}
	      
			Reporter.setEscapeHtml(false);
			Reporter.log("<br/> Saved");
			Reporter.log("<a target='_blank' href='../html/screenshots/"+ destFile +"'>View Screenshot</a>");		
		
		}
	}