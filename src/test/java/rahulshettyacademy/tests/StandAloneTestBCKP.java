package rahulshettyacademy.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StandAloneTestBCKP {
	public static void main(String[] args) {

        String productToBeSelected = "ZARA COAT 3";
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/client/#/auth/login");

        driver.findElement(By.id("userEmail")).sendKeys("dks@rediff.com");
        driver.findElement(By.id("userPassword")).sendKeys("John@12345");
        driver.findElement(By.id("login")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

        List <WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

        for (WebElement product: products) {
            String productName = product.findElement(By.cssSelector("b")).getText();
            if (productName.equalsIgnoreCase(productToBeSelected)) {
                product.findElement(By.cssSelector(".card-body button:last-of-type")).click();
                break;
            }
        }

        //		WebElement prod = products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase("ZARA COAT 3")).findFirst().orElse(null);
        //		
        //		prod.findElement(By.cssSelector(".w-10")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

        boolean productFound = false;

        List < WebElement > cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
        for (WebElement cartProduct: cartProducts) {
            String productInTheCart = cartProduct.getText();
            if (productInTheCart.equalsIgnoreCase(productToBeSelected)) {
                productFound = true;
                driver.findElement(By.cssSelector(".totalRow button")).click();
                break;
            }
        }

        Assert.assertTrue(productFound, "Product not found in the cart as expected");
        //		
        //		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
        //
        //		boolean match = cartProducts.stream()
        //		        .anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productToBeSelected));
        //
        //		Assert.assertTrue(match);
        //		driver.findElement(By.cssSelector(".totalRow button")).click();

        Actions a = new Actions(driver);
        a.sendKeys(driver.findElement(By.cssSelector("[placeholder = 'Select Country']")), "India").build().perform();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ta-results")));
        driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();

        //Foe Scrolling purpose, used JavascriptExecutor. 
        WebElement button = driver.findElement(By.cssSelector(".action__submit"));
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].click();",button);

        String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
        driver.quit();
    }
}
