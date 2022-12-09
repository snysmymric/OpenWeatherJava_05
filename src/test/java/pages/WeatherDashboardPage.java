package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.reporters.jq.Main;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WeatherDashboardPage extends FooterMenuPage {

    @FindBy(xpath = "//div[@class='row weather text-center below']//a[contains(@href, 'id=weather_dashboard_website')]")
    private WebElement tryTheDashBoardButton;

    public WeatherDashboardPage(WebDriver driver) {
        super(driver);
    }

    public WeatherDashboardPage logger_Info(String str) {
        final Logger logger = Logger.getLogger(Main.class.getName());
        logger.log(Level.INFO, String.valueOf(str));

        return this;
    }

    public HomeDashBoardEventsPage clickTryTheDashBoardButton() {
        click(tryTheDashBoardButton);

        return new HomeDashBoardEventsPage(getDriver());
    }
}
