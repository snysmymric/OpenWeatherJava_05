import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

public class NataliiaOliverTest extends BaseTest {

    private final String FAQ_URL = "https://openweathermap.org/faq";

    private final By H2_CITY_COUNTRY_HEADER = By.xpath("//div[@id = 'weather-widget']//h2");
    private final By SEARCH_CITY_FIELD = By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']");
    private final By SEARCH_BUTTON = By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']");
    private final By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
    private final By PARIS_FR_CHOICE_IN_DROPDOWN_MENU = By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']");
    private final By H1_FAQ_TITLE = By.xpath("//main[@class='wrapper']//h1");
    private final By SUBSCRIPTION_FAQ = By.xpath("//div[@id='footer-website']//a[@href='/faq']");

    @Test
    public void testH2TagText_WhenSearchingCityCountry() {
        final String cityName = "Paris";
        final String expectedCityCountryNames = "Paris, FR";

        openBaseURL();

        final String oldH2Header = getText(H2_CITY_COUNTRY_HEADER);

        click(SEARCH_CITY_FIELD);
        input(cityName, SEARCH_CITY_FIELD);
        click(SEARCH_BUTTON);
        waitElementToBeVisible(SEARCH_DROPDOWN_MENU);
        click(PARIS_FR_CHOICE_IN_DROPDOWN_MENU);
        waitTextToBeChanged(H2_CITY_COUNTRY_HEADER, oldH2Header);

        String actualCityCountryNames = getText(H2_CITY_COUNTRY_HEADER);

        Assert.assertEquals(actualCityCountryNames, expectedCityCountryNames);
    }

    @Test
    public void testSearchBlockText_ShownSearchCityField() {
        final String expectedTextSearchCityField = "Search city";

        openBaseURL();

        click(SEARCH_CITY_FIELD);

        String actualTextSearchCityField = getTextByAttribute(SEARCH_CITY_FIELD, "placeholder");

        Assert.assertEquals(actualTextSearchCityField, expectedTextSearchCityField);
    }

    @Test
    public void testSearchBlockText_ShownSearchButton() {
        final String expectedTextSearchButton = "Search";

        openBaseURL();

        Assert.assertTrue(isDisplayed(SEARCH_BUTTON));

        click(SEARCH_BUTTON);

        String actualTextSearchButton = getText(SEARCH_BUTTON);

        Assert.assertEquals(actualTextSearchButton, expectedTextSearchButton);
    }

    @Test
    public void testSearchBlockText_ClickableSearchButton() {
        final String cityName = "Baton Rouge";

        openBaseURL();

        click(SEARCH_CITY_FIELD);
        input(cityName, SEARCH_CITY_FIELD);
        click(SEARCH_BUTTON);
        waitElementToBeVisible(SEARCH_DROPDOWN_MENU);

        Assert.assertTrue(isDisplayed(SEARCH_DROPDOWN_MENU));
    }

    @Test
    public void testMainFooterWebsiteSubscriptionFAQ_ClickableFAQLink() {
        final String expectedTitleFaq = "Frequently Asked Questions";

        openBaseURL();

        scrollToPageBottom();

        click20(SUBSCRIPTION_FAQ);

        Assert.assertEquals(getCurrentURL(), FAQ_URL);

        String actualTitleFaq = getText(H1_FAQ_TITLE);

        Assert.assertEquals(actualTitleFaq, expectedTitleFaq);
    }
}
