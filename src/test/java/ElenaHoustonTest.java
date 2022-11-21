import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

public class ElenaHoustonTest extends BaseTest {
    final static By H2_CITY_COUNTRY_HEADER = By.xpath("//div[@id='weather-widget']//h2");
    final static By SEARCH_CITY_FIELD = By.xpath("//div[@id='weather-widget']//input[@placeholder='Search city']");
    final static By SEARCH_BUTTON = By.xpath("//div[@id='weather-widget']//button[@type='submit']");
    final static By SUPPORT_DROPDOWN = By.id("support-dropdown");
    final static By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
    final static By SUPPORT_DROPDOWN_MENU = By.xpath("//ul[@id='support-dropdown-menu']/li");
    final static By PARIS_CHOICE_IN_DROP_DOWN_MENU = By.xpath(
            "//ul[@class ='search-dropdown-menu']/li/span[text() = 'Paris, FR ']");
    final static By GUIDE_MENU = By.xpath("//div[@id = 'desktop-menu']//a[@href = '/guide']");
    final static By PUSH_IMPERIAL_FAHRENHEIT = By.xpath("//div[@class = 'switch-container']//div"
            + "[text()= 'Imperial: °F, mph']");
    final static By TEMP_IN_F = By.xpath("//div[@class='current-temp']/span");
    final static By SEARCH_SUPPORT = By.xpath("//div[@id = 'support-dropdown']");
    final static By FIND_SUB_1 = By.xpath("//ul[@class ='dropdown-menu dropdown-visible']//"
            + "a[@href = '/faq']");
    final static By FIND_SUB_2 = By.xpath("//ul[@class ='dropdown-menu dropdown-visible']//"
            + "a[@href = '/appid']");
    final static By FIND_SUB_3 = By.xpath("//ul[@class ='dropdown-menu dropdown-visible']//"
            + "a[text() = 'Ask a question']");
    private int dropDownSize(By by, WebDriver driver) {

        return driver.findElements(by).size();
    }

    @Test
    public void testH2TagText_WhenSearchingCityCountry() {
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        openBaseURL();

        String oldH2Header = getText(H2_CITY_COUNTRY_HEADER);

        click(SEARCH_CITY_FIELD);
        input(cityName, SEARCH_CITY_FIELD);
        click(SEARCH_BUTTON);
        waitElementToBeVisible(SEARCH_DROPDOWN_MENU);
        click(PARIS_CHOICE_IN_DROP_DOWN_MENU);
        waitTextToBeChanged(H2_CITY_COUNTRY_HEADER, oldH2Header);
        String actualResult = getText(H2_CITY_COUNTRY_HEADER);

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testConfirmTheLinkAndTitleOnThePage_OpenWeatherMap() {

        String expectedResult1 = "https://openweathermap.org/guide";

        String expectedResult2 = "OpenWeatherMap API guide - OpenWeatherMap";

      openBaseURL();
      click(GUIDE_MENU);

        String actualResult1 = getDriver().getCurrentUrl();
        String actualResult2 = getDriver().getTitle();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
    }
    @Test
    public void testUnitsOfMeasurement_ImperialFMphConfirmFLondon() {

        String fTempSymbol = "°F";
        String expectedResult = "°F";

        openBaseURL();

        click(PUSH_IMPERIAL_FAHRENHEIT);
        getText(TEMP_IN_F);

        String actualResult = getText(TEMP_IN_F).substring((getText(TEMP_IN_F).length()-2));

        Assert.assertTrue(getText(TEMP_IN_F).contains(fTempSymbol));
        Assert.assertEquals(actualResult, expectedResult);
    }
    @Test
    public void testCheckPresenceOfTreeSubMenusInTheHead () {

        String expectedResult1 = "FAQ";
        String expectedResult2 = "How to start";
        String expectedResult3 = "Ask a question";

        openBaseURL();
        click(SUPPORT_DROPDOWN);

        Assert.assertEquals(dropDownSize(SUPPORT_DROPDOWN_MENU, getDriver()), 3);

        String actualResult1 = getText(FIND_SUB_1);
        String actualResult2 = getText(FIND_SUB_2);
        String actualResult3 = getText(FIND_SUB_3);

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
        Assert.assertEquals(actualResult3, expectedResult3);
    }
}