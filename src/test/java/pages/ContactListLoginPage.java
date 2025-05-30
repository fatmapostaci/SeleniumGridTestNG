package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactListLoginPage {

    WebDriver driver;
    public ContactListLoginPage(WebDriver driver) {
        this.driver= driver;
    }

    By emailId = By.id("email");
    By passwordId = By.id("password");
    By submitId = By.id("submit");

    public ContactListLoginPage enterEmail(String email){
        driver.findElement(emailId).sendKeys(email);
        return this;
    }
    public ContactListLoginPage enterPassword(String pass){
        driver.findElement(passwordId).sendKeys(pass);
        return this;
    }
    public ContactListHomePage clickSubmit(){
        driver.findElement(submitId).click();
        return new ContactListHomePage(driver);
    }
}