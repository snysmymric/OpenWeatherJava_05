package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base_abstract.FooterMenuPage;

import java.util.List;

public class FindPage extends FooterMenuPage {

    @FindBy(id = "search_str")
    private WebElement searchFieldWeatherInYourCity;

    @FindBy(xpath = "//div[@id='forecast_list_ul']//tr")
    private List<WebElement> resultRows;

    @FindBy(xpath = "//div[@id='forecast_list_ul']//tr/td[2]/b[1]")
    private List<WebElement> cityCountryNames;

    public FindPage(WebDriver driver) {
        super(driver);
    }

    public String getSearchFieldValue(String attribute) {

        return getAttribute(searchFieldWeatherInYourCity, attribute);
    }

    public List<WebElement> getResultRows() {

        return resultRows;
    }

    public List<String> getCityCountryNames() {

        return getTexts(cityCountryNames);
    }
}
