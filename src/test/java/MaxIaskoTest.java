import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import base.BaseTest;

@Ignore
public class MaxIaskoTest extends BaseTest {
    final static String BASE_URL = "https://openweathermap.org/";
    final static By SEARCH_API_PAGE = By.xpath("//div[@id = 'desktop-menu']//a[text()= 'API']");
    final static By SEARCH_ORANGE_ELEMENT = By.xpath("//a[contains(@class, 'btn_block orange round')" +
            "or contains(@class, 'ow-btn round btn-orange')]");
    //final static By SEARCH_LOGO_FIELD = By.xpath("//a[@href ='/']");
    final static By SEARCH_LOGO_FIELD = By.xpath("//ul[@id= 'first-level-nav']/li[@class = 'logo']");
    final static By SEARCH_WHETHER_IN_YOUR_CITY_FIELD = By.xpath(
            "//div[@id='desktop-menu']/form/input[@type ='text']");
    final static By SEARCH_FIELD_TEXT = By.xpath("//div/input[@id='search_str']");

    private void openBaseUrl(WebDriver driver) {
        getDriver().get(BASE_URL);
    }

    private void click(By by) {
        getDriver().findElement(by).click();
    }

    private void click(By by, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
        getDriver().findElement(by).click();
    }

    private void waitGrayFrameDisappear() {
        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("owm-loader-container")));
    }

    private int size(By by, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return getDriver().findElements(by).size();
    }

    private String getAttributeByName(By by, String name) {
        WebElement searchFieldText = getDriver().findElement(by);
        return searchFieldText.getAttribute(name);
    }

    private void jumpToNextWindow() {
        String mainWindows = getDriver().getWindowHandle();
        for (String windowsHandle : getDriver().getWindowHandles()) {
            if (!mainWindows.contentEquals(windowsHandle)) {
                getDriver().switchTo().window(windowsHandle);
                break;
            }
        }
    }

    private boolean isContainsTextIn(String text) {
        String currentUrl = getDriver().getCurrentUrl();
        return currentUrl.contains(text);
    }

    private void searchFieldToSendKey(String key) {
        WebElement searchWhetherInYourCityField = getDriver().findElement(SEARCH_WHETHER_IN_YOUR_CITY_FIELD);
        click(SEARCH_WHETHER_IN_YOUR_CITY_FIELD);
        searchWhetherInYourCityField.sendKeys(key);
        searchWhetherInYourCityField.sendKeys(Keys.ENTER);
    }

    @Test
    public void testSearch30OrangeButtons() {
        final int expectedResult = 30;

        openBaseUrl(getDriver());

        waitGrayFrameDisappear();
        click(SEARCH_API_PAGE, getWait5());

        Assert.assertEquals(size(SEARCH_ORANGE_ELEMENT, getWait5()), expectedResult);
    }

    @Test
    public void testConfirmLinkNoChangeByLogoClick() {

        openBaseUrl(getDriver());

        waitGrayFrameDisappear();
        click(SEARCH_LOGO_FIELD);

        Assert.assertEquals(getDriver().getCurrentUrl(), BASE_URL);
    }

    @Test
    public void testConfirmTextInLink() {
        final String name = "value";
        final String expectedResult0 = "Rome";
        final String expectedResult1 = "find";

        openBaseUrl(getDriver());

        waitGrayFrameDisappear();
        searchFieldToSendKey(expectedResult0);

        Assert.assertEquals(isContainsTextIn(expectedResult0), isContainsTextIn(expectedResult1));

        jumpToNextWindow();

        Assert.assertEquals(getAttributeByName(SEARCH_FIELD_TEXT, name), expectedResult0);
    }
}






