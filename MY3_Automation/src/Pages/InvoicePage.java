package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.Reporter;

/*
 * @Class for Invoice Page elements and related function,

 * @Date:14-07-2014.
 */

public class InvoicePage extends LoginPage {

	@FindBy(xpath = "//div[@class='mod-autogiro']//h1")
	private WebElement header_invoice_overview;

	@FindBy(xpath = "//*[@id='invoice_513129154029']/div[1]/div[1]/div[1]")
	private WebElement txt_technology;

	@FindBy(xpath = "//*[@id='invoice_513129154029']/div[1]/div[1]/div[6]/a")
	private WebElement lnk_see_Invoice;

	public void verify_invoiceOverviewHeader() {
		isElementPresent(header_invoice_overview);
		verifyText("Fakturaöversikt", header_invoice_overview.getText(),
				"Invoice Overview Header doesn't contain the Fakturaöversikt text.");
		Reporter.log("<br/> Assertion: Invoice Overview Header with Fakturaöversikt text verified.");
	}

	public void verify_Technology() {
		isElementPresent(txt_technology);
		verifyTextContains("Technology Madeleine Berglöf", txt_technology.getText(),
				"Technology doesn't contain the Technology Madeleine Berglöf text.");
		Reporter.log("<br/> Assertion: Technology with Technology Madeleine Berglöf text verified.");
	}

	public void clickOnSeeInvoiceLink() {
		lnk_see_Invoice.click();
		Reporter.log("<br/>See Invoice Link Clicked.");
	}

}
