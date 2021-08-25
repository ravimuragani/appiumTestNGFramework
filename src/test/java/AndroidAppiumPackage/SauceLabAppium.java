package AndroidAppiumPackage;

import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class SauceLabAppium extends Base {

	public AndroidDriver<AndroidElement> driver;
	@Parameters({"flag"})
	@Test(enabled=true)
	public void purchaseFlow(Boolean flag) throws MalformedURLException {
		this.driver = intializationTest(flag);
		PomSauceLab pom = new PomSauceLab(driver);
		pom.loginusername.sendKeys("standard_user");
		pom.loginpassword.sendKeys("secret_sauce");
		pom.logInSubmit.click();
		addItemToCart("Shirt",
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Shirt\").instance(0))");
		addItemToCart("Bike",
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Bike\").instance(0))");
		pom.viewCart.click();
		rmvItemFrmCart("Shirt",
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Bike\").instance(0))");
		// scroll in to check out
		try {
			driver.findElement(MobileBy.AndroidUIAutomator(
					"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\"CHECKOUT\").instance(0))"));
			pom.chkOut.click();
			pom.addrsFrstNme.sendKeys("Ravi");
			pom.addrsLstNme.sendKeys("Appium");
			pom.addrsZip.sendKeys("123");
			pom.btnContinue.click();
			// scroll in to finish button
			try {
				driver.findElement(MobileBy.AndroidUIAutomator(
						"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\"FINISH\").instance(0))"));

				pom.btnFinish.click();
				System.out.println(pom.txtSuccess.getText());
			} catch (org.openqa.selenium.NoSuchElementException e) {
				System.out.println("Searching Element not found");
			}

		} catch (org.openqa.selenium.NoSuchElementException e) {
			System.out.println("Searching Element not found");
		}

		pom.menuList.click();
		pom.logOut.click();
		driver.quit();

	}
	@Parameters({"flag"})
	@Test(enabled=true)
	public void purchase_with_No_Item(Boolean flag) throws MalformedURLException {
		this.driver = intializationTest(flag);
		PomSauceLab pom = new PomSauceLab(driver);
		pom.loginusername.sendKeys("standard_user");
		pom.loginpassword.sendKeys("secret_sauce");
		pom.logInSubmit.click();
		pom.viewCart.click();
		int count = pom.itemsOnCartPage.size();
		if (count == 0) {
			System.out.println("Checkout should not be allowed if no items in cart");
			try {
				driver.findElement(MobileBy.AndroidUIAutomator(
						"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\"CHECKOUT\").instance(0))"));

				// scroll in to finish button

			} catch (org.openqa.selenium.NoSuchElementException e) {
				System.out.println("Searching Element not found");
			}
			assertEquals(pom.chkOut.isEnabled(), false);

		}

		pom.menuList.click();
		pom.logOut.click();
		driver.quit();
	}
	@Parameters({"flag"})
	@Test(enabled=true)
	public void purchase_chkout_validations(Boolean flag) throws MalformedURLException {
		this.driver = intializationTest(flag);
		PomSauceLab pom = new PomSauceLab(driver);
		pom.loginusername.sendKeys("standard_user");
		pom.loginpassword.sendKeys("secret_sauce");
		pom.logInSubmit.click();
		pom.viewCart.click();
		addItemToCart("Shirt",
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Shirt\").instance(0))");
		// scroll in to check out
		try {
			driver.findElement(MobileBy.AndroidUIAutomator(
					"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\"CHECKOUT\").instance(0))"));
			pom.chkOut.click();

			pom.btnContinue.click();
			assert (pom.errMsgChkOut.isDisplayed());
			System.out.println(pom.errMsgChkOut.getText());
			pom.addrsFrstNme.sendKeys("Ravi");
			pom.btnContinue.click();
			assert (pom.errMsgChkOut.isDisplayed());
			System.out.println(pom.errMsgChkOut.getText());
			pom.addrsLstNme.sendKeys("Appium");
			pom.btnContinue.click();
			assert (pom.errMsgChkOut.isDisplayed());
			System.out.println(pom.errMsgChkOut.getText());
			pom.addrsZip.sendKeys("123");
			pom.btnContinue.click();
			assert (pom.elmentChck.isDisplayed());

		} catch (org.openqa.selenium.NoSuchElementException e) {
			System.out.println("Searching Element not found");
		}

		pom.menuList.click();
		pom.logOut.click();
		driver.quit();
	}

}
