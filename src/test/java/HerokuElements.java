import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class HerokuElements {

    WebDriver driver;


    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://the-internet.herokuapp.com/add_remove_elements/");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    // Add 2 elements, delete 1 element, check the number of elements
    @Test
    public void AddRemoveElements() {
        driver.findElement(By.xpath("//button[contains(text(),'Add Element')]")).click();
        driver.findElement(By.xpath("//button[contains(text(),'Add Element')]")).click();
        driver.findElement(By.xpath("//body//button[2]")).click();
        List<WebElement> listOfElements = driver.findElements(By.xpath("//button[@class='added-manually']"));
        Assert.assertEquals(listOfElements.size(), 1, "Number of added elements is not equals to 1");
    }

    @AfterTest
    public void closeDriver() {
        driver.close();
    }
}

