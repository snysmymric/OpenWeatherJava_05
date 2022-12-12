package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomeSignInPage;
import pages.HomeSignUpPage;
import pages.HomeUsersPage;

public class HomeSignUpTest extends BaseTest {

    @Test
    public void testCreateNewAccountWithoutCaptcha() {
    openBaseURL().clickSignInMenu();

    HomeSignInPage homeSignInPage = new HomeSignInPage(getDriver());

    homeSignInPage.clickCreateAccountLink();

    HomeSignUpPage homeSignUpPage = new HomeSignUpPage(getDriver());

    homeSignUpPage.clickClearInputNewUsername()
                  .clickClearInputNewUserEmail()
                  .clickClearInputNewUserPassword()
                  .clickClearInputRepeatPassword()
                  .clickAgeConfirmCheckbox()
                  .clickAgreementCheckbox()
                  .clickCreateAccountButton();

    String actualErrorCaptchaMessage = homeSignUpPage.getErrorCaptchaMessage();

    Assert.assertEquals(actualErrorCaptchaMessage, "reCAPTCHA verification failed, please try again.");
    }
}
