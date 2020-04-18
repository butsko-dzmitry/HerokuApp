import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class HerokuNotification {

    WebDriver driver;


    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://the-internet.herokuapp.com/notification_message_rendered");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    // Click on the button and validate that notification with proper text is displayed
    @Test
    public void validateActionMessage() {
        driver.findElement(By.linkText("Click here")).click();
        String actualText = driver.findElement(By.id("flash")).getText();
        Assert.assertEquals(actualText, "Action successful\n" + "×");
    }

    @AfterMethod
    public void closeDriver() {
        driver.close();
    }
}
