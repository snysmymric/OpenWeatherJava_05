package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WeatherMapsPage extends TopMenuPage {

    public WeatherMapsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy (xpath = "//a[@class='leaflet-control-zoom-in']")
    private WebElement zoomInLoupe;

    @FindBy (xpath = "//a[@class='leaflet-control-zoom-out']")
    private WebElement zoomOutLoupe;

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
}
