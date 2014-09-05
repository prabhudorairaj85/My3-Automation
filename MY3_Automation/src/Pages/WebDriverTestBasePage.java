package Pages;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import listeners.WebDriverManager;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import util.WaitUtility;

/*
 * @Class for WebDriver Test Base Page and related function,

 * @Date:12-07-2014.
 */

public class WebDriverTestBasePage extends WaitUtility {

	/** This page's WebDriver */
	public static WebDriver driver;
	protected String browser;
    public static boolean TestResult = true;
	
	/**
	 * Initialize test properties ( WebDriver, implicitlyWait, and etc).
	 **/
	protected void initialize(WebDriver driver) {
		setImplicitWait(driver, 15);
		WebDriverTestBasePage.driver = driver;
		driver.manage().window().maximize();
	}

	public void scrollDownWindow(WebDriver driver) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,600)", "");
	}

	public void scrollUpWindow(WebDriver driver) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,-600)", "");
	}

	public void clickonSVGObject(String xPathlocator) {
		EventFiringWebDriver myTestDriver = new EventFiringWebDriver(driver);
		myTestDriver.manage().window().maximize();

		Locatable hoverItem = (Locatable) myTestDriver.findElement(By
				.xpath(xPathlocator));

		Coordinates MyTestCoordinates = hoverItem.getCoordinates();

		try {
			myTestDriver.getMouse().mouseMove(MyTestCoordinates);
			myTestDriver.getMouse().mouseDown(MyTestCoordinates);
			myTestDriver.getMouse().mouseUp(MyTestCoordinates);

		} catch (Exception e1) {

			System.out.println(e1);
		}

	}

	public void verifyText(String expected, String actual, String msg) {
		try {
			Assert.assertTrue(expected.equals(actual), "Expected Text : "
					+ expected + " is not equal to the Actual Text : " + actual);
		} catch (Error e) {
			Reporter.log("<br/> <font color='red'>" + msg);
			Reporter.log("<br/>Text Verification Failed with Error: "
					+ e.getMessage() +"</font>");
			
			WebDriver driver = WebDriverManager.getDriverInstance();
			File scrFile = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);
			DateFormat dateFormat = new SimpleDateFormat(
					"dd_MMM_yyyy__hh_mm_ssaa");
			String destDir = "html/screenshots";
			new File(destDir).mkdirs();
			String destFile = dateFormat.format(new Date()) + ".png";

			try {
				FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			Reporter.setEscapeHtml(false);
			Reporter.log("<br/> Saved");
			Reporter.log("<a target='_blank' href='../html/screenshots/"
					+ destFile + "'>View Screenshot</a>");
			TestResult = false;
		}

	}

	public void verifyTextContains(String expected, String actual, String msg) {
		try {
			Assert.assertTrue(actual.contains(expected), "Expected Text : "
					+ expected + " is not equal to the Actual Text : " + actual);
		} catch (Error e) {
			Reporter.log("<br/> <font color='red'>" + msg);
			Reporter.log("<br/>Text Verification Failed with Error: "
					+ e.getMessage() +"</font>");

			WebDriver driver = WebDriverManager.getDriverInstance();
			File scrFile = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);
			DateFormat dateFormat = new SimpleDateFormat(
					"dd_MMM_yyyy__hh_mm_ssaa");
			String destDir = "html/screenshots";
			new File(destDir).mkdirs();
			String destFile = dateFormat.format(new Date()) + ".png";

			try {
				FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			Reporter.setEscapeHtml(false);
			Reporter.log("<br/> Saved");
			Reporter.log("<a target='_blank' href='../html/screenshots/"
					+ destFile + "'>View Screenshot</a>");
			TestResult = false;
		}

	}

	@Parameters("browser")
	@BeforeClass
	public void openUrl(String browser) {

		if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();

		} else if (browser.equalsIgnoreCase("ie")) {
			// System.setProperty("webdriver.ie.driver",
			// "C:/IEDriverServer_x64_2.39.0/IEDriverServer.exe");
			// driver = new InternetExplorerDriver();
		}
	}
}
