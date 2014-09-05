package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

/*
 * @Class for Home Page elements and related function,
 
 * @Date:31-07-2014.
 */

public class HomePage extends WebDriverTestBasePage {

	@FindBy(xpath = "///span[contains(.,'Registrera')]")
	private WebElement lnk_register;

	@FindBy(xpath = "//span[contains(.,'Logga in på Mitt3')]")
	private WebElement lnk_login;

	@FindBy(xpath = "//div[@id='im-close']")
	private WebElement div_popUp;

	public void clickRegisterLink() {
		isElementPresent(lnk_register);
		lnk_register.click();
		Reporter.log("<br/> Register Link clicked.");
	}

	public void closeInitialPopup() {
		if (isElementPresent(div_popUp)) {
			div_popUp.click();
			Reporter.log("<br/> Initial PopUp Closed.");
		} else {
			Reporter.log("<br/> Initial PopUp Not opened.");
		}

	}

	public void clickLogInLink() {
		isElementPresent(lnk_login);
		lnk_login.click();
		Reporter.log("<br/> Login link clicked");
	}

	public void verify_LoginLink() {
		isElementPresent(lnk_login);
		verifyText("Logga in på Mitt3", lnk_login.getText(),
				"Login Link doesn't contain the Logga in på Mitt3 text.");
		Reporter.log("<br/> Assertion: Login Link with Logga in på Mitt3 text verified.");
	}

}
