package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base_abstract.FooterMenuPage;

import java.util.List;

public class FindPage extends FooterMenuPage {

    @FindBy(id = "search_str")
    private WebElement searchFieldWeatherInYourCity;

    @FindBy(xpath = "//div[@id='forecast_list_ul']//tr/td/b/a")
    private List<WebElement> searchedCityNames;

    public FindPage(WebDriver driver) {
        super(driver);
    }

    public String getSearchFieldValue(String attribute) {

        return getAttribute(searchFieldWeatherInYourCity, attribute);
    }

    public boolean IsContainsCurrentUrlCityName(String text) {

        return currentUrlIsContainsText(text);
    }

    public List<String> getSearchCityNames() {

        return getListText(searchedCityNames);
    }

    public boolean isCreatedCityNamesListContainsRome() {

        return isListContains("Rome", getSearchCityNames());
    }
}
