import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;
import java.util.ArrayList;
import java.util.List;

public class SnegafalTest extends BaseTest {

    private final List<String> EXPECTED_DATA_SOURCE_ITEMS = List.of(
            "Personal feelings",
            "Own weather station or devices",
            "Local weather provider",
            "Global weather provider",
            "Other");

    private final By DIFFERENT_WEATHER_POP_UP = By.xpath("//div[@class='pop-up-container']");
    private final By DIFFERENT_WEATHER_BUTTON = By.xpath("//span[contains(text(), 'Different')]");
    private final By MORE_OPTIONS_BUTTON = By.xpath("//div[@class='more-options']");
    private final By DIFFERENT_WEATHER_TEMPERATURE_INPUT = By.xpath("//input[@type='number']");
    private final By START_PAGE_TEMPERATURE = By.xpath("//span[@class='heading']");
    private final By DEGREE_SIGN_IN_DIFFERENT_WEATHER_POPUP = By.xpath("//div[@class='input-with-selection']/div");
    private final By DATA_SOURCE_SELECTOR = By.xpath("//div[@class='dropdown-selector']");
    private final By DATA_SOURCE_OPTIONS = By.xpath("//div[@class='owm-selector open']//ul[@class='dropdown-menu']/li");
    private final By ACTIVE_ICON_IN_DIFFERENT_WEATHER_POP_UP = By.className("activeIcon");
    private final By WIND_SWITCHER_RADIO_BUTTONS = By.xpath("//div[@class='radio-buttons-switch']/div");
    private final By ACTIVE_WIND_OPTION = By.xpath("//div[@class='radio-buttons-switch']/div[@class='active']");

    @Test
    public void testOnlyOneIconIsHighlightedWhenDifferentWeatherPopupAppears() {
        openBaseURL();

        click(DIFFERENT_WEATHER_BUTTON);
        waitElementToBeVisible(DIFFERENT_WEATHER_POP_UP);
        int activeIcon = seeAllElementAndCount(ACTIVE_ICON_IN_DIFFERENT_WEATHER_POP_UP);
        String activeIconBackgroundColor = backgroundColor(ACTIVE_ICON_IN_DIFFERENT_WEATHER_POP_UP);
        String activeIconBackgroundColorHex = Color.fromString(activeIconBackgroundColor).asHex();

        Assert.assertEquals(activeIcon, 1);
        Assert.assertEquals(activeIconBackgroundColorHex, "#ececed");
    }

    @Test
    public void testVerifyTemperatureInDifferentPopUpWeatherAndInStartPage() {
        openBaseURL();

        String temperatureInStartPage = getText(START_PAGE_TEMPERATURE);

        click(DIFFERENT_WEATHER_BUTTON);
        waitElementToBeVisible(DIFFERENT_WEATHER_POP_UP);
        click(MORE_OPTIONS_BUTTON);
        String temperatureInPopup = getTextByAttribute(DIFFERENT_WEATHER_TEMPERATURE_INPUT, "_value");
        String degreeSign = getText(DEGREE_SIGN_IN_DIFFERENT_WEATHER_POPUP);
        String temperatureInPopupWithDegree = temperatureInPopup.concat(degreeSign);

        Assert.assertEquals(temperatureInPopupWithDegree, temperatureInStartPage);
    }

    @Test
    public void testIncreaseTemperatureInDifferentWeatherPopUpByPressingAKey() {
        openBaseURL();

        click(DIFFERENT_WEATHER_BUTTON);
        waitElementToBeVisible(DIFFERENT_WEATHER_POP_UP);
        click(MORE_OPTIONS_BUTTON);
        WebElement temperatureInput = getDriver().findElement(DIFFERENT_WEATHER_TEMPERATURE_INPUT);
        int temperatureInPopup = Integer.parseInt(getTextByAttribute(
                DIFFERENT_WEATHER_TEMPERATURE_INPUT, "_value"));
        temperatureInput.sendKeys(Keys.ARROW_UP);
        int increasedTemperatureInInput = Integer.parseInt(
                getTextByAttribute(DIFFERENT_WEATHER_TEMPERATURE_INPUT, "_value"));

        Assert.assertEquals(increasedTemperatureInInput - temperatureInPopup, 1);
    }

    @Test
    public void testNamesInDataSourceSelectorInDifferentWeatherPopUp() {

        openBaseURL();

        click(DIFFERENT_WEATHER_BUTTON);
        waitElementToBeVisible(DIFFERENT_WEATHER_POP_UP);
        click(MORE_OPTIONS_BUTTON);
        click(DATA_SOURCE_SELECTOR);

        List<WebElement> dataSourceElements = getDriver().findElements(DATA_SOURCE_OPTIONS);
        List <String> actualDataSourceElements = new ArrayList<>();
        for (WebElement option : dataSourceElements) {
            actualDataSourceElements.add(option.getText());
        }

        Assert.assertEquals(actualDataSourceElements, EXPECTED_DATA_SOURCE_ITEMS);
    }

    @Test
    public void testChosenWindOptionSavedAfterClickingLessOptionsInDifferentWeatherPopUp() {
        openBaseURL();
        click(DIFFERENT_WEATHER_BUTTON);
        waitElementToBeVisible(DIFFERENT_WEATHER_POP_UP);
        click(MORE_OPTIONS_BUTTON);
        int windSelectorElements = seeAllElementAndCount(WIND_SWITCHER_RADIO_BUTTONS);
        int randomNumber = (int) ((Math.random() * windSelectorElements) + 1);
        click(By.xpath("//div[@class='radio-buttons-switch']/div[" + randomNumber + "]"));

        String chosenElementBeforeClickingLessOptions = getTextWaiting(
                By.xpath("//div[@class='radio-buttons-switch']/div[" + randomNumber + "]"));

        click(MORE_OPTIONS_BUTTON);
        click(MORE_OPTIONS_BUTTON);

        String chosenElementAfterClickingLessOptions = getTextWaiting(ACTIVE_WIND_OPTION);

        Assert.assertEquals(chosenElementAfterClickingLessOptions, chosenElementBeforeClickingLessOptions);
    }
}

