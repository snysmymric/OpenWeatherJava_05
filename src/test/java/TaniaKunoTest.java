import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaniaKunoTest extends BaseTest {

    final static By H2_CITY_COUNTRY_HEADER = By.xpath("//div[@id = 'weather-widget']//h2");
    final static By SEARCH_CITY_FIELD = By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']");
    final static By SEARCH_BUTTON = By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']");
    final static By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
    final static By PARIS_FR_CHOICE_IN_DROPDOWN_MENU = By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']");
    final static By SUPPORT_MENU = By.xpath("//div[@id = 'support-dropdown']");
    final static By ASK_A_QUESTION_SUBMENU = By.xpath("//ul[@class = 'dropdown-menu dropdown-visible']//a[text() = 'Ask a question']");
    final static By H4_ASK_A_QUESTION_HEADLINE = By.xpath("//div[@class = 'row']//h4");
    final static By LABELS_ASK_A_QUESTION_FORM = By.xpath("//div[@class = 'row']//label");
    final static By LABELS_IS_USER_FIELD = By.xpath("//div[@class = 'col-sm-8']//span//label");
    final static By CHECKBOX_IS_USER_FIELD = By.xpath("//div[@class = 'col-sm-8']//span//input");
    final static By TEXT_IS_USER_YES = By.xpath("//div[@id = 'prompt']");
    final static By SUBMIT_BUTTON = By.xpath("//input[@value= 'Submit']");
    final static By TEXT_RECAPTCHA_FAILED = By.xpath("//div[@class = 'help-block']");
    final static By WEATHER_MAPS = By.xpath("//a[@href = '/api#maps']");
    final static By HEADLINE_WEATHER_MAPS = By.xpath("//h1[@class = 'breadcrumb-title']");
    final static By LINK_HOME = By.xpath("//ol[@class = 'breadcrumb pull-right hidden-xs']//a[text()= 'Home']");

    @Test
    public void testH2TagText_WhenSearchingCityCountry() {
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        openBaseURL();

        String oldH2Header = getText(H2_CITY_COUNTRY_HEADER);

        click(SEARCH_CITY_FIELD);
        input(cityName, SEARCH_CITY_FIELD);
        click(SEARCH_BUTTON);
        waitElementToBeVisible(SEARCH_DROPDOWN_MENU);
        click(PARIS_FR_CHOICE_IN_DROPDOWN_MENU);
        waitTextToBeChanged(H2_CITY_COUNTRY_HEADER, oldH2Header);

        String actualResult = getText(H2_CITY_COUNTRY_HEADER);

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testOpenPageAskAQuestion_WhenClickAskAQuestionSubmenu() {
        String expectedResult = "https://home.openweathermap.org/questions";

        openBaseURL();

        click(SUPPORT_MENU);
        click(ASK_A_QUESTION_SUBMENU);
        jumpToNextWindow();

        String actualResult = getCurrentURL();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testVerifyHeadlineOfForm_OnAskAQuestionPage() {
        final String expectedHeadline = "Ask a question";

        openBaseURL();
        click(SUPPORT_MENU);
        click(ASK_A_QUESTION_SUBMENU);
        jumpToNextWindow();

        Assert.assertTrue(
                getDriver().findElement(
                        By.className("headline")).isDisplayed());

        String actualHeadline = getText(H4_ASK_A_QUESTION_HEADLINE);

        Assert.assertEquals(actualHeadline, expectedHeadline);
    }

    @Test
    public void testVerifyRequiredFieldsOfForm_OnAskAQuestionPage() {
        final List<String> expectedFields = new ArrayList<>(
                Arrays.asList("Are you an OpenWeather user?", "Yes", "No", "* Email", "* Subject", "* Message"));

        openBaseURL();
        click(SUPPORT_MENU);
        click(ASK_A_QUESTION_SUBMENU);
        jumpToNextWindow();

        Assert.assertTrue(
                getDriver().findElement(LABELS_ASK_A_QUESTION_FORM).isDisplayed());

        List<String> actualFields = getListText(LABELS_ASK_A_QUESTION_FORM);

        Assert.assertEquals(actualFields, expectedFields);
    }

    @Test
    public void testVerifyOptionsInIsUserField_OnAskAQuestionForm() {
        List<String> expectedLabels = new ArrayList<>(Arrays.asList("Yes", "No"));
        String expectedText = "Please enter your account email in our system - it will help us process your request faster";

        openBaseURL();
        click(SUPPORT_MENU);
        click(ASK_A_QUESTION_SUBMENU);
        jumpToNextWindow();

        List<WebElement> labelsIsUserField = getListOfElements(LABELS_IS_USER_FIELD);
        List<WebElement> checkboxesIsUserField = getListOfElements(CHECKBOX_IS_USER_FIELD);

        Assert.assertEquals(labelsIsUserField.size(), 2);
        Assert.assertEquals(checkboxesIsUserField.size(), 2);

        checkboxesIsUserField.get(0).click();
        waitElementToBeVisible(TEXT_IS_USER_YES);

        List<String> actualLabels = getListText(LABELS_IS_USER_FIELD);
        String actualText = getText(TEXT_IS_USER_YES);

        Assert.assertTrue(isDisplayed(LABELS_IS_USER_FIELD));
        Assert.assertEquals(actualLabels, expectedLabels);
        Assert.assertEquals(actualText, expectedText);
    }

    @Test
    public void testVerifyButtonSubmit_OnAskAQuestionForm() {
        String buttonText = "Submit";
        String expectedText = "reCAPTCHA verification failed, please try again.";

        openBaseURL();
        click(SUPPORT_MENU);
        click(ASK_A_QUESTION_SUBMENU);
        jumpToNextWindow();

        Assert.assertTrue(
                isDisplayed(
                        By.xpath("//div[@class = 'container']//*[@* = '" + buttonText + "']")));

        click(SUBMIT_BUTTON);
        waitElementToBeVisible(TEXT_RECAPTCHA_FAILED);

        String actualText = getText(TEXT_RECAPTCHA_FAILED);

        Assert.assertEquals(actualText, expectedText);
    }

    @Test
    public void testLinkWeatherMap() {
        final String expectedURL = "https://openweathermap.org/api#maps";

        openBaseURL();
        scrollToPageBottom();
        click(WEATHER_MAPS);

        String actualURL = getCurrentURL();

        Assert.assertEquals(actualURL, expectedURL);
    }

    @Test
    public void testVerifyHeadline_OnWeatherMapsPage() {
        final String expectedHeadline = "Weather API";

        openBaseURL();
        scrollToPageBottom();
        click(WEATHER_MAPS);
        scrollToElement(HEADLINE_WEATHER_MAPS);

        Assert.assertTrue(
                isDisplayed(HEADLINE_WEATHER_MAPS));

        String actualHeadline = getText(HEADLINE_WEATHER_MAPS);

        Assert.assertEquals(actualHeadline, expectedHeadline);
    }

    @Test
    public void testVerifyLinkHome_OnWeatherAPIPage() {
        final String expectedCurrentLink = "https://openweathermap.org/";

        openBaseURL();
        scrollToPageBottom();
        click(WEATHER_MAPS);
        scrollToElement(LINK_HOME);
        click(LINK_HOME);

        String actualCurrentLink = getCurrentURL();

        Assert.assertEquals(actualCurrentLink, expectedCurrentLink);
    }
}
