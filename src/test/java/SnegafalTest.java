import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

public class SnegafalTest extends BaseTest {

    static final String BASE_URL = "https://openweathermap.org/";

    static final By GUIDE_IN_MENU = By.xpath("//ul[@id='first-level-nav']//a[@href='/guide']");
    static final By DIFFERENT_WEATHER_POP_UP = By.xpath("//div[@class='pop-up-container']");
    static final By DIFFERENT_WEATHER_BUTTON = By.xpath("//span[contains(text(), 'Different')]");
    static final By MORE_OPTIONS_BUTTON = By.xpath("//div[@class='more-options']");
    static final By DIFFERENT_WEATHER_TEMPERATURE_INPUT = By.xpath("//input[@type='number']");
    static final By START_PAGE_TEMPERATURE = By.xpath("//span[@class='heading']");
    static final By DEGREE_SIGN_IN_DIFFERENT_WEATHER_POPUP = By.xpath("//div[@class='input-with-selection']/div");


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

    @Ignore
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

    @Test
    public void testVerifyTemperatureInDifferentPopUpWeatherAndInStartPage() {
        getDriver().get(BASE_URL);

        waitForGrayFrameDisappeared();
        click(DIFFERENT_WEATHER_BUTTON, getWait5());
        waitForDifferentWeatherPopUpAppeared();
        getDriver().findElement(MORE_OPTIONS_BUTTON).click();
        String temperatureInPopup = getDriver().findElement(DIFFERENT_WEATHER_TEMPERATURE_INPUT).getAttribute("_value");
        String degreeSign = getDriver().findElement(DEGREE_SIGN_IN_DIFFERENT_WEATHER_POPUP).getText();
        String temperatureInPopupWithDegree = temperatureInPopup.concat(degreeSign);
        String temperatureInStartPage = getDriver().findElement(START_PAGE_TEMPERATURE).getText();

        Assert.assertEquals(temperatureInPopupWithDegree, temperatureInStartPage);
    }

    @Test
    public void testIncreaseTemperatureInDifferentWeatherPopUp() {
        getDriver().get(BASE_URL);

        waitForGrayFrameDisappeared();
        click(DIFFERENT_WEATHER_BUTTON, getWait5());
        waitForDifferentWeatherPopUpAppeared();
        getDriver().findElement(MORE_OPTIONS_BUTTON).click();
        WebElement temperatureInput = getDriver().findElement(DIFFERENT_WEATHER_TEMPERATURE_INPUT);
        int temperatureInPopup = Integer.parseInt(getDriver().
                findElement(DIFFERENT_WEATHER_TEMPERATURE_INPUT).getAttribute("_value"));
        temperatureInput.sendKeys(Keys.ARROW_UP);
        int increasedTemperatureInInput = Integer.parseInt(getDriver().
                findElement(DIFFERENT_WEATHER_TEMPERATURE_INPUT).getAttribute("_value"));

        Assert.assertEquals(increasedTemperatureInInput - temperatureInPopup, 1);
    }
}
