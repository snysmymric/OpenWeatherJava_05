import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

import java.util.List;


public class LiudmilaPlucciTest extends BaseTest {
    private final By H_2_CITY_COUNTRY_HEADER = By.xpath("//div[@id='weather-widget']//h2");
    private final By SEARCH_CITY_FIELD = By.xpath("//div[@id='weather-widget']//input[@placeholder='Search city']");
    private final By SEARCH_BUTTON = By.xpath("//div[@id='weather-widget']//button[@type='submit']");
    private final By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
    private final By PARIS_FR_CHOICE_IN_DROPDOWN_MENU = By.xpath("//ul[@class=\"search-dropdown-menu\"]//li//span[text() = 'Paris, FR ']");
    private final By MENU_PRICING_BUTTON = By.xpath("//div[@id='desktop-menu']//a[@href='/price']");
    private final By BREADCRUMB_ENDPOINT = By.className("breadcrumb__leaf");
    private final By FAQ_BUTTON = By.xpath("//a[@href='/faq#onecall']");
    private final By FAQ_H1_HEADER = By.xpath("//h1[@class='breadcrumb-title']");
    private final By FAQ_H3_HEADER = By.xpath("//div[@class='col-sm-12']/section/h3");


    @Test
    public void testH2TagText_WhenSearchingCityCountry() {
        final String cityName = "Paris";
        final String expectedResult = "Paris, FR";

        openBaseURL();

        final String oldH2Header = getText(H_2_CITY_COUNTRY_HEADER);

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
    public void test_VerifyThePagePricingOpened_WhenClickPricingBtnAtMenu_BreadcrumbsPricing() {
        final String expectedUrl = String.format("%sprice", BASE_URL);
        final String expectedBreadcrumbEndpoint = "Pricing";

        openBaseURL();

        click(MENU_PRICING_BUTTON);

        String currentUrl = getDriver().getCurrentUrl();
        String currentBreadcrumbEndpoint = getText(BREADCRUMB_ENDPOINT);

        Assert.assertEquals(currentUrl, expectedUrl);
        Assert.assertEquals(currentBreadcrumbEndpoint, expectedBreadcrumbEndpoint);
    }

    @Test
    public void test_VerifyFAQPageIsOpened() {
        final String expectedUrl = String.format("%sfaq#onecall", BASE_URL);
        final String expectedBreadcrumbEndpoint = "Frequently Asked Questions";
        final String expectedH1Header = "Frequently Asked Questions";

        openBaseURL();
        click(MENU_PRICING_BUTTON);
        click(FAQ_BUTTON);

        String currentUrl = getDriver().getCurrentUrl();
        String currentH1Header = getText(FAQ_H1_HEADER);
        String currentBreadcrumbEndpoint = getText(BREADCRUMB_ENDPOINT);

        Assert.assertEquals(currentUrl, expectedUrl);
        Assert.assertEquals(currentH1Header, expectedH1Header);
        Assert.assertEquals(currentBreadcrumbEndpoint, expectedBreadcrumbEndpoint);
    }

    @Test
    public void test_VerifyIfAllSectionsExistOnThePageFAQ() {
        final int expectedH3HeadersAmount = 10;

        openBaseURL();
        click(MENU_PRICING_BUTTON);
        click(FAQ_BUTTON);
        int actualH3HeadersAmount = getListSize(FAQ_H3_HEADER);

        Assert.assertEquals(actualH3HeadersAmount, expectedH3HeadersAmount);
    }
}
