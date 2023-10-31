package automationFramework.tests;

import automationFramework.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class standAloneTest_original {
    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        //implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/client");
        //login
        driver.findElement(By.id("userEmail")).sendKeys("testk@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Test@1234");
        driver.findElement(By.xpath("//input[@value='Login']")).click();

        //Explicit Wait
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

        String itemName="ADIDAS ORIGINAL";
       List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
        WebElement prod = products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(itemName)).findFirst().orElse(null);
        prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
        List<WebElement> cartItems =driver.findElements(By.xpath("//*[@class='cartSection']/h3"));
        Boolean match = cartItems.stream().anyMatch(item->item.getText().equalsIgnoreCase(itemName));
        Assert.assertTrue(match);
        driver.findElement(By.xpath("//li[@class='totalRow']/button[@type='button']")).click();
        driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("IND");
        List<WebElement> countries = driver.findElements(By.cssSelector(".ta-item"));
        for(WebElement country:countries){
            if(country.getText().equalsIgnoreCase("India")){
                country.click();
                break;
            }
        }
        driver.findElement(By.cssSelector(".btnn")).click();
        String validate = driver.findElement(By.cssSelector(".hero-primary")).getText();
        System.out.println(validate);
        Assert.assertTrue(validate.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        driver.quit();
    }
}
