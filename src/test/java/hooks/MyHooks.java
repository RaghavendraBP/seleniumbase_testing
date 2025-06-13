
package hooks;

import java.io.IOException;
import java.net.MalformedURLException; 
import java.net.URL;

import org.junit.Assert;
import org.openqa.selenium.UnexpectedAlertBehaviour; 
import org.openqa.selenium.WebDriver; 
import org.openqa.selenium.chrome.ChromeDriver; 
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.CapabilityType; 
import org.openqa.selenium.remote.RemoteWebDriver;

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


	/*@SuppressWarnings("deprecation")
	@Before
	public void LaunchingBrowserinSeleniumBox() throws InterruptedException, MalformedURLException {
 
		ChromeOptions options = new ChromeOptions();
 
		//Specify the version of Chrome that Selenium Box should use (If blank it will use the latest)
		options.setCapability(CapabilityType.BROWSER_VERSION,"");
 
		//Specify Specific playback options (e.g. Maximised window, disable pop-upsand extensions etc)
		options.addArguments("start-maximized");
		options.addArguments("--test-type=browser");
		options.addArguments("--disable-popup-blocking");
		options.addArguments("--disable-extensions");
		options.setExperimentalOption("useAutomationExtension", false);
		options.setAcceptInsecureCerts(true);
		options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
 
		//Set the Token to be used based on the project (Access Management)
		options.setCapability("e34:token", "60cdea51-75b8-4a");
		options.setCapability("e34:acceptInsecureCerts", true);
		options.setCapability("acceptInsecureCerts", true);
 
		//-------------------------------Setup the Proxy Configuration to use-------------------------------
		//Read the current environment OperatingSystem - Check if OpenShift in this case
		String OperatingSystem = System.getProperty("os.name");
		System.out.println("Detected Operating System: " + OperatingSystem);
 
		//If its NOT Windows 10, then assume this is running on the CI server
 
		if(!OperatingSystem.equalsIgnoreCase("Windows 10")) {
 
		}
 
		options.setCapability("e34:video", true);
		driver = new RemoteWebDriver(new URL("https://seleniumbox.bmwgroup.net/wd/hub"), options);
		CommonUtilityUI.ClearBrowserCacheForChrome(driver);
		driver.manage().window().maximize();
		Thread.sleep(5000);
	}*/

		/*@SuppressWarnings("deprecation")
		@Before 
		public void LaunchingEdgeBrowserinSeleniumBox() throws InterruptedException, MalformedURLException {
	
			EdgeOptions options = new EdgeOptions();
			
			String download_Directory_path = "C:/Users/QXZ43QL/Downloads";

			options.addArguments("--test-type=browser");
			options.addArguments("--disable-popup-blocking");
			options.addArguments("--disable-extensions");
			options.addArguments("download.default_directory=" +download_Directory_path);
			options.setExperimentalOption("useAutomationExtension", false);
			options.setAcceptInsecureCerts(true);
			options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
			// Set the Token to be used based on the project (Access Management)    options.setCapability("e34:token", "b6883095-6f91-46");
			options.setCapability("e34:token", "60cdea51-75b8-4a");
			options.setCapability("e34:acceptInsecureCerts", true);
	
			//-------------------------------Setup the Proxy Configuration to use-------------------------------// Read the current environment Operating System - Check if OpenShift in this caseString OperatingSystem = System.getProperty("os.name");
			String OperatingSystem = System.getProperty("os.name");
			System.out.println("Detected Operating System: " + OperatingSystem);
	
			// If it's NOT Windows 10, then assume this is running on the CI serverif (!OperatingSystem.equalsIgnoreCase("Windows 10")) {
			// Add your CI server specific configurations here    }
			options.setCapability("e34:video", true);
	
			driver = new RemoteWebDriver(new URL("https://seleniumbox.bmwgroup.net/wd/hub"), options);
			CommonUtilityUI.ClearBrowserCacheForEdge(driver);
			driver.manage().window().maximize(); 
			Thread.sleep(5000);
		}*/

	// Hooks for Tear Down that means closing the browser
//
//	@After public void ClosingBrowser() throws InterruptedException {
//		Thread.sleep(2000); 
//		if (driver != null) 
//		{ 
//			driver.quit(); 
//		}
//	}
	
	@After
	public void closeBrowser() throws InterruptedException, IOException {
		driver.close();	}

	public static WebDriver getDriver() {
		return driver; 
	} 
}
