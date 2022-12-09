package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

import java.util.List;

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

    @Test
    public void testNavTabLinks() {
        List<String> expectedURLs = List.of(
                "https://home.openweathermap.org/", "https://home.openweathermap.org/myservices",
                "https://home.openweathermap.org/api_keys", "https://home.openweathermap.org/subscriptions",
                "https://home.openweathermap.org/payments",
                "https://home.openweathermap.org/blocks",
                "https://home.openweathermap.org/marketplace/my_orders",
                "https://home.openweathermap.org/home", "https://home.openweathermap.org/questions");

        List<String> actualURLs = openBaseURL()
                .signIn()
                .clickNavTabLinks();

        Assert.assertEquals(actualURLs, expectedURLs);
    }
}
