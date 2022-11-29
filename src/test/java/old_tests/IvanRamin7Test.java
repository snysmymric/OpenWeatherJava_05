package old_tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class IvanRamin7Test extends BaseTest {
    private final By H_2_CITY_COUNTRY_HEADER = By.xpath("//div[@id = 'weather-widget']//h2");
    private final By SEARCH_CITY_FIELD = By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']");
    private final By SEARCH_BUTTON = By.xpath("//button[@type = 'submit']");
    private final By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
    private final By PARIS_FR_CHOICE_FROM_DROPDOWN_MENU = By.xpath("//div[@id = 'weather-widget']//ul[@class = 'search-dropdown-menu']//span[text() = 'Paris, FR ']");
    private final By GUIDE_DESKTOP_MENU = By.xpath("//nav[@id = 'nav-website']//div[@id = 'desktop-menu']//a[@href = '/guide']");
    private final By CELSIUS_FAHRENHEITS_SWITCH_FAHRENHEITS =  By.xpath("//div[@class='page-container']//div[@class = 'switch-container']/div[text() = 'Imperial: °F, mph']");
    private final By CELSIUS_FAHRENHEITS_SWITCH_CELSIUS = By.xpath("//div[@class='page-container']//div[@class = 'switch-container']/div[text() = 'Metric: °C, m/s']");
    private final By MAIN_PAGE_TEMPERATURE = By.xpath("//span[@class = 'heading']");
    private final By TEMPERATURE_UNDER_COUNTRY_NAME = By.xpath("//div[@class='section-content']//span[@class = 'heading']");
    private final By COOKIE_WARNING_TEXT = By.xpath("//div[@class = 'stick-footer-panel']//p[@class = 'stick-footer-panel__description']");
    private final By COOKIE_WARNING_ALLOW_ALL_BUTTON = By.xpath("//div[@class = 'stick-footer-panel']//button");
    private final By COOKIE_WARNING_MANAGE_COOKIES_BUTTON = By.xpath("//div[@class = 'stick-footer-panel']//a[@rel = 'noopener noreferrer']");
    private final By SUPPORT_DROPDOWN =  By.xpath("//div[@id = 'support-dropdown']");
    private final By SUPPORT_DROPDOWN_FAQ = By.xpath("//ul[@class = 'dropdown-menu dropdown-visible']//a[@href='/faq']");
    private final By SUPPORT_DROPDOWN_HOW_TO_START =  By.xpath("//ul[@class = 'dropdown-menu dropdown-visible']/li/a[@href = '/appid']");
    private final By SUPPORT_DROPDOWN_ASK_QUESTION = By.xpath("//ul[@class = 'dropdown-menu dropdown-visible']/li/a[@href = 'https://home.openweathermap.org/questions']");
    private final By EMAIL_FIELD_FROM_ASK_QUESTION = By.xpath("//input[@id = 'question_form_email']");
    private final By SUBJECT_CHOOSE = By.xpath("//option[@value = 'Initiatives']");
    private final By SUBJECT_FIELD_FROM_ASK_QUESTION = By.xpath("//div[@class = 'form-group select required question_form_subject']//select[@id = 'question_form_subject']/option[@value = 'Other']");
    private final By MESSAGE_FIELD_FROM_ASK_QUESTION = By.xpath("//div[@class = 'form-group text required question_form_message']//textarea");
    private final By SUBMIT_BUTTON_FROM_ASK_QUESTION = By.xpath("//input[@value = 'Submit']");
    private final By ERROR_CAPTCHA = By.xpath("//div[@class = 'help-block']");
    private final By LOGO_MAIN = By.xpath("//img[@src='/themes/openweathermap/assets/img/logo_white_cropped.png']");

    @Ignore
    @Test
    public void testH2TagText_WhenSearchingCityCountry(){
        final String cityName = "Paris";
        final String expectedResult = "Paris, FR";

        openBaseURL();

        String oldH2Header = getText(H_2_CITY_COUNTRY_HEADER);
        click(SEARCH_CITY_FIELD);
        input(cityName, SEARCH_CITY_FIELD);
        click(SEARCH_BUTTON);
        waitElementToBeVisible(SEARCH_DROPDOWN_MENU);
        click(PARIS_FR_CHOICE_FROM_DROPDOWN_MENU);
        waitTextToBeChanged(H_2_CITY_COUNTRY_HEADER, oldH2Header);

        String actualResult = getText(H_2_CITY_COUNTRY_HEADER);

        Assert.assertEquals(actualResult, expectedResult);
    }


    @Ignore
    @Test
    public void test_ConfirmGuidePage() {
        final String urlGuidePageExpectedResult = "https://openweathermap.org/guide";
        final String titleGuideExpectedResult = "OpenWeatherMap API guide - OpenWeatherMap";

        openBaseURL();

        click(GUIDE_DESKTOP_MENU);
        String urlGuidePageActualResult = getDriver().getCurrentUrl();
        String titleGuideActualResult = getDriver().getTitle();

        Assert.assertEquals(urlGuidePageActualResult, urlGuidePageExpectedResult);
        Assert.assertEquals(titleGuideActualResult, titleGuideExpectedResult);
    }

    @Ignore
    @Test
    public void test_ConfirmFarenheits() {
        final String fahrenheitExpectedResult = "°F";

        openBaseURL();

        click(CELSIUS_FAHRENHEITS_SWITCH_FAHRENHEITS);

        Assert.assertTrue(getText(TEMPERATURE_UNDER_COUNTRY_NAME).contains(fahrenheitExpectedResult));
    }

    @Ignore
    @Test
    public void test_Cookies() {
        final String textExpectedResult = "We use cookies which are essential for the site to work." +
                " We also use non-essential cookies to help us improve our services. Any data collected is anonymised." +
                " You can allow all cookies or manage them individually.";
        final String allowAllButtonExpectedResult = "Allow all";
        final String manageCookiesButtonExpectedResult = "Manage cookies";

        openBaseURL();

        Assert.assertEquals(getDriver().findElement(COOKIE_WARNING_TEXT).getText(), textExpectedResult);
        Assert.assertEquals(getDriver().findElement(COOKIE_WARNING_ALLOW_ALL_BUTTON).getText(), allowAllButtonExpectedResult);
        Assert.assertEquals(getDriver().findElement(COOKIE_WARNING_MANAGE_COOKIES_BUTTON).getText(), manageCookiesButtonExpectedResult);
    }

    @Ignore
    @Test
    public void test_SupportDropDown() {
        ArrayList<String> expectedResult = new ArrayList<>(Arrays.asList("FAQ", "How to start", "Ask a question"));

        openBaseURL();

        click(SUPPORT_DROPDOWN);
        ArrayList<String> actualResult = new ArrayList<>(Arrays.asList(
                getDriver().findElement(SUPPORT_DROPDOWN_FAQ).getText(),
                getDriver().findElement(SUPPORT_DROPDOWN_HOW_TO_START).getText(),
                getDriver().findElement(SUPPORT_DROPDOWN_ASK_QUESTION).getText()
        ));

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Ignore
    @Test
    public void test_ErrorHandlingCaptcha() {
        final String userEmail = "user@gmail.com";
        final String textMessage = "Hi, i like to walk in the morning";
        final String captchaErrorTextExpectedResult = "reCAPTCHA verification failed, please try again.";

        openBaseURL();

        click(SUPPORT_DROPDOWN);
        click(SUPPORT_DROPDOWN_ASK_QUESTION);
        jumpToNextWindow();
        click(EMAIL_FIELD_FROM_ASK_QUESTION);
        input(userEmail, EMAIL_FIELD_FROM_ASK_QUESTION);
        click(SUBJECT_FIELD_FROM_ASK_QUESTION);
        click(SUBJECT_CHOOSE);
        click(MESSAGE_FIELD_FROM_ASK_QUESTION);
        input(textMessage, MESSAGE_FIELD_FROM_ASK_QUESTION);
        click(SUBMIT_BUTTON_FROM_ASK_QUESTION);

        Assert.assertEquals(getDriver().findElement(ERROR_CAPTCHA).getText(), captchaErrorTextExpectedResult);
    }

    @Ignore
    @Test
    public void test_ConfirmUnitsAreSwitched(){
        final String expectedFahrenheits = "°F";
        final String expectedCelsius = "°C";

        openBaseURL();

        click(CELSIUS_FAHRENHEITS_SWITCH_FAHRENHEITS);
        waitForGrayContainerDisappeared();

        Assert.assertTrue(getDriver().findElement(MAIN_PAGE_TEMPERATURE).getText().contains(expectedFahrenheits));

        click(CELSIUS_FAHRENHEITS_SWITCH_CELSIUS);
        waitForGrayContainerDisappeared();

        Assert.assertTrue(getDriver().findElement(MAIN_PAGE_TEMPERATURE).getText().contains(expectedCelsius));
    }

    @Ignore
    @Test
    public void test_urlNotChanged() {
        openBaseURL();

        click(LOGO_MAIN);

        Assert.assertEquals(getCurrentURL(), BASE_URL);
    }
}