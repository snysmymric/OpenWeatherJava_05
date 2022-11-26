import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LinavolovickTest extends BaseTest {
    private final String WEATHER_API_URL = "https://openweathermap.org/api#current";
    private final String WEATHER_API_URL2 = "https://openweathermap.org/api";
    private final By LOGO = By.xpath("//li[@class='logo']/descendant::img");
    private final By CURRENT_AND_FORECAST_APIS_LINK = By.xpath("//div[@id='footer-website']" +
            "//a[@href='/api#current']");
    private final By NAV_GUIDE = By.xpath("//div[@id='desktop-menu']//a[@href='/guide']");
    private final By GUIDE_APIS_LINK = By.xpath("//p[starts-with(text(),'Our weather products')]//a[@href='/api']");


    @Test
    public void testVerifyLogoExistsAndRedirectsToStartPage() {
        final String expectedResult = BASE_URL;

        openBaseURL();
        click(LOGO);

        Assert.assertEquals(getCurrentURL(), expectedResult);
    }

    @Test
    public void testCurrentAndForecastAPIsLink() {
        final String expectedResult = WEATHER_API_URL;

        openBaseURL();
        scrollToPageBottom();
        click(CURRENT_AND_FORECAST_APIS_LINK);

        String actualResult = getDriver().getCurrentUrl();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testGuideAPIsLink() {
        final String expectedAPIsLink = WEATHER_API_URL2;

        openBaseURL();
        click(NAV_GUIDE);
        click(GUIDE_APIS_LINK);

        String actualResult = getDriver().getCurrentUrl();

        Assert.assertEquals(actualResult, expectedAPIsLink);
    }
}