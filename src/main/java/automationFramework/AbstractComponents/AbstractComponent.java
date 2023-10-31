package automationFramework.AbstractComponents;

import automationFramework.pageobjects.CartPage;
import automationFramework.pageobjects.OrderPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponent {

    @FindBy(css = "[routerlink*='cart']")
    WebElement submitCart;

    @FindBy(css = "[routerlink*='myorders']")
    WebElement orderPage;

    WebDriver driver;
    public AbstractComponent(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void waitForElementToAppear(By findBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public void waitForElementToDissapear(WebElement ele) throws InterruptedException {
        Thread.sleep(1000);
        //gave thread.sleep cuz rahulshetty website had internal issues causing delay nonetheless with tyhe below two lines,
        //it worked but was just slow.
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//        wait.until(ExpectedConditions.invisibilityOf(ele));
    }
    public void waitForWebElementToBeVisible(WebElement findBy){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(findBy));
    }

    public void waitForElementToBeClickable(By findBy){
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    wait.until(ExpectedConditions.elementToBeClickable(findBy));
}

    public CartPage gotoCart(){
        submitCart.click();
        CartPage cartPage = new CartPage(driver);
        return cartPage;
    }

    public OrderPage gotoOrdersPage(){
        orderPage.click();
        OrderPage orderPage = new OrderPage(driver);
        return orderPage;
    }

}
