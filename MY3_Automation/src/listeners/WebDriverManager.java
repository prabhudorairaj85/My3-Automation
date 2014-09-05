package listeners;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import Pages.WebDriverTestBasePage;
 
/*
 * @Class for getting the driver Instance,

 * @Date:12-07-2014.
 */

public class WebDriverManager extends WebDriverTestBasePage {
  	
	public static WebDriver getDriverInstance() {
		return driver;
	}
	
}
