package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.data.DataReader;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckOutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;
import rahulshettyacademy.testcomponent.BaseTest;

public class SubmitOrderTest extends BaseTest{
	
	String productToBeSelected = "ADIDAS ORIGINAL"; 
	
	@Test(dataProvider = "getData")
	public void submitOrder(HashMap<String, String> input) throws IOException {

        ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
        productCatalogue.addProductToCart(input.get("product"));
        CartPage cartPage = productCatalogue.goToCartPage();
        boolean productFound = cartPage.verifyProductInCart(input.get("product"));
        Assert.assertTrue(productFound, "Product not found in the cart as expected");
        CheckOutPage checkOutPage = cartPage.chekOut();
        checkOutPage.selectCountryFromDropDown("India");
        ConfirmationPage confirmationPage = checkOutPage.clickOnPlaceORder();
        String confirmMessage = confirmationPage.getConfirmationMsg();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
    }
	
	@Test(dependsOnMethods = {"submitOrder"}, dataProvider = "getData")
	public void OrderHistoryTest(HashMap<String, String> input) throws IOException  {
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		OrderPage orderPage = productCatalogue.goToOrders();
		boolean productSelected = orderPage.verifyOrderDisplay(input.get("product"));
		Assert.assertTrue(productSelected, "Product not found in the Orders List");
	}
	
	@DataProvider
    public Object[][] getData() throws IOException {

         List<HashMap<String, String>> data = DataReader.getJsonData("PurchaseOrder.json");
         Object[][] obj = new Object[data.size()][1];
         
         for(int i=0; i<data.size(); i++) {
        	 obj[i][0] = data.get(i); 
         }
        return obj;
    }
}

//I have added some new comments for demo purpose for compliete CI/CD Integration
//Again changing for recheck CI/CD is auto triggering or not after commiting to GitHUB