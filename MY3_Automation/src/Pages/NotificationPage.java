package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.Reporter;

/*
 * @Class for Notification Page elements and related function,
 
 * @Date:14-07-2014.
 */

public class NotificationPage extends LoginPage {

	@FindBy(xpath = "//div[@class='notification']//h2")
	private WebElement header_notification;

	@FindBy(xpath = "//div[@id='notifications-table-header']//div[1]/p")
	private WebElement txt_Date;

	@FindBy(xpath = "//div[@id='notifications-table-header']//div[2]/p")
	private WebElement txt_description;

	/*
	 * @FindBy(xpath =
	 * "//div[@id='notifications-list-container']/div[4]//span[1]/strong")
	 * private WebElement txt_Jul2;
	 */

	@FindBy(xpath = "//div[@id='notifications-list-container']/div[1]//div[2]/a")
	private WebElement Notification_loaded;

	public void verify_NotificationHeader() {
		isElementPresent(header_notification);
		verifyText("Notifieringar", header_notification.getText(),
				"Notification Header doesn't contain the Notifieringar text.");
		Reporter.log("<br/> Assertion: Notification Header with Notifieringar text verified.");
	}

	public void verify_DateHeader() {
		isElementPresent(txt_Date);
		verifyText("Datum", txt_Date.getText(),
				"Date Header doesn't contain the Datum text.");
		Reporter.log("<br/> Assertion: Date Header with Datum text verified.");
	}

	public void verify_DescriptionHeader() {
		isElementPresent(txt_description);
		verifyText("Beskrivning", txt_description.getText(),
				"Description Header doesn't contain the Beskrivning text.");
		Reporter.log("<br/> Assertion: Description Header with Beskrivning text verified.");
	}

	/*
	 * public void verify_July2Text() {
	 * Assert.assertTrue(isElementPresent(txt_Jul2));
	 * Assert.assertTrue(txt_Jul2.getText().contains("Jul 2"),
	 * "Second row doesn't contain the Jul 2 text.");
	 * Reporter.log("<br/> Assertion: Second row with Jul 2 text verified."); }
	 */

	public void verify_Notification() {
		isElementPresent(Notification_loaded);
		Reporter.log("<br/> Assertion: Second row's Link verified.");
	}

}
