package automationFramework.pageobjects;
import automationFramework.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckoutPage extends AbstractComponent {

    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Basic way to write |
    //WebElement userEmail = driver.findElement(By.id("userEmail"));

    //Cleaner way is by using Page factory
    @FindBy(css = ".ta-item")
    List<WebElement> countries;

    @FindBy(xpath = "//input[@placeholder='Select Country']")
    WebElement countryName;

    @FindBy(css = ".btnn")
    WebElement placeOrder;

    public void selectCountry(String country){
        //we can use actions class to perform belowsteps too
        countryName.sendKeys(country);
        for(WebElement count:countries){
            if(count.getText().equalsIgnoreCase(country)){
                count.click();
                break;
            }
        }
}
public ConfirmationPage submitOrder(){
        placeOrder.click();
        return new ConfirmationPage(driver);
     }

}

