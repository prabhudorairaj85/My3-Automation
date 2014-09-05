package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

/*
 * @Class for Login Page elements and related function,

 * @Date:12-07-2014.
 */

public class LoginPage extends HomePage {

	@FindBy(xpath = "//*[@id='txtUsername']")
	private WebElement txt_userName;

	@FindBy(xpath = "//*[@id='txtPassword']")
	private WebElement txt_password;

	@FindBy(xpath = "//*[@id='btnLogin']")
	private WebElement btn_logIn;

	@FindBy(xpath = "//h2[contains(.,'Logga in på Mitt3')]")
	private WebElement header_logIn;

	@FindBy(xpath = "//p[@class='error-message colored']")
	private WebElement para_Error;

	public void fillCredentials(String uid, String Password) {
		txt_userName.clear();
		txt_userName.sendKeys(uid);
		txt_password.clear();
		txt_password.sendKeys(Password);
		btn_logIn.click();
	}

	public void verifyLoginHeader() {
		waitForJQueryProcessing(driver, 10);
		isElementPresent(header_logIn);
	}

	public void verifyErrorMessage() {
		Assert.assertTrue(
				para_Error
						.getText()
						.contains(
								"Fel användarnamn/telefonnummer. Vänligen försök igen."),
				"Error message not displayed");
		Reporter.log(" Error Message displayed is: " + para_Error.getText());
	}

	public void signOut() {

	}
}
