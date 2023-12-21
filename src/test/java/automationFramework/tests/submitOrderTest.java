package automationFramework.tests;

import automationFramework.TestComponents.BaseTest;
import automationFramework.pageobjects.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
//import sun.nio.cs.UTF_8;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class submitOrderTest extends BaseTest {
    //Common Components like initialize drivers etc. all moved to baseTest
    //implicit wait transfered to baseTest  as common part
    //login

    @Test(dataProvider = "getData", groups = "Purchase")
    public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

        ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("passwd"));
        //Explicit Wait

        List<WebElement> products = productCatalogue.getProductsList();
//      productCatalogue.addToCart(itemName);
        CartPage cartPage = productCatalogue.addToCart(input.get("itemName"));
        productCatalogue.gotoCart();

        Boolean match = cartPage.verifyProduct(input.get("itemName"));
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.gotoCheckout();

        String countryName = "INDIA";
        checkoutPage.selectCountry(countryName);
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();
        String validate = confirmationPage.validateOrder();
        Assert.assertTrue(validate.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    }

    @Test(dependsOnMethods = "submitOrder", dataProvider = "getData")
    public void orderHistoryCheck(HashMap<String, String> input) {
        ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("passwd"));
        OrderPage orderPage = productCatalogue.gotoOrdersPage();
        Assert.assertTrue(orderPage.verifyOrder(input.get("itemName")));
    }

    @DataProvider
    public Object[][] getData() throws IOException {

//Method2
//        HashMap<String, String> map = new HashMap<String, String>();
//        map.put("email", "testk@gmail.com");
//        map.put("passwd", "Test@1234");
//        map.put("itemName", "ZARA COAT 3");
//Method1
//    @DataProvider
  //  public Object[][] getData() throws IOException{
//        return new Object[][] {{"testk@gmail.com","Test@1234","ZARA COAT 3" }};}

        List<HashMap<String, String>> data = getJSONDataToMap(System.getProperty("user.dir")+ "\\src\\test\\java\\automationFramework\\data\\PurchaseOrder.json");
        return new Object[][]  {{data.get(0)}};
    }

}