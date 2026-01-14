package rahulshettyacademy.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;
import rahulshettyacademy.testcomponent.BaseTest;
import rahulshettyacademy.testcomponent.Retry;

public class ErrorValidationTest extends BaseTest{
	
	@Test(groups = {"ErrorHandling"},retryAnalyzer = Retry.class)
	public void LoginErrorValidations() throws IOException {
		
		landingPage.loginApplication("dksad@rediff.com", "Johnda@12345");
        Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
    }
	
	@Test(groups = {"ErrorHandling"})
	public void productErrorValidations() throws IOException {
		
		String productToBeSelected = "ADIDAS ORIGINAL";           
        ProductCatalogue productCatalogue = landingPage.loginApplication("namrata@hotmail.com", "Dilip@1234");
        productCatalogue.addProductToCart(productToBeSelected);
        CartPage cartPage = productCatalogue.goToCartPage();
        boolean productFound = cartPage.verifyProductInCart("Zara Coat 3");
        Assert.assertFalse(productFound);
    }
}
