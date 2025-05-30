package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ContactListHomePage {
    WebDriver driver;
    WebDriverWait wait;

    public ContactListHomePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    By addContactButtonId = By.id("add-contact");

    By logoutButtonId = By.id("logout");

    public boolean isAddButtonDisplayed(){

        WebElement addButton = wait.until(ExpectedConditions.visibilityOfElementLocated(addContactButtonId));
        return addButton.isDisplayed();
    }

    public boolean isLogoutButtonDisplayed(){
        WebElement logoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(logoutButtonId));
        return logoutButton.isDisplayed();
    }
}