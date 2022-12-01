package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class MainPage extends FooterMenuPage {

    @FindBy(className = "owm-loader-container")
    private WebElement grayContainer;

    @FindBy(xpath = "//div[@id = 'weather-widget']//h2")
    private WebElement h2CityCountryHeader;

    @FindBy(xpath = "//div[@id = 'weather-widget']//input[@placeholder = 'Search city']")
    private WebElement searchCityField;

    @FindBy(xpath = "//div[@id = 'weather-widget']//button[@type = 'submit']")
    private WebElement searchButton;

    @FindBy(className = "search-dropdown-menu")
    private WebElement searchDropdownMenu;

    @FindBy(xpath = "//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']")
    private WebElement parisFRChoiceInDropdownMenu;

    @FindBy(xpath = "//div[@class='mobile-padding']/h1/span")
    private WebElement colorAndFontSizeOfH1Header;

    @FindBy(xpath = "//ul[@class = 'search-dropdown-menu']/li/span[@style='width: 140px;']")
    private List<WebElement> allChoicesInDropDownMenu;

    @FindBy(xpath = "//div[@class = 'controls']//span")
    private WebElement differentWeatherButton;

    @FindBy(className = "pop-up-container")
    private WebElement differentWeatherPopUpContainer;

    @FindBy(xpath = "//ul[@class='icons']/*")
    private WebElement differentWeatherIconsContainer;

    @FindBy(xpath = "//ul[@class = 'icons']/*")
    private List<WebElement> iconsList;

    @FindBy(xpath = "//div[@id='support-dropdown']")
    private WebElement buttonSupport;

    @FindBy(xpath = "//ul[@id='support-dropdown-menu']")
    private WebElement supportDropDownMenu;

    @FindBy(xpath = "//ul[@class='dropdown-menu dropdown-visible']//a[@href='/faq']")
    private WebElement buttonFAQ;
    
    @FindBy(xpath = "//div[@aria-label='Loading']")
    private WebElement seeLoading;

    @FindBy(xpath = "//span[text()='45.787, -87.904']")
    private WebElement cityNorway;

    @FindBy(xpath = "//div[@class='control-el']")
    private WebElement locationButton;

    @FindBy(xpath = "//ul[@class='icons']/li")
    private List<WebElement> differentWeatherIcons;

    @FindBy(xpath = "//button[text()='Send']")
    private WebElement sendButtonInDifferentWeatherContainer;

    @FindBy(xpath = "//div[@class='pop-up-container']//*[name()='path' and @fill='#8a8a8a']")
    private WebElement xButtonInDifferentWeatherContainer;

    @FindBy(xpath = "//span[text()='No results for Rrr']")
    private WebElement noResultError;

    @FindBy(xpath = "//div[@class='widget-notification']")
    private WebElement errorButtonBackgroundColor;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void waitForGrayContainerDisappeared() {
        getWait20().until(ExpectedConditions.invisibilityOf(grayContainer));
    }

    public String getCityCountryName() {

        return getText(h2CityCountryHeader);
    }

    public MainPage clickSearchCityField() {
        click(searchCityField);

        return this;
    }

    public MainPage inputSearchCriteria(String text) {
        input(text, searchCityField);

        return this;
    }

    public MainPage clickSearchButton() {
        click(searchButton);

        return this;
    }

    public MainPage clickParisInDropDownList() {
        wait20ElementToBeVisible(searchDropdownMenu);
        click(parisFRChoiceInDropdownMenu);

        return this;
    }

    public MainPage clickChoiceInDropDownList(String cityCountry) {
        wait20ElementToBeVisible(searchDropdownMenu);
        for (WebElement choice : allChoicesInDropDownMenu) {
            if (choice.getText().equalsIgnoreCase(cityCountry)) {
                click(choice);
            }
        }

        return this;
    }

    public MainPage waitForCityCountryNameChanged(String oldText) {
        waitTextToBeChanged(h2CityCountryHeader, oldText);

        return this;
    }

    public String getColor() {

        return getBackgroundColor(colorAndFontSizeOfH1Header);
    }

    public String getFontSize() {

        return getFontSize(colorAndFontSizeOfH1Header);
    }

    public MainPage clickDifferentWeatherButton() {
        click(differentWeatherButton);

        return this;
    }

    public MainPage waitUntilDifferentWeatherPopUpIsVisible() {
        getWait10().until(ExpectedConditions.visibilityOf(differentWeatherPopUpContainer));

        return this;
    }

    public int getListSizeOfIconsOnDifferentWeatherPopUp() {
        return getListSize(iconsList);
    }

    public List<WebElement> getListOfIconsOnDifferentWeatherPopUp() {
        return iconsList;
    }

    public MainPage clickOnIconsOnDifferentWeatherPopUp(WebElement element) {
        click(element);
        return this;
    }

    public MainPage clickSupportButton() {

        click(buttonSupport);

        return this;
    }

    public String getTextAttribute(String attribute) {

        return getAttributeOfElement(seeLoading, attribute);
    }

    public MainPage clickCityNorway() {
        click20(cityNorway);

        return this;
    }

    public MainPage clickFAQButton(){
         click(buttonFAQ);

        return this;
    }

   public MainPage clickLocationButton() {
        click20(locationButton);

        return this;
    }

    public boolean checkAllIconsAreVisibleAndClickable() {

        return allElementsVisibleAndClickable(differentWeatherIcons);
    }

    public boolean isSendButtonDisplayed() {

        return isDisplayedElement(sendButtonInDifferentWeatherContainer);
    }

    public boolean isXButtonDisplayed() {

        return isDisplayedElement(xButtonInDifferentWeatherContainer);
    }

    public String getErrorText() {

        return getText(noResultError);
    }

    public String getErrorButtonBackgroundColor() {

        return getBackgroundColor(errorButtonBackgroundColor);
    }
}

