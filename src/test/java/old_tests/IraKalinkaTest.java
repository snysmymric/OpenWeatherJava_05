package old_tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import base.BaseTest;

import java.util.Iterator;
import java.util.List;

public class IraKalinkaTest extends BaseTest {

    private final By SUPPORT_LINK = By.id("support-dropdown");
    private final By DROPDOWN_MENU  = By.xpath("//li[@class='with-dropdown']/ul[@class='dropdown-menu dropdown-visible']");
    private final By TAG_NAME = By.tagName("a");
    private final By ASK_AQUESTION_LINK = By.xpath("//div[@id='desktop-menu']//li/a[@href='https://home.openweathermap.org/questions']");
    private final By EMAIL_TEXTBOX = By.id("question_form_email");
    private final By SUBJECT_TEXTBOX = By.id("question_form_subject");
    private final By MESSAGE_TEXTBOX = By.id("question_form_message");
    private final By SUBMIT_BUTTON = By.xpath("//div[@class='col-sm-8']//input[@type='submit']");
    private final By ERROR_MESSAGE = By.xpath("//div[@class='help-block']");

    @Test
    public void testTitleTagAndUrL() {
        final String pageTitle = "Сurrent weather and forecast - OpenWeatherMap";
        openBaseURL();
        getWait20();
        Assert.assertEquals(getTitle(), pageTitle);
        Assert.assertEquals(getCurrentURL(), BASE_URL);
    }

    @Test
    public void testSupportMenuHasLinks() {
       final List<String> expectedList = List.of("FAQ", "How to start", "Ask a question");
       openBaseURL();
       click(SUPPORT_LINK);
       Assert.assertEquals(getLinksList(DROPDOWN_MENU, TAG_NAME), expectedList);
    }

    @Ignore
    @Test
    public void testCaptchaErrorMessage() {
        final String email = "test@test.com";
              String subject = "Other";
        final String messageText = "Message";
        final String expectedMessage = "reCAPTCHA verification failed, please try again.";

        openBaseURL();
        click(SUPPORT_LINK);
        click(ASK_AQUESTION_LINK);
        iterateWindows();
        waitElementToBeVisible(EMAIL_TEXTBOX);
        input(email, EMAIL_TEXTBOX);
        selectOption(SUBJECT_TEXTBOX,subject);
        input(messageText, MESSAGE_TEXTBOX);
        click(SUBMIT_BUTTON);

        Assert.assertEquals(getText(ERROR_MESSAGE), expectedMessage);
    }
}


