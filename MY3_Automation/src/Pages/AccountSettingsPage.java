package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.Reporter;

/*
 * @Class for Account Settings Page elements and related function,

 * @Date:14-07-2014.
 */

public class AccountSettingsPage extends LoginPage {

	@FindBy(xpath = "//div[@class='container my-details']//div[1]/div[1]/h2")
	private WebElement header_Account_Settings;

	@FindBy(xpath = "//nav/a")
	private WebElement lnk_logout;

	public void verify_AccountSettingsHeader() {
		isElementPresent(header_Account_Settings);
		verifyText("Kontoinställningar", header_Account_Settings.getText(),
				"Header Account Settings doesn't contain the Kontoinställningar text.");
		Reporter.log("<br/> Assertion: Header Account Settings with Kontoinställningar text verified.");
	}

	public void clickOnLogOutLink() {
		lnk_logout.click();
		Reporter.log("<br/>LogOut link Clicked.");
	}

}
