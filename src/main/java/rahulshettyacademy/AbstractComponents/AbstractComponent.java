package rahulshettyacademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.OrderPage;

public class AbstractComponent {
	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(css = "[routerlink*='cart']")
	private WebElement cartHeader;
	
	@FindBy(css = "[routerlink*='myorders']")
	private WebElement orderHeader;
	
	@FindBy(css = ".ngx-spinner-overlay")
	private WebElement spinner;


	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}
	
	public void waitForElementToAppear(By findBy) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementToAppear(WebElement ele) {
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public void waitForElementToDissaper(WebElement ele) {
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	public CartPage goToCartPage() {
		waitForElementToDissaper(spinner);
		waitAndClick(cartHeader);
		return new CartPage(driver);
	}
	
	public OrderPage goToOrders() {
		orderHeader.click();
		return new OrderPage(driver);
	}
	
	public void waitForElementToBeVisible(By countryToBeVisible) {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(countryToBeVisible));
	}
	
	public void waitAndClick(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

}
