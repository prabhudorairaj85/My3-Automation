package util;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import listeners.WebDriverManager;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

/**
 * @Class Wait Utility provides Wait methods for an elements and AJAX elements
 *        to load,
  * @Date:12-07-2014.
 */

public class WaitUtility {

	/* Wait time for an element. 10 seconds. */
	public static final int wait_for_element = 10;
	/* Wait time for a page to be displayed. 10 seconds. */
	public static final int wait_for_page = 10;

	/*
	 * Wait for the element to be present in the DOM, and displayed on the page.
	 * And returns the first WebElement.
	 */
	public static WebElement waitForElementVisible(WebDriver driver, final By by) {
		WebElement element;
		try {
			nullifyImplicitWait(driver);
			WebDriverWait wait = new WebDriverWait(driver, wait_for_element);
			element = wait.until(ExpectedConditions
					.visibilityOfElementLocated(by));

			driver.manage().timeouts()
					.implicitlyWait(wait_for_page, TimeUnit.SECONDS);
			return element;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Wait for the element to be present in the DOM, regardless of being
	 * displayed or not. And returns the first WebElement
	 */
	public static WebElement waitForElementPresent(WebDriver driver, final By by) {
		WebElement element;
		try {
			nullifyImplicitWait(driver);

			WebDriverWait wait = new WebDriverWait(driver, wait_for_element);
			element = wait.until(ExpectedConditions
					.presenceOfElementLocated(by));

			driver.manage().timeouts()
					.implicitlyWait(wait_for_page, TimeUnit.SECONDS);
			return element;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Wait for the List<WebElement> to be present in the DOM, regardless of
	 * being displayed or not. Returns all elements within the current page DOM.
	 */
	public static List<WebElement> waitForListElementsPresent(WebDriver driver,
			final By by) {
		List<WebElement> elements;
		try {
			nullifyImplicitWait(driver);

			WebDriverWait wait = new WebDriverWait(driver, wait_for_element);
			wait.until((new ExpectedCondition<Boolean>() {
				@Override
				public Boolean apply(WebDriver driverObject) {
					return areElementsPresent(driverObject, by);
				}
			}));

			elements = driver.findElements(by);
			driver.manage().timeouts()
					.implicitlyWait(wait_for_page, TimeUnit.SECONDS);
			return elements;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Waits for the completion of Ajax jQuery processing by checking
	 * "return jQuery.active == 0" condition.
	 */
	public static boolean waitForJQueryProcessing(WebDriver driver,
			int timeOutInSeconds) {
		boolean jQcondition = false;
		try {
			nullifyImplicitWait(driver);
			new WebDriverWait(driver, timeOutInSeconds) {
			}.until(new ExpectedCondition<Boolean>() {

				@Override
				public Boolean apply(WebDriver driverObject) {
					return (Boolean) ((JavascriptExecutor) driverObject)
							.executeScript("return jQuery.active == 0");
				}
			});
			jQcondition = (Boolean) ((JavascriptExecutor) driver)
					.executeScript("return jQuery.active == 0");
			driver.manage().timeouts()
					.implicitlyWait(wait_for_page, TimeUnit.SECONDS);
			return jQcondition;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jQcondition;
	}

	/**
	 * If you have set implicit wait once then you would have to explicitly set
	 * it to zero to nullify it -
	 */
	public static void nullifyImplicitWait(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}

	/**
	 * Set driver implicitlyWait() time.
	 */
	public static void setImplicitWait(WebDriver driver, int waitTime_InSeconds) {
		driver.manage().timeouts()
				.implicitlyWait(waitTime_InSeconds, TimeUnit.SECONDS);
	}

	/**
	 * Reset ImplicitWait.
	 */
	public static void resetImplicitWait(WebDriver driver) {
		nullifyImplicitWait(driver);
		driver.manage().timeouts()
				.implicitlyWait(wait_for_page, TimeUnit.SECONDS);
	}

	/**
	 * Reset ImplicitWait.
	 * 
	 * @param int - a new wait time in seconds
	 */
	public static void resetImplicitWait(WebDriver driver,
			int newWaittime_InSeconds) {
		nullifyImplicitWait(driver);
		driver.manage().timeouts()
				.implicitlyWait(newWaittime_InSeconds, TimeUnit.SECONDS);
	}

	/**
	 * Checks if the element is present
	 */
	public static boolean isElementPresent(WebElement ele) {
		try {
			Assert.assertTrue(ele.isDisplayed());
			return true;
		} catch (NoSuchElementException e) {

			HandleNoSuchElementException(e);
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

			return false;
		} 
		catch (Exception e) {
			Reporter.log("Inside other exception.");
			System.out.println("Exception Raised is:" + e.toString());
			return false;
		}
	}

	/**
	 * Checks if the List<WebElement> are in the DOM, regardless of being
	 * displayed or not.
	 */
	public static boolean areElementsPresent(WebDriver driver, By by) {
		try {
			driver.findElements(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	public static void HandleNoSuchElementException(NoSuchElementException e1) {
			
		int s, e, s_semicolon1, s_semicolon2;
		s = e1.getMessage().indexOf("{");
		e = e1.getMessage().indexOf("}");
		String selector_WithType = e1.getMessage().substring(s + 1, e);
		s_semicolon1 = selector_WithType.indexOf(":");
		s_semicolon2 = selector_WithType.indexOf(":", s_semicolon1+1);
		String selector = selector_WithType.substring(s_semicolon2 + 1, selector_WithType.length());
		
		Reporter.log("<br/> <font color='red'> The Element with Selector : " + selector + " is not found.</font>");

	}

}