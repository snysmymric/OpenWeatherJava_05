package old_tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import base.BaseTest;

@Ignore
public class AlinaIvanovaTest extends BaseTest {
    private final By MENU_OUR_INITIATIVES = By.xpath("//div[@id= 'desktop-menu']//a[@href= '/our-initiatives']");
    private final By MENU_GUIDE = By.xpath("//div[@id= 'desktop-menu']//a[@href= '/guide']");
    private final By ROAD_RISK_API = By.xpath("//p/a[@href='/api/road-risk']");

    @Test
    public void testOurInitiativesMenu() {

        final String expectedLink = "https://openweathermap.org/our-initiatives";

        openBaseURL();
        click(MENU_OUR_INITIATIVES);

        String actualLink = getCurrentURL();

        Assert.assertEquals(actualLink, expectedLink);
    }

    @Test
    public void testGuidePageTitleAndURL() {
        final String expectedURL = "https://openweathermap.org/guide";
        final String expectedPageTitle = "OpenWeatherMap API guide - OpenWeatherMap";

        openBaseURL();
        final String oldPageTitle = getTitle();

        click(MENU_GUIDE);
        waitURLToBeChanged(BASE_URL);
        waitTitleToBeChanged(oldPageTitle);

        String actualURL = getCurrentURL();
        String actualPageTitle = getTitle();

        Assert.assertEquals(actualURL, expectedURL);
        Assert.assertEquals(actualPageTitle, expectedPageTitle);
    }

    @Test
    public void testPageRoadRiskAPI() {
        final String expectedPageTitle = "Road Risk - OpenWeatherMap";

        openBaseURL();
        click(MENU_GUIDE);
        waitURLToBeChanged(BASE_URL);
        click(ROAD_RISK_API);

        String actualPageTitle = getTitle();

        Assert.assertEquals(actualPageTitle, expectedPageTitle);
    }
}
