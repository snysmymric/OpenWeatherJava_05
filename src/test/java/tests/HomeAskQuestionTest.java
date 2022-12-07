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
}
