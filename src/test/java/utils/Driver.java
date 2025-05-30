package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class Driver {

    private static WebDriver driver;
    private static Process process;  //class seviyesinde tanımlanır.

    private Driver() {
    }//new keyword'ü ile başka class'lardan bu class'ın bir objesinin oluşturulmasının önüne geçer.

    public static WebDriver getDriver() {

        if (driver == null) {//Eğer driver null(oluşturulmamış)  ise yeni driver oluştur.

            String browser = ConfigReader.getProperty("browser");//configuration.properties dosyasında yazılı browser değerini  String olarak döner

            switch (browser) {//configuration.properties dosyasından gelen değere göre ilgili browser'ı açar.

                case "chrome":
                    // Chrome için sürücüyü ayarla ve oluştur
                    driver = new ChromeDriver();
                    break;
                case "chrome_headless":
                    // Başsız (Headless) Chrome için sürücüyü ayarla ve oluştur
                    driver = new ChromeDriver(new ChromeOptions().addArguments("--headless=new"));
                    break;
                case "firefox":
                    // Firefox için sürücüyü ayarla ve oluştur
                    driver = new FirefoxDriver();
                    break;
                case "ie":
                    // Internet Explorer için sürücüyü ayarla ve oluştur
                    if (!System.getProperty("os.name").toLowerCase().contains("windows")) {
                        throw new WebDriverException("Your OS doesn't support Internet Explorer");
                    }
                    driver = new InternetExplorerDriver();
                    break;
                case "edge":
                    // Edge için sürücüyü ayarla ve oluştur
                    if (!System.getProperty("os.name").toLowerCase().contains("windows")) {
                        throw new WebDriverException("Your OS doesn't support Edge");
                    }
                    driver = new EdgeDriver();
                    break;
                case "safari":
                    // Safari için sürücüyü ayarla ve oluştur
                    if (!System.getProperty("os.name").toLowerCase().contains("mac")) {
                        throw new WebDriverException("Your OS doesn't support Safari");
                    }
                    driver = new SafariDriver();
                    break;
                case "remote":

                    //kod ile terminalden selenium grid bağlantısı kurulur
                    try {
                        process = new ProcessBuilder().command("cmd.exe","/c","java -jar selenium-server-4.31.0.jar standalone").start();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    DesiredCapabilities capabilities = new DesiredCapabilities();
                    capabilities.setCapability("browserName","chrome");
                    //capabilities.setCapability("platformName", Platform.WIN11);
                    try {
                        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                default:
                    // Varsayılan olarak Chrome sürücüsünü ayarla ve oluştur
                    driver = new ChromeDriver();
                    break;
            }

            // Oluşturulan sürücü varsa pencereyi maksimize et ve bekleme süresini ayarla
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        }

        return driver;
    }


    public static void closeDriver() {

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (driver != null) {//Eğer driver null değil(oluşturulmuş) ise quit() metodunu kullan.
            driver.quit();
            driver = null;//quit() işlemi sonrası getDriver() methodunun tekrar çalışabilmsi için gerekli.
            process.destroy();
        }

    }

}