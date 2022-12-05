package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import pages.*;

import java.util.List;

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
//        final String expectedURL = "https://home.openweathermap.org/marketplace";
//
//        openBaseURL();
//
//        MainPage mainPage = new MainPage(getDriver());
//
//        mainPage.clickMarketplaceMenu();
//
//        switchToAnotherWindow(getDriver());
//
//        Assert.assertEquals(getExternalPageURL(), expectedURL);
    }

    @Test
    public void testGuideMenuNavigatesToGuidePage() {
        final String basePageTitle = "Сurrent weather and forecast - OpenWeatherMap";
        final String expectedUrl = "https://openweathermap.org/guide";
        final String expectedTitle = "OpenWeatherMap API guide - OpenWeatherMap";

        GuidePage guidePage = openBaseURL().clickGuideMenu();

        Assert.assertNotEquals(basePageTitle, guidePage.getTitle());
        Assert.assertEquals(guidePage.getCurrentURL(), expectedUrl);
        Assert.assertEquals(guidePage.getTitle(), expectedTitle);
    }

    @Test
    public void testMapsMenuNavigatesToWeatherMapsPage_IriSamo () {
//        final String mapsUrl = "https://openweathermap.org/weathermap";
//        final String mapsTitle = "Interactive weather maps - OpenWeatherMap";
//
//        openBaseURL();
//
//        MainPage mainPage = new MainPage(getDriver());
//
//        mainPage.clickMapsMenu();
//
//        Assert.assertEquals(getCurrentURL().substring(0, 37), mapsUrl);
//
//        Assert.assertEquals(getTitle(), mapsTitle);
    }

    @Test
    public void testHowToStartMenuNavigatesToHowToStartPage() {
        final String basePageTitle = "Сurrent weather and forecast - OpenWeatherMap";
        final String expectedTitle = "How to start to work with Openweather API - OpenWeatherMap";
        final String expectedUrl = "https://openweathermap.org/appid";

        HowToStartPage howToStartPage = openBaseURL()
                .clickSupportMenu()
                .clickHowToStartSupportSubmenu();

        Assert.assertNotEquals(basePageTitle, howToStartPage.getTitle());
        Assert.assertEquals(howToStartPage.getCurrentURL(), expectedUrl);
        Assert.assertEquals(howToStartPage.getTitle(), expectedTitle);
    }

    @Test
    public void testPlaceholderIsAvailable_TopMenu() {
        final String expectedInnerTextOfPlaceholder = "Weather in your city";
        final String attribute = "placeholder";

        Assert.assertTrue(openBaseURL().isPlaceholderDisplayed());

        String innerTextOfPlaceholder =
                openBaseURL().
                        getInnerTextOfPlaceholder(attribute);

        Assert.assertEquals(innerTextOfPlaceholder, expectedInnerTextOfPlaceholder);
    }

    @Test
    public void testOurInitiativesMenuNavigatesToOurInitiativesPage() {
        final String expectedUrl = "https://openweathermap.org/our-initiatives";
        final String expectedTitle = "Our Initiatives - OpenWeatherMap";

        openBaseURL();

        OurInitiativesPage ourInitiativesPage = openBaseURL().clickOurInitiativesMenu();

        Assert.assertEquals(ourInitiativesPage.getCurrentURL(), expectedUrl);
        Assert.assertEquals(ourInitiativesPage.getTitle(), expectedTitle);
    }

    @Test
    public void testSupportMenuHasLinks() {
        final List<String> expectedList = List.of("FAQ", "How to start", "Ask a question");
        Assert.assertEquals(openBaseURL().clickSupportMenu().getLinksText(), expectedList);
    }
    @Ignore
    @Test    
    public void testSupportMenuIsClickable() {
        final String attribute = "class";
        final String expectedIfVisible = "dropdown-menu dropdown-visible";
        final String expectedIfNotVisible = "dropdown-menu";

        openBaseURL();

        MainPage mainPage = new MainPage(getDriver());

        mainPage.clickSupportMenu();

        Assert.assertEquals(mainPage.getSupportMenuIsActiveValue(attribute), expectedIfVisible);

        mainPage.clickSupportMenu();

        Assert.assertEquals(mainPage.getSupportMenuIsActiveValue(attribute), expectedIfNotVisible);
    }

    @Test
    public void testPartnersMenuNavigatesToPartnersPage() {
        final String expectedCurrentURL = "https://openweathermap.org/examples";

        String currentURL =
                openBaseURL()
                        .clickPartnersMenu()
                        .getCurrentURL();

        Assert.assertEquals(currentURL, expectedCurrentURL);
    }

    @Test
    public void testPartnersPageTitle() {
        final String expectedTitle = "Partners and solutions - OpenWeatherMap";

        String title = openBaseURL()
                .clickPartnersMenu()
                .getTitle();

        Assert.assertEquals(title, expectedTitle);
    }


    @Test
    public void testGuideMenuNavigatesToGuidePage1() {
        final String expectedUrl = "https://openweathermap.org/guide";
        final String expectedTitle = "OpenWeatherMap API guide - OpenWeatherMap";

        GuidePage guidePage =
                openBaseURL()
                        .clickGuideMenu();
        Assert.assertEquals(guidePage.getCurrentURL(), expectedUrl);
        Assert.assertEquals(guidePage.getTitle(), expectedTitle);
    }

    @Test
    public void testCountTopMenuButtons() {
        int expectedButton = 12;

        int actualCountTopMenuButtons =
                openBaseURL()
                        .countTopMenuButtons();

        Assert.assertEquals(actualCountTopMenuButtons, expectedButton);
    }

    @Test
    public  void testCompanyLogoNavigatesToBaseURL() {
        final String expectedURL = "https://openweathermap.org/";
        final String expectedTitle = "Сurrent weather and forecast - OpenWeatherMap";

        MainPage mainPage = openBaseURL().clickLogo();

        Assert.assertEquals(mainPage.getCurrentURL(), expectedURL);
        Assert.assertEquals(mainPage.getTitle(), expectedTitle);
    }

    @Test
    public void testFAQSupportSubmenuNavigatesToFAQPage() {
        final String expectedUrl = String.format(BASE_URL + "faq");

        String actualUrl = openBaseURL()
                .clickSupportMenu()
                .clickFAQSupportSubmenu()
                .getCurrentURL();

        Assert.assertEquals(actualUrl, expectedUrl);
    }

    @Test
    public void testDashboardMenuNavigatesToDashboardPage() {
        final String expectedURL = "https://openweathermap.org/weather-dashboard";

        String weatherDashboardURL = openBaseURL()
                .clickDashboardMenu()
                .getCurrentURL();

        Assert.assertEquals(weatherDashboardURL, expectedURL);
    }

    @Test
    public void testPricingMenuNavigatesToPricePage() {
        final String basePageTitle = "Сurrent weather and forecast - OpenWeatherMap";
        final String expectedPriceURL = "https://openweathermap.org/price";
        final String expectedPricingTitle = "Pricing - OpenWeatherMap";

        PricePage pricePage = openBaseURL().clickPricingMenu();

        Assert.assertNotEquals(basePageTitle, pricePage.getTitle());
        Assert.assertEquals(pricePage.getCurrentURL(), expectedPriceURL);
        Assert.assertEquals(pricePage.getTitle(),expectedPricingTitle);
    }

    @Test
    public void testEachTopMenuNavigatesToCorrespondingPage() {
        List<String> expectedURLs = List.of(
                "https://openweathermap.org/guide", "https://openweathermap.org/api",
                "https://openweathermap.org/weather-dashboard", "https://home.openweathermap.org/marketplace",
                "https://openweathermap.org/price",
                "https://openweathermap.org/weathermap?basemap=map&cities=true&layer=temperature&lat=30&lon=-20&zoom=5",
                "https://openweathermap.org/our-initiatives", "https://openweathermap.org/examples",
                "https://openweather.co.uk/blog/category/weather", "https://openweather.co.uk/",
                "https://home.openweathermap.org/users/sign_in");

        List<String> actualURLs = openBaseURL().clickAllMenus();

        Assert.assertEquals(actualURLs, expectedURLs);
    }
}