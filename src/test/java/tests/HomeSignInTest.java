package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomeSignInPage;

public class HomeSignInTest extends BaseTest {

    @Test
    public void testSignInMenuNavigateToSignInPage() {
        final String expectedWelcomeMassage = "Sign In To Your Account";

        String actualWelcomeMassage = openBaseURL_ReturnMainPage()
                .clickSignInMenu()
                .getWelcomeMassageOnSignInPage();

        Assert.assertEquals(actualWelcomeMassage, expectedWelcomeMassage);
    }
}
