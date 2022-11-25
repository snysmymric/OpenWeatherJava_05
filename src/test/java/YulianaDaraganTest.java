import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

public class YulianaDaraganTest extends BaseTest {
    final static String BASE_URL = "https://openweathermap.org/";

    final static By SUPPORT_MENU = By.xpath("//div[@id = 'support-dropdown']");
    final static By ASK_A_QUESTION_SUBMENU_CHOICE_IN_SUPPORT_MENU = By.xpath(
            "//ul[@id = 'support-dropdown-menu']//a[@href = 'https://home.openweathermap.org/questions']");
    final static By EMAIL_FIELD_IN_ASK_A_QUESTION_PAGE= By.xpath("//input[@id = 'question_form_email']");
    final static By SUBJECT_DROPDOWN_LIST_IN_ASK_A_QUESTION_PAGE = By.xpath(
            "//select[@class = 'form-control select required']");
    final static By MESSAGE_FIELD_IN_ASK_A_QUESTION_PAGE= By.xpath("//textarea[@class = 'form-control text required']");
    final static By SUMBIT_BUTTON_IN_ASK_A_QUESTION_PAGE = By.xpath("//input[@name = 'commit']");
    final static By CAPTCHA_HELP_BLOCK_IN_ASK_A_QUESTION_PAGE = By.xpath("//div[@class = 'help-block']");

    final static By LOGO = By.xpath( "//li[@class='logo']//img");
    final static By SIGN_IN_MENU = By.xpath(
            "//li[@class='user-li']/a[@href='https://openweathermap.org/home/sign_in']");

    @Test
    public void testFillAskAQuestion_WithoutCapcha() {
        final String email = "tester@tester.com";
        final String message = "i didn't confirm capcha :)";
        final String expectedText = "reCAPTCHA verification failed, please try again.";

        openBaseURL();
        waitForGrayContainerDisappeared();

        click(SUPPORT_MENU);
        click(ASK_A_QUESTION_SUBMENU_CHOICE_IN_SUPPORT_MENU);
        switchToAnotherWindow(getDriver());
        inputTextAndClickEnter(EMAIL_FIELD_IN_ASK_A_QUESTION_PAGE, email);
        click(SUBJECT_DROPDOWN_LIST_IN_ASK_A_QUESTION_PAGE);
        getDriver().findElement(SUBJECT_DROPDOWN_LIST_IN_ASK_A_QUESTION_PAGE).sendKeys(Keys.ARROW_DOWN);
        inputTextAndClickEnter(MESSAGE_FIELD_IN_ASK_A_QUESTION_PAGE, message);
        click(SUMBIT_BUTTON_IN_ASK_A_QUESTION_PAGE);

        String actualText = getText(CAPTCHA_HELP_BLOCK_IN_ASK_A_QUESTION_PAGE);

        Assert.assertEquals(actualText,expectedText);
    }

    @Test
    public void testLogoChecking_HappyPath() {
        final String expectedLogoImageLink =
                "https://openweathermap.org/themes/openweathermap/assets/img/logo_white_cropped.png";

        openBaseURL();
        waitForGrayContainerDisappeared();

        String actualLogoImageLink = getElement(LOGO).getAttribute("src");

        click(SIGN_IN_MENU);

        Assert.assertNotEquals(getDriver().getCurrentUrl(), BASE_URL);

        click(LOGO);

        Assert.assertTrue(getElement(LOGO).isDisplayed());
        Assert.assertEquals(actualLogoImageLink, expectedLogoImageLink);
        Assert.assertEquals(getDriver().getCurrentUrl(), BASE_URL);
    }
}


