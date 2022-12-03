package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
}
