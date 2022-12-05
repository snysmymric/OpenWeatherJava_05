package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

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
}
