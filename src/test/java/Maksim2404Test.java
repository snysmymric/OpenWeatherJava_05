import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

public class Maksim2404Test extends BaseTest {
    final  By H2_CITY_COUNTRY_HEADER = By.xpath("//div[@id='weather-widget']//h2");
    final  By SEARCH_CITY_FIELD = By.xpath("//div[@id='weather-widget']//input[@placeholder='Search city']");
    final  By SEARCH_BUTTON = By.xpath("//div[@id='weather-widget']//button[@type='submit']");
    final  By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
    final  By PARIS_CHOICE_IN_DROP_DOWN_MENU = By.xpath(
            "//ul[@class ='search-dropdown-menu']/li/span[text() = 'Paris, FR ']");
    final By SEARCH_BUTTON1 = By.id("support-dropdown");
    final By CONFIRM_TYPE1 = By.xpath("//ul[@id='support-dropdown-menu']/li/a[@href='/faq']");
    final By CONFIRM_TYPE2 = By.xpath("//ul[@id='support-dropdown-menu']/li/a[@href='/appid']");
    final By CONFIRM_TYPE3 = By.xpath(
            "//ul[@id='support-dropdown-menu']/li/a[@href='https://home.openweathermap.org/questions']");
    final By GET_LOGO = By.xpath("//div[@id='desktop-menu']//a[@href='/weather-dashboard']");
    final By GET_NAME_METRIC = By.xpath("//div[@class='option']");
    final By SELECTED_IS_TRUE = By.xpath("//div[@id='selected']");
    final By WIDGET_NOTIFICATION = By.xpath("//div[@id='weather-widget']//div[@class='widget-notification']");
    final By OPEN_STREET_MAP = By.xpath("//div[@id='weather-widget']//a[@href='https://www.openstreetmap.org/copyright']");
    final By CONFIRM_ELEMENT_IS_VISIBLE_OPEN_STREET_MAP = By.xpath("//div[@id='content']//h1");
    final By SUBSCRIBE_FREE_BUTTON = By.xpath("//a[@href='https://home.openweathermap.org/users/sign_up']");
    final By CONFIRM_SUBSCRIBE_FREE_LINK_OPENED = By.xpath("//h3[@class='first-child']");

    @Test
    public void testH2TextWhenSearchingCityCountry() {
        String cityName = "Paris";
        String expectedCItyCountry = "Paris, FR";

        openBaseURL();

        String oldH2Header = getText(H2_CITY_COUNTRY_HEADER);

        click(SEARCH_CITY_FIELD);
        input(cityName, SEARCH_CITY_FIELD);
        click(SEARCH_BUTTON);
        waitElementToBeVisible(SEARCH_DROPDOWN_MENU);
        click(PARIS_CHOICE_IN_DROP_DOWN_MENU);
        waitTextToBeChanged(H2_CITY_COUNTRY_HEADER, oldH2Header);
        String actualCityCountry = getText(H2_CITY_COUNTRY_HEADER);

        Assert.assertEquals(actualCityCountry, expectedCItyCountry);
    }
    @Test
    public void testConfirmThatSupportHave3Types() {

        String expectedSupportType1 = "FAQ";
        String expectedSupportType2 = "How to start";
        String expectedSupportType3 = "Ask a question";

        openBaseURL();

        click(SEARCH_BUTTON1);

        String actualSupportType1 = getText(CONFIRM_TYPE1);
        String actualSupportType2 = getText(CONFIRM_TYPE2);
        String actualSupportType3 = getText(CONFIRM_TYPE3);

        Assert.assertEquals(actualSupportType1,expectedSupportType1);
        Assert.assertEquals(actualSupportType2, expectedSupportType2);
        Assert.assertEquals(actualSupportType3, expectedSupportType3);
    }
    @Test
    public void testconfirmSite() {

        String expectedConfirmSite = "https://openweathermap.org/weather-dashboard";

        openBaseURL();

        click(GET_LOGO);

        String actualConfirmSite = getDriver().getCurrentUrl();

        Assert.assertEquals(actualConfirmSite,expectedConfirmSite);
    }
    @Test
    public void testVerifyMetricSymbolIsShownInCurrentTemp () {

        String expectedMetricDisplayed = "Metric: °C, m/s";
        Boolean expectedMetricSelected = true;

        openBaseURL();

        String actualMetricDisplayed = getText(GET_NAME_METRIC);

        Assert.assertEquals(actualMetricDisplayed,expectedMetricDisplayed);

        Boolean actualMetricSelected = isDisplayed(SELECTED_IS_TRUE);

        Assert.assertTrue(actualMetricSelected);
    }
    @Test
    public void testXButtonColorGreen () {

        String name = "pref";
        String expectedXButtonGreenColor = "rgba(120, 203, 191, 0.8)";

        openBaseURL();
        click(SEARCH_CITY_FIELD);
        input(name,SEARCH_CITY_FIELD);
        click(SEARCH_BUTTON);
        waitElementToBeVisible(WIDGET_NOTIFICATION);

        String actualXbottonGreenColor = backgroundColor(WIDGET_NOTIFICATION);

        Assert.assertEquals(actualXbottonGreenColor,expectedXButtonGreenColor);
    }
    @Test
    public void testWidgetMapAndLinkOpenStreetMapAreWorking() {

        String expectedLinkIsClickableAndOpened = "https://www.openstreetmap.org/copyright";

        openBaseURL();
        click(OPEN_STREET_MAP);
        jumpToNextWindow();
        waitElementToBeVisible(CONFIRM_ELEMENT_IS_VISIBLE_OPEN_STREET_MAP);

        Assert.assertEquals(getCurrentURL(),expectedLinkIsClickableAndOpened);
    }
    @Test
    public void testSubscribeForFreeConfirmLinkIsClickableAndOpened() {

        String expectedLinkSubscribeFreeWorks = "https://home.openweathermap.org/users/sign_up";

        openBaseURL();
        scrollByVisibleElement(SUBSCRIBE_FREE_BUTTON);

        Assert.assertTrue(isDisplayed(SUBSCRIBE_FREE_BUTTON));

        click(SUBSCRIBE_FREE_BUTTON);
        jumpToNextWindow();

        waitElementToBeVisible(CONFIRM_SUBSCRIBE_FREE_LINK_OPENED);

        Assert.assertEquals(getCurrentURL(),expectedLinkSubscribeFreeWorks);

    }
}