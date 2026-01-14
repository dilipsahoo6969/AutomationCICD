package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent{

	WebDriver driver;
	
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "[placeholder = 'Select Country']")
	private WebElement countryDetails;
	
	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
	private WebElement selectCountry;
	
	@FindBy(css = ".action__submit")
	private WebElement button;
	
	By countryToBeVisible = By.cssSelector(".ta-results");
	
	public void selectCountryFromDropDown(String countryName) {
		Actions a = new Actions(driver);
        a.sendKeys(countryDetails, countryName).build().perform();
        waitForElementToBeVisible(countryToBeVisible);
        selectCountry.click();
	}
	
	public ConfirmationPage clickOnPlaceORder() {
		//Foe Scrolling purpose, used JavascriptExecutor. 
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].click();",button);
        return new ConfirmationPage(driver);

	}
}
