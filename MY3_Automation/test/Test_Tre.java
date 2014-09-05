package test;




import Pages.AccountSettingsPage;
import Pages.InvoicePage;
import Pages.NotificationPage;
import Pages.OverviewPage;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.SubscriptionPage;
import Pages.WebDriverTestBasePage;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Listeners({ listeners.ScreenShotOnFailure.class })
public class Test_Tre extends WebDriverTestBasePage {

	int i=1;
	HomePage homePage = new HomePage();
	OverviewPage overview = new OverviewPage();

	@BeforeMethod
	public void resetResult() {
		TestResult = true;
		System.out
				.println("\n The test Result before test method " + i + " execution is: "
						+ TestResult);

	}

	@AfterMethod
	public void increaseCounter()
	{
		i++;
	}
	public void CheckTestResult(int k) {
		System.out
				.println("\n The test Result after test method " + k +" execution is: "
						+ TestResult);
		Assert.assertTrue(TestResult,
				"The Test Case failed with above Assertions shown in red fonts.");
	}

	@Parameters("browser")
	@Test(alwaysRun=true)
	public void TC1_verifyDetailsAfterLogin(String browser) {
		Reporter.log("Browser: " + browser + "<br />");
		
		Reporter.log(" <br/><br/>Verifying the overview page and its functionality" + "<br />");
		login();
		try {

		overview = PageFactory.initElements(driver, OverviewPage.class);

		System.out.println("Center Point");
		scrollDownWindow(driver);
		overview.verifyCenter_points();
		overview.verifyCenter_threePott();
		overview.verify_UserName();
		overview.verify_Subscriptiontype();
		overview.verify_Notification();
		overview.verify_susbcription_overview();
		overview.verify_invoice_overview();
		overview.clickOnCircle();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		overview.verify_SubCircleDetails();
		//logout();
		} catch (NoSuchElementException e1) {
			HandleNoSuchElementException(e1);
			TestResult = false;
		}
		CheckTestResult(i);
	}

	@Parameters("browser")
	@Test(alwaysRun=true)
	public void TC2_verifySubscriptions(String browser) {
		Reporter.log("Browser: " + browser + "<br />");
		//login();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		overview = PageFactory.initElements(driver, OverviewPage.class);
		overview.clickOnSubscriptionLink();
		SubscriptionPage subscription = PageFactory.initElements(driver,
				SubscriptionPage.class);
		subscription.verify_subscriptionHeader();
		subscription.verify_UserName();
		subscription.verify_ServicesHeader();
		subscription.changename_subscriptionpage();
		subscription.buynewsimcard();
		subscription.activate_simcard();
		subscription.othersettings();
		subscription.costcontrol();
		subscription.active_reccuring();
		subscription.change_reccuring_status();
		subscription.verify_yourServicesLink();
		subscription.verify_ServicesNames();
		//logout();
		CheckTestResult(i);
	}

	@Parameters("browser")
	@Test(alwaysRun=true)
	public void TC3_verifyInvoice(String browser) {
		Reporter.log("Browser: " + browser + "<br />");
		//login();
		try {
		overview = PageFactory.initElements(driver, OverviewPage.class);
		overview.clickOnInvoiceLink();
		InvoicePage invoicePage = PageFactory.initElements(driver,
				InvoicePage.class);
		invoicePage.verify_invoiceOverviewHeader();
		invoicePage.verify_Technology();
		// Store the current window handle
		String winHandleBefore = driver.getWindowHandle();

		// Perform the click operation that opens new window
		invoicePage.clickOnSeeInvoiceLink();
https://www.tre.se/privat/Mitt3/Faktura/Faktura/?i=NTEzMTI5MTU0MDI5&a=
		// Switch to new window opened
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}

		verifyText(
				"https://www.tre.se/privat/Mitt3/Faktura/Faktura/?i=NTEzMTI5MTU0MDI5&a=",
				driver.getCurrentUrl(),
				"SeeInvoice1 URL is not same as: 'https://www.tre.se/privat/Mitt3/Faktura/Faktura/?i=NTEzMjAwOTk5MDI5&a='");
		Reporter.log("<br/>Assertion: See Invoice 1 Pdf URL verified");
		// Close the new window, if that window no more required
		driver.close();

		// Switch back to original browser (first window)

		driver.switchTo().window(winHandleBefore);
		//logout();
		} catch (NoSuchElementException e1) {
			HandleNoSuchElementException(e1);
			TestResult = false;
		}
		CheckTestResult(i);
	}

	@Parameters("browser")
	@Test(alwaysRun=true)
	public void TC4_verifyNotifications(String browser) {
		Reporter.log("Browser: " + browser + "<br />");
		//login();
		try {
		overview = PageFactory.initElements(driver, OverviewPage.class);
		overview.clickOnNotificationsLink();
		NotificationPage notificationPage = PageFactory.initElements(driver,
				NotificationPage.class);
		notificationPage.verify_NotificationHeader();
		notificationPage.verify_DateHeader();
		notificationPage.verify_DescriptionHeader();
		notificationPage.verify_Notification();
		//logout();
		} catch (NoSuchElementException e1) {
			HandleNoSuchElementException(e1);
			TestResult = false;
		}
		CheckTestResult(i);
	}

	

	

	public void login() {

		initialize(driver);
		driver.get("https://www.tre.se");

		homePage = PageFactory.initElements(driver, HomePage.class);

		if(i==0)
		{
		homePage.closeInitialPopup();
		}
		homePage.clickLogInLink();

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.verifyLoginHeader();
		loginPage.fillCredentials("46723009821", "abc123");

		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void logout() {
		overview.clickOnAccountSettingsLink();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AccountSettingsPage accountSettingsPage = PageFactory.initElements(
				driver, AccountSettingsPage.class);
		accountSettingsPage.verify_AccountSettingsHeader();
		accountSettingsPage.clickOnLogOutLink();
		homePage.verify_LoginLink();
	}
	
	
	
	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}
}
