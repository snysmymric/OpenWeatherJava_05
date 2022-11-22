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
}
