package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.TechnologyPage;
import pages.WeatherDashboardPage;

public class FooterMenuTest extends BaseTest {

    @Test
    public void testOurTechnologyFooterLinkNavigatesToTechnologyPage() {
        final String basePageTitle = "Сurrent weather and forecast - OpenWeatherMap";
        final String expectedURL = "https://openweathermap.org/technology";
        final String expectedTitle = "Weather model - OpenWeatherMap";

        TechnologyPage technologyPage = openBaseURL()
                .scrollToOurTechnologyFooterMenu()
                .clickOurTechnologyFooterMenu();

        Assert.assertNotEquals(basePageTitle, technologyPage.getTitle());
        Assert.assertEquals(technologyPage.getCurrentURL(), expectedURL);
        Assert.assertEquals(technologyPage.getTitle(), expectedTitle);
    }

    @Test
    public void testPrivacyPolicyFooterLinkNavigatesToPrivacyPolicyWeb() {
//        final String basePageTitle = "Сurrent weather and forecast - OpenWeatherMap";
//        final String expectedURL = "https://openweather.co.uk/privacy-policy";
//        final String expectedTitle = "Privacy policy - OpenWeatherMap";
//
//        openBaseURL()
//                .scrollToPrivacyPolicyFooterMenu()
//                .clickPrivacyPolicyFooterMenu();
//
//        switchToAnotherWindow(getDriver());
//
//        Assert.assertNotEquals(basePageTitle, getExternalPageTitle());
//        Assert.assertEquals(getExternalPageURL(), expectedURL);
//        Assert.assertEquals(getExternalPageTitle(), expectedTitle);
    }

    @Test
    public void testWeatherDashboardLinkNabigatesToWeatherDashboardPage() {
        final String expectedResult = "https://openweathermap.org/weather-dashboard";

        WeatherDashboardPage weatherDashboardPage = openBaseURL()
                .scrollToWeatherDashboardFooterMenu()
                .clickWeatherDashboardFooterMenu();

        Assert.assertEquals(weatherDashboardPage.getCurrentURL(), expectedResult);
    }

    @Test
    public void testAboutUsFooterLinkNavigatesToAboutUsPage() {
        final String expectedUrl = "https://openweathermap.org/about-us";

        String actualUrl = openBaseURL()
                .scrollToAboutUsFooterMenu()
                .clickOnAboutUsFooterMenu()
                .waitForAboutUsPageHeaderBeVisible()
                .getCurrentURL();

        Assert.assertEquals(actualUrl, expectedUrl);
    }

    @Test
    public void testWidgetsFooterLinkNavigatesToWidgetsPage() {
        final String expectedURL = "https://openweathermap.org/widgets-constructor";

        final String actualURL = openBaseURL()
                .scrollToWeatherDashboardFooterMenu()
                .clickWidgetsPageFooterMenu()
                .getCurrentURL();

        Assert.assertEquals(actualURL, expectedURL);
    }

    @Test
    public void testDownloadOnTheAppStoreLinkNavigatesToAppStore() {
        final String expectedDownloadOnTheAppStoreLink =
                "https://apps.apple.com/gb/app/openweather/id1535923697";

        openBaseURL()
                .scrollToDownloadOnTheAppStoreLink()
                .clickDownloadOnTheAppStoreLink()
                .switchToAppStorePage();

        Assert.assertEquals(getExternalPageURL(), expectedDownloadOnTheAppStoreLink);
    }

    @Test
    public void testSocialPanelExistsAndHasIcons() {
        final int expectedQuantity = 6;

        MainPage mainPage = openBaseURL();

        Assert.assertTrue(mainPage.isSocialPanelDisplayed());
        Assert.assertEquals(mainPage.getSocialPanelSize(), expectedQuantity);
    }

    @Test
    public void testGithubIconExistsAndNavigateToGithub() {
        final String expectedURL = "https://github.com/search?q=openweathermap&ref=cmdform";

        MainPage mainPage = openBaseURL().scrollByGithubIcon();

        Assert.assertTrue(mainPage.isGithubIconDisplayed());

        mainPage.clickToGithubIcon().switchToGithubWebsite();

        Assert.assertEquals(getExternalPageURL(), expectedURL);
    }

    @Test
    public void testPricingFooterLinkNavigatesToPricePage(){
        final String expectedURL = "https://openweathermap.org/price";

        String actualUrl = openBaseURL()
                .scrollToWeatherDashboardFooterMenu()
                .clickPricingFooterMenu()
                .getCurrentURL();

        Assert.assertEquals(actualUrl, expectedURL);
    }

    @Test
    public void testConnectYourWeatherStationFooterLinkNavigatesToWeatherStationsPage () {
//        final String weatherStationsUrl = "https://openweathermap.org/stations";
//        final String weatherStationsTitle = "Weather Stations - OpenWeatherMap";
//
//        openBaseURL()
//                .scrollToPageBottom()
//                .clickConnectYourWeatherStationFooterMenu();
//
//        Assert.assertEquals(getCurrentURL(), weatherStationsUrl);
//        Assert.assertEquals(getTitle(), weatherStationsTitle);
    }

    @Test
    public void testDownloadOpenWeatherAppTextOnFooterMenu() {
        final String expectedText = "Download OpenWeather app";

        Assert.assertTrue(openBaseURL().textDownloadOpenWeatherAppIsDisplayed());

        String textDownloadOpenWeatherApp = openBaseURL()
                .getTextDownloadOpenWeatherApp();

        Assert.assertEquals(textDownloadOpenWeatherApp, expectedText);
    }

    @Test
    public void testAskQuestionFooterLinkNavigatesToHomeAskQuestionPage(){
        final String expectedUrl = "https://home.openweathermap.org/questions";

        String actualUrl = openBaseURL()
                .scrollToAskQuestionFooterMenu()
                .clickOnAskQuestionFooterMenu()
                .waitForAskQuestionPageHeaderBeVisible()
                .getCurrentURL();

        Assert.assertEquals(actualUrl, expectedUrl);
    }
}
