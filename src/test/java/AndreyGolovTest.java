import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

public class AndreyGolovTest extends BaseTest {
    final static By DIFFERENT_WEATHER_BUTTON = By.xpath("//div[@class = 'controls']//span");
    final static By DIFFERENT_WEATHER_POP_UP = By.className("pop-up-container");
    final static By BUTTON_X_ON_POPUP = By.xpath("//div[@class = 'pop-up-container']//*[@class='icon-close']");
    final static By DIFFERENT_WEATHER_ICONS_CONTAINER = By.xpath("//div[@class = 'pop-up-container']//div[@id='dialogDesc']//li");
    final static By FIRST_WEATHER_ICON_IN_POP_UP = By.xpath("//div[@class = 'pop-up-container']//ul[@class = 'icons']//li[1]");

    private By getByOfElementWithExactIndex(By by, int numberInSequence) {
        String NumberInSequence = String.valueOf(numberInSequence);
        return By.xpath(by.toString().substring(10, by.toString().length() - 2) + NumberInSequence + "]");
    }

    private String getAttributeValueAfterClick(By by, String nameOfAttribute) {
        click(by);
        WebElement elementAfterClick = getDriver().findElement(by);

        return elementAfterClick.getAttribute(nameOfAttribute);
    }

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
                    getAttributeValueAfterClick((getByOfElementWithExactIndex(FIRST_WEATHER_ICON_IN_POP_UP, element)), nameOfTestedAttribute),
                    expectedValueOfTestedAttribute
            );
        }
    }
}
