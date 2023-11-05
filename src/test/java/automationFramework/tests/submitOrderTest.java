package automationFramework.tests;

import automationFramework.TestComponents.BaseTest;
import automationFramework.pageobjects.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class submitOrderTest extends BaseTest {
    String itemName = "ZARA COAT 3";
    //Common Components like initialize drivers etc. all moved to baseTest
    //implicit wait transfered to baseTest  as common part
    //login

    @Test
    public void submitOrder() throws IOException,InterruptedException {

        ProductCatalogue productCatalogue = landingPage.loginApplication("testk@gmail.com", "Test@1234");
        //Explicit Wait

        List<WebElement> products = productCatalogue.getProductsList();
//      productCatalogue.addToCart(itemName);
        CartPage cartPage = productCatalogue.addToCart(itemName);
        productCatalogue.gotoCart();

        Boolean match = cartPage.verifyProduct(itemName);
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.gotoCheckout();

        String countryName = "INDIA";
        checkoutPage.selectCountry(countryName);
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();
        String validate = confirmationPage.validateOrder();
        Assert.assertTrue(validate.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    }

    @Test(dependsOnMethods = "submitOrder")
    public void orderHistoryCheck(){
        ProductCatalogue productCatalogue = landingPage.loginApplication("testk@gmail.com", "Test@1234");
        OrderPage orderPage = productCatalogue.gotoOrdersPage();
        Assert.assertTrue(orderPage.verifyOrder(itemName));
    }
}