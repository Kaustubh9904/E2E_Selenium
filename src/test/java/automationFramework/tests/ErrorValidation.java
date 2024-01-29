package automationFramework.tests;

import automationFramework.TestComponents.BaseTest;
import automationFramework.TestComponents.Retry;
import automationFramework.pageobjects.CartPage;
import automationFramework.pageobjects.CheckoutPage;
import automationFramework.pageobjects.ConfirmationPage;
import automationFramework.pageobjects.ProductCatalogue;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ErrorValidation extends BaseTest {

    //Common COmponents like initialize dricevrs etc all moved to baseTest
    //implicit wait trassfered to baseTest  as common part
    //login

    @Test(groups= {"ErrorHandling"},retryAnalyzer = Retry.class)
    public void LoginErrorValidation() throws IOException, InterruptedException {
        String itemName = "ADIDAS ORIGINAL";
        landingPage.loginApplication("testl@gmail.com", "Test@1234");
        Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage());
    }

    @Test(groups= {"ErrorHandling"})
    public void ProductErrorValidation() throws IOException, InterruptedException
    {

        String productName = "IPHONE 13 PRO";
        ProductCatalogue productCatalogue = landingPage.loginApplication("testk@gmail.com", "Test@1234");
        List<WebElement> products = productCatalogue.getProductsList();
        productCatalogue.addToCart(productName);
        CartPage cartPage = productCatalogue.gotoCart();
        Boolean match = cartPage.verifyProduct("NOKIA");
        Assert.assertFalse(match);

    }
}