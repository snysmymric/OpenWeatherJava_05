package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


import java.util.List;

public class WidgetsPage extends FooterMenuPage {

    @FindBy(id = "api-key")
    private WebElement yourAPIKeyField;

    @FindBy(id = "city-name")
    private WebElement yourCityNameField;

    @FindBy(id = "search-city")
    private WebElement searchCityButton;

    @FindBy(xpath = "//div[@class='widget-left-menu__layout']/h2")
    private WebElement biggerWidgetCityName;

    @FindBy(xpath = "//ul[@id='city-list']//span")
    private List<WebElement> selectYourCity;

    @FindBy(xpath = "//div[@id=\"cities\"]/ul/li[1]/label/span")
    private WebElement cityFromSelectYourCityList;

    @FindBy(xpath= "//*[@id='error-key']")
    private WebElement apiKeyErrorMessage;

    public WidgetsPage(WebDriver driver) {
        super(driver);
    }

    public WidgetsPage inputYourAPIKey(String key) {
        inputText(yourAPIKeyField, key);

        return this;
    }

    public WidgetsPage inputYourCityName(String city) {
        inputText(yourCityNameField, city);

        return this;
    }

    public String getCityName() {

        return getText(cityFromSelectYourCityList);
    }

    public WidgetsPage clickSearchCityButton() {
        click(searchCityButton);

        return this;
    }

    public WidgetsPage waitCityFromSelectCityToBeChanged(String oldCity) {
        waitTextToBeChanged(cityFromSelectYourCityList, oldCity);

        return this;
    }

    public List<String> getSelectedCityTexts() {
        getWait20().until(ExpectedConditions.visibilityOfAllElements(selectYourCity));
        for (int i = 0; i < selectYourCity.size(); i++) {
            waitForTextNotToBeEmpty(selectYourCity.get(i));
        }

        return getListText(selectYourCity);
    }

    public String getErrorMessage() {

        return getText(apiKeyErrorMessage);
    }
}
