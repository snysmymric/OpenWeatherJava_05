package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.TechnologyPage;
import pages.WeatherDashboardPage;
import pages.WidgetsPage;
import java.util.List;

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
        final String basePageTitle = "Сurrent weather and forecast - OpenWeatherMap";
        final String expectedURL = "https://openweathermap.org/widgets-constructor";
        final String expectedTitle = "Weather widgets constructor - OpenWeatherMap";

        WidgetsPage widgetsPage = openBaseURL()
                .scrollToWeatherDashboardFooterMenu()
                .clickWidgetsPageFooterMenu();

        Assert.assertNotEquals(basePageTitle, widgetsPage.getTitle());
        Assert.assertEquals(widgetsPage.getCurrentURL(), expectedURL);
        Assert.assertEquals(widgetsPage.getTitle(), expectedTitle);
    }

    @Test
    public void testDownloadOnTheAppStoreLinkNavigatesToAppStore() {
        final String expectedAppStoreURL =
                "https://apps.apple.com/gb/app/openweather/id1535923697";

        openBaseURL()
                .scrollToDownloadOnTheAppStoreLink()
                .clickDownloadOnTheAppStoreLinkFooterMenu()
                .switchToAppStorePage();

        Assert.assertEquals(getExternalPageURL(), expectedAppStoreURL);
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
    public void testPricingFooterLinkNavigatesToPricePage() {
        final String expectedURL = "https://openweathermap.org/price";

        String actualUrl = openBaseURL()
                .scrollToWeatherDashboardFooterMenu()
                .clickPricingFooterMenu()
                .getCurrentURL();

        Assert.assertEquals(actualUrl, expectedURL);
    }

    @Test
    public void testConnectYourWeatherStationFooterLinkNavigatesToWeatherStationsPage() {
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
    public void testAskQuestionFooterLinkNavigatesToHomeAskQuestionPage() {
        final String expectedUrl = "https://home.openweathermap.org/questions";

        String actualUrl = openBaseURL()
                .scrollToAskQuestionFooterMenu()
                .clickOnAskQuestionFooterMenu()
                .waitForAskQuestionPageHeaderBeVisible()
                .getCurrentURL();

        Assert.assertEquals(actualUrl, expectedUrl);
    }

    @Test
    public void testStorePanelHasTwoIcons() {
        final int expectedResult = 2;

        TechnologyPage technologyPage = openBaseURL()
                .scrollToOurTechnologyFooterMenu()
                .clickOurTechnologyFooterMenu();

        Assert.assertEquals(technologyPage.getStoreIconsSize(), expectedResult);
    }

    @Test
    public void testGetItOnGooglePlayIconTextExistAndNavigate() {
        final String expectedGooglePlayURL = "https://play.google.com/store/apps/details?id=uk.co.openweather";

        openBaseURL()
                .scrollToDownloadOnFooterMenuGooglePlayStore()
                .clickDownloadGooglePlayLinkFooterMenu()
                .switchToAppStorePage();
        Assert.assertEquals(getExternalPageURL(), expectedGooglePlayURL);
    }

    @Test
    public void testCopyrightPhraseOnFooterMenu(){
        final String expectedCopyright = "© 2012 — 2022 OpenWeather ® All rights reserved";
        String actualCopyright = openBaseURL()
                .scrollToWidgetsFooterMenu()
                .getCopyrightSign();

        Assert.assertEquals(actualCopyright,expectedCopyright);
    }

    @Test
    public void testFacebookIcon_IfCorrespondingFacebookWebpageOpens() {
        final String expectedFacebookURLPart = "facebook.com";

        openBaseURL()
                .scrollToPageBottom()
                .clickFacebookIcon()
                .switchToFacebookWebsite();

        Assert.assertTrue((getExternalPageURL().contains(expectedFacebookURLPart)));
    }

    @Test
    public void testSocialPanelIconsNavigateToTheirWebSites() {

        boolean actualDomains = openBaseURL()
                .scrollByGithubIcon()
                .clickAllIconsAndValidateDomains();

        Assert.assertTrue(actualDomains);
    }
}
