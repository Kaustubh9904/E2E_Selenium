package automationFramework.stepDefinations;

import automationFramework.TestComponents.BaseTest;
import automationFramework.pageobjects.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class StepDefinations extends BaseTest {

    public LandingPage landingPage;
    public ProductCatalogue productCatalogue;
    public CartPage cartPage;
    public ConfirmationPage confirmationPage;
    @Given("I land on the Ecommerce page")
    public void I_land_on_th_Ecommerce_page() throws IOException {
        //code here
        landingPage = launchApplication();
    }

    @Given("^Logged in with username (.+) and password (.+)$")
    public void Logged_in_with_username_and_password(String email, String password){
        ProductCatalogue productCatalogue = landingPage.loginApplication(email,password);
    }

    @When("^I add productname (.+) from cart$")
    public void add_productname_from_cart(String itemName) throws InterruptedException {
        List<WebElement> products = productCatalogue.getProductsList();
        CartPage cartPage = productCatalogue.addToCart(itemName);
    }

    @When("I checkout the product (.+) and submit the order")
    public void checkout_and_submitOrder(String itemName) throws InterruptedException {
        productCatalogue.gotoCart();

        Boolean match = cartPage.verifyProduct(itemName);
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.gotoCheckout();

        String countryName = "INDIA";
        checkoutPage.selectCountry(countryName);
        confirmationPage = checkoutPage.submitOrder();
    }

    @Then("{String} is displayed on ConfirmationPage")
    public void verify_order_on_confirmationpage(String string){
        String validate = confirmationPage.validateOrder();
        Assert.assertTrue(validate.equalsIgnoreCase(string));
    }
}
