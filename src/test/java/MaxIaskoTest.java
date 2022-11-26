import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

import java.util.List;

public class MaxIaskoTest extends BaseTest {
    final static By SEARCH_API_PAGE = By.xpath("//div[@id='desktop-menu']//a[text()='API']");
    final static By SEARCH_ORANGE_ELEMENT = By.xpath("//a[contains(@class, 'btn_block orange round')" +
            "or contains(@class, 'ow-btn round btn-orange')]");
    final static By SEARCH_LOGO_FIELD = By.xpath("//ul[@id='first-level-nav']/li[@class ='logo']");
    final static By SEARCH_WHETHER_IN_YOUR_CITY_FIELD = By.xpath(
            "//div[@id='desktop-menu']/form/input[@type='text']");
    final static By SEARCH_FIELD_TEXT = By.xpath("//div/input[@id='search_str']");
    final static By SEARCH_BLOG_FOOTER_LINK = By.xpath(
            "//div[@class='footer-section not-foldable']/div/ul//li/a[@target='_blank']");
    final static By SEARCH_ELEMENTS_CONTAINER = By.xpath("//ul[@id='blog-categories']/li");
    final static By SEARCH_SIGN_IN_LINK = By.xpath(
            "//li/a[@href='https://openweathermap.org/home/sign_in']");
    final static By SEARCH_EMAIL_FIELD = By.xpath("//input[@id='user_email']");
    final static By SEARCH_PASSWORD_FIELD = By.xpath("//input[@id='user_password']");
    final static By SEARCH_SUBMIT_BUTTON = By.xpath("//div/form/input[@value='Submit']");
    final static By SEARCH_ALERT_MESSAGE = By.xpath("//div[@class='panel-body']");
    final static By SEARCH_CREATE_AN_ACCOUNT_LINK = By.xpath("//div/p/a[@href='/users/sign_up']");
    final static By SEARCH_USERNAME_FIELD = By.xpath("//input[@id='user_username']");
    final static By SEARCH_ENTER_EMAIL_FIELD = By.xpath("//input[@id='user_email']");
    final static By SEARCH_PASSWORD1_FIELD = By.xpath("//input[@id='user_password']");
    final static By SEARCH_REPEAT_PASSWORD_FIELD = By.xpath("//input[@id='user_password_confirmation']");
    final static By SEARCH_AGREEMENT_ACCEPTED_FIELD = By.xpath("//input[@id='agreement_is_accepted']");
    final static By SEARCH_CREATE_ACCOUNT_BUTTON = By.xpath("//div/input[@type='submit']");
    final static By SEARCH_RECOVERY_DATA_LINK = By.xpath("//div[@class ='pwd-lost-q show']/a");
    final static By SEARCH_SEND_BUTTON = By.xpath("//input[@id='user_email']");
    final static By SEARCH_POPUP_MESSAGE = By.xpath("//div[@class='panel-body']");

    @Test
    public void testSearch30OrangeButtons() {
        final int expectedResult = 30;

        openBaseURL();
        click(SEARCH_API_PAGE);

        Assert.assertEquals(countElements(SEARCH_ORANGE_ELEMENT), expectedResult);
    }

    @Test
    public void testConfirmLinkNoChangeByLogoClick() {

        openBaseURL();
        click(SEARCH_LOGO_FIELD);

        Assert.assertEquals(getCurrentURL(), BASE_URL);
    }

    @Test
    public void testConfirmTextInLink() {
        final String name = "value";
        final String expectedResult0 = "Rome";
        final String expectedResult1 = "find";

        openBaseURL();
        inputAndEnter(SEARCH_WHETHER_IN_YOUR_CITY_FIELD, expectedResult0);

        Assert.assertTrue(isContainsTextInUrl(expectedResult0));
        Assert.assertTrue(isContainsTextInUrl(expectedResult1));

        jumpToNextWindow();

        Assert.assertEquals(getTextByAttribute(SEARCH_FIELD_TEXT, name), expectedResult0);
    }

    @Test
    public void test6LinksPresentEnable() {
        final List<String> expectedLinkList = List.of(
                "ALL", "WEATHER", "AGRO", "PLATFORM", "TECHNOLOGIES", "TEAM&COMPANY");

        openBaseURL();

        scrollByVisibleElement(SEARCH_BLOG_FOOTER_LINK);
        click(SEARCH_BLOG_FOOTER_LINK);
        jumpToNextWindow();

        Assert.assertEquals(getListSize(SEARCH_ELEMENTS_CONTAINER), expectedLinkList.size());

        Assert.assertEquals(getListText(SEARCH_ELEMENTS_CONTAINER), expectedLinkList);

        Assert.assertTrue(checkIfAllElementsAreVisibleAndClickable(SEARCH_ELEMENTS_CONTAINER));
    }

    @Test
    public void testInvalidEmailDataInput() {
        final String[] testEmailDataList =
                {" ", "E mail@mail.com", "EMAIL", ".email", "@email", "12345", "£$%", "почта", "@ ."};
        final String expectedAlertMessage = "Invalid Email or password.";
        final String pass = "B4-emm9PXu2WhdR";

        openBaseURL();
        click(SEARCH_SIGN_IN_LINK);
        jumpToNextWindow();

        for (String email : testEmailDataList) {
            clear(SEARCH_EMAIL_FIELD);
            input(email, SEARCH_EMAIL_FIELD);
            input(pass, SEARCH_PASSWORD_FIELD);
            click(SEARCH_SUBMIT_BUTTON);

            Assert.assertEquals(getText(SEARCH_ALERT_MESSAGE), expectedAlertMessage);
        }
    }

    @Test
    public void testRegistrationUnder16() {
        final String user = "tester";
        final String email = "tester@gmail.com";
        final String pass = "&#KFHLx6d.)n+)z";

        openBaseURL();
        
        click(SEARCH_SIGN_IN_LINK);
        jumpToNextWindow();
        click(SEARCH_CREATE_AN_ACCOUNT_LINK);
        jumpToNextWindow();
        input(user, SEARCH_USERNAME_FIELD);
        input(email, SEARCH_ENTER_EMAIL_FIELD);
        input(pass, SEARCH_PASSWORD1_FIELD);
        input(pass, SEARCH_REPEAT_PASSWORD_FIELD);
        click(SEARCH_AGREEMENT_ACCEPTED_FIELD);

        Assert.assertFalse(isElementEnabled(SEARCH_CREATE_ACCOUNT_BUTTON));
    }

    @Test
    public void testDataRecoveringEmptyFields() {
        final String email = "tester@gmail.com";
        final String popUpMessage = "Invalid Email or password.";

        openBaseURL();

        click(SEARCH_SIGN_IN_LINK);
        jumpToNextWindow();
        click(SEARCH_RECOVERY_DATA_LINK);
        jumpToNextWindow();
        inputAndEnter(SEARCH_SEND_BUTTON, email);
        jumpToNextWindow();
        getText(SEARCH_POPUP_MESSAGE);

        Assert.assertEquals(getText(SEARCH_POPUP_MESSAGE), popUpMessage);
    }
}






