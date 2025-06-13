package stepDefinitions;


import hooks.MyHooks;
import io.cucumber.java.en.Given;
import pages.LoginPage;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import common.GetPropertyValues;

public class LoginStepDefinition {

	WebDriver driver;
	LoginPage login;

	static String HOMEPAGE_URL = "https://int-web-epdn-ml8.bmwgroup.net/web-epdn/";

	@Given("Login to the Electronic Parts Delivery Note EPDN application in {string} Environment with Admin Role")
	public void Login_to_EPDN_application_Admin_Role(String Env) throws IOException, InterruptedException 
	{
		driver = MyHooks.getDriver();
		login= new LoginPage(driver);
		GetPropertyValues properties = new GetPropertyValues();
		String URL = properties.getPropValues("url");
		driver.get(URL);
		Thread.sleep(5000);
		
	}
	
	
	

}
