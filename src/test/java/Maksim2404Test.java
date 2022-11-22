import org.openqa.selenium.By;
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
}