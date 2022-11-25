import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

public class VeraFesTest extends BaseTest {

    private final By LOGO = By.xpath("//li[@class='logo']/a");
    private final By LOGO_IMAGE = By.xpath("//a[@href]//img");
    private final By NAME_WEBSITE = By.xpath("//div[@class='section where-to']//span[@class='orange-text']");

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

    @Test
    public void testLogoLink() {
        final String attribute = "href";
        final String expectedResultLogoLink = "/";

        openBaseURL();

        waitElementToBeVisible(LOGO);

        String actualResultLogoLink = getTextByAttribute(LOGO, attribute);

        Assert.assertTrue(actualResultLogoLink.contains(expectedResultLogoLink));
    }

    @Test
    public void testImageLogo() {
        final String attribute = "src";
        final String expectedResultLogoImage = "/themes/openweathermap/assets/img/logo_white_cropped.png";

        openBaseURL();
        waitElementToBeVisible(LOGO_IMAGE);

        String actualResultLogoImage = getTextByAttribute(LOGO_IMAGE, attribute);

        Assert.assertTrue(actualResultLogoImage.contains(expectedResultLogoImage));
    }
}
