package automationFramework.pageobjects;

import automationFramework.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponent {

    WebDriver driver;

    public LandingPage(WebDriver driver){
        super(driver);
        this.driver= driver;
        PageFactory.initElements(this.driver,this);
    }

    //Basic way to write |
    //WebElement userEmail = driver.findElement(By.id("userEmail"));

    //Cleaner way is by using Page factory
    @FindBy(id = "userEmail")
    WebElement userEmail;

    @FindBy(id="userPassword")
    WebElement userPassword;

    @FindBy(xpath="//input[@value='Login']")
    WebElement login;

    @FindBy(css = "[class*='flyInOut']")
    WebElement errorMessage;

    public ProductCatalogue loginApplication(String email,String passwd){
    userEmail.sendKeys(email);
    userPassword.sendKeys(passwd);
    login.click();
    ProductCatalogue productCatalogue = new ProductCatalogue(driver);
    return productCatalogue;
    }

    public void goTo(){
        driver.get("https://rahulshettyacademy.com/client");
    }

    public String getErrorMessage(){
        waitForWebElementToBeVisible(errorMessage);
        return errorMessage.getText();
    }
}
