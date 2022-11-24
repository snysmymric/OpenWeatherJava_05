import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class IvanRamin7Test extends BaseTest {
    final static By H_2_CITY_COUNTRY_HEADER = By.xpath("//div[@id = 'weather-widget']//h2");
    final static By SEARCH_CITY_FIELD = By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']");
    final static By SEARCH_BUTTON = By.xpath("//button[@type = 'submit']");
    final static By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
    final static By PARIS_FR_CHOICE_FROM_DROPDOWN_MENU = By.xpath("//div[@id = 'weather-widget']//ul[@class = 'search-dropdown-menu']//span[text() = 'Paris, FR ']");
    final static By GUIDE_DESKTOP_MENU = By.xpath("//nav[@id = 'nav-website']//div[@id = 'desktop-menu']//a[@href = '/guide']");
    final static By CELSIUS_FAHRENHEITS_SWITCH_FAHRENHEITS =  By.xpath("//div[@class='page-container']//div[text() = 'Imperial: °F, mph']");
    final static By CELSIUS_FAHRENHEITS_SWITCH_CELSIUS = By.xpath("//div[@class='page-container']//div[text() = 'Metric: °C, m/s']");
    final static By TEMPERATURE_UNDER_COUNTRY_NAME = By.xpath("//div[@class='section-content']//span[@class = 'heading']");
    final static By COOKIE_WARNING_TEXT = By.xpath("//div[@class = 'stick-footer-panel']//p[@class = 'stick-footer-panel__description']");
    final static By COOKIE_WARNING_ALLOW_ALL_BUTTON = By.xpath("//div[@class = 'stick-footer-panel']//button");
    final static By COOKIE_WARNING_MANAGE_COOKIES_BUTTON = By.xpath("//div[@class = 'stick-footer-panel']//a[@rel = 'noopener noreferrer']");
    final static By SUPPORT_DROPDOWN =  By.xpath("//div[@id = 'support-dropdown']");
    final static By SUPPORT_DROPDOWN_FAQ = By.xpath("//ul[@class = 'dropdown-menu dropdown-visible']//a[@href='/faq']");
    final static By SUPPORT_DROPDOWN_HOW_TO_START =  By.xpath("//ul[@class = 'dropdown-menu dropdown-visible']/li/a[@href = '/appid']");
    final static By SUPPORT_DROPDOWN_ASK_QUESTION = By.xpath("//ul[@class = 'dropdown-menu dropdown-visible']/li/a[@href = 'https://home.openweathermap.org/questions']");
    final static By EMAIL_FIELD_FROM_ASK_QUESTION = By.xpath("//input[@id = 'question_form_email']");
    final static By SUBJECT_FIELD_FROM_ASK_QUESTION = By.xpath("//div[@class = 'form-group select required question_form_subject']//select[@id = 'question_form_subject']/option[@value = 'Other']");
    final static By MESSAGE_FIELD_FROM_ASK_QUESTION = By.xpath("//div[@class = 'form-group text required question_form_message']//textarea");
    final static By SUBMIT_BUTTON_FROM_ASK_QUESTION = By.xpath("//input[@value = 'Submit']");
    final static By ERROR_CAPTCHA = By.xpath("//div[@class = 'help-block']");
    final static By LOGO_MAIN = By.xpath("//img[@src='/themes/openweathermap/assets/img/logo_white_cropped.png']");


    private void waitForGreyFrameDisappeared(){
        getWait20().until(ExpectedConditions
                .invisibilityOfElementLocated(
                        By.className("owm-loader-container")));
    }


    @Test
        public void testH2TagText_WhenSearchingCityCountry(){
            String cityName = "Paris";
            String expectedResult = "Paris, FR";

            openBaseURL();
            waitForGreyFrameDisappeared();
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

    @Test
    public void test_ConfirmGuidePage() {
        String urlGuidePageExpectedResult = "https://openweathermap.org/guide";
        String titleGuideExpectedResult = "OpenWeatherMap API guide - OpenWeatherMap";

        openBaseURL();
        waitForGreyFrameDisappeared();
        click(GUIDE_DESKTOP_MENU);
        String urlGuidePageActualResult = getDriver().getCurrentUrl();
        String titleGuideActualResult = getDriver().getTitle();

        Assert.assertEquals(urlGuidePageActualResult, urlGuidePageExpectedResult);
        Assert.assertEquals(titleGuideActualResult, titleGuideExpectedResult);
    }

    @Test
    public void test_ConfirmFarenheits() {
        String fahrenheitExpectedResult = "°F";

        openBaseURL();
        waitForGreyFrameDisappeared();
        click(CELSIUS_FAHRENHEITS_SWITCH_FAHRENHEITS);
        getWait10();
        boolean visibilityOfFahrenheits = getDriver()
                .findElement(TEMPERATURE_UNDER_COUNTRY_NAME).getText().contains(fahrenheitExpectedResult);

        Assert.assertTrue(visibilityOfFahrenheits);
    }

    @Test
    public void test_Cookies() {
        String textExpectedResult = "We use cookies which are essential for the site to work." +
                " We also use non-essential cookies to help us improve our services. Any data collected is anonymised." +
                " You can allow all cookies or manage them individually.";
        String allowAllButtonExpectedResult = "Allow all";
        String manageCookiesButtonExpectedResult = "Manage cookies";

        openBaseURL();
        waitForGreyFrameDisappeared();

        Assert.assertEquals(getDriver().findElement(COOKIE_WARNING_TEXT).getText(), textExpectedResult);
        Assert.assertEquals(getDriver().findElement(COOKIE_WARNING_ALLOW_ALL_BUTTON).getText(), allowAllButtonExpectedResult);
        Assert.assertEquals(getDriver().findElement(COOKIE_WARNING_MANAGE_COOKIES_BUTTON).getText(), manageCookiesButtonExpectedResult);
    }

    @Test
    public void test_SupportDropDown() {
        ArrayList<String> expectedResult = new ArrayList<>(Arrays.asList("FAQ", "How to start", "Ask a question"));

        openBaseURL();
        waitForGreyFrameDisappeared();
        getDriver().manage().window().maximize();
        click(SUPPORT_DROPDOWN);
        ArrayList<String> actualResult = new ArrayList<>(Arrays.asList(
                getDriver().findElement(SUPPORT_DROPDOWN_FAQ).getText(),
                getDriver().findElement(SUPPORT_DROPDOWN_HOW_TO_START).getText(),
                getDriver().findElement(SUPPORT_DROPDOWN_ASK_QUESTION).getText()
        ));

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void test_ErrorHandlingCaptcha() {
        String userEmail = "user@gmail.com";
        String textMessage = "Hi, i like to walk in the morning";
        String captchaErrorTextExpectedResult = "reCAPTCHA verification failed, please try again.";

        openBaseURL();
        waitForGreyFrameDisappeared();
        getDriver().manage().window().maximize();
        click(SUPPORT_DROPDOWN);
        click(SUPPORT_DROPDOWN_ASK_QUESTION);
        String oldTab = getDriver().getWindowHandle();
        ArrayList<String> newTab = new ArrayList<>(getDriver().getWindowHandles());
        newTab.remove(oldTab);
        getDriver().switchTo().window(newTab.get(0));
        click(EMAIL_FIELD_FROM_ASK_QUESTION);
        input(userEmail, EMAIL_FIELD_FROM_ASK_QUESTION);
        click(SUBJECT_FIELD_FROM_ASK_QUESTION);
        WebElement subjectChoose = getDriver().findElement(By.xpath("//option[@value = 'Initiatives']"));
        subjectChoose.click();
        click(MESSAGE_FIELD_FROM_ASK_QUESTION);
        input(textMessage, MESSAGE_FIELD_FROM_ASK_QUESTION);
        click(SUBMIT_BUTTON_FROM_ASK_QUESTION);

        Assert.assertEquals(getDriver().findElement(ERROR_CAPTCHA).getText(), captchaErrorTextExpectedResult);
    }

    @Test
    public void test_ConfirmUnitsAreSwitched(){
        String expectedFahrenheits = "°F";
        String expectedCelsius = "°C";

        openBaseURL();
        waitForGreyFrameDisappeared();
        click(CELSIUS_FAHRENHEITS_SWITCH_FAHRENHEITS);

        Assert.assertTrue(getDriver().findElement(CELSIUS_FAHRENHEITS_SWITCH_FAHRENHEITS).getText().contains(expectedFahrenheits));

        click(CELSIUS_FAHRENHEITS_SWITCH_CELSIUS);

        Assert.assertTrue(getDriver().findElement(CELSIUS_FAHRENHEITS_SWITCH_CELSIUS).getText().contains(expectedCelsius));
    }

    @Test
    public void test_urlNotChanged() {
        openBaseURL();
        click(LOGO_MAIN);
        String actualResultURL = getCurrentURL();

        Assert.assertEquals(actualResultURL, BASE_URL);
    }


}
