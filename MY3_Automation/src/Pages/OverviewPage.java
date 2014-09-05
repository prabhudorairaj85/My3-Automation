package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

/*
 * @Class for Overview Page elements and related function,
  * @Date:14-07-2014.
 */

public class OverviewPage extends LoginPage {

	@FindBy(xpath = "//div[@class='usage-summary-disc']//*[name()='svg']/*[name()='text'][7]/*[name()='tspan']")
	private WebElement header_center_point_txt;

	@FindBy(xpath = "//div[@class='usage-summary-disc']//*[name()='svg']/*[name()='text'][6]/*[name()='tspan']")
	private WebElement header_center_three_pott_txt;

	@FindBy(xpath = "//div[@class='usage-summary-module']//a[@class='active']")
	private WebElement lnk_username;

	@FindBy(xpath = "//*[@id='usage-summary-module']/div[2]/header/p/a" )
	private WebElement Subscriptiontype_inoverviewpage;
	
	@FindBy(id = "notifications-list-container" )
	private WebElement Notification_in_overviewpage;
	
	@FindBy(xpath = "//*[@id='ctl02']/div[3]/main/section[1]/div/div/div[2]/div/div[2]/p/span")
	private WebElement subscription_in_overviewpage;
	
	@FindBy(xpath = "//*[@id='new-invoice']/div/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/span" )
	private WebElement Invoice_in_overviewpage;
	
	@FindBy(xpath = "//*[name()='svg']/*[name()='text'][13]/*[name()='tspan']")
	private WebElement circle_minute_Point;

	@FindBy(xpath = "//div[@class='usage-summary-sub-discs']//*[name()='svg']/*[name()='text'][9]/*[name()='tspan']")
	private WebElement circle_SMS_Point;

	@FindBy(xpath = "//div[@class='usage-summary-sub-discs']//*[name()='svg']/*[name()='text'][5]/*[name()='tspan']")
	private WebElement circle_KB_data_Point;

	@FindBy(xpath = "//button[@class='button close-details']")
	private WebElement btn_back;

	@FindBy(xpath = "//div[@class='usage-summary-details']//button[@class='button close-details']")
	private WebElement btn_back_AfterSubCircleClicked;

	@FindBy(xpath = "//div[@class='usage-sum']//h1/span[contains(.,'sek')]")
	private WebElement span_minute_point_kr_txt;

	@FindBy(xpath = "//div[@class='usage-sum']//h1/span[contains(.,'St')]")
	private WebElement span_SMS_point_kr_txt;

	@FindBy(xpath = "//div[@class='usage-sum']//h1/span[contains(.,'KB')]")
	private WebElement span_KB_data_point_kr_txt;

	@FindBy(xpath = "//*[@id='sub-nav']//a[contains(.,'Abonnemang')]")
	private WebElement lnk_subscription;

	@FindBy(xpath = "//*[@id='sub-nav']//a[contains(.,'Faktura')]")
	private WebElement lnk_invoice;

	@FindBy(xpath = "//*[@id='sub-nav']//a[contains(.,'Notifieringar')]")
	private WebElement lnk_notification;

	@FindBy(xpath = "//div[@class='site-operations container']//li[4]/a")
	private WebElement lnk_account_settings;

	
	public void verifyCenter_points() {
		isElementPresent(header_center_point_txt);
		verifyText("169", header_center_point_txt.getText().toString(),
				"Center Point doesn't contain the text 69.");
		Reporter.log("<br/> Assertion: Center Point 69 verified.");
	}

	public void verifyCenter_threePott() {
		isElementPresent(header_center_three_pott_txt);
		verifyText("3Pott", header_center_three_pott_txt.getText(),
				"Center text doesn't contain the text 3Pott.");
		Reporter.log("<br/>Assertion: Center text 3Pott verified.");
	}

	public void verify_Subscriptiontype() {
		isElementPresent(Subscriptiontype_inoverviewpage);
		verifyText("3Pott 399",Subscriptiontype_inoverviewpage.getText(), "its not a 3Pott 69 subscription.");
		Reporter.log("<br/>Assertion: Its a" + Subscriptiontype_inoverviewpage.getText() + "Subscription"  );
	}
	
	
	public void verify_UserName() {
		isElementPresent(lnk_username);
		verifyText("Mannen från 3", lnk_username.getText(),
				"UserName 	Mannen från 3 in the bottom of the page is not displayed.");
		Reporter.log("<br/>Assertion: UserName in the bottom of the page verified.");
	}
	
	public void verify_Notification(){
		isElementPresent(Notification_in_overviewpage);
		Reporter.log("<br/> Notificaiton is loaded on the overview page");
		
	}
	
	public void verify_susbcription_overview() {
		isElementPresent(subscription_in_overviewpage);
		verifyText("072 3009 821", subscription_in_overviewpage.getText(), "Subscription details(number) are not loaded on the overview page");
		Reporter.log("<br/> Assertion : subscription details are loaded on the overview page");
	}
	
	public void verify_invoice_overview() {
		isElementPresent(Invoice_in_overviewpage);
		verifyText("10063300148", Invoice_in_overviewpage.getText(), "Faktura is not  loaded on the overview page");
		Reporter.log("<br/> Assertion : faktura is loaded on the overview page");
	}
	
	public void clickOnCircle() {
		clickonSVGObject("//*[@r='140']");
		Reporter.log("<br/>Center Circle Clicked.");
	}

	public void clickOnAccountSettingsLink() {
		lnk_account_settings.click();
		Reporter.log("<br/>Account Settings link Clicked.");
	}

	public void verifyCircle_Minutes_points() {
		verifyText("0", circle_minute_Point.getText(),
				"Circle with minute doesn't contain the text");
		Reporter.log("<br/> Assertion: Minute Circle Point 0 verified.");
	}

	public void verifyCircle_SMS_points() {
		isElementPresent(circle_SMS_Point);
		verifyText("0", circle_SMS_Point.getText(),
				"Circle with SMS/MMS doesn't contain the text.");
		Reporter.log("<br/> Assertion: SMS/MMS Circle Point 0 verified.");
	}

	public void verifyCircle_KB_data_points() {
		isElementPresent(circle_KB_data_Point);
		verifyText("0", circle_KB_data_Point.getText(),
				"Circle with KB data doesn't contain the text.");
		Reporter.log("<br/> Assertion: KB data Circle Point 0 verified.");
	}

	public void clickOnMinuteCircle() {
		clickonSVGObject("//*[name()='svg']/*[name()='text'][13]/*[name()='tspan']");
		Reporter.log("<br/>Minute Center Circle Clicked.");
	}

	public void clickOnSMSCircle() {
		clickonSVGObject("//div[@class='usage-summary-sub-discs']//*[name()='svg']/*[name()='text'][9]/*[name()='tspan']");
		Reporter.log("<br/>SMS Circle Clicked.");
	}

	public void clickOnKBdataCircle() {
		clickonSVGObject("//div[@class='usage-summary-sub-discs']//*[name()='svg']/*[name()='text'][5]/*[name()='tspan']");
		Reporter.log("<br/>KB data Circle Clicked.");
	}

	public void clickOnBackButton() {
		btn_back.click();
		Reporter.log("<br/>Back Button Clicked.");
	}

	public void clickOnKrBackButton() {
		btn_back_AfterSubCircleClicked.click();
		Reporter.log("<br/>Back Button After Kr Verification Clicked.");
	}

	public void verify_Minutes_points_kr() {
		isElementPresent(span_minute_point_kr_txt);
		verifyTextContains("0 kr", span_minute_point_kr_txt.getText(),
				"Text with sek doesn't contain the 0 kr text.");
		Reporter.log("<br/> Assertion: Minutes Point 0 kr verified.");
	}

	public void verify_SMS_points_kr() {
		isElementPresent(span_SMS_point_kr_txt);
		verifyTextContains("0 kr", span_SMS_point_kr_txt.getText(),
				"Text with st doesn't contain the 0 kr text.");
		Reporter.log("<br/> Assertion: SMS Point with St 0 kr verified.");
	}

	public void verify_KB_data_points_kr() {
		isElementPresent(span_KB_data_point_kr_txt);
		verifyTextContains("0 kr", span_KB_data_point_kr_txt.getText(),
				"Text with KB doesn't contain the 0 kr text.");
		Reporter.log("<br/> Assertion: KB data Point with KB 0 kr verified.");
	}

	public void clickOnSubscriptionLink() {
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		isElementPresent(lnk_subscription);
		Reporter.log("<br/>Before subscription link clicking.");
		lnk_subscription.click();
		Reporter.log("<br/>Subscription Link Clicked.");
	}

	public void clickOnInvoiceLink() {
		lnk_invoice.click();
		Reporter.log("<br/>Invoice Link Clicked.");
	}

	public void clickOnNotificationsLink() {
		lnk_notification.click();
		Reporter.log("<br/>Notification Link Clicked.");
	}
	
	public void verify_SubCircleDetails() {
		verifyCircle_Minutes_points();
		verifyCircle_SMS_points();
		verifyCircle_KB_data_points();
		clickOnMinuteCircle();
		verify_Minutes_points_kr();
		clickOnKrBackButton();
		verifyCircle_SMS_points();
		clickOnSMSCircle();
		verify_SMS_points_kr();
		clickOnKrBackButton();
		verifyCircle_KB_data_points();
		clickOnKBdataCircle();
		verify_KB_data_points_kr();
		clickOnKrBackButton();
		clickOnBackButton();
	}
	
}
