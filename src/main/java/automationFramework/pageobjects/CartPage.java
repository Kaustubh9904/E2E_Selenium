package automationFramework.pageobjects;
import automationFramework.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponent {

    WebDriver driver;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Basic way to write |
    //WebElement userEmail = driver.findElement(By.id("userEmail"));

    //Cleaner way is by using Page factory
    @FindBy(xpath = "//*[@class='cartSection']/h3")
    List<WebElement> products;

    @FindBy(xpath = "//li[@class='totalRow']/button[@type='button']")
    WebElement checkoutElement;

    public List<WebElement> getProductsList(){
        return products;
    }

    public Boolean verifyProduct(String productName){
        Boolean match = getProductsList().stream().anyMatch(item->item.getText().equalsIgnoreCase(productName));
        return match;
    }
public CheckoutPage gotoCheckout(){
        checkoutElement.click();
        return new CheckoutPage(driver);
}

}
