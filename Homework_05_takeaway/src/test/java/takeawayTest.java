import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class takeawayTest {

    public WebDriver driver;

    @BeforeMethod
    public void setupBrowser() {
        System.setProperty("webdriver.chrome.driver", "C:\\WEB Browser Drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.takeaway.com/bg/");
        driver.manage().window().maximize();
    }

    @Test
    public void findDesiredRestaurantFromTakeaway () {

        WebElement enteringAddress = driver.findElement(By.cssSelector("#imysearchstring"));
//        enteringAddress.sendKeys("Славянска 2"); - НЕ СЕ ПОЛУЧИ ДА МУ ЗАДАМ ДРУГ АДРЕС! Не разбирам защо?

        WebElement cookiesAgreement = driver.findElement(By.xpath("/html/body/div[4]/section/article/button"));
        cookiesAgreement.click();

        WebElement submitDeliveryArea = driver.findElement(By.id("submit_deliveryarea"));
        submitDeliveryArea.click();

        WebDriverWait wait = new WebDriverWait(driver, 10);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"page\"]/div[4]/section/div[1]/div/div[1]/div/div[1]/div/div/div[2]/div/div[3]/button")));

        WebElement popUpclick = driver.findElement(By.xpath("//*[@id=\"page\"]/div[4]/section/div[1]/div/div[1]/div/div[1]/div/div/div[2]/div/div[3]/button"));
        popUpclick.click();

        WebElement inputNameOfTheRestaurant = driver.findElement(By.xpath("//*[@id=\"input_0\"]"));
        inputNameOfTheRestaurant.sendKeys("Gofretti Bar & Dinner|Гофрети Бар & Динър");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search-results-panel-restaurants")));

        WebElement desiredRestaurantConf = driver.findElement(By.id("search-results-panel-restaurants"));
        desiredRestaurantConf.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-qa=\"heading\"]")));

        WebElement confirmationOfDesiredRestaurant = driver.findElement(By.cssSelector("[data-qa=\"heading\"]"));
        String textToCompare = confirmationOfDesiredRestaurant.getText();
//        confirmationOfDesiredRestaurant.getText().equals("Gofretti Bar & Dinner|Гофрети Бар & Динър");
        Assert.assertEquals(textToCompare, "Gofretti Bar & Dinner|Гофрети Бар & Динър");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }


}
