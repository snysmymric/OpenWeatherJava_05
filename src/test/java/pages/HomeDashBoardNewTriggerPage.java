package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * @author Alina Syniagina.
 * Dashboard New Trigger Creation page.
 */
public class HomeDashBoardNewTriggerPage extends FooterMenuPage {

    public static final String FAHRENHEIT = "F";
    public static final String CELCIUS = "C";
    public static final int NUMBER_OF_INPUT_TRIGGER_PARAMS = 4;
    public static final int UNITS_INDEX = 2;

    @FindBy(xpath = "//input[@placeholder='Enter name']")
    private WebElement nameNewTriggerField;

    @FindBy(xpath = "//input[@placeholder='Enter location']")
    private WebElement locationField;

    @FindBy(xpath = "//button[contains(text(),'Coordinates')]")
    private WebElement coordinates;

    @FindBy(xpath = "//input[@placeholder='Latitude']")
    private WebElement latitude;

    @FindBy(xpath = "//input[@placeholder='Longitude']")
    private WebElement longitude;

    @FindBy(xpath = "//div[@class='flex-grow-1']//button[contains(text(),'Set')]")
    private WebElement setCoordinatesButton;

    @FindBy(xpath = "//div[@class='react-select__input-container css-ackcql']")
    private List<WebElement> inputContainersList;

    @FindBy(xpath = "//div[@class='text-end col']//button[contains(text(),'Create new trigger')]")
    private WebElement createNewTriggerButton;

    @FindBy(xpath = "//div[@class='text-end']//button[contains(text(),'Set location')]")
    private WebElement createSetLocationButton;

    @FindBy(xpath = "//h2[contains(text(),'Trigger Created')]")
    private WebElement triggerCreatedText;

    public HomeDashBoardNewTriggerPage(WebDriver driver) {
        super(driver);
    }

    public HomeDashBoardNewTriggerPage inputNameNewTrigger(String triggerName) {
        input(triggerName, nameNewTriggerField);

        return this;
    }

    public HomeDashBoardNewTriggerPage clickOnLocationField() {
        click(locationField);

        return this;
    }

    public HomeDashBoardNewTriggerPage clickOnCoordinates() {
        click(coordinates);

        return this;
    }

    public HomeDashBoardNewTriggerPage setCoordinatesLatitude(String latitudeCoordinate) {
        input(latitudeCoordinate, latitude);

        return this;
    }

    public HomeDashBoardNewTriggerPage setCoordinatesLongitude(String longitudeCoordinate) {
        input(longitudeCoordinate, longitude);

        return this;
    }

    public HomeDashBoardNewTriggerPage clickOnSetCoordinates() {
        click(setCoordinatesButton);

        return this;
    }

    public List<WebElement> getListOfInputContainers() {

        return inputContainersList;
    }

    public HomeDashBoardNewTriggerPage clickOnCreateNewTrigger() {
        clickByJavaScript(createNewTriggerButton);

        return this;
    }

    public HomeDashBoardNewTriggerPage clickOnSetLocation() {
        click(createSetLocationButton);

        return this;
    }

    public HomeDashBoardNewTriggerPage setUnits(String unit) {
        List<WebElement> listOfInputContainers = getListOfInputContainers();
        assertEquals(listOfInputContainers.size(), NUMBER_OF_INPUT_TRIGGER_PARAMS);

        WebElement unitsWebElement = listOfInputContainers.get(UNITS_INDEX);
        click(unitsWebElement);
        WebElement option;
        if (FAHRENHEIT.equals(unit)) {
            option = getDriver().findElement(By.id("react-select-4-option-1"));
        } else if (CELCIUS.equals(unit)) {
            option = getDriver().findElement(By.id("react-select-4-option-0"));
        } else {
            throw new IllegalArgumentException("Unsupported unit param: " + unit);
        }
        click(option);

        return this;
    }

    public String getTriggerCreatedText() {
        return triggerCreatedText.getText();
    }
}
