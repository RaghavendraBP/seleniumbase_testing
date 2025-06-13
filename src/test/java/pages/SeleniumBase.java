package pages;

import java.time.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.Color;

import common.Common;

public class SeleniumBase {
	
	WebDriver driver;
	
	@CacheLookup
	@FindBy(how = How.XPATH, using = "//input[@id='myTextInput']")
	private WebElement TextInputFieldTextbox;
	
	@CacheLookup
	@FindBy(how = How.XPATH, using = "//input[@id='checkBox6']")
	private WebElement iFrameCheckbox;
	
	@CacheLookup
	@FindBy(how = How.XPATH, using = "//select[@id='mySelect']")
	private WebElement SelectDropdown;
	
	@CacheLookup
	@FindBy(how = How.ID, using = "svgRect")
	private WebElement HTMLSVGwithrectColorBox;
	
	@CacheLookup
	@FindBy(how = How.XPATH, using = "//meter[@id='meterBar']")
	private WebElement HTMLMeterPoll;
	
	@CacheLookup
	@FindBy(how = How.XPATH, using = "//label[@id='meterLabel']")
	private WebElement HTMLMeterLabel;
	
	
	public SeleniumBase(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
		
	public void test_operations(String TextFieldInput, String dropdownvalue) throws InterruptedException
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		
		Common.waitFor(driver, By.xpath("//input[@id='myTextInput']"));  
		
		TextInputFieldTextbox.sendKeys(TextFieldInput);
		
		if(TextInputFieldTextbox.getDomAttribute("value").equals(TextFieldInput))
			System.out.println("Text Input Field is filled with the value:" +TextFieldInput);
		else
		{
			System.out.println("Text Input Field is NOT filled with the value:" +TextFieldInput);
			Assert.fail("Text Input Field is NOT filled with the value:" +TextFieldInput);
		}
		
		String rgb_color = HTMLSVGwithrectColorBox.getCssValue("background-color");
		
		Color color = Color.fromString(rgb_color);
        String actualColor_Hex = color.asHex();
        
        System.out.println("rgb color for HTML SVG with rect: is" +rgb_color);
        System.out.println("rgb color for HTML SVG with rect: in Hex format is:" +actualColor_Hex);
        
        iFrameCheckbox.click();
        
        if(iFrameCheckbox.isSelected())
        	System.out.println("CheckBox in the iFrame is Checked");
		else
		{
			System.out.println("CheckBox in the iFrame is NOT Checked");
			Assert.fail("CheckBox in the iFrame is NOT Checked");
		}
        
        Select sel = new Select(SelectDropdown);
        
        sel.selectByVisibleText("Set to 50%");
        
        String HTMLMeter_Label_Selected = HTMLMeterLabel.getText();
        String HTMLMeter_Poll = HTMLMeterPoll.getDomAttribute("value");
        
        String[] strArray = HTMLMeter_Label_Selected.split(":");
        strArray[1] = strArray[1].trim();
        
        strArray[1] = strArray[1].replace("(", "");
        String HTMLMeter_Label__Selected_Percentage = strArray[1].replace(")", "");
        
        Assert.assertEquals(HTMLMeter_Label__Selected_Percentage, "25%");
        Assert.assertEquals(HTMLMeter_Poll, "0.25");
        
        
	}
	
	
	
	
	
	
}
