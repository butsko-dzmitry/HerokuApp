import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class HerokuInput {

    WebDriver driver;


    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://the-internet.herokuapp.com/inputs");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void workWithCheckboxArrowUp() {
        driver.findElement(By.xpath("//div[@class='example']//input")).sendKeys(Keys.ARROW_UP);
        int currentInputValue = Integer.parseInt(driver.findElement(By.xpath("//div[@class='example']//input")).getAttribute("value"));
        Assert.assertEquals(currentInputValue, 1, "");
    }

    //  Number is decreasing with ARROW_DOWN
    @Test
    public void workWithCheckboxArrowDown() {
        driver.findElement(By.xpath("//div[@class='example']//input")).sendKeys(Keys.ARROW_DOWN);
        int currentInputValue = Integer.parseInt(driver.findElement(By.xpath("//div[@class='example']//input")).getAttribute("value"));
        Assert.assertEquals(currentInputValue, -1, "value is not correct");
    }

    @AfterMethod
    public void closeDriver() {
        driver.close();
    }
}
