package tests;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CurrentWeatherPage;
import pages.home.HomeSignInPage;
import pages.MainPage;
import utils.DateTimeUtils;
import java.util.List;

import static pages.MainPage.RANDOM_TEXT;

public class MainTest extends BaseTest {

    @Test
    public void testH2TagText_WhenSearchingCityCountry() {
        final String cityName = "Paris";
        final String expectedCityCountryNames = "Paris, FR";

        openBaseURL();

        MainPage mainPage = new MainPage(getDriver());

        final String oldCityCountryName = mainPage.getCityCountryName();

        mainPage.clickSearchCityField();
        mainPage.inputSearchCriteria(cityName);
        mainPage.clickSearchButton();
        mainPage.clickParisInDropDownList();
        mainPage.waitForCityCountryNameChanged(oldCityCountryName);

        String newCityCountryNames = mainPage.getCityCountryName();

        Assert.assertEquals(newCityCountryNames, expectedCityCountryNames);
    }

    @Test
    public void testH2TagText_WhenSearchingCityCountry1() {
        final String cityName = "Paris";
        final String expectedCityCountryNames = "Paris, FR";

        openBaseURL();

        MainPage mainPage = new MainPage(getDriver());

        final String oldCityCountryName = mainPage.getCityCountryName();

        String newCityCountryNames = mainPage
                .clickSearchCityField()
                .inputSearchCriteria(cityName)
                .clickSearchButton()
                .clickParisInDropDownList()
                .waitForCityCountryNameChanged(oldCityCountryName)
                .getCityCountryName();

        Assert.assertEquals(newCityCountryNames, expectedCityCountryNames);
    }

    @Test
    public void testH1HeaderColorAndFontSize() {
        final String expectedColor = "rgba(0, 0, 0, 0)";
        final String expectedFontSize = "45px";

        openBaseURL();

        MainPage mainPage = new MainPage(getDriver());

        String h1HeaderColor = mainPage.getColor();
        String h1HeaderFontSize = mainPage.getFontSize();

        Assert.assertEquals(h1HeaderColor, expectedColor);
        Assert.assertEquals(h1HeaderFontSize, expectedFontSize);
    }

    @Test
    public void testAmountOfIcons_OnDifferentWeatherPopUp() {
        final int expectedAmountOfIcons = 9;

        openBaseURL();

        MainPage mainPage = new MainPage(getDriver());

        int elements = mainPage
                .clickDifferentWeatherButton()
                .waitUntilDifferentWeatherPopUpIsVisible()
                .getAmountOfIconsOnDifferentWeatherPopUp();

        Assert.assertEquals(elements, expectedAmountOfIcons);
    }

    @Test
    public void testEachIconBecomesActiveAfterClick_DifferentWeatherPopUp_() {
        final String nameOfTestedAttribute = "class";
        final String expectedValueOfTestedAttribute = "activeIcon";

        openBaseURL();

        MainPage mainPage = new MainPage(getDriver());

        List<WebElement> elements = mainPage
                .clickDifferentWeatherButton()
                .waitUntilDifferentWeatherPopUpIsVisible()
                .getListOfIconsOnDifferentWeatherPopUp();

        for (WebElement element : elements) {
            Assert.assertEquals(
                    mainPage
                            .clickIconOnDifferentWeatherPopUp(element)
                            .getAttribute(element, nameOfTestedAttribute),
                    expectedValueOfTestedAttribute
            );
        }
    }

    @Test
    public void testRefreshPage() {
        final String expectedTextWhenLoading = "Loading";

        String actualTextWhenLoading = openBaseURL()
                .clickLogo()
                .getLoadingText("aria-label");

        Assert.assertEquals(actualTextWhenLoading, expectedTextWhenLoading);
    }

    @Test
    public void testCurrentLocation() {
        final String cityName = "Norway";
        final String expectedCityName = "London, GB";

        String actualCityName = openBaseURL()
                .clickSearchCityField()
                .inputSearchCriteria(cityName)
                .clickSearchButton()
                .clickCityNorway()
                .clickLocationButton()
                .getCityCountryName();

        Assert.assertEquals(actualCityName, expectedCityName);
    }

    @Test
    public void testAllIconsAreShownAndClickable() {
        MainPage mainPage = openBaseURL()
                .clickDifferentWeatherButton();

        Assert.assertTrue(mainPage.checkAllIconsAreVisibleAndClickable());
    }

    @Test
    public void testIfXButtonDisplayed() {
        MainPage mainPage = openBaseURL()
                .clickDifferentWeatherButton();

        Assert.assertTrue(mainPage.isXButtonDisplayed());
    }

    @Test
    public void testTempUnitChangedToC_WhenSwitchingToMetric() {
        MainPage mainPage = openBaseURL().switchToMetric();
        waitForGrayContainerDisappeared();

        Assert.assertTrue(mainPage.isTextContainsC());
    }

    @Test
    public void testTempUnitChangedToF_WhenSwitchingToImperial() {
        MainPage mainPage = openBaseURL().switchToImperial();
        waitForGrayContainerDisappeared();

        Assert.assertTrue(mainPage.isTextContainF());
    }

    @Test
    public void testIfSendButtonDisplayed() {
        MainPage mainPage = openBaseURL()
                .clickDifferentWeatherButton();

        Assert.assertTrue(mainPage.isSendButtonDisplayed());
    }

    @Test
    public void testErrorMessageWhenCityDoesNotExists() {
        final String cityName = RANDOM_TEXT;
        final String expectedErrorText = "No results for " + cityName;

        String actualErrorText = openBaseURL()
                .clickSearchCityField()
                .inputSearchCriteria(cityName)
                .clickSearchButton()
                .getErrorText();

        Assert.assertEquals(actualErrorText, expectedErrorText);
    }

    @Test
    public void testIfXButtonHasColorGreen() {
        final String cityName = RANDOM_TEXT;
        final String expectedXButtonBackgroundColor = "rgba(120, 203, 191, 0.8)";

        String actualXButtonBackgroundColor = openBaseURL()
                .clickSearchCityField()
                .inputSearchCriteria(cityName)
                .clickSearchButton()
                .getErrorButtonBackgroundColor();

        Assert.assertEquals(actualXButtonBackgroundColor, expectedXButtonBackgroundColor);
    }

    @Test
    public void testOnlyOneIconHighlighted_WhenDifferentWeatherContainerAppears() {
        openBaseURL();

        MainPage mainPage = new MainPage(getDriver());

        int activeIcon = mainPage.clickDifferentWeatherButton()
                .waitUntilDifferentWeatherPopUpIsVisible()
                .countActiveIconsInDifferentWeatherContainer();

        final String activeIconColor = mainPage.getActiveIconBackgroundColorInHEX();

        Assert.assertEquals(activeIcon, 1);
        Assert.assertEquals(activeIconColor, "#ececed");
    }

    @Test
    public void testCheckTempInC_inDayList_whenSwitchingToMetric() {
        MainPage mainPage = openBaseURL()
                .switchToMetric();
        mainPage.waitForGrayContainerDisappeared();

        Assert.assertTrue(mainPage.isDayListValuesContainsC());
    }

    @Test
    public void testDifferentWeatherButtonOpensDifferentWeatherContainer() {
        String expectedHeader = "Different weather";

        String actualHeader = openBaseURL()
                .clickDifferentWeatherButton()
                .getHeaderForDifferentWeatherContainer();

        Assert.assertEquals(actualHeader, expectedHeader);
    }

    @Test
    public void testCheckTempInF_inDayList_whenSwitchingToImperial() {
        MainPage mainPage = openBaseURL()
                .switchToImperial();
        mainPage.waitForGrayContainerDisappeared();

        Assert.assertTrue(mainPage.isDayListValuesContainsF());
    }

    @Test
    public void testCurrentDateAndTimeOnMainPage() {
        String expectedTime = DateTimeUtils.getSystemDate();

        String actualTime = openBaseURL().getActualTime();

        Assert.assertEquals(actualTime, expectedTime);
    }

    @Test
    public void testCheckTextInTheBottomOfThePage() {
        final String expectedResult = "We use cookies which are essential for the site to work. We also use non-essential "
                + "cookies to help us improve our services. Any data collected is anonymised. You can allow all cookies"
                + " or manage them individually.";

        String actualResultTextPanel = openBaseURL()
                .waitForFooterPanelToBeVisible()
                .waitForElementToBeVisible()
                .getBottomPanelText();

        Assert.assertEquals(actualResultTextPanel, expectedResult);
    }

    @Test
    public void testTemperatureValueIncreasedByOne_WhenClickingUp() {
        openBaseURL();

        MainPage mainPage = new MainPage(getDriver());

        int temperatureValueInContainer = mainPage
                .clickDifferentWeatherButton()
                .waitUntilDifferentWeatherPopUpIsVisible()
                .clickMoreOptionsDropDown()
                .getTemperatureValueInDifferentWeatherContainer();

        mainPage.clickUpKeyInTemperatureInput();

        int increasedTemperatureValueInContainer = mainPage.getTemperatureValueInDifferentWeatherContainer();

        Assert.assertEquals(increasedTemperatureValueInContainer - temperatureValueInContainer, 1);
    }
    @Test
    public void testLocationButtonIsDisplayedandClicabl(){
        final String expectedNotificationMessage  = "Location unavailable. Displaying default location: London";

        Assert.assertTrue(openBaseURL().isLocationButtonDisplayed());

        String actualNotificationMessage = openBaseURL()
                .clickLocationButton()
                .getNotificationMessage();

        Assert.assertEquals(actualNotificationMessage,expectedNotificationMessage);
    }

    @Test
    public void testAPIIconsAreDisplayed_SectionOrangeBackground() {
        int expectedAPIIconsQuantity = 5;
        List<String> expectedAPIIconsNames = List.of("current\nweather\n(current)", "hourly\nforecast\n(4 days)",
                "daily\nforecast\n(16 days)", "climatic\nforecast\n(30 days)", "historical\nweather\n(1 month, 1 year)");

        MainPage mainPage = openBaseURL()
                .scrollByCurrentWeatherIcon();

        List<WebElement> displayedAPIIcons = mainPage.getDisplayedAPIIcons();

        Assert.assertEquals(displayedAPIIcons, mainPage.getApiIcons());
        Assert.assertEquals(mainPage.getAPIIconsQuantity(), expectedAPIIconsQuantity);
        Assert.assertEquals(mainPage.getAPIIconsNames(), expectedAPIIconsNames);
    }

    @Test
    public void testHeadersMainPage() {
        final String expectedHeader1 = "OpenWeather";
        final String expectedHeader2 = "Weather forecasts, nowcasts and history in a fast and elegant way";

        MainPage mainPage = openBaseURL();

        Assert.assertEquals(mainPage.getHeader1Text(), expectedHeader1);
        Assert.assertEquals(mainPage.getHeader2Text(), expectedHeader2);
    }

    @Test
    public void testDataSourceOptionsNames() {
        final List<String> expectedDataSourceOptionsNames = List.of(
                "Personal feelings",
                "Own weather station or devices",
                "Local weather provider",
                "Global weather provider",
                "Other");

        List<String> dataSourceOptionsTexts = openBaseURL()
                .clickDifferentWeatherButton()
                .waitUntilDifferentWeatherPopUpIsVisible()
                .clickMoreOptionsDropDown()
                .clickDataSourceDropDown()
                .clickAllDataSourceOptions()
                .getDataSourceOptionsTexts();

        Assert.assertEquals(dataSourceOptionsTexts, expectedDataSourceOptionsNames);
    }

    @Test
    public void testChosenDataSourceOptionIsSavedAfterClickingLessOptionsDropDown() {
        String dataSourceDropDownTextBefore = openBaseURL()
                .clickDifferentWeatherButton()
                .waitUntilDifferentWeatherPopUpIsVisible()
                .clickMoreOptionsDropDown()
                .clickDataSourceDropDown()
                .clickFirstDataSourceOption()
                .getDataSourceDropDownText();

        MainPage mainPage = new MainPage(getDriver());

        String dataSourceDropDownTextAfter = mainPage
                .clickMoreOptionsDropDown()
                .clickMoreOptionsDropDown()
                .getDataSourceDropDownText();

        Assert.assertEquals(dataSourceDropDownTextAfter, dataSourceDropDownTextBefore);
    }

    @Test
    public void testFooterMenuLinks() {
        final int expectedLinks = 27;

        openBaseURL();

        MainPage mainPage = new MainPage(getDriver()).scrollToFooterMenu();
        int actualLinks = mainPage.footerMenuLinks();

        Assert.assertEquals(actualLinks, expectedLinks);
    }

    @Test
    public void testSignInFooterMenuLinks() {
        final int expectedLinks = 27;

        openBaseURL().signIn();

        MainPage mainPage = new MainPage(getDriver()).scrollToFooterMenu();
        int actualLinks = mainPage.footerMenuLinks();

        Assert.assertEquals(actualLinks, expectedLinks);

        new HomeSignInPage(getDriver()).signOut();
    }
    
    @Test
    public void testEmailAndAdditionalInfoIsNotSavedAfterClickingOutOfContainer() {
        MainPage mainPage = new MainPage(getDriver());

        String emailText = openBaseURL()
                .clickDifferentWeatherButton()
                .waitUntilDifferentWeatherPopUpIsVisible()
                .clickMoreOptionsDropDown()
                .inputTextInEmailTextbox()
                .getEmailTextboxText();

        String anyAdditionalInfoText = mainPage
                .inputTextInAnyAdditionalInfoTextarea()
                .getAnyAdditionalInfoText();

        String emailTextAfterClosingContainer = mainPage
                .clickOutOfDifferentWeatherContainer()
                .clickDifferentWeatherButton()
                .waitUntilDifferentWeatherPopUpIsVisible()
                .clickMoreOptionsDropDown()
                .getEmailTextboxText();

        String anyAdditionalInfoTextAfterClosingContainer = mainPage
                .getAnyAdditionalInfoText();

        Assert.assertNotEquals(emailTextAfterClosingContainer, emailText);
        Assert.assertNotEquals(anyAdditionalInfoTextAfterClosingContainer, anyAdditionalInfoText);
    }

    @Test
    public void testCorrect8DaysForecastCalendarSequence() {

        List<String> listOfEightDaysData = openBaseURL().getListOfEightDaysDataText();

        String expectedResult = new MainPage(getDriver()).getEightDaysForecastCalendarSequanceText();

        String actualResult = listOfEightDaysData.toString().substring(1, listOfEightDaysData.toString().length() - 1);

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testCurrentWeatherAPIIconNavigatesToCorrespondingPage() {
        final String basePageTitle = "Сurrent weather and forecast - OpenWeatherMap";
        final String expectedUrl = "https://openweathermap.org/current";
        final String expectedTitle = "Current weather data - OpenWeatherMap";

        CurrentWeatherPage currentWeatherPage = openBaseURL()
                .scrollByOrangeBackground()
                .clickCurrentWeatherIcon();

        Assert.assertNotEquals(basePageTitle, currentWeatherPage.getTitle());
        Assert.assertEquals(currentWeatherPage.getCurrentURL(), expectedUrl);
        Assert.assertEquals(currentWeatherPage.getTitle(), expectedTitle);
    }
}
