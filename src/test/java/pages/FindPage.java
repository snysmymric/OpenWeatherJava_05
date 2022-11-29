package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FindPage extends FooterMenuPage{
    @FindBy(id = "search_str")
    private WebElement searchFieldWeatherInYourCity;
    public FindPage(WebDriver driver) {
        super(driver);
    }

    public String getSearchFieldValue(String attribute) {

        return getAttributeOfElement(searchFieldWeatherInYourCity, attribute);
    }
}
