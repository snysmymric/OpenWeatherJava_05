
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class Elena_STest extends BaseTest {
    final static String BASE_URL ="https://openweathermap.org/";
    final static By NAVIGATION_BAR = By.xpath("//nav[@id='nav-website']");
    final static By LOGO_IMAGE =
            By.xpath("//li[@class='logo']//img[@src='/themes/openweathermap/assets/img/logo_white_cropped.png']");
    final static By SEARCH_FIELD = By.xpath("//div[@id='desktop-menu']//input[@name='q']");
    final static By GUID_BUTTON = By.xpath("//div[@id='desktop-menu']/ul/li/a[@href='/guide']");
    final static By API_BUTTON = By.xpath("//div[@id='desktop-menu']//a[@href='/api']");
    final static By DASHBOARD_BUTTON = By.xpath("//div[@id='desktop-menu']//a[@href='/weather-dashboard']");
    final static By MARKETPLACE_BUTTON = By.xpath("//div[@id='desktop-menu']//a[@href='https://home.openweathermap.org/marketplace']");
    final static By PRICING_BUTTON = By.xpath("//div[@id='desktop-menu']//a[@href='/price']");
    final static By MAPS_BUTTON = By.xpath("//div[@id='desktop-menu']//a[@href='/weathermap']");
    final static By OUR_INITIATIVES_BUTTON = By.xpath("//div[@id='desktop-menu']//a[@href='/our-initiatives']");
    final static By PARTNERS_BUTTON = By.xpath("//div[@id='desktop-menu']//a[@href='/examples']");
    final static By BLOG_BUTTON = By.xpath("//div[@id='desktop-menu']//a[@href='https://openweather.co.uk/blog/category/weather']");
    final static By FOR_BUSINESS_BUTTON = By.xpath("//div[@id='desktop-menu']//a[@class='marketplace']");
    final static By SING_IN_BUTTON = By.xpath("//div[@id='desktop-menu']//a[@href='https://openweathermap.org/home/sign_in']");
    final static By SUPPORT_BUTTON = By.xpath("//div[@id='support-dropdown']");
    private void openBaseUrl(){
        getDriver().get(BASE_URL);
    }

    private boolean isElementExists(By by){
        return  getWait5().until(ExpectedConditions.visibilityOfElementLocated(by)).isDisplayed();
    }

    private void waitForGrayFrameDisappeared(){
        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("own-loader-container")));
    }

    private void clickElement(By by, WebDriver driver){
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(by));
        getWait5().until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    @Test
    public void testOpenWeatherStartPageOpened(){
        String expectedResultTitle = "Сurrent weather and forecast - OpenWeatherMap";
        String expectedResultUrl = "https://openweathermap.org/";
        openBaseUrl();
        String actualResultTitle = getDriver().getTitle();
        String actualResultUrl = getDriver().getCurrentUrl();

        Assert.assertEquals(actualResultTitle,expectedResultTitle);
        Assert.assertEquals(actualResultUrl,expectedResultUrl);
    }

    @Test
    public void   testVerifyAllElementsOfNavigationBarAreVisible(){
        openBaseUrl();

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
        openBaseUrl();
        waitForGrayFrameDisappeared();
        clickElement(LOGO_IMAGE,getDriver());

        Assert.assertTrue(isElementExists(LOGO_IMAGE));
        Assert.assertTrue(getDriver().findElement(LOGO_IMAGE).isEnabled());
        Assert.assertEquals(getDriver().getCurrentUrl(),BASE_URL);
    }

}

