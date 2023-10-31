package automationFramework.pageobjects;
import automationFramework.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage extends AbstractComponent {

    WebDriver driver;

    public OrderPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Basic way to write |
    //WebElement userEmail = driver.findElement(By.id("userEmail"));

    //Cleaner way is by using Page factory
    @FindBy(css = "tr td:nth-child(3)")
    List<WebElement> productsList;

    public List<WebElement> getProductsList(){
        return productsList;
    }

    public Boolean verifyOrder(String productName){
        Boolean match = getProductsList().stream().anyMatch(item->item.getText().equalsIgnoreCase(productName));
        return match;
    }

}
