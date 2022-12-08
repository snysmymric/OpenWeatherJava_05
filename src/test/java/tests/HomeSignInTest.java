package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.HomeSignInPage;

import java.util.ArrayList;
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

    @Test
    public void testNamesUserDropdownMenuLinks() {
        List<String> expectedLinksTexts = new ArrayList<>();
        expectedLinksTexts.add("My services");
        expectedLinksTexts.add("My API keys");
        expectedLinksTexts.add("My payments");
        expectedLinksTexts.add("My profile");
        expectedLinksTexts.add("Logout");

        List<String> actualLinksTexts = openBaseURL()
                .signIn()
                .clickUserDropdown()
                .getTextUserDropDownMenuLInks();

        Assert.assertEquals(actualLinksTexts, expectedLinksTexts);
    }

    @Test
    public void testExpectedH2TitlesList() {
        List<String> expectedResult = List.of("Historical weather for any location", "Weather Dashboard",
                "Agricultural Dashboard and Agro API");

        openBaseURL().signIn();

        HomeSignInPage homeSignInPage = new HomeSignInPage(getDriver());

        Assert.assertEquals(homeSignInPage.listH2TitlesSighInPage(), expectedResult);

        homeSignInPage.signOut();
    }

    @Test
    public void testOrangeButtonsClickableAndVisible() {
        int expectedResult = 6;

        openBaseURL().signIn();

        HomeSignInPage homeSignInPage = new HomeSignInPage(getDriver());
        int actualResult = homeSignInPage.orangeButtonsSignIn();

        Assert.assertEquals(actualResult, expectedResult);

        homeSignInPage.signOut();
    }
}
