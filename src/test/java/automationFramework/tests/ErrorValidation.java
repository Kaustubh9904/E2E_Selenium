package automationFramework.tests;

import automationFramework.TestComponents.BaseTest;
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

    @Test
    public void submitOrder() throws IOException, InterruptedException {
        String itemName = "ADIDAS ORIGINAL";
        landingPage.loginApplication("testl@gmail.com", "Test@1234");
        Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage());
    }
}