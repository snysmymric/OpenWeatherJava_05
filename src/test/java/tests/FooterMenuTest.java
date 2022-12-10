package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


public class FooterMenuTest extends BaseTest {

    @Test
    public void testOurTechnologyFooterLinkNavigatesToTechnologyPage() {
        final String basePageTitle = "Сurrent weather and forecast - OpenWeatherMap";
        final String expectedURL = "https://openweathermap.org/technology";
        final String expectedTitle = "Weather model - OpenWeatherMap";

        TechnologyPage technologyPage = openBaseURL()
                .scrollToFooterMenu()
                .clickOurTechnologyFooterMenu();

        Assert.assertNotEquals(basePageTitle, technologyPage.getTitle());
        Assert.assertEquals(technologyPage.getCurrentURL(), expectedURL);
        Assert.assertEquals(technologyPage.getTitle(), expectedTitle);
    }

    @Test
    public void testPrivacyPolicyFooterLinkNavigatesToPrivacyPolicyWeb() {
        final String basePageTitle = "Сurrent weather and forecast - OpenWeatherMap";
        final String expectedURL = "https://openweather.co.uk/privacy-policy";
        final String expectedTitle = "Privacy policy - OpenWeatherMap";

        openBaseURL()
                .scrollToFooterMenu()
                .clickPrivacyPolicyFooterMenu();

        new MainPage(getDriver()).switchToExternalPage();

        Assert.assertNotEquals(basePageTitle, getExternalPageTitle());
        Assert.assertEquals(getExternalPageURL(), expectedURL);
        Assert.assertEquals(getExternalPageTitle(), expectedTitle);
    }

    @Test
    public void testWeatherDashboardLinkNabigatesToWeatherDashboardPage() {
        final String expectedResult = "https://openweathermap.org/weather-dashboard";

        WeatherDashboardPage weatherDashboardPage = openBaseURL()
                .scrollToFooterMenu()
                .clickWeatherDashboardFooterMenu();

        Assert.assertEquals(weatherDashboardPage.getCurrentURL(), expectedResult);
    }

    @Test
    public void testAboutUsFooterLinkNavigatesToAboutUsPage() {
        final String basePageTitle = "Сurrent weather and forecast - OpenWeatherMap";
        final String expectedTitle = "About us - OpenWeatherMap";
        final String expectedUrl = "https://openweathermap.org/about-us";

        AboutUsPage aboutUsPage = openBaseURL()
                .scrollToFooterMenu()
                .clickOnAboutUsFooterMenu()
                .waitForAboutUsPageHeaderBeVisible();

        Assert.assertNotEquals(basePageTitle, aboutUsPage.getTitle());
        Assert.assertEquals(aboutUsPage.getCurrentURL(), expectedUrl);
        Assert.assertEquals(aboutUsPage.getTitle(), expectedTitle);
    }

    @Test
    public void testWidgetsFooterLinkNavigatesToWidgetsPage() {
        final String basePageTitle = "Сurrent weather and forecast - OpenWeatherMap";
        final String expectedURL = "https://openweathermap.org/widgets-constructor";
        final String expectedTitle = "Weather widgets constructor - OpenWeatherMap";

        WidgetsPage widgetsPage = openBaseURL()
                .scrollToFooterMenu()
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
                .scrollToFooterMenu()
                .clickDownloadOnTheAppStoreLinkFooterMenu()
                .switchToExternalPage();

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

        MainPage mainPage = openBaseURL().scrollToFooterMenu();

        Assert.assertTrue(mainPage.isGithubIconDisplayed());

        mainPage.clickToGithubIcon().switchToExternalPage();

        Assert.assertEquals(getExternalPageURL(), expectedURL);
    }

    @Test
    public void testPricingFooterLinkNavigatesToPricePage() {
        final String expectedURL = "https://openweathermap.org/price";

        String actualUrl = openBaseURL()
                .scrollToFooterMenu()
                .clickPricingFooterMenu()
                .getCurrentURL();

        Assert.assertEquals(actualUrl, expectedURL);
    }

    @Test
    public void testConnectYourWeatherStationFooterLinkNavigatesToWeatherStationsPage() {
        final String weatherStationsUrl = "https://openweathermap.org/stations";
        final String weatherStationsTitle = "Weather Stations - OpenWeatherMap";

        WeatherStationsPage weatherStationsPage =
                openBaseURL()
                        .scrollToFooterMenu()
                        .clickConnectYourWeatherStationFooterMenu();

        String actualWeatherStationsUrl = weatherStationsPage.getCurrentURL();
        String actualWeatherStationsTitle = weatherStationsPage.getTitle();

        Assert.assertNotEquals(weatherStationsUrl, BASE_URL);
        Assert.assertEquals(actualWeatherStationsUrl, weatherStationsUrl);
        Assert.assertEquals(actualWeatherStationsTitle, weatherStationsTitle);
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
        final String basePageTitle = "Сurrent weather and forecast - OpenWeatherMap";
        final String expectedTitle = "Members";
        final String expectedUrl = "https://home.openweathermap.org/questions";

        HomeAskQuestionPage homeAskQuestionPage = openBaseURL()
                .scrollToFooterMenu()
                .clickOnAskQuestionFooterMenu()
                .switchToHomeAskQuestionPage();

        Assert.assertNotEquals(basePageTitle, homeAskQuestionPage.getTitle());
        Assert.assertEquals(homeAskQuestionPage.getCurrentURL(), expectedUrl);
        Assert.assertEquals(homeAskQuestionPage.getTitle(), expectedTitle);
    }

    @Test
    public void testStorePanelHasTwoIcons() {
        final int expectedResult = 2;

        FooterMenuPage footerMenuPage = openBaseURL()
                .scrollToFooterMenu()
                .clickDownloadGooglePlayLinkFooterMenu();

        Assert.assertEquals(footerMenuPage.getStoreIconsSize(), expectedResult);
    }

    @Test
    public void testGetItOnGooglePlayIconTextExistAndNavigate() {
        final String expectedGooglePlayURL = "https://play.google.com/store/apps/details?id=uk.co.openweather";

        openBaseURL()
                .scrollToFooterMenu()
                .clickDownloadGooglePlayLinkFooterMenu()
                .switchToExternalPage();

        Assert.assertEquals(getExternalPageURL(), expectedGooglePlayURL);
    }

    @Test
    public void testCopyrightPhraseOnFooterMenu(){
        final String expectedCopyright = "© 2012 — 2022 OpenWeather ® All rights reserved";
        String actualCopyright = openBaseURL()
                .scrollToFooterMenu()
                .getCopyrightSign();

        Assert.assertEquals(actualCopyright,expectedCopyright);
    }

    @Test
    public void testFacebookIcon_IfCorrespondingFacebookWebpageOpens() {
        final String expectedFacebookURLPart = "facebook.com";

        openBaseURL()
                .scrollToFooterMenu()
                .clickFacebookIcon()
                .switchToExternalPage();

        Assert.assertTrue((getExternalPageURL().contains(expectedFacebookURLPart)));
    }

    @Test
    public void testSocialPanelIconsNavigateToTheirWebSites() throws MalformedURLException {
        final List<String> expectedDomains = List.of("www.facebook.com", "twitter.com", "www.linkedin.com",
                "openweathermap.medium.com", "t.me", "github.com");

        List<String> actualURLs = openBaseURL()
                .scrollToFooterMenu()
                .clickEachIconAndGetURL();

        Assert.assertEquals(actualURLs.size(), expectedDomains.size());

        for (int i = 0; i < actualURLs.size(); i++) {
            String expectedDomain = expectedDomains.get(i);
            URL url = new URL(actualURLs.get(i));
            String actualDomain = url.getHost();

            Assert.assertEquals(actualDomain, expectedDomain);
        }
    }
}
