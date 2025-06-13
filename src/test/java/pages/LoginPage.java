package pages;

import common.Common;
import common.GetPropertyValues;
import hooks.MyHooks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class LoginPage {
	
	WebDriver driver;
	
	By username = By.xpath("//input[contains(@type,'text')]");
	By password = By.xpath("//input[contains(@type,'password')]");
	By loginButton = By.xpath("//input[@value='Login']");
	By IWALogin = By.xpath("//input[@value='IWA Login']");
	By continueButton = By.xpath("//div[@class='notifyDiv textAlignCenter']//button[@class='button button--primary']");
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void typeUsername() throws InterruptedException {
		try {
			Thread.sleep(3000);
			Common.waitFor(driver, username);
			GetPropertyValues properties = new GetPropertyValues();
			driver.findElement(username).sendKeys(properties.getPropValues("userid"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void typePassword() {
		try {
			GetPropertyValues properties = new GetPropertyValues();
			String Decrypted_password = Common.decrypt(properties.getPropValues("password"));
			driver.findElement(password).sendKeys(Decrypted_password);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void clickLogin() {
		driver.findElement(loginButton).click();
	}

	public void IWALogin() {
		driver.findElement(IWALogin).click();
	}
	
	public void clickcontinueBtn() {
		driver.findElement(continueButton).click();
	}
	
	public void typeRetailerRoleUsername() throws InterruptedException {
		try {
			Thread.sleep(3000);
			Common.waitFor(driver, username);
			GetPropertyValues properties = new GetPropertyValues();
			driver.findElement(username).sendKeys(properties.getPropValues("RetailerRoleUserid"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void typeRetailerRolePassword() {
		try {
			GetPropertyValues properties = new GetPropertyValues();
			String Decrypted_password = Common.decrypt(properties.getPropValues("RetailerRolePassword"));
			driver.findElement(password).sendKeys(Decrypted_password);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	

}
