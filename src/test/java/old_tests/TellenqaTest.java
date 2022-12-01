package old_tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import base.BaseTest;

import java.util.ArrayList;
import java.util.List;

@Ignore
public class TellenqaTest extends BaseTest {

    final static String PRICE_URL = "https://openweathermap.org/price";

    final static By H_2_CITY_COUNTRY_HEADER = By.xpath("//div[@id='weather-widget']//h2");
    final static By SEARCH_CITY_FIELD = By.xpath("//div[@id = 'weather-widget']//input[@placeholder='Search city']");
    final static By SEARCH_BUTTON = By.xpath("//div[@id='weather-widget']//button[@type='submit']");
    final static By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
    final static By PARIS_FR_CHOICE_IN_DROPDOWN_MENU = By.xpath("//ul[@class='search-dropdown-menu']/li/span[text()='Paris, FR ']");
    final static By GUIDE_MENU_BUTTON = By.xpath("//div[@id = 'desktop-menu']//a[@href = '/guide']");
    final static By FOOTER_CONTAINER = By.xpath("//div[@class = 'inner-footer-container']");

    @Test
    public void testH2Tag_WhenSearchingCityCountry() {
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        openBaseURL();

        String oldH2Header = getText(H_2_CITY_COUNTRY_HEADER);

        click(SEARCH_CITY_FIELD);
        input(cityName, SEARCH_CITY_FIELD);
        click(SEARCH_BUTTON);
        waitElementToBeVisible(SEARCH_DROPDOWN_MENU);
        click(PARIS_FR_CHOICE_IN_DROPDOWN_MENU);
        waitTextToBeChanged(H_2_CITY_COUNTRY_HEADER, oldH2Header);

        String actualResult = getText(H_2_CITY_COUNTRY_HEADER);

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testGuideMenuOpensGuidePage() {
        String expectedResult = "https://openweathermap.org/guide";

        openBaseURL();

        click(GUIDE_MENU_BUTTON);

        Assert.assertEquals(getDriver().getCurrentUrl(),expectedResult);
    }

    @Test
    public void testFooterContainerIsDisplayed() {
        openBaseURL();

        Assert.assertTrue(getDriver().findElement(FOOTER_CONTAINER).isDisplayed());
    }

    @Test
    public void testH2Headers_UnderProfessionalCollections() {
        int expectedAmountH2Headers = 5;
        List<String> expectedH2Headers = List.of(
                "Current weather and forecasts collection","Special products","Historical weather collection",
                "Special products","Free data for students");

        openBaseURL();
        getDriver().get(PRICE_URL);
        getWait20();

        List<String> actualH2Headers = new ArrayList<>();
        List<WebElement> actualH2HeadersElements = getDriver().findElements(By.xpath("//h2"));
        for(int i = 2; i < actualH2HeadersElements.size(); i++) {
            actualH2Headers.add(actualH2HeadersElements.get(i).getText());
        }

        Assert.assertEquals(actualH2Headers.size(),expectedAmountH2Headers);
        Assert.assertEquals(actualH2Headers, expectedH2Headers);
    }
}