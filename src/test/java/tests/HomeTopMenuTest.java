package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

public class HomeTopMenuTest extends BaseTest {

    @Test
    public void testNavTabLinksAmount() {
        int expectedNavTabLinksAmount = 9;

        HomePage homePage = new HomePage(getDriver());
        int actualNavTabLinksAmount = openBaseURL()
                .signIn()
                .getNavTabLinksAmount();

        Assert.assertEquals(actualNavTabLinksAmount, expectedNavTabLinksAmount);

        homePage.signOut();
    }
}
