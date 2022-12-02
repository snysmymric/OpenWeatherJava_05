package pages;

import org.openqa.selenium.JavascriptExecutor;
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

    @FindBy(id = "support-dropdown")
    private WebElement supportMenu;

    @FindBy(xpath = "//ul[@class='dropdown-menu dropdown-visible']//a[@href='/faq']")
    private WebElement faqSupportSubmenu;

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
    private WebElement noResultsError;

    @FindBy(xpath = "//div[@class='widget-notification']")
    private WebElement errorButtonBackgroundColor;

    @FindBy(xpath = "//div[text()='Metric: °C, m/s']")
    private WebElement metricButton;

    @FindBy(xpath = "//div[text()='Imperial: °F, mph']")
    private WebElement imperialButton;

    @FindBy(xpath = "//div[@class='current-temp']/span")
    private WebElement currentTempAndUnit;

    @FindBy(xpath="//div[@class='option']")
    private WebElement getNameMetric;

    @FindBy(xpath="//div[@id='selected']")
    private WebElement selectedIsTrue;

    @FindBy(xpath="//div[@id='weather-widget']//a[@href='https://www.openstreetmap.org/copyright']")
    private WebElement openStreetMap;

    @FindBy(xpath="//div[@id='content']//h1")
    private WebElement confirmElementIsVisibleOpenStreetMap;

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
        wait10ElementToBeVisible(differentWeatherPopUpContainer);

        return this;
    }

    public int getAmountOfIconsOnDifferentWeatherPopUp() {

        return getListSize(iconsList);
    }

    public List<WebElement> getListOfIconsOnDifferentWeatherPopUp() {

        return iconsList;
    }

    public MainPage clickOnIconsOnDifferentWeatherPopUp(WebElement element) {
        click(element);

        return this;
    }

    public MainPage clickSupportMenu() {
        click(supportMenu);

        return this;
    }

    public String getLoadingText(String attribute) {

        return getAttribute(seeLoading, attribute);
    }

    public MainPage clickCityNorway() {
        click20(cityNorway);

        return this;
    }

    public MainPage clickFAQSupportSubmenu() {
        click(faqSupportSubmenu);

        return this;
    }

    public String getTextMetric() {

        return getText(getNameMetric);
    }
    public boolean MetricSelected() {

        return selectedIsTrue.isDisplayed();
    }
    public MainPage clickStreetMap() {
        click(openStreetMap);

        return this;
    }
    public MainPage confirmElementOpenStreetMapVisible() {
        wait20ElementToBeVisible(confirmElementIsVisibleOpenStreetMap);

        return this;
    }

    public MainPage clickLocationButton() {
        click20(locationButton);

        return this;
    }

    public MainPage scrollToOurTechnologyFooterMenu() {
        scrollByVisibleElement(getOurTechnologyFooterMenu());

        return this;
    }

    public MainPage scrollToPrivacyPolicyFooterMenu() {
        scrollByVisibleElement(getPrivacyPolicyFooterMenu());

        return this;
    }

    public MainPage scrollToWeatherDashboardFooterMenu() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollTo(0, 5200)");

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

        return getText(noResultsError);
    }

    public MainPage switchToMetric() {
        click(metricButton);

        return this;
    }

    public MainPage switchToImperial() {
        click(imperialButton);

        return this;
    }

    public boolean isTextContainC() {

        return isTextContain(currentTempAndUnit, "°C");
    }

    public boolean isTextContainF() {

        return isTextContain(currentTempAndUnit, "°F");
    }

    public String getErrorButtonBackgroundColor() {

        return getBackgroundColor(errorButtonBackgroundColor);
    }
}

