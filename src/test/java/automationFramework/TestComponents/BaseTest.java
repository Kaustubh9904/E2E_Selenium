package automationFramework.TestComponents;

import automationFramework.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {
public WebDriver driver;
public LandingPage landingPage;

    public WebDriver initializeDriver() throws IOException {
        //Properties class to access properties from global.properties file
        Properties property = new Properties();
        //Stream Input taken of .properties file
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")
        + "//src//main//java//automationFramework//Resources//GlobalData.properties");
        property.load(fileInputStream);
        String browserName = property.getProperty("browser");
        if(browserName.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            EdgeOptions options = new EdgeOptions();
            //options.addArguments("--headless=new");
            driver = new EdgeDriver(options);
        }else if(browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            //options.addArguments("--headless=new");
            driver = new ChromeDriver(options);
        }else if(browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;
    }
    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApplication() throws IOException {
        WebDriver driver = initializeDriver();
        landingPage = new LandingPage(driver);
        landingPage.goTo();
        return landingPage;
    }

    @AfterMethod(alwaysRun = true)
    public void teardown(){
        driver.close();
    }
}
