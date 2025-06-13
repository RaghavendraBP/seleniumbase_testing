package stepDefinitions;


import hooks.MyHooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.SeleniumBase;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import common.GetPropertyValues;


public class SeleniumBaseStepDefinition {

	WebDriver driver=MyHooks.getDriver();
	SeleniumBase seleniumBase = new SeleniumBase(driver);
	
	
	@Given("Navigate to Selenium Base Demo Page")
	public void navigate_to_URL() throws InterruptedException, IOException
	{
		GetPropertyValues properties = new GetPropertyValues();
		String URL = properties.getPropValues("url");
		driver.get(URL);
	}
	
	
	@Then("Requested functionalities for {string}, {string} and other fields should be working as Expected")
	public void validate_functionalities_in_Selenium_Base_Demo_Page(String TextInputField, String SelectDropdown) throws InterruptedException, IOException
	{
		seleniumBase.test_operations(TextInputField, SelectDropdown);
	}
	

}
