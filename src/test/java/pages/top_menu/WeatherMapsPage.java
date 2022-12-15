package pages.top_menu;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.base_abstract.TopMenuPage;

import java.util.List;

public class WeatherMapsPage extends TopMenuPage {

    @FindBy (xpath = "//a[@class='leaflet-control-zoom-in']")
    private WebElement zoomInLoupe;

    @FindBy (xpath = "//a[@class='leaflet-control-zoom-out']")
    private WebElement zoomOutLoupe;

    @FindBy(xpath = "//div[@id='desktop-menu']//a[@href='/weathermap']")
    private WebElement searchMapMenu;

    @FindBy (xpath ="//div[@id='map']//label/span")
    private List<WebElement> weatherControlMenus;

    @FindBy(xpath ="//form[@style='display: block; position: absolute; left: 27px; top: 0px; z-index: -10;']")
    private WebElement loopDisplayBlock;

    @FindBy (xpath = "//input[@style='height: 25px; border: 1px solid grey; padding: 0px 0px 0px 10px;']")
    private WebElement searchLoopField;

    @FindBy(xpath = "//a[@title='Nominatim Search']")
    private WebElement nominatimSearchButton;

    public WeatherMapsPage(WebDriver driver) {
        super(driver);
    }

    public WeatherMapsPage clickZoomInLoupe() {
        click(zoomInLoupe);

        return this;
    }

    public WeatherMapsPage clickZoomOutLoupe() {
        click(zoomOutLoupe);

        return this;
    }

    public WeatherMapsPage waitUntilUrlContains(String text) {
        waitForUrlContains(text);

        return this;
    }

    public String getZoomOutText() {

        return getText(zoomOutLoupe);
    }

    public String getZoomInText() {

        return getText(zoomInLoupe);
    }

    public List<String> getMenusTexts() {

        return getTexts(weatherControlMenus);
    }

    public WeatherMapsPage clickNominatimSearchButton() {
        click (nominatimSearchButton);

        return this;
    }

    public boolean isLoopDisplayBlockDisplayed() {

        return isElementDisplayed(loopDisplayBlock);
    }

    public WeatherMapsPage clickLoopDisplayBlock() {
        click (loopDisplayBlock);

        return this;
    }

    protected void inputAndEnter(WebElement element, String text) {
        getWait10().until(ExpectedConditions.visibilityOf(searchLoopField));
        element.sendKeys(text, Keys.ENTER);
    }
}
