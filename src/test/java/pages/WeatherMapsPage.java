package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class WeatherMapsPage extends TopMenuPage {

    public WeatherMapsPage(WebDriver driver) {
        super(driver);
    }
    @FindBy (xpath = "//a[@class='leaflet-control-zoom-in']")
    private WebElement zoomInLoupe;

    @FindBy (xpath = "//a[@class='leaflet-control-zoom-out']")
    private WebElement zoomOutLoupe;

    @FindBy(xpath = "//div[@id='desktop-menu']//a[@href='/weathermap']")
    private WebElement searchMapMenu;

    @FindBy (xpath ="//div[@id='map']//label/span")
    private List<WebElement> weatherControlLayers;

    public WeatherMapsPage clickZoomInLoupe() {
        click(zoomInLoupe);

        return this;
    }

    public WeatherMapsPage clickZoomOutLoupe() {
        click(zoomOutLoupe);

        return this;
    }

    public WeatherMapsPage waitUntilUrlContainsHttp(String text) {
        wait10UrlContains(text);

        return this;
    }

    public String getZoomTextOut() {

        return getText(zoomOutLoupe);
    }

    public String getZoomTextIn() {

        return getText(zoomInLoupe);
    }

    public List<String> getLayersTexts() {

        return getTexts(weatherControlLayers);
    }
}
