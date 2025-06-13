package stepDefinitions;


import hooks.MyHooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.PartsDeliveryNote;

import java.awt.AWTException;
import java.io.IOException;
import java.text.ParseException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import common.GetPropertyValues;

public class PartsDeliveryNoteStepDefinition {

	WebDriver driver=MyHooks.getDriver();
	PartsDeliveryNote partsDeliveryNote = new PartsDeliveryNote(driver);
	
		
	@Then("System displays the requested delivery note in Parts Despatch Note screen for {string} , {string}")
	public void validate_EPDN_Parts_Display_Note_Screen_Admin_Role(String DealerName, String DespatchNoteDateTime) throws InterruptedException, ParseException
	{
		partsDeliveryNote.display_delivery_note_in_Parts_Despatch_Note_screen_Admin_Role(DealerName, DespatchNoteDateTime);
	}
	
	@Then("Export functionality in Parts Despatch Note screen for {string} , {string} should be working as Expected when logged in with Admin Role in EPDN application")
	public void validate_Export_EPDN_Parts_Display_Note_Screen_Admin_Role(String DealerName, String DespatchNoteDateTime) throws InterruptedException, ParseException, AWTException
	{
		partsDeliveryNote.export_in_Parts_Despatch_Note_screen_Admin_Role(DealerName, DespatchNoteDateTime);
	}
	
	

}
