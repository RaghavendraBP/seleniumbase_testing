
package hooks;

import java.io.IOException;
import org.junit.Assert;
import org.openqa.selenium.WebDriver; 
import org.openqa.selenium.chrome.ChromeDriver; 
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import common.CommonUtilityUI;
import common.GetPropertyValues;
import io.cucumber.java.After; 
import io.cucumber.java.Before;

public class MyHooks {

	static WebDriver driver = null;

	@Before
	public void LaunchingBrowser() throws InterruptedException, IOException {
		GetPropertyValues properties = new GetPropertyValues();
		String browserValue = properties.getPropValues("browser");
		if(browserValue.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:/Users/004A57744/Workspace/seleniumbase_testing/src/test/resources/Drivers/chromedriver.exe"); 
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(options);
			CommonUtilityUI.ClearBrowserCacheForChrome(driver);
		} 
		else if(browserValue.equalsIgnoreCase("edge")){
			System.setProperty("webdriver.edge.driver", "C:/Users/004A57744/Workspace/seleniumbase_testing/src/test/resources/Drivers/edgedriver.exe");
			EdgeOptions edgeOptions = new EdgeOptions();
			
			edgeOptions.addArguments("--remote-allow-origins=*");
			driver = new EdgeDriver(edgeOptions);
			CommonUtilityUI.ClearBrowserCacheForEdge(driver);
		}
		else {
			System.out.println("Invalid value of browser is mentioned in the config.properties file");
			Assert.fail("Invalid value of browser is mentioned in the config.properties file");
		}

		driver.manage().window().maximize();
		Thread.sleep(5000);
	}


	@After
	public void closeBrowser() throws InterruptedException, IOException {
		driver.close();	}

	public static WebDriver getDriver() {
		return driver; 
	} 
}
