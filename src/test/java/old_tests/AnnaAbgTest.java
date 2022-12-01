package old_tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import base.BaseTest;

@Ignore
public class AnnaAbgTest extends BaseTest {

    private final By API_DESKTOP_MENU = By.xpath("//a[@href='/api']");
    private final By BUTTONS_COUNT = By.xpath("// a[contains(@class,'orange')]");
    private final By PARTNERS_DESKTOP_MENU = By.xpath("//div[@id='desktop-menu']//a[@href='/examples']");
    private final By SEARCH_BREADCRUMB_TITLE = By.xpath("//div[@class='col-sm-7']/h1");
    private final By APACHE_CAMEL_HYPERLINK = By.xpath("//a[@href='#apache']");
    private final By SEE_ON_WEBSITE_BUTTON = By.xpath("//a[@href='http://camel.apache.org/weather.html']");
    private final By COLOR_AND_FONT = By.xpath("//div[@class='mobile-padding']/h1/span");

    @Test
    public void testThirtyOrangeButtons() {

        final int expectedNumberOfButtons = 30;

        openBaseURL();

        click(API_DESKTOP_MENU);

        int actualNumberOfButtons = countElements(BUTTONS_COUNT);

        Assert.assertEquals(actualNumberOfButtons, expectedNumberOfButtons);
    }

    @Test
    public void testPartnersAndSolutionsLinkPage() {

        final String expectedCurrentURL = "https://openweathermap.org/examples";

        openBaseURL();

        click20(PARTNERS_DESKTOP_MENU);

        String actualCurrentURL = getCurrentURL();

        Assert.assertEquals(actualCurrentURL, expectedCurrentURL);
    }

    @Test
    public void testBreadcrumbTitle() {

        final String expectedTitle = "Partners and solutions - OpenWeatherMap";

        openBaseURL();

        click(PARTNERS_DESKTOP_MENU);

        String actualTitle = getTitle();

        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @Test
    public void testBreadcrumbTitle_InnerText() {

        final String expectedTitle = "Partners and solutions";

        openBaseURL();

        click(PARTNERS_DESKTOP_MENU);

        String actualTitle2 = getText(SEARCH_BREADCRUMB_TITLE);
        waitElementToBeVisible(SEARCH_BREADCRUMB_TITLE);

        Assert.assertEquals(actualTitle2, expectedTitle);
    }

    @Test
    public void testActiveHyperlinkButton_SeeOnTheWebsite() {

        final String expectedURL = "https://camel.apache.org/components/next/weather-component.html";

        openBaseURL();

        click(PARTNERS_DESKTOP_MENU);
        click20(APACHE_CAMEL_HYPERLINK);
        click20(SEE_ON_WEBSITE_BUTTON);
        switchToAnotherWindow(getDriver());

        Assert.assertEquals(getCurrentURL(), expectedURL);
    }


    @Test
    public void testOpenWeatherMainPageHeader_ColorAndFontSize() {

        final String expectedColor = "rgba(0, 0, 0, 0)";
        final String expectedFontSize = "45px";

        openBaseURL();

        Assert.assertEquals(getBackgroundColor(COLOR_AND_FONT), expectedColor);
        Assert.assertEquals(getFontSize(COLOR_AND_FONT), expectedFontSize);
    }
}
