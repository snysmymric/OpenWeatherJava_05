package pages;

import org.openqa.selenium.WebDriver;
import org.testng.reporters.jq.Main;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WeatherDashboardPage extends FooterMenuPage {

    public WeatherDashboardPage(WebDriver driver) {
        super(driver);
    }

    public WeatherDashboardPage logger_Info(String str) {
        final Logger logger = Logger.getLogger(Main.class.getName());
        logger.log(Level.INFO, String.valueOf(str));

        return this;
    }
}
