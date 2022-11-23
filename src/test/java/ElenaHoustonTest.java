import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

import java.util.ArrayList;
import java.util.List;

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
    final static By MAP_MENU = By.xpath("//div[@id='desktop-menu']//a[@href='/weathermap']");
    /*
    final static By WEATHER_CONTROL_LAYERS = By.xpath("//div[@class='weather-control-layers-new "
           + "weather-control-layers-expanded leaflet-control']");

     */
    final static By ZOOM_PLUS_MAP_PAGE = By.xpath("//a[@class='leaflet-control-zoom-in']");
    final static By ZOOM_MINUS_MAP_PAGE = By.xpath("//a[@class='leaflet-control-zoom-out']");
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
    @Test
    public void testMapMenu (){

        String expectedCurrentURL = "https://openweathermap.org/weathermap";
        String expectedResultTitle = "Interactive weather maps - OpenWeatherMap";

        openBaseURL();
        click(MAP_MENU);
        getWait10().until(ExpectedConditions.urlContains("http"));


        String actualCurrentURL = getCurrentURL();
        String actualResultTitle = getTitle();

        Assert.assertTrue(actualCurrentURL.contains(expectedCurrentURL));
        Assert.assertEquals(actualResultTitle, expectedResultTitle);
    }
    /*
    @Test
    public void testWeatherControlLayers_OnTeWeatherMapPage(){
        int expectedAmountOfLabels = 6;
        List<String> expectedLabels = new ArrayList<>();

        expectedLabels.add("Temperature");
        expectedLabels.add("Pressure");
        expectedLabels.add("Wind speed");
        expectedLabels.add("Clouds");
        expectedLabels.add("Global Precipitation");
        expectedLabels.add("Cities");
        String mapURL = "https://openweathermap.org/weathermap";

        openBaseURL();
        getDriver().get(mapURL);

        Assert.assertTrue(isElementExists(WEATHER_CONTROL_LAYERS));

        List<String> actualLabels = new ArrayList<>();

        List<WebElement> actualLabelsElements = getDriver().findElements(By.xpath("//div[@class='weather-control-"
               + "layers-new weather-control-layers-expanded leaflet-control']"));


        Assert.assertEquals(actualLabels, expectedLabels);
        Assert.assertEquals(actualLabelsElements.size(), expectedAmountOfLabels);
    }
     */
    @Test
    public void testZoomLeafletControl_OnTheMapPage() {

        String mapURL = "https://openweathermap.org/weathermap";
        String expectedZoomIN = "+";
        String expectedZoomOut = "-";

        openBaseURL();
        getDriver().get(mapURL);

        click(ZOOM_MINUS_MAP_PAGE);
        getWait10().until(ExpectedConditions.urlContains("http"));
        click(ZOOM_PLUS_MAP_PAGE);

        String actualZoomIN = getDriver().findElement(ZOOM_PLUS_MAP_PAGE).getText();
        String actualZoomOUT = getDriver().findElement(ZOOM_MINUS_MAP_PAGE).getText();

        Assert.assertEquals(actualZoomIN, expectedZoomIN);
        Assert.assertEquals(actualZoomOUT, expectedZoomOut);
    }
}