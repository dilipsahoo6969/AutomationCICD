package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {

	WebDriver driver;
	
	By productsBy = By.cssSelector(".mb-3");
	
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".mb-3")
	private List<WebElement> products;
	
	@FindBy(css = ".ng-animating")
	private WebElement spinner;
	
	private By addToCart = By.cssSelector(".card-body button:last-of-type");
	private By toastMessage = By.cssSelector("#toast-container");
	
	private List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return products;
	}
	
	private WebElement getProductByName(String productName) {
		for (WebElement product: getProductList()) {
            String nameOfTheProduct = product.findElement(By.cssSelector("b")).getText().trim();
            if (nameOfTheProduct.equalsIgnoreCase(productName)) {
                return product;
            }
	}
		throw new RuntimeException("Product not found: " + productName);
	}
	
	public void addProductToCart(String productName) {
		WebElement prod = getProductByName(productName);
		//prod.findElement(addToCart).click();
		WebElement addToCartBtn = prod.findElement(addToCart);
		JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].click();",addToCartBtn);
		waitForElementToAppear(toastMessage);
		waitForElementToDissaper(spinner);
	}
}
