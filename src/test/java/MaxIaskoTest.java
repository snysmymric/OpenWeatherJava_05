import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class MaxIaskoTest extends BaseTest {
    final static String BASE_URL = "https://openweathermap.org/";
    final static By SEARCH_API_PAGE = By.xpath("//div[@id = 'desktop-menu']//a[text()= 'API']");
    final static By SEARCH_ORANGE_ELEMENT = By.xpath("//a[contains(@class, 'btn_block orange round') " +
            "or contains(@class, 'ow-btn round btn-orange')]");
    final static By SEARCH_LOGO_FIELD = By.xpath("//a[@href ='/']");

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

    @Test
    public void testSearch30OrangeButtons() {
        final int expectedResult = 30;

        getDriver().get(BASE_URL);

        waitGrayFrameDisappear();
        click(SEARCH_API_PAGE, getWait5());

        Assert.assertEquals(size(SEARCH_ORANGE_ELEMENT, getWait5()), expectedResult);
    }

    @Test
    public void testConfirmLinkNoChangeByLogoClick() {
        getDriver().get(BASE_URL);

        waitGrayFrameDisappear();
        click(SEARCH_LOGO_FIELD);

        Assert.assertEquals(getDriver().getCurrentUrl(), BASE_URL);
    }
}






