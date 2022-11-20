//import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;
//import org.testng.annotations.Ignore;
//import org.testng.annotations.Test;
//import base.BaseTest;
//import java.util.List;
//
//@Ignore
//public class elenuraTest extends BaseTest {
//
//    final static String BASE_URL = "https://openweathermap.org/";
//
//    final static By H2_CITY_COUNTRY_HEADER = By.xpath("//div[@id = 'weather-widget']//h2");
//    final static By SEARCH_CITY_FIELD = By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']");
//    final static By SEARCH_BUTTON = By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']");
//    final static By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
//    final static By PARIS_FR_CHOICE_IN_DROPDOWN_MENU = By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']");
//    final static By DASHBOARD_MENU = By.xpath("//div[@id='desktop-menu']//a[@href='/weather-dashboard']");
//    final static By DASHBOARD_HEADER = By.xpath("//h1[@class = 'breadcrumb-title']");
//
//    private void openBaseURL() {
//        getDriver().get(BASE_URL);
//    }
//
//    private void waitForGrayFrameDisappeared() {
//        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(
//                By.className("owm-loader-container")));
//    }
//
//    private String getText(By by, WebDriver driver) {
//
//        return driver.findElement(by).getText();
//    }
//
//    private void click(By by,  WebDriverWait wait) {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
//        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
//    }
//
//    private void input(String text, By by, WebDriver driver) {
//        driver.findElement(by).sendKeys(text);
//    }
//
//    private void waitElementToBeVisible(By by, WebDriverWait wait) {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
//    }
//
//    private void waitTextToBeChanged(By by, String text, WebDriver driver, WebDriverWait wait) {
//        wait.until(ExpectedConditions
//                .not(ExpectedConditions.textToBePresentInElement(driver.findElement(by), text)));
//    }
//
//    @Test
//    public void testH2TagTextWhenSearchingCityCountry() throws InterruptedException {
//
//        String cityName = "Paris";
//        String expectedResult = "Paris, FR";
//
//        openBaseURL();
//        waitForGrayFrameDisappeared();
//
//        String oldH2Header = getText(H2_CITY_COUNTRY_HEADER, getDriver());
//
//        click(SEARCH_CITY_FIELD, getWait5());
//        input(cityName, SEARCH_CITY_FIELD, getDriver());
//        click(SEARCH_BUTTON, getWait5());
//        waitElementToBeVisible(SEARCH_DROPDOWN_MENU, getWait10());
//        click(PARIS_FR_CHOICE_IN_DROPDOWN_MENU, getWait10());
//        waitTextToBeChanged(H2_CITY_COUNTRY_HEADER, oldH2Header, getDriver(), getWait10());
//
//        String actualResult = getText(H2_CITY_COUNTRY_HEADER, getDriver());
//
//        Assert.assertEquals(actualResult, expectedResult);
//    }
//
//    @Test
//    public void testDashboardIsClickableVerifyTitle() {
//        String expectedResultURL = "https://openweathermap.org/weather-dashboard";
//        String expectedResultHeader = "Weather dashboard";
//
//        openBaseURL();
//        waitForGrayFrameDisappeared();
//        click(DASHBOARD_MENU,getWait10());
//
//        Assert.assertEquals(getDriver().getCurrentUrl(),expectedResultURL);
//        Assert.assertEquals(getText(DASHBOARD_HEADER, getDriver()), expectedResultHeader);
//    }
//    @Ignore
//    @Test
//    public void testTitleOpenWeatherMapAPIGuide() throws InterruptedException {
//
//        String url = "https://openweathermap.org/";
//        String expectedResultOpenPage = "https://openweathermap.org/guide";
//        String expectedResultTitle = "OpenWeatherMap API guide - OpenWeatherMap";
//
//        getDriver().get(url);
//        Thread.sleep(10000);
//
//        WebElement Title = getDriver().findElement(
//                By.xpath("//div/ul/li/a[@href='/guide']")
//        );
//        Title.click();
//        Thread.sleep(3000);
//
//        Assert.assertEquals(getDriver().getCurrentUrl(),expectedResultOpenPage);
//        Assert.assertEquals(getDriver().getTitle(),expectedResultTitle);
//    }
//
//    @Ignore
//    @Test
//
//    public void testUnitOfMeasureFahrenheit () throws InterruptedException {
//        String url = "https://openweathermap.org/";
//        boolean expectedResultFahrenheit = true;
//
//        getDriver().get(url);
//        Thread.sleep(10000);
//
//        WebElement tempUnit = getDriver().findElement(
//                By.xpath("//div[text()='Imperial: °F, mph']")
//        );
//        tempUnit.click();
//        Thread.sleep(5000);
//
//        WebElement tempUnitHeading = getDriver().findElement(
//                By.xpath("//div[@class='current-temp']/span")
//        );
//        Assert.assertEquals(tempUnitHeading.getText().contains("°F"),expectedResultFahrenheit);
//    }
//
//    @Ignore
//    @Test
//    public void testNumberOfOrangeButtons() throws InterruptedException {
//        String url = "https://openweathermap.org/";
//        getDriver().get(url);
//        Thread.sleep(10000);
//
//        WebElement menuAPI = getDriver().findElement(
//                By.xpath("//li/a[@href='/api']")
//        );
//        menuAPI.click();
//        Thread.sleep(2000);
//
//        List<WebElement> orangeButtons = getDriver().findElements(
//                By.xpath("//a[@type='button' and contains(@class,'orange') or contains(@class, 'btn-orange')]")
//        );
//
//        Assert.assertEquals(orangeButtons.size(),30);
//    }
//
//    @Ignore
//    @Test
//    public void  testLogoNotChangeURL() throws InterruptedException {
//        String url = "https://openweathermap.org/";
//        String expectedResult = "https://openweathermap.org/";
//        getDriver().get(url);
//        Thread.sleep(10000);
//
//        WebElement logo = getDriver().findElement(
//                By.xpath("//img[@src = '/themes/openweathermap/assets/img/logo_white_cropped.png']")
//        );
//        logo.click();
//        Thread.sleep(5000);
//
//        Assert.assertEquals(getDriver().getCurrentUrl(),expectedResult);
//    }
//
//    @Ignore
//    @Test
//    public void testCookiesPanel_Footer_WhenOpenWebsite() throws InterruptedException {
//        String url = "https://openweathermap.org/";
//        String expectedResultPanelText = "We use cookies which are essential for the site to work. " +
//                "We also use non-essential cookies to help us improve our services. Any data collected " +
//                "is anonymised. You can allow all cookies or manage them individually.";
//        String expectedResultAllowAll = "Allow all";
//        String expectedResultManageCookies = "Manage cookies";
//
//        getDriver().get(url);
//        Thread.sleep(10000);
//
//        WebElement cookies = getDriver().findElement(
//                By.xpath("//div/p[@class='stick-footer-panel__description']")
//        );
//        Assert.assertEquals(cookies.getText(), expectedResultPanelText);
//
//        WebElement allowAllButton = getDriver().findElement(
//                By.xpath("//div/button[@type='button']")
//        );
//        Assert.assertEquals(allowAllButton.getText(), expectedResultAllowAll);
//
//        WebElement manageCookies = getDriver().findElement(
//                By.xpath("//div/a[@href='/cookies-settings']")
//        );
//        Assert.assertEquals(manageCookies.getText(), expectedResultManageCookies);
//    }
//
//    @Ignore
//    @Test
//    public void testWeatherInYourCityField() throws InterruptedException {
//        String url = "https://openweathermap.org/";
//        String cityName = "Rome";
//        boolean expectedResult = true;
//        String expectedResult2 = "Rome";
//
//        getDriver().get(url);
//        Thread.sleep(10000);
//
//        WebElement weatherInYourCityField = getDriver().findElement(
//                By.xpath("//div[@id = 'desktop-menu']//input[@placeholder='Weather in your city']")
//        );
//        weatherInYourCityField.click();
//        weatherInYourCityField.sendKeys(cityName);
//        weatherInYourCityField.sendKeys(Keys.ENTER);
//
//        Assert.assertEquals(getDriver().getCurrentUrl().contains("find"), expectedResult);
//
//        WebElement searchField = getDriver().findElement(
//                By.xpath("//div[@class='form-group']/input[@class='form-control border-color col-sm-12']")
//        );
//        Assert.assertEquals(searchField.getAttribute("value"), expectedResult2);
//    }
//}
