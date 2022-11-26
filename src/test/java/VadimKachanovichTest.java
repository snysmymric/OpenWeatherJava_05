import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;
import java.util.ArrayList;
import java.util.List;

public class VadimKachanovichTest extends BaseTest {
    private final By H2_CITY_COUNTRY_HEADER = By.xpath("//div[@id = 'weather-widget']//h2");
    private final By SEARCH_CITY_FIELD = By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']");
    private final By SEARCH_BUTTON = By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']");
    private final By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
    private final By PARIS_FR_DROPDOWN_MENU = By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']");
    private final By DIFFERENT_WEATHER_BUTTON = By.xpath("//span[contains(@class,'control-el owm-switch' ) and contains(text(), 'Different Weather?')]");
    private final By POPUP_MENU_NAME = By.xpath("//div[@class = 'pop-up-container']/div/h3[@id = 'dialogTitle']");
    private final By IMPERIAL_UOM = By.xpath("//div[@id='weather-widget']//div[@class = 'switch-container']/div[contains(text(),'°F')]");
    private final By HEADING_UOM = By.xpath("//div[@id='weather-widget']//div[@class = 'section-content']//span[@class='heading']");
    private final By GREY_CONTAINER_SEARCH_FIELD = By.xpath("//div[@id='weather-widget']//input");
    private final By GREY_CONTAINER_SEARCH_BUTTON = By.xpath("//div[@id='weather-widget']//div[@class = 'search-block']//button");
    private final By GREY_CONTAINER_ICON_CURRENT_LOCATION = By.xpath("//div[@id='weather-widget']//div[@class = 'controls']//*[local-name()= 'svg']");
    private final By METRIC_UOM = By.xpath("//div[@id='weather-widget']//div[@class = 'switch-container']/div[contains(text(),'°C')]");
    private final By GREY_CONTAINER_AREA = By.xpath("//div[@id= 'weather-widget']/div[@class='grey-container']/*");
    @Test
    public void testWhenSearchingCityCountry()  {
        final String cityName = "Paris";
        final String expectedResult = "Paris, FR";
        openBaseURL();
        String startH2Header = getText(H2_CITY_COUNTRY_HEADER);
        click(SEARCH_CITY_FIELD);
        input(cityName,SEARCH_CITY_FIELD);
        click(SEARCH_BUTTON);
        waitElementToBeVisible(SEARCH_DROPDOWN_MENU);
        click(PARIS_FR_DROPDOWN_MENU);
        waitTextToBeChanged(H2_CITY_COUNTRY_HEADER,startH2Header);
        String actualResult = getText(H2_CITY_COUNTRY_HEADER);
        Assert.assertEquals(actualResult,expectedResult);
    }
    @Test
    public void testVerifyPopUpWindowShownWhenClickingDifferentWeatherButton () {
        final String expectedResult = "Different weather";
        openBaseURL();
        click(DIFFERENT_WEATHER_BUTTON);
        String actualResult = getText(POPUP_MENU_NAME);
        Assert.assertEquals(actualResult,expectedResult);
    }
    @Test
    public void testVerifyChangingTempUnitInHeadingWhenSwitchTempUnitButton () {
        final boolean expectedResult = true;
        openBaseURL();
        click(IMPERIAL_UOM);
        boolean actualResult = getText(HEADING_UOM).contains("°F");
        Assert.assertEquals(actualResult,expectedResult);
    }
    @Test
    public void testAllElementsExistsAndVisibleGreyContainer() {
        final int expectedDisplayed = 6;
        int counter = 0;
        openBaseURL();
        getWait20().until(ExpectedConditions.visibilityOfAllElements(getDriver().findElement(GREY_CONTAINER_AREA)));
        List<By> greyElements = new ArrayList<>();
        greyElements.add(GREY_CONTAINER_SEARCH_FIELD);
        greyElements.add(GREY_CONTAINER_SEARCH_BUTTON);
        greyElements.add(GREY_CONTAINER_ICON_CURRENT_LOCATION);
        greyElements.add(DIFFERENT_WEATHER_BUTTON);
        greyElements.add(METRIC_UOM);
        greyElements.add(IMPERIAL_UOM);
        for (By webelement: greyElements) {
            Assert.assertTrue(isElementEnabled(webelement));
            Assert.assertTrue(isDisplayed(webelement));
            counter++;
        }
        Assert.assertEquals(counter, expectedDisplayed);
    }
}
