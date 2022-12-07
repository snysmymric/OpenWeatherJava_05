package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.HomeSignInPage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeSignInTest extends BaseTest {

    @Test
    public void testSignInMenuNavigateToSignInPage() {
        final String expectedWelcomeMassage = "Sign In To Your Account";

        String actualWelcomeMassage = openBaseURL()
                .clickSignInMenu()
                .getWelcomeMassage();

        Assert.assertEquals(actualWelcomeMassage, expectedWelcomeMassage);
    }

    @Test
    public void testSignIn() {

        String actualSignInMessage = openBaseURL()
                .signIn()
                .getMessage();

        Assert.assertEquals(actualSignInMessage, "Signed in successfully.");

        String actualSignOutMessage = new HomePage(getDriver())
                .signOut()
                .getMessage();

        Assert.assertEquals(actualSignOutMessage, "You need to sign in or sign up before continuing.");
    }

    @Test
    public void testH2TitlesAmount() {
        int expectedH2TitlesAmount = 3;

        openBaseURL().signIn();

        HomeSignInPage homeSignInPage = new HomeSignInPage(getDriver());

        Assert.assertEquals(homeSignInPage.getH2Titles(), expectedH2TitlesAmount);

        homeSignInPage.signOut();
    }
}
