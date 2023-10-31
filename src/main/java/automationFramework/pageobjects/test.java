package automationFramework.pageobjects;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class test {
    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        //implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
        driver.get("https://developer.heartlandpaymentsystems.com/Documentation/tokenization-demo");

        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@src='https://jsfiddle.net/j01fs9et/embedded/result,js,html,css,resources/']")));
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@src='//fiddle.jshell.net/j01fs9et/show/light/']")));
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@src='https://hps.github.io/token/2.1/field.html#cardNumber:https%3A%2F%2Ffiddle.jshell.net%2Fj01fs9et%2Fshow%2Flight%2F']")));
//        driver.findElement(By.id("heartland-field")).sendKeys("1111 2222 3333 4444");
     driver.findElement(By.xpath("//input[@placeholder='•••• •••• •••• ••••']")).sendKeys("1234 5678 9999");
//        driver.quit();

    }
}
