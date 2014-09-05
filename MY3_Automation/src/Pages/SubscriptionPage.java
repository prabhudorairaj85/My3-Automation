package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.Reporter;
import org.junit.*;
import static org.junit.Assert.*;

import com.opera.core.systems.scope.protos.Esdbg6Protos.NodeInfo.Attribute;

/*
 * @Class for Subscription Page elements and related function,

 * @Date:14-07-2014.
 */

public class SubscriptionPage extends LoginPage {

	@FindBy(xpath = "//section/div[1]/h1")
	private WebElement header_Subscription;

	@FindBy(xpath = "//div[@id='settings-container-region']//a")
	private WebElement lnk_userName;

	@FindBy(xpath = "//div[@id='services']/div/div/div/section/div[1]/div/h1")
	private WebElement header_services;
	
	@FindBy(xpath = "//*[@id='account-name']")
	private WebElement account_name;
					
	@FindBy(xpath = "//*[@id='settings-container-region']/div/nav/ul/li/a")
	private WebElement changedaccountname;
	
	@FindBy(xpath = "//*[@id='subscription-settings-holder']/div/div[1]/div[3]/div/div[1]/a")
	private WebElement Savebutton;
	
	@FindBy(xpath = "//*[@id='subscription-settings-holder']/div/div[1]/div[1]/div/div[2]/nav/ul/li[1]/a/span/strong")
	private WebElement buynewsim;
	
	@FindBy(xpath = "//*[@id='new-sim']/section[1]/div/div[2]/div[2]/table/tbody/tr[1]/td[2]/label/strong")
	private WebElement Simtype;
	
//	
	//private WebElement NanoSimSelected;
	
	@FindBy(id = "activate-uiccid")
	private WebElement activatesimcard;
	
	@FindBy(xpath = "//*[@id='subscription-settings-holder']/div/div[1]/div[1]/div/div[2]/nav/ul/li[3]/a/span/strong")
	private WebElement othersettings;
	
	@FindBy(xpath = "//div[@id='CostControl']//div[@class='container']//div[@class='status']")
	private WebElement costcontrolactive;
			
	@FindBy(xpath = "//*[@id='activePrenumerations']/div[3]/div[1]/div[1]/div/div/div[2]/div[2]/p[1]" )
	private WebElement reccuring;
	
	@FindBy(xpath = "//*[@id='activePrenumerations']/div[3]/div[1]/div[1]/div/div/div[2]/div[1]/p[1]" )
	private WebElement recurring_status;
					
	@FindBy(xpath = "//*[@id='activePrenumerations']/div[3]/div[1]/div[1]/div/div/div[2]/div[8]/p/a/i" )
	private WebElement change_reccuring_status;
	
	
	@FindBy(xpath = "//*[@id='activePrenumerations']/div[3]/div/div[1]/div/div/div[2]/div[1]/p[1]")
	private WebElement verifypausedstatus;
	
	@FindBy(xpath = "//*[@id='edit-recurring-info-47648894']/div[3]/div[1]/div/div[2]/div[1]/div[2]/span/input" )
	private WebElement clickpause;
	
	@FindBy(xpath = "//a[@id='yourServices']")
	private WebElement lnk_yourServices;

	@FindBy(xpath = "//*[@id='addon-favorites-container']//div[1]/div/strong")
	private WebElement txt_service1;

	@FindBy(xpath = "//*[@id='addon-favorites-container']//div[2]/div/strong")
	private WebElement txt_service2;

	@FindBy(xpath = "//*[@id='addon-favorites-container']//div[3]/div/strong")
	private WebElement txt_service3;

	public void verify_subscriptionHeader() {
		isElementPresent(header_Subscription);
		verifyText("Abonnemang", header_Subscription.getText(),
				"Subscription Header doesn't contain the Abonnemang text.");
		Reporter.log("<br/> Assertion: Subscription Header with Abonnemang verified.");
	}

	public void verify_UserName() {
		isElementPresent(lnk_userName);
		verifyText("Mannen från 3", lnk_userName.getText(),
				"User Name Under Abonnemang doesn't contain the text Maddes.");
		Reporter.log("<br/> Assertion: User Name: Maddes Under Abonnemang verified.");
	}

	public void verify_ServicesHeader() {
		isElementPresent(header_services);
		verifyText("Tjänster", header_services.getText(),
				"Services Header doesn't contain the text Tjänster.");
		Reporter.log("<br/> Assertion: Services Header verified.");
		//verify whether installed services is loaded 
		driver.findElement(By.id("has-services")); 
		Reporter.log("<br/> Assertion: installed verified.");
	}
	
	public void changename_subscriptionpage() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		isElementPresent(account_name);
		account_name.sendKeys("Tester");
		Savebutton.click();
		isElementPresent(changedaccountname);
		verifyText("Tester",changedaccountname.getText(),"account name was not changed successfully.");
		Reporter.log("<br/>Account name changed succesfully");
	}
	
	public void buynewsimcard() {
		isElementPresent(buynewsim);
		verifyText("Beställ nytt SIM-kort",buynewsim.getText(),"Buy new sim is not loaded or not available");
		buynewsim.click();
		isElementPresent(Simtype);
		verifyText("Nano SIM",Simtype.getText(),"There is no simtype called nano sim");
		//driver.findElement(By.id("sim-product-F01000")).click();
		driver.findElement(By.id("sim-product-F01000")).click();
	    assertTrue(driver.findElement(By.id("sim-product-F01000")).isSelected());
		Reporter.log("</br> Nano Sim was selected and verified thats nano sim was selected");
  }

	public void activate_simcard() {
		//clicks the activate sim card
		driver.findElement(By.xpath("//section[@id='subscription-settings-holder']/div/div/div/div/div[2]/nav/ul/li[2]/a/span/strong")).click();
		isElementPresent(activatesimcard);
		Reporter.log("</br> Sim card activation module was loaded and verified with field");
	}
	
	public void othersettings() {
		//clicks the other settings tab
		othersettings.click();
		driver.findElement(By.id("tre-svar"));
		Reporter.log("</br> 3svar module was loaded");
		//Nummervisning is already set to no
		driver.findElement(By.id("msisdn-visible-no"));
		assertTrue(driver.findElement(By.id("msisdn-visible-no")).isSelected()); // verifying if the msisdn visibility is set to NO
		Reporter.log("</br> MSISDN visibility is set to NO - Nummervisning Nej");
		//Nummerupplysning is already set to yes
		driver.findElement(By.id("inquiry-visible-yes"));
		assertTrue(driver.findElement(By.id("inquiry-visible-yes")).isSelected());
		Reporter.log("</br> Nummerupplysning is set to Yes - Nummerupplysning Ja");
		/* Vidarekoppling & 3Svar  setting default is set to inactive and change it to aktive verify 
		and again change it back to inactive*/
		driver.findElement(By.id("call-forwarding-status-no"));
		assertTrue(driver.findElement(By.id("call-forwarding-status-no")).isSelected());
		driver.findElement(By.id("call-forwarding-status-yes")).click();
		assertTrue(driver.findElement(By.id("call-forwarding-status-yes")).isSelected());
		//change back to inactive
		driver.findElement(By.id("call-forwarding-status-no")).click();
		assertTrue(driver.findElement(By.id("call-forwarding-status-no")).isSelected());
		Reporter.log( "</br>" + "</br>  Vidarekoppling & 3Svar  was default set to Inaktiv - Vidarekoppling & 3Svar was set to Aktiv and changed back to Inaktiv" + "</br>");
		othersettings.click();
 }
	
	public void data() {
		isElementPresent(driver.findElement(By.id("buyExtraData")));
		isElementPresent(driver.findElement(By.id("buyWeeklyPass")));
		isElementPresent(driver.findElement(By.id("buyAbroadBundle")));
		Reporter.log("</br>Extradata,3surfpass,3utland was loaded");
	}

	public void costcontrol() {
		isElementPresent(costcontrolactive);
		verifyText("Status: Aktiv", costcontrolactive.getText(), "cost control is not active");
		Reporter.log("<br/> Assertion: cost control is active.");
		
	}
	
	public void active_reccuring() {
		isElementPresent(reccuring);
		verifyText("073 5434 816", reccuring.getText(), "Recuring topup is not active");
		Reporter.log("<br/> status of reccuring top up is " + recurring_status.getText() + "For" + reccuring.getText());
		
	}
	
	public void change_reccuring_status()  {
		isElementPresent(change_reccuring_status);
		change_reccuring_status.click();
		isElementPresent(clickpause);
		clickpause.click();
		//driver.findElement(By.xpath("//*[@id='edit-recurring-info-47648894']/div[3]/div[1]/div/div[2]/div[1]/div[2]/span/input")).click();
	//	Thread.getDefaultUncaughtExceptionHandler();
		driver.findElement(By.xpath("(//a[contains(text(),'Spara')])[2]")).click();
		isElementPresent(verifypausedstatus);
	    verifyText("Pausad", verifypausedstatus.getText(), "status of reccuring not changed");
		Reporter.log("<br/>changed from active to paused "+ "<br/>current status of reccuring is " +  verifypausedstatus.getText());
		driver.findElement(By.xpath("(//a[contains(text(),'Ok')])[5]")).click();
		
	//change it back to active status to continue testing
		change_reccuring_status.click();
		driver.findElement(By.name("recurringstatus")).click();
		driver.findElement(By.xpath("(//a[contains(text(),'Spara')])[2]")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Ok')][5]")).click();
		
	}
	
	
	
	public void verify_yourServicesLink() {
		isElementPresent(lnk_yourServices);
		verifyText("Maddes: Dina tjänster", lnk_yourServices.getText(),
				"Your Services link doesn't contain the text Maddes: Dina tjänster.");
		Reporter.log("<br/> Assertion: Maddes: Your Services verified.");
	}

	public void verify_ServicesNames() {
		isElementPresent(txt_service1);
		verifyText("3Mobilsurf 0,5GB", txt_service1.getText(),
				"Service 1 doesn't contain the text 3Mobilsurf 0,5GB.");
		Reporter.log("<br/> Assertion: Service 1 text: 3Mobilsurf 0,5GB verified.");
		isElementPresent(txt_service2);
		verifyText("3Mobilsurf 3GB", txt_service2.getText(),
				"Service 2 doesn't contain the text 3Mobilsurf 3GB.");
		Reporter.log("<br/> Assertion: Service 2 text: 3Mobilsurf 3GB verified.");
		isElementPresent(txt_service3);
		verifyText("3Mobilsurf 5GB", txt_service3.getText(),
				"Service 3 doesn't contain the text 3Mobilsurf 5GB.");
		Reporter.log("<br/> Assertion: Service 3 text: 3Mobilsurf 5GB verified.");
	}
	
	
}
	

