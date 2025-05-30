package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ContactListHomePage;
import pages.ContactListLoginPage;
import utils.ConfigReader;

import static utils.ConfigReader.getProperty;
import static utils.Driver.closeDriver;
import static utils.Driver.getDriver;

public class GridTestWithDriverClass {

    @Test
    public void gridTest() throws InterruptedException {

        getDriver().get(getProperty("contact_list_url"));

        ContactListLoginPage loginPage = new ContactListLoginPage(getDriver());
        ContactListHomePage homePage = new ContactListHomePage(getDriver());
        Thread.sleep(3000);
        loginPage.enterEmail(getProperty("contact_list_username"))
                .enterPassword(getProperty("contact_list_password"))
                .clickSubmit();
        Thread.sleep(3000);
        Assert.assertTrue(homePage.isAddButtonDisplayed());
        Assert.assertTrue(homePage.isLogoutButtonDisplayed());
        Assert.assertTrue(getDriver().getTitle().contains("Contacts"));
        Thread.sleep(3000);
        closeDriver();
    }
}
