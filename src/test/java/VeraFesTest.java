import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

public class VeraFesTest extends BaseTest {

    final static By LOGO =
            By.xpath("//a[@href]//img[@src='/themes/openweathermap/assets/img/logo_white_cropped.png']");
    final static By NAME_WEBSITE = By.xpath("//div[@class='section where-to']//span[@class='orange-text']");

    @Test
    public void testLogo_WhenClickRedirects2Start() {
        final String expectedResultTitle = "OpenWeather";
        final String expectedResultURL = BASE_URL;

        openBaseURL();

        waitElementToBeVisible(LOGO);
        click(LOGO);

        String actualResultURL = getDriver().getCurrentUrl();

        Assert.assertEquals(actualResultURL, expectedResultURL);

        String actualResultTitle = getText(NAME_WEBSITE);

        Assert.assertEquals(actualResultTitle, expectedResultTitle);
    }
}
