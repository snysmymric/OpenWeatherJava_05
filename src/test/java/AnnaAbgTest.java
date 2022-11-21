import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

public class AnnaAbgTest extends BaseTest {

    private final By API_LINK_PAGE = By.xpath("//a[@href='/api']");
    private final By BUTTONS_COUNT = By.xpath("// a[contains(@class,'orange')]");
    private final By SEARCH_PARTNERS_MENU = By.xpath("//div[@id='desktop-menu']//a[@href='/examples']");
    private final By SEARCH_BREADCRUMB_TITLE = By.xpath("//div[@class='col-sm-7']/h1");

    @Test
    public void testThirtyOrangeButtons() {

        final int expectedNumberOfButtons = 30;

        openBaseURL();

        click(API_LINK_PAGE);

        int actualNumberOfButtons = countOrangeButtons(BUTTONS_COUNT);

        Assert.assertEquals(actualNumberOfButtons, expectedNumberOfButtons);
    }

    @Test
    public void testPartnersAndSolutionsLinkPage() {

        final String expectedCurrentURL = "https://openweathermap.org/examples";

        openBaseURL();

        click(SEARCH_PARTNERS_MENU);

        String actualResultCurrentURL = getCurrentURL();

        Assert.assertEquals(actualResultCurrentURL, expectedCurrentURL);
    }

    @Test
    public void testBreadcrumbTitle() {

        final String expectedTitle = "Partners and solutions - OpenWeatherMap";

        openBaseURL();

        click(SEARCH_PARTNERS_MENU);

        String actualTitle = getTitle();

        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @Test
    public void testBreadcrumbTitle2() {

        final String expectedResultTitle = "Partners and solutions";

        openBaseURL();

        click(SEARCH_PARTNERS_MENU);

        String actualResultTitle2 = getText(SEARCH_BREADCRUMB_TITLE);
        waitElementToBeVisible(SEARCH_BREADCRUMB_TITLE);

        Assert.assertEquals(actualResultTitle2, expectedResultTitle);
    }
}
