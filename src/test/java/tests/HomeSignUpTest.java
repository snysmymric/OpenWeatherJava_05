package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomeSignUpTest extends BaseTest {

    @Test
    public void testSubscribeForFreeConfirmLinkIsClickableAndOpened2() {
        final String expectedLinkSubscribeFreeWorks = "https://home.openweathermap.org/users/sign_up";

        String actualUrl = openBaseURL_ReturnMainPage()
                .scrollToPageSubscribeFreeButton()
                .clickSubscribeFreeButton()
                .confirmPageSubscribeFreeOpen()
                .getCurrentURL();

        Assert.assertEquals(actualUrl,expectedLinkSubscribeFreeWorks);
    }
}
