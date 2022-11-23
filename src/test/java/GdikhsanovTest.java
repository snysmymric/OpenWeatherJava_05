import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GdikhsanovTest extends BaseTest {

    final static String BASE_URL = "https://openweathermap.org/";
    final static By H1_PRICING_PAGE = By.className("breadcrumb-title");
    final static By MENU_PRICING_BUTTON = By.xpath("//div[@id='desktop-menu']//a[@href='/price']");
    final static By MENU_GUIDE_BUTTON = By.xpath("//div[@id='desktop-menu']//a[@href='/guide']");

    @Test
    public void testTitleAndUrl_WhenGoToGuide_gdikhsanov() {

        String expectedUrl = String.format("%sguide", BASE_URL);
        String expectedTitle = "OpenWeatherMap API guide - OpenWeatherMap";

        openBaseURL();
        click(MENU_GUIDE_BUTTON);

        String currentUrl = getDriver().getCurrentUrl();
        String currentTitle = getDriver().getTitle();

        Assert.assertEquals(currentUrl, expectedUrl);
        Assert.assertEquals(currentTitle, expectedTitle);
    }

    @Test
    public void testVerifyThePagePricingOpened_WhenClickPricingBtnAtMenu_gdikhsanov() {

        String expectedUrl = String.format("%sprice", BASE_URL);
        String expectedH1Text = "Pricing";

        openBaseURL();

        click(MENU_PRICING_BUTTON);

        String currentUrl = getDriver().getCurrentUrl();
        String currentH1Text = getText(H1_PRICING_PAGE);

        Assert.assertEquals(currentUrl, expectedUrl);
        Assert.assertEquals(currentH1Text, expectedH1Text);
    }
}