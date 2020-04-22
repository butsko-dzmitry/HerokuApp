import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class HerokuTypos {

    WebDriver driver;


    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://the-internet.herokuapp.com/typos");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    // Check if there are any typos in the text.
    @Test
    public void checkTyposInText() {
        String actualText = driver.findElement(By.xpath("//p[contains(text(),\"Sometimes you'll see a typo, other times you won\")]")).getText();
        Assert.assertEquals(actualText, "Sometimes you'll see a typo, other times you won't.");
    }

    @AfterMethod
    public void closeDriver() {
        driver.close();
    }
}

