package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base_abstract.FooterMenuPage;

import java.util.List;

public class WeatherStationsPage extends FooterMenuPage {

    @FindBy(xpath = "//div[@class='doc-container']//nav//li/a")
    private List<WebElement> summaryListWeatherStations;

    public WeatherStationsPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getSummaryTextsWeatherStations() {

        return getTexts(summaryListWeatherStations);
    }

    public WeatherStationsPage waitAllSummaryElementsVisibleAndClickable() {
        allElementsVisibleAndClickable(summaryListWeatherStations);

        return this;
    }

    public boolean isActualSummaryTextsEqualsExpectedSummaryTexts(List<String> list, List<String> expectedList) {

        return isListEqualsExpectedList(list, expectedList);
    }
}
