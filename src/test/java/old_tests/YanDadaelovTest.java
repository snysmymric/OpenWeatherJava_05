package old_tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import base.BaseTest;

@Ignore
public class YanDadaelovTest extends BaseTest {

    private final By NAVIGATION_BAR_CONTAINER = By.xpath("//nav[@id='nav-website']");
    private final By NAVIGATION_BAR_ITEMS = By.xpath("//ul[@id='first-level-nav']");
    private final By NAVIGATION_BAR_LOGO = By.xpath(("//li[@class='logo']/a/img"));
    private final By NAVIGATION_BAR_SEARCH_PANEL = By.xpath(("//div[@id='desktop-menu']/form"));
    private final By NAVIGATION_BAR_SEARCH_PANEL_INPUT = By.xpath(("//div[@id='desktop-menu']//input[@type='text']"));
    private final By NAVIGATION_BAR_GUIDE = By.xpath(("//div[@id='desktop-menu']//a[@href='/guide']"));
    private final By NAVIGATION_BAR_API = By.xpath(("//div[@id='desktop-menu']//a[@href='/api']"));
    private final By NAVIGATION_BAR_DASHBOARD = By.xpath(("//div[@id='desktop-menu']//a[@href='/weather-dashboard'] "));
    private final By NAVIGATION_BAR_MARKETPLACE = By.xpath((
            "//div[@id='desktop-menu']//a[contains(@href, 'marketplace')]"));
    private final By NAVIGATION_BAR_PRICING = By.xpath(("//div[@id='desktop-menu']//a[@href='/price']"));
    private final By NAVIGATION_BAR_MAPS = By.xpath(("//div[@id='desktop-menu']//a[@href='/weathermap']"));
    private final By NAVIGATION_BAR_INITIATIVES = By.xpath(("//div[@id='desktop-menu']//a[@href='/our-initiatives']"));
    private final By NAVIGATION_BAR_PARTNERS = By.xpath(("//div[@id='desktop-menu']//a[@href='/examples']"));
    private final By NAVIGATION_BAR_BLOG = By.xpath(("//div[@id='desktop-menu']//a[contains(@href, 'blog')]"));
    private final By NAVIGATION_BAR_FOR_BUSINESS = By.xpath(("//div[@id='desktop-menu']//a[@class='marketplace']"));
    private final By NAVIGATION_BAR_SIGN_IN = By.xpath((
            "//div[@id='desktop-menu']//a[@href='https://openweathermap.org/home/sign_in']"));
    private final By NAVIGATION_BAR_SUPPORT = By.xpath(("//div[@id='desktop-menu']//div"));


    @Test
    public void testStartPageUrl() {
        openBaseURL();
        final String actual_URL = getCurrentURL();

        Assert.assertEquals(actual_URL, BASE_URL);
    }

    @Test
    public void testStartPageTitleText() {
        final String startPageTitle = "Сurrent weather and forecast - OpenWeatherMap";

        openBaseURL();
        final String actual_titleText = getTitle();

        Assert.assertEquals(actual_titleText, startPageTitle);
    }

    @Test
    public void testNavigationBarIsDisplayedOnMainPage() {
        openBaseURL();

        Assert.assertTrue(isDisplayed(NAVIGATION_BAR_CONTAINER)
                && isDisplayed(NAVIGATION_BAR_ITEMS));
    }

    @Test
    public void testNavigationBarElementsIsDisplayed() {
        By[] navigationBarElements = new By[]{NAVIGATION_BAR_LOGO, NAVIGATION_BAR_SEARCH_PANEL,
                NAVIGATION_BAR_SEARCH_PANEL_INPUT, NAVIGATION_BAR_GUIDE, NAVIGATION_BAR_API, NAVIGATION_BAR_DASHBOARD,
                NAVIGATION_BAR_MARKETPLACE, NAVIGATION_BAR_PRICING, NAVIGATION_BAR_MAPS, NAVIGATION_BAR_INITIATIVES,
                NAVIGATION_BAR_PARTNERS, NAVIGATION_BAR_BLOG, NAVIGATION_BAR_FOR_BUSINESS, NAVIGATION_BAR_SIGN_IN,
                NAVIGATION_BAR_SUPPORT
        };

        openBaseURL();

        for (By el : navigationBarElements) {
            waitForElement(el);
            Assert.assertTrue(isDisplayed(el));
        }
    }
}
