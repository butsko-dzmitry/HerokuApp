import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class HerokuTable {

    WebDriver driver;


    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://the-internet.herokuapp.com/tables");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    // Check tables content
    @Test
    public void checkTableValues() {
        getParameters("//table//tr[1]", "Smith", "John", "jsmith@gmail.com", "$50.00", "http://www.jsmith.com");
        getParameters("//table//tr[2]", "Bach", "Frank", "fbach@yahoo.com", "$51.00", "http://www.frank.com");
        getParameters("//table//tr[3]", "Doe", "Jason", "jdoe@hotmail.com", "$100.00", "http://www.jdoe.com");
        getParameters("//table//tr[4]", "Conway", "Tim", "tconway@earthlink.net", "$50.00", "http://www.timconway.com");
    }


    public void getParameters(String locator, String lastName, String firstName, String email, String due, String webSite) {
        Assert.assertEquals(driver.findElement(By.xpath(locator + "/td[1]")).getText(), lastName, "lastName is not equal");
        Assert.assertEquals(driver.findElement(By.xpath(locator + "/td[2]")).getText(), firstName, "firstName is not equal");
        Assert.assertEquals(driver.findElement(By.xpath(locator + "/td[3]")).getText(), email, "email is not equal");
        Assert.assertEquals(driver.findElement(By.xpath(locator + "/td[4]")).getText(), due, "due is not equal");
        Assert.assertEquals(driver.findElement(By.xpath(locator + "/td[5]")).getText(), webSite, "webSite is not equal");
    }


    @AfterMethod
    public void closeDriver() {
        driver.close();
    }
}
