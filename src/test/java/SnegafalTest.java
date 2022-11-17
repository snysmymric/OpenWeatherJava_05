import org.openqa.selenium.By;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class SnegafalTest extends BaseTest {

    static final String BASE_URL = "https://openweathermap.org/";

    static final By GUIDE_IN_MENU = By.xpath("//ul[@id='first-level-nav']//a[@href='/guide']");
    static final By DIFFERENT_WEATHER_POP_UP = By.xpath("//div[@class='pop-up-container']");
    static final By DIFFERENT_WEATHER_BUTTON = By.xpath("//span[contains(text(), 'Different')]");


    private void waitForGrayFrameDisappeared() {
        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("owm-loader-container")));
    }

    private void waitForDifferentWeatherPopUpAppeared() {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(DIFFERENT_WEATHER_POP_UP));
    }

    private void click(By by, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    @Test
    public void testTitleAndUrlPage_WhenClickingGuideMenu() {

        String expectedResultTitle = "OpenWeatherMap API guide - OpenWeatherMap";
        String expectedResultLink = "https://openweathermap.org/guide";

        getDriver().get(BASE_URL);
        waitForGrayFrameDisappeared();
        click(GUIDE_IN_MENU, getWait5());

        Assert.assertEquals(getDriver().getTitle(), expectedResultTitle);
        Assert.assertEquals(getDriver().getCurrentUrl(), expectedResultLink);
    }

    @Test
    public void testOnlyOneIconIsHighlightedWhenDifferentWeatherPopupAppears() {
        getDriver().get(BASE_URL);
        waitForGrayFrameDisappeared();
        click(DIFFERENT_WEATHER_BUTTON, getWait5());
        waitForDifferentWeatherPopUpAppeared();

        int activeIcons = getDriver().findElements(By.className("activeIcon")).size();
        String activeIconBackgroundColor = getDriver().findElement(By.className("activeIcon"))
                .getCssValue("background-color");
        String activeIconBackgroundColorHex = Color.fromString(activeIconBackgroundColor).asHex();

        Assert.assertEquals(activeIcons, 1);
        Assert.assertEquals(activeIconBackgroundColorHex, "#ececed");
    }
}
