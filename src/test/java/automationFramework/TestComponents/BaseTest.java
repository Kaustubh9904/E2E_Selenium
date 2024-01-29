package automationFramework.TestComponents;

import automationFramework.pageobjects.LandingPage;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public abstract class BaseTest extends LogginUtility {
public WebDriver driver;
public LandingPage landingPage;

    public WebDriver initializeDriver() throws IOException {
        //Properties class to access properties from global.properties file
        Properties property = new Properties();
        //Stream Input taken of .properties file
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")
        + "//src//main//java//automationFramework//Resources//GlobalData.properties");
        property.load(fileInputStream);
        String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : property.getProperty("browser");
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


    public List<HashMap<String,String>> getJSONDataToMap(String filePath) throws IOException {
        //Step 1 is to read the string as shown below, need to type filetype encoding now at end
        String jsonContent = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
        //Next step is to create object mapper i.e string to hashmap - Jackson Bind dependency
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
        });
        return data;
    }
    public String getScreenshot(String testCaseName,WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
        FileUtils.copyFile(source,file);
        return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
    }
}
