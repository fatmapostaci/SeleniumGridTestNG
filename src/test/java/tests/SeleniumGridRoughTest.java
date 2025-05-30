package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class SeleniumGridRoughTest {


    @Test
    public void dummyGridTest() throws InterruptedException {

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        //desiredCapabilities.setBrowserName("chrome");
        desiredCapabilities.setBrowserName("safari");
        //desiredCapabilities.setPlatform(Platform.WIN11);  //mac bilgisayara bağlanma hatası aldı
        // desiredCapabilities.setPlatform(Platform.MAC);   //
        WebDriver driver = null;
        try {
          //   driver = new RemoteWebDriver(new URL( "https://indexes-employers-wings-outlet.trycloudflare.com/wd/hub"),desiredCapabilities);
            driver = new RemoteWebDriver(new URL( "http://localhost:4444"),desiredCapabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://google.com");
        Assert.assertTrue(driver.findElement(By.name("q")).isDisplayed());
        driver.navigate().to("https://academy.clarusway.com");

        Thread.sleep(2000);

        driver.quit();

    }
}
