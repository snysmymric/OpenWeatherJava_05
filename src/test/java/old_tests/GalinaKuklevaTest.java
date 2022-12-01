package old_tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import base.BaseTest;

@Ignore
public class GalinaKuklevaTest extends BaseTest {
    final static String FAHRENHEIT_SIGN = "°F";
    final static By H2_CITY_COUNTRY_HEADER = By.xpath("//div[@id = 'weather-widget']//h2");
    final static By SEARCH_CITY_FIELD = By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']");
    final static By SEARCH_BUTTON = By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']");
    final static By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
    final static By PARIS_FR_CHOICE_IN_DROPDOWN_MENU = By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']");
    final static By GUIDE_BUTTON = By.xpath("//div[@id = 'desktop-menu']//li/a [@href='/guide']");
    final static By IMPERIAL_DATA = By.xpath("//div[@id = 'weather-widget']//div[text() = 'Imperial: °F, mph']");
    final static By TEMP_IN_THE_CITY = By.xpath("//div[@id = 'weather-widget']//span[@class = 'heading']");
    final static By SUPPORT_BUTTON = By.xpath("//div[@id='support-dropdown']");
    final static By SUB_MENU_ASK_A_QUESTION = By.xpath("//div[@id = 'desktop-menu']//a[text() = 'Ask a question']");
    final static By EMAIL_FIELD = By.id("question_form_email");
    final static By SUBJECT_FIELD = By.id("question_form_subject");
    final static By OPTION_TECH = By.xpath("//select[@id = 'question_form_subject']/option[@value = 'Tech Issue']");
    final static By MESSAGE_FIELD = By.id("question_form_message");
    final static By SUBMIT_FIELD = By.xpath("//form[@id = 'new_question_form']//input[@value = 'Submit']");
    final static By SUBMIT_VERIFICATION = By.xpath("//div[text() = 'reCAPTCHA verification failed, please try again.']");
    final static By ELEMENT_ORANGE = By.xpath("//div[@id = 'desktop-menu']//a[text()= 'API']");

    @Test
    public void testH2TagText_WhenSearchingCityCountry() {
        final String cityName = "Paris";
        final String expectedCityCountryName = "Paris, FR";

        openBaseURL();

        String oldH2Header = getText(H2_CITY_COUNTRY_HEADER);

        click(SEARCH_CITY_FIELD);
        input(cityName, SEARCH_CITY_FIELD);
        click(SEARCH_BUTTON);
        waitElementToBeVisible(SEARCH_DROPDOWN_MENU);
        click(PARIS_FR_CHOICE_IN_DROPDOWN_MENU);
        waitTextToBeChanged(H2_CITY_COUNTRY_HEADER, oldH2Header);

        String actualResult = getText(H2_CITY_COUNTRY_HEADER);

        Assert.assertEquals(actualResult, expectedCityCountryName);
    }

    @Test
    public void testOpenWeatherMapGuidePageTitle() {

        String expectedResult_1 = "https://openweathermap.org/guide";
        String expectedResult_2 = "OpenWeatherMap API guide - OpenWeatherMap";

        openBaseURL();

        click(GUIDE_BUTTON);

        String actualResult_1 = getDriver().getCurrentUrl();
        String actualResult_2 = getDriver().getTitle();

        Assert.assertEquals(actualResult_1, expectedResult_1);
        Assert.assertEquals(actualResult_2, expectedResult_2);
    }

    @Test
    public void testTemperatureInFahrenheits() {

        String expectedResult = FAHRENHEIT_SIGN;

        openBaseURL();

        click(IMPERIAL_DATA);
        getText(TEMP_IN_THE_CITY);
        Boolean actualResult = getText(TEMP_IN_THE_CITY).contains(expectedResult);

        Assert.assertTrue(actualResult, expectedResult);
    }

    @Test
    public void testNonCaptchaVerification() {

        final String eMail = "tester@test.com";
        final String message = "Hello, World!";
        final String expectedResult = "reCAPTCHA verification failed, please try again.";

        openBaseURL();

        click(SUPPORT_BUTTON);
        click(SUB_MENU_ASK_A_QUESTION);

        String originalWindow = getDriver().getWindowHandle();
        assert getDriver().getWindowHandles().size() != 1;
        getWait10();
        for (String windowHandle : getDriver().getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                getDriver().switchTo().window(windowHandle);
                break;
            }
        }

        input(eMail, EMAIL_FIELD);
        getWait10();
        click(SUBJECT_FIELD);
        click(OPTION_TECH);
        input(message, MESSAGE_FIELD);
        getWait10();
        click(SUBMIT_FIELD);

        String actualResult = getText(SUBMIT_VERIFICATION);

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testDeskTopMenuClickAPIFind30Buttons1() {

        openBaseURL();

        int expectedResult = 30;

        waitElementToBeVisible(ELEMENT_ORANGE);
        click(ELEMENT_ORANGE);

        int actualResult = getDriver().findElements(
                By.xpath("//a[contains(@class, 'btn_block orange round') " +
                        "or contains(@class, 'ow-btn round btn-orange') ]")).size();

        Assert.assertEquals(actualResult, expectedResult);
    }
}
