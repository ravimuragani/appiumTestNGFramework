package AndroidAppiumPackage;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AndroidFindBySet;
import io.appium.java_client.pagefactory.AndroidFindBys;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class PomSauceLab {

//constructor
	public PomSauceLab(AppiumDriver<AndroidElement> driver)

	{

		PageFactory.initElements(new AppiumFieldDecorator(driver), this);// provides compatability for both ios/andriod
	}

	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Username']")
	public WebElement loginusername;

	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Password']")
	public WebElement loginpassword;

	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-LOGIN']")
	public WebElement logInSubmit;

	@AndroidFindBy(xpath = "//android.widget.ScrollView[@content-desc='test-PRODUCTS']/android.view.ViewGroup/android.view.ViewGroup")
	public List<WebElement> itemsOnlistingPage;
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Item']")
	public List<WebElement> itemsOnCartPage;

	// @AndroidFindBy(xpath =
	// "//android.view.ViewGroup[@content-desc='test-Description']")
	// public List<WebElement> rmvPrdctParent;

	// public List<WebElement> rmvPrdctName=((WebElement)
	// rmvPrdctParent).findElements(MobileBy.className("android.widget.TextView"));

	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='test-Item title']")
	public List<WebElement> addPrdctName;

	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-ADD TO CART']")
	public List<WebElement> addToCart;

	@FindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Cart']/android.view.ViewGroup")
	public WebElement viewCart;

	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-REMOVE']")
	public List<WebElement> rmvFrmCart;

	//@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-CHECKOUT']")
	//public WebElement chkOut;

	@AndroidFindBy(xpath = "//android.widget.EditText[@text='First Name']")
	public WebElement addrsFrstNme;

	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Last Name']")
	public WebElement addrsLstNme;

	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Zip/Postal Code']")
	public WebElement addrsZip;

	@AndroidFindBy(accessibility = "test-CONTINUE")
	public WebElement btnContinue;

	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-FINISH']")
	public WebElement btnFinish;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='THANK YOU FOR YOU ORDER']")
	public WebElement txtSuccess;

	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Menu']/android.view.ViewGroup")
	public WebElement menuList;

	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-LOGOUT']")
	public WebElement logOut;

	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Error message']/android.widget.TextView")
	public WebElement errMsgChkOut;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='CHECKOUT: OVERVIEW']")
	public WebElement elmentChck;
	
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-CHECKOUT']")
	public WebElement btnChkOut;

}
