package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.APIPage;
import pages.GuidePage;
import pages.MainPage;
import pages.OurInitiativesPage;

public class TopMenuTest extends BaseTest {

    @Test
    public void testAPIMenuNavigatesToAPIPage() {
        final String expectedURL = "https://openweathermap.org/api";

        openBaseURL();

        MainPage mainPage = new MainPage(getDriver());

        mainPage.clickAPIMenu();

        APIPage apiPage = new APIPage(getDriver());

        Assert.assertEquals(apiPage.getCurrentURL(), expectedURL);
    }

    @Test
    public void testMarketplaceMenuNavigatesToMarketplacePage() {
        final String expectedURL = "https://home.openweathermap.org/marketplace";

        openBaseURL();

        MainPage mainPage = new MainPage(getDriver());

        mainPage.clickMarketplaceMenu();

        switchToAnotherWindow(getDriver());

        Assert.assertEquals(getCurrentURL(), expectedURL);
    }

    @Test
    public void testLinkAndTitle_WhenGoingToGuideMenu() {
        final String expectedUrl = "https://openweathermap.org/guide";
        final String expectedTitle = "OpenWeatherMap API guide - OpenWeatherMap";

        GuidePage guidePage = openBaseURL_ReturnMainPage().clickGuideMenu();

        Assert.assertEquals(guidePage.getCurrentURL(), expectedUrl);
        Assert.assertEquals(guidePage.getTitle(), expectedTitle);
    }

    @Test
    public void testMapsMenuNavigatesToWeatherMapsPage_IriSamo () {
        final String mapsUrl = "https://openweathermap.org/weathermap";
        final String mapsTitle = "Interactive weather maps - OpenWeatherMap";

        openBaseURL();

        MainPage mainPage = new MainPage(getDriver());

        mainPage.clickMapsMenu();

        Assert.assertEquals(getCurrentURL().substring(0, 37), mapsUrl);

        Assert.assertEquals(getTitle(), mapsTitle);
    }

    @Test
    public void testPlaceholderIsAvailable_TopMenu() {
        final String expectedInnerTextOfPlaceholder = "Weather in your city";
        final String attribute = "placeholder";

        Assert.assertTrue(openBaseURL_ReturnMainPage().isPlaceholderDisplayed());

        String innerTextOfPlaceholder =
                openBaseURL_ReturnMainPage().
                        getInnerTextOfPlaceholder(attribute);

        Assert.assertEquals(innerTextOfPlaceholder, expectedInnerTextOfPlaceholder);
    }

    @Test
    public void testOurInitiativesMenuNavigatesToOurInitiativesPage() {
        final String expectedUrl = "https://openweathermap.org/our-initiatives";
        final String expectedTitle = "Our Initiatives - OpenWeatherMap";

        openBaseURL_ReturnMainPage();

        OurInitiativesPage ourInitiativesPage = openBaseURL_ReturnMainPage().clickOurInitiativesMenu();

        Assert.assertEquals(ourInitiativesPage.getCurrentURL(), expectedUrl);
        Assert.assertEquals(ourInitiativesPage.getTitle(), expectedTitle);
    }

    @Test
    public void testSupportMenuIsClickable() {
        final String attribute = "class";
        final String expectedIfVisible = "dropdown-menu dropdown-visible";
        final String expectedIfNotVisible = "dropdown-menu";

        openBaseURL();

        MainPage mainPage = new MainPage(getDriver());

        mainPage.clickSupportMenu();

        Assert.assertEquals(mainPage.getActualAttributeValue(attribute), expectedIfVisible);

        mainPage.clickSupportMenu();

        Assert.assertEquals(mainPage.getActualAttributeValue(attribute), expectedIfNotVisible);
    }

    @Test
    public  void testCompanyLogoNavigatesToBaseURL() {
        final String expectedURL = "https://openweathermap.org/";
        final String expectedTitle = "Сurrent weather and forecast - OpenWeatherMap";

        MainPage mainPage = openBaseURL_ReturnMainPage().clickLogo();

        Assert.assertEquals(mainPage.getCurrentURL(), expectedURL);
        Assert.assertEquals(mainPage.getTitle(), expectedTitle);
    }
}
