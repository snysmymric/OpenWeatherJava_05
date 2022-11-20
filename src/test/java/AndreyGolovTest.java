import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
public class AndreyGolovTest extends BaseTest {
    final static String BASE_URL = "https://openweathermap.org/";
    final static By DIFFERENT_WEATHER_BUTTON = By.xpath("//div[@class = 'controls']//span");
    final static By BUTTON_X_ON_POPUP = By.xpath("//div[@class = 'pop-up-container']//*[@class='icon-close']");
    final static By DIFFERENT_WEATHER_ICONS_CONTAINER = By.xpath("//div[@class = 'pop-up-container']//*[@class='icons']//li");
    final static By WEATHER_ICON_IN_POP_UP = By.xpath("//div[@class = 'pop-up-container']//ul[@class = 'icons']//li[1]");
    final static By MORE_OPTIONS_BUTTON = By.className("more-options");
    final static By MORE_OPTIONS_PANEL = By.xpath("//div[@class = 'more-options']/following-sibling::div");

    private void waitForGrayFrameDisappeared() {
        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("owm-loader-container")));
    }

    private void openBaseURL() {
        getDriver().get(BASE_URL);
    }

    private void click(By by, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    private By getByWithNumber(By by, int numberInSequence) {
        String NumberInSequence = String.valueOf(numberInSequence);
        By newBy = By.xpath(by.toString().substring(10, by.toString().length() - 2) + NumberInSequence + "]");
        return newBy;
    }

    private String clickGetAttributeValue(WebDriver driver, By by, String nameOfAttribute, WebDriverWait wait) {
        driver.findElement(by).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
        WebElement iconInActiveCondition = getDriver().findElement(by);

        return iconInActiveCondition.getAttribute(nameOfAttribute);
    }

    @Test
    public void testXButtonIsDisplayed() {
        boolean expectedCondition = true;

        openBaseURL();
        waitForGrayFrameDisappeared();
        click(DIFFERENT_WEATHER_BUTTON, getWait5());
        WebElement buttonX = getDriver().findElement(BUTTON_X_ON_POPUP);

        Assert.assertEquals(buttonX.isDisplayed(), expectedCondition);
    }

    @Test
    public void testDifferentWeatherIconsOnPopUp() {

        String attributeTested = "class";
        String expectedValueOfAttribute = "activeIcon";

        openBaseURL();
        waitForGrayFrameDisappeared();
        click(DIFFERENT_WEATHER_BUTTON, getWait10());

        Assert.assertEquals(
                getDriver().findElements(DIFFERENT_WEATHER_ICONS_CONTAINER)
                        .size(), 9
        );

        Assert.assertEquals(
                clickGetAttributeValue(getDriver(), getByWithNumber(WEATHER_ICON_IN_POP_UP, 1), attributeTested, getWait5()),
                expectedValueOfAttribute
        );

        Assert.assertEquals(
                clickGetAttributeValue(getDriver(), getByWithNumber(WEATHER_ICON_IN_POP_UP, 2), attributeTested, getWait5()),
                expectedValueOfAttribute
        );

        Assert.assertEquals(
                clickGetAttributeValue(getDriver(), getByWithNumber(WEATHER_ICON_IN_POP_UP, 3), attributeTested, getWait5()),
                expectedValueOfAttribute
        );

        Assert.assertEquals(
                clickGetAttributeValue(getDriver(), getByWithNumber(WEATHER_ICON_IN_POP_UP, 4), attributeTested, getWait5()),
                expectedValueOfAttribute
        );

        Assert.assertEquals(
                clickGetAttributeValue(getDriver(), getByWithNumber(WEATHER_ICON_IN_POP_UP, 5), attributeTested, getWait5()),
                expectedValueOfAttribute
        );

        Assert.assertEquals(
                clickGetAttributeValue(getDriver(), getByWithNumber(WEATHER_ICON_IN_POP_UP, 6), attributeTested, getWait5()),
                expectedValueOfAttribute
        );

        Assert.assertEquals(
                clickGetAttributeValue(getDriver(), getByWithNumber(WEATHER_ICON_IN_POP_UP, 7), attributeTested, getWait5()),
                expectedValueOfAttribute
        );

        Assert.assertEquals(
                clickGetAttributeValue(getDriver(), getByWithNumber(WEATHER_ICON_IN_POP_UP, 8), attributeTested, getWait5()),
                expectedValueOfAttribute
        );

        Assert.assertEquals(
                clickGetAttributeValue(getDriver(), getByWithNumber(WEATHER_ICON_IN_POP_UP, 9), attributeTested, getWait5()),
                expectedValueOfAttribute
        );
    }

    @Test
    public void testMoreOptionsButtonAndPanelAppears() {
        String expectedText = "Less options";

        openBaseURL();
        waitForGrayFrameDisappeared();
        click(DIFFERENT_WEATHER_BUTTON, getWait10());

        Assert.assertTrue(
                getDriver().findElement(MORE_OPTIONS_BUTTON).isDisplayed()
        );

        click(MORE_OPTIONS_BUTTON, getWait5());

        Assert.assertEquals(
                getDriver().findElement(MORE_OPTIONS_BUTTON).getText(), expectedText
        );

        Assert.assertTrue(
                getDriver().findElement(MORE_OPTIONS_PANEL).isDisplayed()
        );
    }

}
