import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
public class TimberhutTest extends BaseTest {
    final static String BASE_URL = "https://openweathermap.org/";
    final static By DIFFERENT_WEATHER_BUTTON = By.xpath("//span[@class='control-el owm-switch']");
    final static By MORE_OPTION_BUTTON = By.xpath("//div[@class='more-options']");
    final static By POPUP_MENU_WEATHER_BUTTONS = By.xpath("//ul[@class='icons']//li");
    final static By CLEAR_SKY_BUTTON = By.xpath(
            "//ul[@class='icons']//span[contains(text(), 'clear sky')]");
    final static By WIND_RADIOBUTTON_MODERATE_OPTION = By.xpath("//label[contains(text(), 'Moderate')]");
    final static By DATA_SOURCE_DROPDOWN_MENU= By.xpath("//div[@class='owm-selector']");
    final static By DATA_SOURCE_DROPDOWN_MENU_PERSONAL_FEELINGS_OPTION = By.xpath(
            "//ul[@class='dropdown-menu']//span[contains(text(),'Personal')]");
    final static By DIFFERENT_WEATHER_SEND_BUTTON = By.xpath(
            "//button[@class='button-round dark'][contains(text(),'Send')]");
    final static By DIFFERENT_WEATHER_CLOSE_WINDOW_BUTTON = By.xpath(
            "/html[1]/body[1]/main[1]/div[2]/div[3]/div[1]/*[name()='svg'][1]"
    );
    final static By POPUP_WINDOW = By.xpath("//div[@class='pop-up-container']");
    final static By WIND_RADIO_BUTTONS =  By.xpath("//div[@class='radio-buttons-switch']//div");
    final static By TEMPERATURE_FIELD = By.xpath("//input[@type='number']");
    final static By EMAIL_FIELD =  By.xpath("//input[@type='email']");
    final static By ANY_ADDITIONAL_INFORMATION_FIELD =  By.xpath("//textarea[@class='owm_textarea']");


    private void openBaseURL() {
        getDriver().get(BASE_URL);
    }
    private void waitingGrayFieldDisappears() {getWait20().until(ExpectedConditions.
            invisibilityOfElementLocated(By.className("own-loader-container")));
    }
    private void click(By by, WebDriver driver) {
        driver.findElement(by).click();
    }
    private void input(String text, By by, WebDriver driver) {
        driver.findElement(by).clear();
        driver.findElement(by).sendKeys(text);
    }
    private boolean checkWebElementIsVisibleOrNotVisible(By by, WebDriver driver) {
        try {
            driver.findElement(by).isDisplayed();
            return false;
        } catch (Exception e) {
            return true;
        }
    }
    private boolean checkWebElementIsEnabledOrDisabled(By by, WebDriver driver) {
        try {
            driver.findElement(by).isEnabled();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Test
    public void testMainStartWeatherDifferentWeatherPopUp() {
        String temperature = "26";
        String email = "test@gmail.com";
        String message = "Test message";

        openBaseURL();
        waitingGrayFieldDisappears();
        click(DIFFERENT_WEATHER_BUTTON,getDriver());
        click(MORE_OPTION_BUTTON,getDriver());

        Assert.assertTrue(checkWebElementIsEnabledOrDisabled(POPUP_MENU_WEATHER_BUTTONS,getDriver()));
        Assert.assertTrue(checkWebElementIsEnabledOrDisabled(WIND_RADIO_BUTTONS,getDriver()));

        click(CLEAR_SKY_BUTTON,getDriver());

        click(TEMPERATURE_FIELD,getDriver());
        input(temperature,TEMPERATURE_FIELD,getDriver());
        click(WIND_RADIOBUTTON_MODERATE_OPTION,getDriver());
        input(email,EMAIL_FIELD,getDriver());
        click(DATA_SOURCE_DROPDOWN_MENU,getDriver());
        click(DATA_SOURCE_DROPDOWN_MENU_PERSONAL_FEELINGS_OPTION,getDriver());
        click(ANY_ADDITIONAL_INFORMATION_FIELD,getDriver());
        input(message,ANY_ADDITIONAL_INFORMATION_FIELD,getDriver());
        click(DIFFERENT_WEATHER_SEND_BUTTON,getDriver());
        getWait10();

        Assert.assertFalse(checkWebElementIsVisibleOrNotVisible(POPUP_WINDOW,getDriver()));

        click(DIFFERENT_WEATHER_BUTTON,getDriver());
        click(DIFFERENT_WEATHER_CLOSE_WINDOW_BUTTON,getDriver());
        getWait10();

        Assert.assertFalse(checkWebElementIsVisibleOrNotVisible(POPUP_WINDOW,getDriver()));
    }
}



