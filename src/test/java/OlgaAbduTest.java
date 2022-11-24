import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;


public class OlgaAbduTest extends BaseTest {

    private final By SEARCH_SUPPORT_BUTTON = By.xpath("//div[@id = 'support-dropdown']");
    private final By OPTION_DROPDOWN_FAQ = By.xpath("//ul[@id='support-dropdown-menu']//a[contains(text(),'FAQ')]");
    private final By OPTION_DROPDOWN_HOW_TO_START = By
            .xpath("//ul[@id='support-dropdown-menu']//a[contains(text(),'How to start')]");
    private final By OPTION_DROPDOWN_ASK_QUESTIONS = By
            .xpath("//ul[@id='support-dropdown-menu']//a[contains(text(),'Ask a question')]");
    private final By HEADER_FAQ_PAGE = By.xpath("//h1[contains(text(),'Frequently Asked Questions')]");
    private final By HEADER_HOW_TO_START_PAGE = By.xpath("//h1[contains(text(),'How to start ')]");
    private final By NEW_QUESTION_FORM = By.xpath("//form[@id='new_question_form']");
    public final By ENTER_EMAIL_FIELD = By.xpath("//div[@class='input-group']/input[@placeholder='Enter email']");
    public final By BUTTON_SUBMIT = By.xpath("//input[@class='btn btn-default btn-color'][@value='Submit']");
    public final By ENTER_PASSWORD_FIELD = By.xpath("//div[@class='input-group']/input[@placeholder='Password']");
    public final By SEARCH_SIGN_IN_BUTTON = By.xpath("//ul[@id='first-level-nav']//a[contains(text(),'Sign in')]");
    public final By API_KEY_INPUT = By.id("api_key_form_name");
    public final By API_KEY_GET_NAME = By.xpath("//tbody/tr/td[contains(text(),'Default')]");
    public final By SIGN_IN_OK = By.xpath("//div[contains(text(),'Signed in successfully.')]");
    public final By GENERATE_BUTTON = By.xpath("//input[@class='button-round dark']");
    public final By API_CREATED_MESSAGE = By.xpath("//div[contains(text(),'API key was created successfully')]");
    public final By API_TAB = By.xpath("//ul[@id='myTab']//a[@href='/api_keys']");

    @Test
    public void testSupportButtonClickable() {

        openBaseURL();
        click(SEARCH_SUPPORT_BUTTON);
        click(OPTION_DROPDOWN_FAQ);
        waitElementToBeVisible(HEADER_FAQ_PAGE);
        Assert.assertTrue(getCurrentURL().contains("https://openweathermap.org/faq"));

        click(SEARCH_SUPPORT_BUTTON);
        click(OPTION_DROPDOWN_HOW_TO_START);
        waitElementToBeVisible(HEADER_HOW_TO_START_PAGE);
        Assert.assertTrue(getCurrentURL().contains("https://openweathermap.org/appid"));

        click(SEARCH_SUPPORT_BUTTON);
        click(OPTION_DROPDOWN_ASK_QUESTIONS);
        switchToAnotherWindow(getDriver());
        waitElementToBeVisible(NEW_QUESTION_FORM);
        Assert.assertTrue(getCurrentURL().contains("https://home.openweathermap.org/questions"));
    }

    @Test
    public void testGenerateNewApiKey() {
        final String LOGIN = "jka59433@xcoxc.com";
        final String PASSWORD = "Tester12#";
        final String API_URL = "https://home.openweathermap.org/api_keys";

        openBaseURL();
        click(SEARCH_SIGN_IN_BUTTON);
        input(LOGIN, ENTER_EMAIL_FIELD);
        input(PASSWORD, ENTER_PASSWORD_FIELD);
        click20(BUTTON_SUBMIT);
        Assert.assertTrue(isDisplayed((SIGN_IN_OK)));

        click(API_TAB);
        Assert.assertTrue(isContainsTextInUrl(API_URL));

        CopyAndPast(API_KEY_GET_NAME, API_KEY_INPUT);
        click(GENERATE_BUTTON);
        Assert.assertTrue(isDisplayed(API_CREATED_MESSAGE));
    }
}
