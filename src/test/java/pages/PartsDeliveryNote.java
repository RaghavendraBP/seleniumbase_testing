package pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import java.util.Calendar;
import java.time.LocalDate;

import common.Common;

public class PartsDeliveryNote {
	
	WebDriver driver;
	
	@CacheLookup
	@FindBy(how = How.XPATH, using = "//td[contains(@id,'tabDeliveryNote_div')]")
	private WebElement PartsDeliveryNoteLink;
	
	@CacheLookup
	@FindBy(how = How.XPATH, using = "//select[contains(@id,'dealerCode')]")
	private WebElement DealerNameDropdown;
	
	@CacheLookup
	@FindBy(how = How.XPATH, using = "//select[contains(@id,'dispatchNoteNum')]")
	private WebElement DespatchNoteDateTimeDropdown;
	
	@CacheLookup
	@FindBy(how = How.XPATH, using = "(//b[contains(text(),'Dealer Reference:')]//parent::td//following-sibling::td)[1]")
	private WebElement DealerReference;
	
	@CacheLookup
	@FindBy(how = How.XPATH, using = "(//b[contains(text(),'Despatch Note:')]//parent::td//following-sibling::td)[1]")
	private WebElement DespatchNote;
	
	@CacheLookup
	@FindBy(how = How.XPATH, using = "(//b[contains(text(),'Despatch Date:')]//parent::td//following-sibling::td)[1]")
	private WebElement DespatchDate;
	
	@CacheLookup
	@FindBy(how = How.XPATH, using = "(//b[contains(text(),'Despatch Time:')]//parent::td//following-sibling::td)[1]")
	private WebElement DespatchTime;
	
	@CacheLookup
	@FindBy(how = How.XPATH, using = "(//tbody[contains(@id,'bdyResults')]//tr)[1]//td")
	private WebElement tableItems;
	
	@CacheLookup
	@FindBy(how = How.XPATH, using = "//th[contains(text(),'Ordered')]")
	private WebElement OrderedColumn;
	
	@CacheLookup
	@FindBy(how = How.XPATH, using = "//input[contains(@value,'Export')]")
	private WebElement ExportButton;
	
	
	
	public PartsDeliveryNote(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
		
	public void display_delivery_note_in_Parts_Despatch_Note_screen_Admin_Role(String DealerName, String DespatchNoteDateTime) throws InterruptedException
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		
		Common.waitFor(driver, By.xpath("//td[contains(@id,'tabDeliveryNote_div')]"));  
		
		Select DealernameDropdown = new Select(DealerNameDropdown);
		
		DealernameDropdown.selectByVisibleText(DealerName);
		
		Select DespatchnoteDateTimeDropdown = new Select(DespatchNoteDateTimeDropdown);
		
		DespatchnoteDateTimeDropdown.selectByVisibleText(DespatchNoteDateTime);
	
		Common.waitFor(driver, By.xpath("//tbody[contains(@id,'bdyResults')]//tr"));  
		
		String[] dealerNamesParts = DealerName.split("-");
		
		String DealerReferenceValue_UI = DealerReference.getText().trim();
		
		if(DealerReferenceValue_UI.equals(dealerNamesParts[0].trim()))
			System.out.println("Dealer Reference: field value is populated in Parts Delivery note screen for the Dealer:" +DealerName);
		else
		{
			System.out.println("Dealer Reference: field value is NOT populated in Parts Delivery note screen for the Dealer:" +DealerName);
			Assert.fail("Dealer Reference: field value is NOT populated in Parts Delivery note screen for the Dealer:" +DealerName);
		}
		
		String[] DespatchNoteDateTimeParts = DespatchNoteDateTime.split(" ");
		
		String DespatchNote_UI = DespatchNote.getText().trim();
		String DespatchDate_UI = DespatchDate.getText().trim();
		String DespatchTime_UI = DespatchTime.getText().trim();
		 
		if(DespatchNote_UI.equals(DespatchNoteDateTimeParts[0].trim()))
			System.out.println("Despatch Note: field value is populated in Parts Delivery note screen for the DespatchNoteDateTime:" +DespatchNoteDateTime);
		else
		{
			System.out.println("Despatch Note: field value is NOT populated in Parts Delivery note screen for the DespatchNoteDateTime:" +DespatchNoteDateTime);
			Assert.fail("Despatch Note: field value is NOT populated in Parts Delivery note screen for the DespatchNoteDateTime:" +DespatchNoteDateTime);
		}
		
		if(DespatchDate_UI.equals(DespatchNoteDateTimeParts[2].trim()))
			System.out.println("Despatch Date: field value is populated in Parts Delivery note screen for the DespatchNoteDateTime:" +DespatchNoteDateTime);
		else
		{
			System.out.println("Despatch Date: field value is NOT populated in Parts Delivery note screen for the DespatchNoteDateTime:" +DespatchNoteDateTime);
			Assert.fail("Despatch Date: field value is NOT populated in Parts Delivery note screen for the DespatchNoteDateTime:" +DespatchNoteDateTime);
		}
		
		if(DespatchTime_UI.equals(DespatchNoteDateTimeParts[3].trim()))
			System.out.println("Despatch Time: field value is populated in Parts Delivery note screen for the DespatchNoteDateTime:" +DespatchNoteDateTime);
		else
		{
			System.out.println("Despatch Time: field value is NOT populated in Parts Delivery note screen for the DespatchNoteDateTime:" +DespatchNoteDateTime);
			Assert.fail("Despatch Time: field value is NOT populated in Parts Delivery note screen for the DespatchNoteDateTime:" +DespatchNoteDateTime);
		}
		
		List<WebElement> PartsDeliveryNoteTableElements = driver.findElements(By.xpath("(//tbody[contains(@id,'bdyResults')]//tr)[1]//td"));
		
		String[] PartsDeliveryNoteTableNames = {"Part Number", "Description", "Order", "Type", "Order Date", "Text", "BMW Ref", "Label", "Cage/Package", "Ordered", "Supplied", "Supply Code", "Wip No", "Comment", "Message"};
	
		int j = 0;
		
		for(int i=1;i<PartsDeliveryNoteTableElements.size()-1; i++)
		{
			if(PartsDeliveryNoteTableElements.get(i).isDisplayed())
				System.out.println(PartsDeliveryNoteTableNames[j]+ " field is displayed for Dealer Name:" +DealerName+ " and Despatch Note & Date/Time:" +DespatchNoteDateTime+ " in Parts Delivery note screen");
			else
			{
				System.out.println(PartsDeliveryNoteTableNames[j]+ " field is NOT displayed for Dealer Name:" +DealerName+ " and Despatch Note & Date/Time:" +DespatchNoteDateTime+ " in Parts Delivery note screen");
				Assert.fail(PartsDeliveryNoteTableNames[j]+ " field is NOT displayed for Dealer Name:" +DealerName+ " and Despatch Note & Date/Time:" +DespatchNoteDateTime+ " in Parts Delivery note screen");
			}
			j = j + 1;
		}
		
		
		OrderedColumn.click();
		Thread.sleep(1000);
		List<WebElement> PartsDeliveryNoteTableItems = driver.findElements(By.xpath("(//tbody[contains(@id,'bdyResults')]//tr//td)"));
		int[] OrderedColumnIntegerValue = new int[149];
		
		int i=10;
		j = 0;
		while(i < PartsDeliveryNoteTableItems.size())
		{
			String OrderedColumnStringValue = PartsDeliveryNoteTableItems.get(i).getText();
			OrderedColumnIntegerValue[j] = Integer.valueOf(OrderedColumnStringValue);
			i = i + 17;
			j = j + 1;
		}
		
		int n = OrderedColumnIntegerValue.length;
		
		if(arraySortedOrNot(OrderedColumnIntegerValue,n))
			System.out.println("Ordered column values are sorted when user cliks on Ordered column in Parts Delivery note screen");
		else
		{
			System.out.println("Ordered column values are NOT sorted when user cliks on Ordered column in Parts Delivery note screen");
			Assert.fail("Ordered column values are NOT sorted when user cliks on Ordered column in Parts Delivery note screen");
		}
		
	}
	
	static boolean arraySortedOrNot(int arr[], int n)
    {

        // Array has one or no element
        if (n == 0 || n == 1)
            return true;

        for (int i = 1; i < n; i++)

            // Unsorted pair found
            if (arr[i - 1] > arr[i])
                return false;

        // No unsorted pair found
        return true;
    }

	public void export_in_Parts_Despatch_Note_screen_Admin_Role(String DealerName, String DespatchNoteDateTime) throws InterruptedException, AWTException
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		
		Common.waitFor(driver, By.xpath("//td[contains(@id,'tabDeliveryNote_div')]"));  
		
		Select DealernameDropdown = new Select(DealerNameDropdown);
		
		DealernameDropdown.selectByVisibleText(DealerName);
		
		Select DespatchnoteDateTimeDropdown = new Select(DespatchNoteDateTimeDropdown);
		
		DespatchnoteDateTimeDropdown.selectByVisibleText(DespatchNoteDateTime);
	
		Common.waitFor(driver, By.xpath("//tbody[contains(@id,'bdyResults')]//tr"));  
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()", ExportButton);
		
		ExportButton.click();
		Thread.sleep(1000);
		fileDownload();
	}
	
	public void fileDownload() throws AWTException {
		Robot robot = new Robot(); 
		
		robot.keyPress(KeyEvent.VK_TAB);
		
		        robot.keyRelease(KeyEvent.VK_TAB);
		
		        robot.keyPress(KeyEvent.VK_ENTER); 
		
		        robot.keyRelease(KeyEvent.VK_ENTER);  
		
		
		    }
	
}
