import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;
import java.util.List;

public class TimberhutTest extends BaseTest {
    final static By DIFFERENT_WEATHER_BUTTON = By.xpath("//span[@class='control-el owm-switch']");
    final static By MORE_OPTION_BUTTON = By.xpath("//div[@class='more-options']");
    final static By POPUP_MENU_WEATHER_BUTTONS = By.xpath("//ul[@class='icons']//li");
    final static By CLEAR_SKY_BUTTON = By.xpath(
            "//ul[@class='icons']//span[contains(text(), 'clear sky')]");
    final static By WIND_RADIOBUTTON_MODERATE_OPTION = By.xpath("//label[contains(text(), 'Moderate')]");
    final static By DATA_SOURCE_DROPDOWN_MENU= By.xpath("//div[@class='owm-selector']");
    final static By DATA_SOURCE_DROPDOWN_MENU_LIST = By.xpath("//ul[@class='dropdown-menu']/*");
    final static By DIFFERENT_WEATHER_SEND_BUTTON = By.xpath(
            "//button[@class='button-round dark'][contains(text(),'Send')]");
    final static By DIFFERENT_WEATHER_CLOSE_WINDOW_BUTTON = By.xpath(
            "/html[1]/body[1]/main[1]/div[2]/div[3]/div[1]/*[name()='svg'][1]");
    final static By POPUP_WINDOW = By.xpath("//div[@class='pop-up-container']");
    final static By WIND_RADIO_BUTTONS =  By.xpath("//div[@class='radio-buttons-switch']//div");
    final static By TEMPERATURE_FIELD = By.xpath("//input[@type='number']");
    final static By EMAIL_FIELD =  By.xpath("//input[@type='email']");
    final static By ANY_ADDITIONAL_INFORMATION_FIELD =  By.xpath("//textarea[@class='owm_textarea']");
    final static By MAIN_PAGE_GUIDE_BUTTON_UPPER_MENU =  By.xpath(
            "//div[@id='desktop-menu']//a[@href='/guide']");


    @Test
    public void testMainStartWeatherDifferentWeatherPopUpMenuWeatherIconsIsClickable() {
        String backgroundColor;
        final String backgroundColorGray = "rgba(236, 236, 237, 1)";

        openBaseURL();
        click(DIFFERENT_WEATHER_BUTTON);
        click(MORE_OPTION_BUTTON);

        List<WebElement> list = getDriver().findElements(POPUP_MENU_WEATHER_BUTTONS);

        for (WebElement element : list) {
            element.click();
            backgroundColor = element.getCssValue("background-color");
            Assert.assertEquals(backgroundColor, backgroundColorGray);
        }
    }

    @Test
    public void testMainStartWeatherDifferentWeatherPopUpMenuWindRadioButtonIsClickable() {
        String backgroundColor;
        final String backgroundColorGray = "rgba(236, 236, 237, 1)";

        openBaseURL();
        click(DIFFERENT_WEATHER_BUTTON);
        click(MORE_OPTION_BUTTON);

        List<WebElement> list = getDriver().findElements(WIND_RADIO_BUTTONS);

        for (WebElement element : list) {
            element.click();
            backgroundColor = element.getCssValue("background-color");
            Assert.assertEquals(backgroundColor, backgroundColorGray);
        }
    }

    @Test
    public void testDifferentWeatherPopUpFillingFields() {

        final String temperature = "26";
        final String email = "test@gmail.com";
        final String message = "Test message";

        openBaseURL();
        click(DIFFERENT_WEATHER_BUTTON);
        click(MORE_OPTION_BUTTON);
        click(CLEAR_SKY_BUTTON);
        click(TEMPERATURE_FIELD);
        input(temperature, TEMPERATURE_FIELD);
        click(WIND_RADIOBUTTON_MODERATE_OPTION);
        input(email, EMAIL_FIELD);
        click(DATA_SOURCE_DROPDOWN_MENU);
        List<WebElement> list = getDriver().findElements(DATA_SOURCE_DROPDOWN_MENU_LIST);
        list.get(3).click();
        click(ANY_ADDITIONAL_INFORMATION_FIELD);
        input(message, ANY_ADDITIONAL_INFORMATION_FIELD);
        click(DIFFERENT_WEATHER_SEND_BUTTON);
        getWait10();

        Assert.assertFalse(checkWebElementIsVisibleOrNotVisible(POPUP_WINDOW, getDriver()));
    }

    @Test
    public void testDifferentWeatherPopUpCloseWindowButton() {
        openBaseURL();
        click(DIFFERENT_WEATHER_BUTTON);
        click(DIFFERENT_WEATHER_CLOSE_WINDOW_BUTTON);
        getWait10();

        Assert.assertFalse(checkWebElementIsVisibleOrNotVisible(POPUP_WINDOW,getDriver()));
    }

    @Test
    public void mainPageGuideButtonClickableTest() {
        final String expectedResult = "https://openweathermap.org/guide";

        openBaseURL();
        click(MAIN_PAGE_GUIDE_BUTTON_UPPER_MENU);

        Assert.assertEquals(getCurrentURL(),expectedResult);
    }
}
