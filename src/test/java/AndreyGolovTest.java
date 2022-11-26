import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

public class AndreyGolovTest extends BaseTest {
    final static By DIFFERENT_WEATHER_BUTTON = By.xpath("//div[@class = 'controls']//span");
    final static By DIFFERENT_WEATHER_POP_UP = By.className("pop-up-container");
    final static By BUTTON_X_ON_POPUP = By.xpath("//div[@class = 'pop-up-container']//*[@class='icon-close']");
    final static By DIFFERENT_WEATHER_ICONS_CONTAINER = By.xpath("//div[@class = 'pop-up-container']//div[@id='dialogDesc']//li");
    final static By FIRST_WEATHER_ICON_IN_POP_UP = By.xpath("//div[@class = 'pop-up-container']//ul[@class = 'icons']//li[1]");
    final static By MORE_OPTIONS_BUTTON = By.className("more-options");
    final static By MORE_OPTIONS_PANEL = By.xpath("//div[@class = 'more-options']/following-sibling::div");

    @Test
    public void testXButtonIsDisplayed() {

        boolean expectedXButtonIsVisible = true;

        openBaseURL();
        click(DIFFERENT_WEATHER_BUTTON);
        waitElementToBeVisible(DIFFERENT_WEATHER_POP_UP);
        WebElement buttonX = getDriver().findElement(BUTTON_X_ON_POPUP);

        Assert.assertEquals(buttonX.isDisplayed(), expectedXButtonIsVisible);
    }

    @Test
    public void testDifferentWeatherIconsOnPopUp() {

        String nameOfTestedAttribute = "class";
        String expectedValueOfTestedAttribute = "activeIcon";
        int[] elementIndexes = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};

        openBaseURL();
        click(DIFFERENT_WEATHER_BUTTON);
        waitElementToBeVisible(DIFFERENT_WEATHER_POP_UP);

        Assert.assertEquals(
                getDriver().findElements(DIFFERENT_WEATHER_ICONS_CONTAINER).
                        size(), 9
        );

        for (int element : elementIndexes) {
            Assert.assertEquals(
                    getAttributeValueAfterClick((getXPathOfElementWithExactIndex(FIRST_WEATHER_ICON_IN_POP_UP, element)), nameOfTestedAttribute),
                    expectedValueOfTestedAttribute
            );
        }
    }

    @Test
    public void testMoreOptionsButtonAndPanelAppears() {

        openBaseURL();
        click(DIFFERENT_WEATHER_BUTTON);
        click(MORE_OPTIONS_BUTTON);
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(MORE_OPTIONS_PANEL));

        Assert.assertTrue(getDriver().findElement(MORE_OPTIONS_PANEL).isDisplayed());
    }

    @Test
    public void testMoreOptionsButtonСhangesToLessOptionsAfterClick() {

        String expectedTextOnButtonAfterClick = "Less options";

        openBaseURL();
        click(DIFFERENT_WEATHER_BUTTON);
        click(MORE_OPTIONS_BUTTON);
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(MORE_OPTIONS_PANEL));

        Assert.assertEquals(getText(MORE_OPTIONS_BUTTON), expectedTextOnButtonAfterClick);
    }
}
