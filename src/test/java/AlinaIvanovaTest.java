import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

public class AlinaIvanovaTest extends BaseTest {
    private final By MENU_OUR_INITIATIVES = By.xpath("//div[@id= 'desktop-menu']//a[@href= '/our-initiatives']");
    private final By MENU_GUIDE = By.xpath("//div[@id= 'desktop-menu']//a[@href= '/guide']");

    @Test
    public void testOurInitiativesMenu() {

        final String expectedLink = "https://openweathermap.org/our-initiatives";

        openBaseURL();
        click(MENU_OUR_INITIATIVES);

        String actualLink = getCurrentURL();

        Assert.assertEquals(actualLink, expectedLink);
    }

    @Test
    public void testGuidePageTitleAndURL(){
        final String expectedURL = "https://openweathermap.org/guide";
        final String expectedPageTitle = "OpenWeatherMap API guide - OpenWeatherMap";

        openBaseURL();
        final String oldPageTitle = getTitle();

        click(MENU_GUIDE);
        waitURLToBeChanged(BASE_URL);
        waitTitleToBeChanged(oldPageTitle);

        String actualURL= getCurrentURL();
        String actualPageTitle = getTitle();

        Assert.assertEquals(actualURL, expectedURL);
        Assert.assertEquals(actualPageTitle, expectedPageTitle);
    }
}
