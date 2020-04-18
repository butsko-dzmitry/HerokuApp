import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class HerokuCheckbox {

    WebDriver driver;


    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://the-internet.herokuapp.com/checkboxes");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    //  Validate that the first checkbox is unchecked then check it. After that validate that the first checkbox is checked
    //  Validate that the second checkbox is checked then uncheck it. After that validate that the first checkbox is checked
    @Test
    public void workWithCheckbox() {
        // first checkbox
        Assert.assertFalse(driver.findElement(By.xpath("//form[@id='checkboxes']/input[1]")).isSelected(), "First checkbox is checked");
        driver.findElement(By.xpath("//form[@id='checkboxes']/input[1]")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//form[@id='checkboxes']/input[1]")).isSelected(), "First checkbox is unchecked");
        // second checkbox
        Assert.assertTrue(driver.findElement(By.xpath("//form[@id='checkboxes']/input[2]")).isSelected(), "Second checkbox is unchecked");
        driver.findElement(By.xpath("//form[@id='checkboxes']/input[2]")).click();
        Assert.assertFalse(driver.findElement(By.xpath("//form[@id='checkboxes']/input[2]")).isSelected(), "Second checkbox is checked");
    }

    @AfterTest
    public void closeDriver() {
        driver.close();
    }
}
