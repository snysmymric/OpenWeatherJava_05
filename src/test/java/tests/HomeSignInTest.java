package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.home.HomePage;
import pages.home.HomeSignInPage;

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

        new HomeSignInPage(getDriver()).signOut();
        String actualSignOutMessage = new HomePage(getDriver()).getMessage();

        Assert.assertEquals(actualSignOutMessage, "You need to sign in or sign up before continuing.");
    }

    @Test
    public void testH2TitlesAmount() {
        int expectedH2TitlesAmount = 3;

        openBaseURL().signIn();

        Assert.assertEquals(new HomePage(getDriver()).getH2Titles(), expectedH2TitlesAmount);

        new HomeSignInPage(getDriver()).signOut();
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

        Assert.assertEquals(new HomePage(getDriver()).listH2TitlesSighInPage(), expectedResult);

        new HomeSignInPage(getDriver()).signOut();
    }

    @Test
    public void testOrangeButtonsClickableAndVisible() {
        int expectedResult = 6;

        openBaseURL().signIn();
        int actualResult = new HomePage(getDriver()).orangeButtonsSignIn();

        Assert.assertEquals(actualResult, expectedResult);

        new HomeSignInPage(getDriver()).signOut();
    }

    @Test
    public void testNavTabsClickableAndVisible() {
        int expectedNavTabElements = 9;
        List<String> expectedResultListElements = List.of("New Products", "Services", "API keys",
                "Billing plans", "Payments", "Block logs", "My orders", "My profile", "Ask a question");


        openBaseURL().signIn();

        HomePage homePage = new HomePage(getDriver());
        int actualResultNavTabElements = homePage.navTabs();

        Assert.assertEquals(expectedNavTabElements, actualResultNavTabElements);
        Assert.assertEquals(expectedResultListElements, homePage.listNavTabElements());

        homePage.signOut();
    }
}
