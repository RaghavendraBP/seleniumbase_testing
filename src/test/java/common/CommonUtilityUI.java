package common;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class CommonUtilityUI {

	public static void ClearBrowserCache(WebDriver driver) throws InterruptedException {
		Thread.sleep(4000);
		driver.manage().deleteAllCookies();
		driver.get("chrome://settings/clearBrowserData");
		Thread.sleep(5000);
		driver.switchTo().activeElement();
		driver.findElement(By.xpath("//settings-ui")).sendKeys(Keys.ENTER);
	}
	
	public static void ClearBrowserCacheForChrome(WebDriver driver) throws InterruptedException {
		Thread.sleep(4000);
		driver.manage().deleteAllCookies();
		driver.get("chrome://settings/clearBrowserData");
		Thread.sleep(5000);
		driver.switchTo().activeElement();
		driver.findElement(By.xpath("//settings-ui")).sendKeys(Keys.ENTER);
	}
	
	public static void ClearBrowserCacheForEdge(WebDriver driver) throws InterruptedException {
		Thread.sleep(4000);
		driver.manage().deleteAllCookies();
		driver.get("edge://settings/clearBrowserData");
		Thread.sleep(5000);
		driver.switchTo().activeElement();
		driver.findElement(By.xpath("//button[@id='clear-now']")).sendKeys(Keys.ENTER);
	}

	public static String convertFileIntoString(String file)throws Exception
	{
		String result;
		result = new String(Files.readAllBytes(Paths.get(file)));
		return result;
	}
}
