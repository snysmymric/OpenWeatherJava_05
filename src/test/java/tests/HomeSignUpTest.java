package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomeSignUpTest extends BaseTest {

    @Test
    public void testErrorMessageWhenCreatingNewAccountWithoutCaptcha() {
        final String expectedReCaptchaErrorMessage = "reCAPTCHA verification failed, please try again.";

        String actualReCaptchaErrorMessage = openBaseURL()
                .clickSignInMenu()
                .clickCreateAnAccountLink()
                .clickClearInputNewUsername()
                .clickClearInputNewUserEmail()
                .clickClearInputNewUserPassword()
                .clickClearInputRepeatPassword()
                .clickAgeConfirmCheckbox()
                .clickAgreementCheckbox()
                .clickCreateAccountButton()
                .getErrorCaptchaMessage();

        Assert.assertEquals(actualReCaptchaErrorMessage, expectedReCaptchaErrorMessage);
    }
}
