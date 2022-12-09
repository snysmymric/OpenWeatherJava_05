package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.reporters.jq.Main;
import utils.TestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    @FindBy(xpath = "//div[@class='mobile-padding']/h1/span")
    private WebElement colorAndFontSizeOfH1Header;

    @FindBy(xpath = "//ul[@class = 'search-dropdown-menu']/li/span[@style='width: 140px;']")
    private List<WebElement> allChoicesInDropDownMenu;

    @FindBy(xpath = "//div[@class = 'controls']//span")
    private WebElement differentWeatherButton;

    @FindBy(xpath = "//div[@class='control-el']")
    private WebElement locationButton;

    @FindBy(xpath = "//div[text()='Metric: °C, m/s']")
    private WebElement metricButton;

    @FindBy(xpath = "//div[text()='Imperial: °F, mph']")
    private WebElement imperialButton;

    @FindBy(xpath = "//div[@class='current-temp']/span")
    private WebElement currentTempAndUnit;

    @FindBy(className = "pop-up-container")
    private WebElement differentWeatherPopUpContainer;

    @FindBy(xpath = "//ul[@class='icons']/*")
    private WebElement differentWeatherIconsContainer;

    @FindBy(xpath = "//ul[@class = 'icons']/*")
    private List<WebElement> iconsList;

    @FindBy(xpath = "//div[@aria-label='Loading']")
    private WebElement seeLoading;

    @FindBy(xpath = "//ul[@class='icons']/li")
    private List<WebElement> differentWeatherIcons;

    @FindBy(xpath = "//button[text()='Send']")
    private WebElement sendButtonInDifferentWeatherContainer;

    @FindBy(xpath = "//div[@class='pop-up-container']//*[name()='path' and @fill='#8a8a8a']")
    private WebElement xButtonInDifferentWeatherContainer;

    @FindBy(xpath = "//div[@class='widget-notification']")
    private WebElement resultErrorWidget;

    @FindBy(xpath = "//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']")
    private WebElement parisFRChoiceInDropdownMenu;

    @FindBy(xpath = "//span[text()='45.787, -87.904']")
    private WebElement cityNorway;

    @FindBy(className = "activeIcon")
    private List<WebElement> activeIconsInDifferentWeatherContainer;

    @FindBy(className = "activeIcon")
    private WebElement activeIcon;
    
    @FindBy(xpath = "//div[@class='day-list-values']/div/span")
    private List<WebElement> dayListValues;

    @FindBy(id = "dialogTitle")
    private WebElement h3DialogTitle;

    @FindBy(xpath = "//div[@id='weather-widget']//span[@Class='orange-text']")
    private WebElement currentTime;

    @FindBy(xpath = "//div[@class='stick-footer-panel__container']")
    private WebElement footerPanelContainer;

    @FindBy(xpath = "//div[@class='stick-footer-panel__container']")
    private WebElement bottomPanel;

    @FindBy(xpath = "//button[@type='button' and text()='Allow all']")
    private WebElement allowAllButton;

    @FindBy(xpath = "//a[@href='/cookies-settings' and text()=' Manage cookies ']")
    private WebElement manageButton;

    @FindBy(xpath = "//p[@class='stick-footer-panel__description']")
    private WebElement textPanel;

    @FindBy (xpath="//div[@id='footer-website']//a[@href='/weather-dashboard']")
    private WebElement weatherDashboardFooterMenu;

    @FindBy(xpath = "//div[@class='more-options']")
    private WebElement moreOptionsDropDown;

    @FindBy(xpath = "//input[@type='number']")
    private WebElement temperatureInputInDifferentWeatherContainer;

    @FindBy(xpath="//a[text()='Bulks']")
    private WebElement bulkLink;

    @FindBy(xpath = "//div[@class = 'widget-notification']")
    private WebElement notificationMessage;

    @FindBy(xpath = "//div/a[@class='stats white-text']")
    private List<WebElement> apiIcons;

    @FindBy(xpath = "//div/a[@href='/current']")
     private WebElement currentWeatherIcon;

    @FindBy(xpath = "//div[@class = 'mobile-padding']/h1")
    private WebElement mainPageHeader1;

    @FindBy(xpath = "//span[@class = 'white-text']")
    private WebElement mainPageHeader2;

    @FindBy(xpath = "//div[@class='dropdown-selector']")
    private WebElement dataSourceDropDown;

    @FindBy(xpath = "//div[@class='owm-selector open']//ul[@class='dropdown-menu']/li")
    private List<WebElement> dataSourceOptions;

    public static final String RANDOM_TEXT = TestUtils.getRandomName();

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void waitForGrayContainerDisappeared() {
        getWait20().until(ExpectedConditions.invisibilityOf(grayContainer));
    }

    public void switchToExternalPage() {
        switchToAnotherWindow();
        getWait20().until(ExpectedConditions.numberOfWindowsToBe(2));
    }

    public String getCityCountryName() {

        return getText(h2CityCountryHeader);
    }

    public MainPage clickSearchCityField() {
        click(searchCityField);

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

    public MainPage clickDifferentWeatherButton() {
        click(differentWeatherButton);

        return this;
    }

    public MainPage waitUntilDifferentWeatherPopUpIsVisible() {
        wait10ElementToBeVisible(differentWeatherPopUpContainer);

        return this;
    }

    public MainPage inputSearchCriteria(String text) {
        input(text, searchCityField);

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

    public int getAmountOfIconsOnDifferentWeatherPopUp() {

        return getListSize(iconsList);
    }

    public List<WebElement> getListOfIconsOnDifferentWeatherPopUp() {

        return iconsList;
    }

    public MainPage clickIconOnDifferentWeatherPopUp(WebElement element) {
        click(element);

        return this;
    }

    public String getLoadingText(String attribute) {

        return getAttribute(seeLoading, attribute);
    }

    public MainPage clickCityNorway() {
        click20(cityNorway);

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

        return getText(resultErrorWidget);
    }

    public String getErrorButtonBackgroundColor() {

        return getBackgroundColor(resultErrorWidget);
    }

    public MainPage switchToMetric() {
        click(metricButton);

        return this;
    }

    public MainPage switchToImperial() {
        click(imperialButton);

        return this;
    }

    public boolean isTextContainsC() {

        return isTextContains("°C", currentTempAndUnit);
    }

    public boolean isTextContainF() {

        return isTextContains("°F", currentTempAndUnit);
    }

    public int countActiveIconsInDifferentWeatherContainer() {

        return getListSize(activeIconsInDifferentWeatherContainer);
    }

    public String getActiveIconBackgroundColorInHEX() {

        return getBackgroundColorInHEX(activeIcon);
    }
        
    public boolean isDayListValuesContainsC() {

        return isListContains("°C", getTexts(dayListValues));
    }

    public MainPage scrollToAboutUsFooterMenu() {
        scrollByVisibleElement(getAboutUsFooterMenu());

        return this;
    }

    public String getHeaderForDifferentWeatherContainer() {

        return getText(h3DialogTitle);
    }

    public boolean isDayListValuesContainsF() {

        return isListContains("°F", getTexts(dayListValues));
    }

    public MainPage scrollToDownloadOnTheAppStoreLink() {
        scrollByVisibleElement(getDownloadOnTheAppStoreLinkFooterMenu());

        return new MainPage(getDriver());
    }

    public MainPage scrollByGithubIcon() {
        scrollByVisibleElement(getGithubIconFooterMenu());

        return new MainPage(getDriver());
    }

    public MainPage scrollToPageBottom() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        return this;
    }

    public String getActualTime() {

        return getText(currentTime).substring(0, 10);
    }

    public MainPage waitForFooterPanelToBeVisible() {
        wait20ElementToBeVisible(footerPanelContainer);
        wait20ElementToBeVisible(bottomPanel);

        return this;
    }

    public MainPage waitForElementToBeVisible() {
        wait20ElementToBeVisible(allowAllButton);
        wait20ElementToBeVisible(manageButton);

        return this;
    }

    public String getBottomPanelText() {

        return getText(textPanel);
    }

    public MainPage scrollByCoordinatesToWeatherDashboardFooterMenu() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        Point point = weatherDashboardFooterMenu.getLocation();
        int xCoord = point.getX();
        int yCoord = point.getY();
        getWait20();
        js.executeScript("window.scrollTo(" + xCoord + "," + (yCoord - 200) + ")");

        return this;
    }

    public MainPage logger_Info(String str) {
        final Logger logger = Logger.getLogger(Main.class.getName());
        logger.log(Level.INFO, String.valueOf(str));

        return this;
    }

    public MainPage scrollToAskQuestionFooterMenu() {
        scrollByVisibleElement(getAskQuestionFooterMenu());

        return this;
    }

    public MainPage scrollToWidgetsFooterMenu() {
        scrollByVisibleElement(getWidgetFooterMenu());

        return this;
    }

    public MainPage clickMoreOptionsDropDown() {
        click20(moreOptionsDropDown);

        return this;
    }

    public int getTemperatureValueInDifferentWeatherContainer() {

        return convertStringToInt(getAttribute(temperatureInputInDifferentWeatherContainer,
                "_value"));
    }

    public MainPage clickUpKeyInTemperatureInput() {
        clickAKey(temperatureInputInDifferentWeatherContainer, Keys.ARROW_UP);

        return this;
    }

    public MainPage scrollToDownloadOnFooterMenuGooglePlayStore() {
        scrollByVisibleElement(getDownloadOnTheAppStoreLinkFooterMenu());

        return new MainPage(getDriver());
    }

    public WebElement getBulkLink() {

        return bulkLink;
        }
    public MainPage scrollToBulkLink() {

        if (isDisplayedElement(bulkLink)) {
            wait20ElementToBeVisible(bulkLink);
            }
        scrollByVisibleElement(bulkLink);

        return this;
        }

    public BulkPage clickBulks() {
        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
        executor.executeScript("arguments[0].click()", bulkLink);

        return new BulkPage(getDriver());
    }

    public boolean isLocationButtonDisplayed(){

        return isDisplayedElement(locationButton);
    }

    public String getNotificationMessage(){

        return getText(notificationMessage);
    }

    public List<WebElement> getDisplayedAPIIcons() {
        List<WebElement> displayedIcons = new ArrayList<>();

        for(WebElement element: apiIcons) {
            if(isDisplayedElement(element) && element.isEnabled()) {
                displayedIcons.add(element);
            } else {
                getWait20().until(ExpectedConditions.visibilityOf(element));
                displayedIcons.add(element);
            }
        }

        return displayedIcons;
    }

    public MainPage scrollByCurrentWeatherIcon() {
        scrollByVisibleElement(currentWeatherIcon);

        return this;
    }

    public List<WebElement> getApiIcons() {

        return apiIcons;
    }

    public int getAPIIconsQuantity() {

        return getListSize(getDisplayedAPIIcons());
    }

    public List<String> getAPIIconsNames() {

        return getTexts(apiIcons);
    }

    public String getHeader1Text() {

        return getText(mainPageHeader1);
    }

    public String getHeader2Text() {

        return getText(mainPageHeader2);
    }

    public MainPage clickDataSourceDropDown() {
        click(dataSourceDropDown);

        return this;
    }

    public List<String> getDataSourceOptionsTexts() {

        return getListText(dataSourceOptions);
    }

    public MainPage clickAllDataSourceOptions() {
        for (int i = 0; i < dataSourceOptions.size(); i++) {
            click(dataSourceOptions.get(i));
            click(dataSourceDropDown);
        }

        return this;
    }

    public MainPage clickFirstDataSourceOption() {
        click(dataSourceOptions.get(0));

        return this;
    }

    public String getDataSourceDropDownText() {

        return getText(dataSourceDropDown);
    }
}
