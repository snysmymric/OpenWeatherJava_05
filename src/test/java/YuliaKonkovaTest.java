import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class YuliaKonkovaTest extends BaseTest {
    final static String BASE_URL = "https://openweathermap.org/";
    final static By H2_CITY_NAME_HEADER = By.xpath("//div[@id='weather-widget']//h2");
    final static By SEARCH_CITY_FIELD = By.xpath("//div[@id = 'weather-widget']//input[@placeholder='Search city']");
    final static By BUTTON = By.xpath("//div[@id='weather-widget']//button[@type='submit']");
    final static By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
    final static By PARIS_FR_CHOICE_IN_DROPDOWN_MENU =
            By.xpath("//ul[@class='search-dropdown-menu']/li/span[text()='Paris, FR ']");
    final static By SUPPORT_DROPDOWNMENU_IN_NAVBAR = By.xpath("//div[@id='support-dropdown']");
    final static By SUPPORT_DROPDOWNMENU = By.xpath("//ul[@id='support-dropdown-menu']");
    final static By SUBMENUES_OF_SUPPORT_LIST = By.xpath("//ul[@class='dropdown-menu dropdown-visible']/li");
    final static By FIRST_SUBMENUE_OF_SUPPORT =
            By.xpath("//ul[@class='dropdown-menu dropdown-visible']/li/a[@href='/faq']");
    final static By SECOND_SUBMENUE_OF_SUPPORT =
            By.xpath("//ul[@class='dropdown-menu dropdown-visible']/li/a[@href='/appid']");
    final static By THIRD_SUBMENUE_OF_SUPPORT =
            By.xpath("//ul[@class='dropdown-menu dropdown-visible']/li" +
                    "/a[@href='https://home.openweathermap.org/questions']");
    final static By ButtonsInUpperNavigationBar = By.xpath("//div[@id='desktop-menu']/ul/li");
    final static By GUIDE_BUTTON = By.xpath("//div[@id='desktop-menu']//ul/li/a[@href='/guide']");
    final static By API_BUTTON = By.xpath("//div[@id='desktop-menu']//ul/li/a[@href='/api']");
    final static By DASHBOARD_BUTTON = By.xpath("//div[@id='desktop-menu']//ul/li/a[@href='/weather-dashboard']");
    final static By MARKETPLACE_BUTTON = By.xpath(
            "//div[@id='desktop-menu']//ul/li/a[@href='https://home.openweathermap.org/marketplace']");
    final static By PRICING_BUTTON = By.xpath("//div[@id='desktop-menu']//ul/li/a[@href='/price']");
    final static By MAPS_BUTTON = By.xpath("//div[@id='desktop-menu']//ul/li/a[@href='/weathermap']");
    final static By OUR_INITIATIVES_BUTTON = By.xpath("//div[@id='desktop-menu']//ul/li/a[@href='/our-initiatives']");
    final static By PARTNERS_BUTTON = By.xpath("//div[@id='desktop-menu']//ul/li/a[@href='/examples']");
    final static By BLOG_BUTTON = By.xpath(
            "//div[@id='desktop-menu']//ul/li/a[@href='https://openweather.co.uk/blog/category/weather']");
    final static By FOR_BUSINESS_BUTTON = By.xpath("//div[@id='desktop-menu']//ul/li/a[@href='https://openweather.co.uk']");
    final static By SIGN_IN_BUTTON = By.xpath(
            "//div[@id='desktop-menu']//ul/li/a[@href='https://openweathermap.org/home/sign_in']");
    final static By SUPPORT_BUTTON = By.xpath("//div[@id='desktop-menu']/ul/li/div[@id='support-dropdown']");

    @Test
    public void testH2TagText_WhenSearchingCityCountry(){
        final String cityName = "Paris";
        final String expectedResult = "Paris, FR";

        openBaseURL();
        String oldH2Header = getText(H2_CITY_NAME_HEADER);

        click(SEARCH_CITY_FIELD);
        input(cityName, SEARCH_CITY_FIELD);
        click(BUTTON);
        waitElementToBeVisible(SEARCH_DROPDOWN_MENU);
        click(PARIS_FR_CHOICE_IN_DROPDOWN_MENU);
        waitTextToBeChanged(H2_CITY_NAME_HEADER, oldH2Header);

        String actualResult = getText(H2_CITY_NAME_HEADER);

        Assert.assertEquals(actualResult,expectedResult);
    }

    @Test
    public void testMenuSupportHasThreeSubmenus() {
        final int expectedNumberOfSubmenues = 3;
        final String expectedFirstSubmenue = "FAQ";
        final String expectedSecondSubmenue = "How to start";
        final String expectedThirdSubmenue = "Ask a question";

        openBaseURL();
        click(SUPPORT_DROPDOWNMENU_IN_NAVBAR);
        waitElementToBeVisible(SUBMENUES_OF_SUPPORT_LIST);
        Assert.assertTrue(getDriver().findElement(SUPPORT_DROPDOWNMENU).isDisplayed());

        int actualNumberOfSubmenues = getDriver().findElements(SUBMENUES_OF_SUPPORT_LIST).size();
        Assert.assertEquals(actualNumberOfSubmenues,expectedNumberOfSubmenues);

        Assert.assertEquals(getText(FIRST_SUBMENUE_OF_SUPPORT),expectedFirstSubmenue);
        Assert.assertEquals(getText(SECOND_SUBMENUE_OF_SUPPORT), expectedSecondSubmenue);
        Assert.assertEquals(getText(THIRD_SUBMENUE_OF_SUPPORT), expectedThirdSubmenue);

        click(SUPPORT_DROPDOWNMENU_IN_NAVBAR);
        Assert.assertFalse(getDriver().findElement(SUPPORT_DROPDOWNMENU).isDisplayed());
    }

    @Test
    public void testVerifyQuantityOfElementsInNavigationBar_VisibleAndEnabled(){
        openBaseURL();

        List <By> buttonsInNavigationBarMenu = new ArrayList<>(){{
            add(GUIDE_BUTTON);
            add(API_BUTTON);
            add(DASHBOARD_BUTTON);
            add(MARKETPLACE_BUTTON);
            add(PRICING_BUTTON);
            add(MAPS_BUTTON);
            add(OUR_INITIATIVES_BUTTON);
            add(PARTNERS_BUTTON);
            add(BLOG_BUTTON);
            add(FOR_BUSINESS_BUTTON);
            add(SIGN_IN_BUTTON);
            add(SUPPORT_BUTTON);
        }};

        for (By buttons: buttonsInNavigationBarMenu) {
            waitElementToBeVisible(buttons);
            Assert.assertTrue(isDisplayed(buttons));
            Assert.assertTrue(isElementEnabled(buttons));
        }

        int expectedNumberOfButtonsInNavigationBar = 12;
        int actualNumberOfButtonsInNavigationBar = buttonsInNavigationBarMenu.size();
        Assert.assertEquals(actualNumberOfButtonsInNavigationBar,expectedNumberOfButtonsInNavigationBar);
    }
}
