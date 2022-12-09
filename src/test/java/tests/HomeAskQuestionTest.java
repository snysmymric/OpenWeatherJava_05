package tests;

import base.BaseTest;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.bouncycastle.crypto.agreement.srp.SRP6Client;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomeAskQuestionPage;

public class HomeAskQuestionTest extends BaseTest {

    @Test
    public void testCaptchaErrorMessage() {
        final String email = "test@test.com";
        String subject = "Other";
        final String messageText = "Message";
        final String expectedMessage = "reCAPTCHA verification failed, please try again.";

        String actualMessage = openBaseURL()
                .clickSupportMenu()
                .clickAskQuestionSupportSubmenu()
                .inputTextInEmailTextbox(email)
                .selectSubject(subject)
                .inputTextInMessageTextbox(messageText)
                .clickOnSubmitButton()
                .getErrorMessageText();

        Assert.assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void testAskQuestionYesRadioButtonMessage() {
        String expectedMessage =
                "Please enter your account email in our system - it will help us process your request faster";

        String actualMessage = openBaseURL()
                .clickSupportMenu()
                .clickAskQuestionSupportSubmenu()
                .clickYesRadioButton()
                .getRadioButtonText();
        
        Assert.assertEquals(actualMessage, expectedMessage);

    }
}
