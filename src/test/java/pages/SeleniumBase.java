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
	@FindBy(how = How.XPATH, using = "//select[@id='mySelect']")
	private WebElement SelectDropdown;
	
	@CacheLookup
	@FindBy(how = How.XPATH, using = "//*[name()='svg' and @id='mySVG']/*[name()='rect']")
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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(20));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		
		Common.waitFor(driver, By.xpath("//input[@id='myTextInput']"));  
		
		TextInputFieldTextbox.sendKeys(TextFieldInput);
		Thread.sleep(1000);
		
		if(TextInputFieldTextbox.getDomProperty("value").equals(TextFieldInput))
			System.out.println("Text Input Field is filled with the value:" +TextFieldInput);
		else
		{
			System.out.println("Text Input Field is NOT filled with the value:" +TextFieldInput);
			Assert.fail("Text Input Field is NOT filled with the value:" +TextFieldInput);
		}
		
		String rgb_color = HTMLSVGwithrectColorBox.getCssValue("fill");
		
		Color color = Color.fromString(rgb_color);
        String actualColor_Hex = color.asHex();
        
        System.out.println("rgb color for HTML SVG with rect: is: " +rgb_color);
        System.out.println("rgb color for HTML SVG with rect: in Hex format is:" +actualColor_Hex);
        
        driver.switchTo().frame(2);
        
        WebElement iframeCheckbox = driver.findElement(By.xpath("//input[contains(@id,'checkBox')]"));
        
        iframeCheckbox.click();
        
        if(iframeCheckbox.isSelected())
        	System.out.println("CheckBox in the iFrame is Checked");
		else
		{
			System.out.println("CheckBox in the iFrame is NOT Checked");
			Assert.fail("CheckBox in the iFrame is NOT Checked");
		}
        
        driver.switchTo().defaultContent();
        
        Select sel = new Select(SelectDropdown);
        
        sel.selectByVisibleText("Set to 50%");
        Thread.sleep(1000);
        
        String HTMLMeter_Label_Selected = HTMLMeterLabel.getText();
        String HTMLMeter_Poll = HTMLMeterPoll.getDomAttribute("value");
        
        String[] strArray = HTMLMeter_Label_Selected.split(":");
        strArray[1] = strArray[1].trim();
        
        strArray[1] = strArray[1].replace("(", "");
        String HTMLMeter_Label_Selected_Percentage = strArray[1].replace(")", "");
        
        Assert.assertEquals(HTMLMeter_Label_Selected_Percentage, "50%");
        Assert.assertEquals(HTMLMeter_Poll, "0.5");
        
        if(HTMLMeter_Poll.equals("0.5"))
        	System.out.println("HTMLMeter Poll is set to 50% when Set to 50% is selected in Select Dropdown");
        else
        {
        	System.out.println("HTMLMeter Poll is NOT set to 50% when Set to 50% is selected in Select Dropdown");
            Assert.fail("HTMLMeter Poll is NOT set to 50% when Set to 50% is selected in Select Dropdown");
        }
	}
	
	
	
	
	
	
}
