package AndroidAppiumPackage;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
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

	public static AndroidDriver<AndroidElement> intializationTest(Boolean flag) throws MalformedURLException {
		// TODO Auto-generated method stub
		DesiredCapabilities dc = new DesiredCapabilities();
		if (!flag) {

		File f = new File("src");// first moving to main folder
		File fl = new File(f, "Android.SauceLabs.apk");// then mention file to open
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, "emulatorPixel");// open emulator
		dc.setCapability(MobileCapabilityType.APP, fl.getAbsolutePath());// invoke app using absolutepath
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		dc.setCapability(MobileCapabilityType.NO_RESET, true);
		dc.setCapability("appActivity", "com.swaglabsmobileapp.MainActivity");
		dc.setCapability("appPackage", "com.swaglabsmobileapp");
		// connection details and capabilities
		driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), dc);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		}
		else {
			
			dc.setCapability("browserstack.user", "??");
			dc.setCapability("browserstack.key", "??");

			// Set URL of the application under test
			dc.setCapability(MobileCapabilityType.APP, "bs://83edf470a2a09cd7e0552d008b975049c2451a85");

			// Specify device and os_version for testing
			dc.setCapability("device", "Google Pixel 3");
			dc.setCapability("os_version", "9.0");

			//driver = new AndroidDriver<>(new URL("http://hub.browserstack.com/wd/hubs"), dc);
			 AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(
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
			System.out.println("Searching Element not found");
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
			System.out.println("Searching Element not found");
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
			System.out.println("Searching Element not found");
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
			System.out.println("Searching Element not found");
		}

	}
	public  void scrollTilltext(String SearchText)
	
	{
		driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+SearchText+"\").instance(0)))"));

	}
	
	

}
