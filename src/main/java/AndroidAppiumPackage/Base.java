package AndroidAppiumPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class Base {

	public static AndroidDriver<AndroidElement> driver;

	public static AndroidDriver<AndroidElement> intializationTest(Boolean flag) throws IOException {
		// TODO Auto-generated method stub
		DesiredCapabilities dc = new DesiredCapabilities();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\global.properties");
		Properties prop = new Properties();
		prop.load(fis);
		if (!flag) {
			
		File f = new File(System.getProperty("user.dir"));// first moving to main folder
		File fl = new File(f, "Android.SauceLabs.apk");// then mention file to open
		dc.setCapability(MobileCapabilityType.DEVICE_NAME,  prop.get("localdevice"));// invoke emulator
		dc.setCapability(MobileCapabilityType.APP, fl.getAbsolutePath());// invoke app using absolutepath
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, prop.get("AUTOMATION_NAME"));
		dc.setCapability(MobileCapabilityType.NO_RESET, true);
		dc.setCapability("appActivity", prop.get("sauceLabAppappPackage"));
		dc.setCapability("appPackage", prop.get("sauceLabAppappActivity"));
		// connection details and capabilities
		driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), dc);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		}
		else {
			
			dc.setCapability("browserstack.user", prop.get("cloudUser"));
			dc.setCapability("browserstack.key", prop.get("cloudKey"));

			// Set URL of the application under test
			dc.setCapability(MobileCapabilityType.APP, prop.get("sauceLabAppAppUrl"));

			// Specify device and os_version for testing
			dc.setCapability("device", prop.get("cloudDevice"));
			dc.setCapability("os_version", prop.get("cloudOSversion"));

			//driver = new AndroidDriver<>(new URL("http://hub.browserstack.com/wd/hubs"), dc);
			 driver = new AndroidDriver<AndroidElement>(
			            new URL("http://hub.browserstack.com/wd/hub"), dc);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
		}
		return driver;

	}

	public void addItemToCart(String SearchText, String scroll) {
		PomSauceLab pom = new PomSauceLab(driver);

		try {
			driver.findElement(MobileBy.AndroidUIAutomator(scroll));
			int count = pom.itemsOnlistingPage.size();
			System.out.println("Items displaying in page "+count);
			String productName;

			for (int i = 0; i < count; i++) {

				productName = pom.addPrdctName.get(i).getText();
				System.out.println("Item name present in page "+productName);
				if (productName.contains(SearchText)) {
					pom.addToCart.get(i).click();
					System.out.println(productName+" Item added");
					break;

				}

			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			System.err.println("Searching Element not found"+e);
		}

	}

	@SuppressWarnings("rawtypes")
	public void rmvItemFrmCart(String SearchText, String scroll) {
		PomSauceLab pom = new PomSauceLab(driver);
		try {
			driver.findElement(MobileBy.AndroidUIAutomator(scroll));
			int count = pom.itemsOnCartPage.size();
			System.out.println("Items present in Page "+count);
			String productName;
			WebElement parent = ((AndroidDriver) driver).findElement(MobileBy.xpath("//android.widget.ScrollView[@content-desc='test-Cart Content']/android.view.ViewGroup"));



			for (int i = 0; i < count; i++) {
		
				productName = parent.findElements(MobileBy.xpath("//android.view.ViewGroup[@content-desc='test-Description']/android.widget.TextView")).get(i).getText();
				System.out.println("Item name present in page "+productName);
				if (productName.contains(SearchText)) {
					pom.rmvFrmCart.get(i).click();
					System.out.println(productName+" Item removed");
					break;

				}

			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			System.err.println("Searching Element not found"+e);
		}

	}
	public  void sauceLabAddItemToCart(String SearchText) {
		PomSauceLab pom = new PomSauceLab(driver);

		try {
			
			scrollTilltext(SearchText);

			driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+SearchText+"\").instance(0)))"));
			
			int count = pom.itemsOnlistingPage.size();
			System.out.println("Items displaying in page "+count);
			String productName;

			for (int i = 0; i < count; i++) {

				productName = pom.addPrdctName.get(i).getText();
				System.out.println("Item name present in page "+productName);
				if (productName.contains(SearchText)) {
					pom.addToCart.get(i).click();
					System.out.println(productName+" Item added");
					break;

				}

			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			System.err.println("Searching Element not found"+e);
		}

	}

	public  void sauceLabRmvItemFrmCart(String SearchText) {
		PomSauceLab pom = new PomSauceLab(driver);
		try {
			
			scrollTilltext(SearchText);
			//driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+SearchText+"\").instance(0)))"));
			int count = pom.itemsOnCartPage.size();
			System.out.println("Items present in Page "+count);
			String productName;
			WebElement parent = ((AndroidDriver) driver).findElement(MobileBy.xpath("//android.widget.ScrollView[@content-desc='test-Cart Content']/android.view.ViewGroup"));

			for (int i = 0; i < count; i++) {

				productName = parent.findElements(MobileBy.xpath("//android.view.ViewGroup[@content-desc='test-Description']/android.widget.TextView")).get(i).getText();
				System.out.println("Item name present in page "+productName);
				if (productName.contains(SearchText)) {
					pom.rmvFrmCart.get(i).click();
					System.out.println(productName+" Item removed");
					break;

				}

			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			System.err.println("Searching Element not found"+e);
		}

	}
	public  void scrollTilltext(String SearchText)
	
	{
		driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+SearchText+"\").instance(0)))"));

	}
	
	

}
