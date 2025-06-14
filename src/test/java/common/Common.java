
package common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Common {
	
	public static void waitFor(WebDriver driver, By element) 
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		System.out.println("The Element "+element+" is Visibile");
	}
	
	public static void waitTime(WebDriver driver, WebElement element) 
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		System.out.println("The Element "+element+" is Clickable");
	}
	
}
