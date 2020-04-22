import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class HerokuDropdown {

    WebDriver driver;


    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://the-internet.herokuapp.com/dropdown");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    //  Take all the elements of dropdown and check their availability
    //  Get first element and check that it is selected
    //  Get second element and check that it is selected
    @Test
    public void workWithDropdown() {
        Select select = new Select(driver.findElement(By.id("dropdown")));
        List<WebElement> listOfDropdownValues = select.getOptions();
        Assert.assertEquals(listOfDropdownValues.size(), 3, "Not all elements are presented in the dropdown");
        select.selectByVisibleText("Option 1");
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "Option 1", "Option 1 is not selected");
        select.selectByVisibleText("Option 2");
        Assert.assertTrue(driver.findElement(By.xpath("//select[@id='dropdown']/option[text()='Option 2']")).isSelected(), "Option 2 is not selected");

    }

    @AfterTest
    public void closeDriver() {
        driver.close();
    }


}
