
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

public class Elena_STest extends BaseTest {
    private final   String GUID_URL = "https://openweathermap.org/guide";
    private final  By NAVIGATION_BAR = By.xpath("//nav[@id='nav-website']");
    private final  By LOGO_IMAGE =
            By.xpath("//li[@class='logo']//img[@src='/themes/openweathermap/assets/img/logo_white_cropped.png']");
    private final  By SEARCH_FIELD = By.xpath("//div[@id='desktop-menu']//input[@name='q']");
    private final  By GUID_BUTTON = By.xpath("//div[@id='desktop-menu']/ul/li/a[@href='/guide']");
    private final  By API_BUTTON = By.xpath("//div[@id='desktop-menu']//a[@href='/api']");
    private final  By DASHBOARD_BUTTON = By.xpath("//div[@id='desktop-menu']//a[@href='/weather-dashboard']");
    private final  By MARKETPLACE_BUTTON = By.xpath("//div[@id='desktop-menu']//a[@href='https://home.openweathermap.org/marketplace']");
    private final  By PRICING_BUTTON = By.xpath("//div[@id='desktop-menu']//a[@href='/price']");
    private final  By MAPS_BUTTON = By.xpath("//div[@id='desktop-menu']//a[@href='/weathermap']");
    private final  By OUR_INITIATIVES_BUTTON = By.xpath("//div[@id='desktop-menu']//a[@href='/our-initiatives']");
    private final  By PARTNERS_BUTTON = By.xpath("//div[@id='desktop-menu']//a[@href='/examples']");
    private final  By BLOG_BUTTON = By.xpath("//div[@id='desktop-menu']//a[@href='https://openweather.co.uk/blog/category/weather']");
    private final  By FOR_BUSINESS_BUTTON = By.xpath("//div[@id='desktop-menu']//a[@class='marketplace']");
    private final  By SING_IN_BUTTON = By.xpath("//div[@id='desktop-menu']//a[@href='https://openweathermap.org/home/sign_in']");
    private final  By SUPPORT_BUTTON = By.xpath("//div[@id='support-dropdown']");

    @Test
    public void testOpenWeatherStartPageOpened(){
        String expectedResultTitle = "Сurrent weather and forecast - OpenWeatherMap";
        String expectedResultUrl = "https://openweathermap.org/";
        openBaseURL();
        String actualResultTitle = getTitle();
        String actualResultUrl = getCurrentURL();

        Assert.assertEquals(actualResultTitle,expectedResultTitle);
        Assert.assertEquals(actualResultUrl,expectedResultUrl);
    }
    @Test
    public void   testVerifyAllElementsOfNavigationBarAreVisible(){
        openBaseURL();

        Assert.assertTrue(isElementExists(NAVIGATION_BAR));
        Assert.assertTrue(isElementExists(LOGO_IMAGE));
        Assert.assertTrue(isElementExists(SEARCH_FIELD));
        Assert.assertTrue(isElementExists(GUID_BUTTON));
        Assert.assertTrue(isElementExists(API_BUTTON));
        Assert.assertTrue(isElementExists(DASHBOARD_BUTTON));
        Assert.assertTrue(isElementExists(MARKETPLACE_BUTTON));
        Assert.assertTrue(isElementExists(PRICING_BUTTON));
        Assert.assertTrue(isElementExists(MAPS_BUTTON));
        Assert.assertTrue(isElementExists(OUR_INITIATIVES_BUTTON));
        Assert.assertTrue(isElementExists(PARTNERS_BUTTON));
        Assert.assertTrue(isElementExists(BLOG_BUTTON));
        Assert.assertTrue(isElementExists(FOR_BUSINESS_BUTTON));
        Assert.assertTrue(isElementExists(SING_IN_BUTTON));
        Assert.assertTrue(isElementExists(SUPPORT_BUTTON));
    }
    @Test
    public void testLogoExistsAndClickableUserAndStartPageRefresh(){
        openBaseURL();
        click(LOGO_IMAGE);

        Assert.assertTrue(isElementExists(LOGO_IMAGE));
        Assert.assertTrue(isElementEnabled(LOGO_IMAGE));
        Assert.assertEquals(getCurrentURL(),BASE_URL);
    }

    @Test
    public void testRedirectToStartPageAfterLogoClick(){
        openURL(GUID_URL);
        click(LOGO_IMAGE);

        Assert.assertEquals(getCurrentURL(),BASE_URL);
    }

}

