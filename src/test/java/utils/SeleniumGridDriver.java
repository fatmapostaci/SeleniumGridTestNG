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

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;


public class SeleniumGridDriver {
    // Her bir thread için ayrı bir WebDriver örneğini yönetmek için ThreadLocal kullanılır.
    private static final ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();

    public SeleniumGridDriver() {
    }

    public static WebDriver getDriver() {
        // Eğer sürücü havuzunda bir sürücü yoksa
        if (driverPool.get() == null) {
            // Ortam değişkenlerinden tarayıcı parametresini al
            String browserParamFromEnv = System.getProperty("browser");
            // Eğer ortam değişkeni yoksa varsayılan değeri al
            String browser = (browserParamFromEnv == null) ? ConfigReader.getProperty("browser") : browserParamFromEnv;

            WebDriver driver = null;

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName(browser);
            try {
                driver = new RemoteWebDriver(new URL("http://localhost:4444"),capabilities);
//                    driver = new RemoteWebDriver(new URL("https://indexes-employers-wings-outlet.trycloudflare.com/wd/hub"),capabilities);  süleyman hocanun tunnel ile açtığı locali
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }

            // Oluşturulan sürücü varsa pencereyi maksimize et ve bekleme süresini ayarla
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driverPool.set(driver); // Sürücüyü havuza ekle
        }

        return driverPool.get(); // Oluşturulan veya mevcut sürücüyü döndür
    }

    // Mevcut WebDriver örneğini kapatır ve temizler.
    public static void closeDriver() {
        WebDriver driver = driverPool.get();
        if (driver != null) {
            driver.quit();
            driverPool.remove();
        }
    }
}