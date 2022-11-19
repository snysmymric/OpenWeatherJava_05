import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

public class ElenaHoustonTest extends BaseTest {
    final static String Base_URL = "https://openweathermap.org/";
    final static By H2_CITY_COUNTRY_HEADER = By.xpath("//div[@id='weather-widget']//h2");
    final static By SEARCH_CITY_FIELD = By.xpath("//div[@id='weather-widget']//input[@placeholder='Search city']");
    final static By SEARCH_BUTTON = By.xpath("//div[@id='weather-widget']//button[@type='submit']");
    final static By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
    final static By PARIS_CHOICE_IN_DROP_DOWN_MENU = By.xpath(
            "//ul[@class ='search-dropdown-menu']/li/span[text() = 'Paris, FR ']");
    final static By GUIDE_MENU = By.xpath("//div[@id = 'desktop-menu']//a[@href = '/guide']");

    private void openBaseURL() {
        getDriver().get(Base_URL);
    }
    private void waitForGrayFrameDisappeared() {
        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(By.className("owm-loader-container")));
    }

    private String getText(By by, WebDriver driver) {
        return driver.findElement(by).getText();
    }

    private void click(By by, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }
    private void input(String text, By by, WebDriver driver) {
        driver.findElement(by).sendKeys(text);
    }

    private void waitElementToBeVisible(By by, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    private void waitTextToBeChanged(By by, String text, WebDriver driver, WebDriverWait wait) {
        wait.until(ExpectedConditions
                .not(ExpectedConditions.textToBePresentInElement(driver.findElement(by), text)));
    }

    private void findGuideMenuByLink(String text){
     getDriver().findElement(By.linkText(text));
    }

    @Test
    public void testH2TagText_WhenSearchingCityCountry() {
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        openBaseURL();
        waitForGrayFrameDisappeared();

        String oldH2Header = getText(H2_CITY_COUNTRY_HEADER, getDriver());

        click(SEARCH_CITY_FIELD, getWait5());
        input(cityName, SEARCH_CITY_FIELD, getDriver());
        click(SEARCH_BUTTON, getWait5());
        waitElementToBeVisible(SEARCH_DROPDOWN_MENU, getWait10());
        click(PARIS_CHOICE_IN_DROP_DOWN_MENU, getWait5());
        waitTextToBeChanged(H2_CITY_COUNTRY_HEADER, oldH2Header, getDriver(), getWait10());
        String actualResult = getText(H2_CITY_COUNTRY_HEADER, getDriver());

        Assert.assertEquals(actualResult, expectedResult);
    }
    @Test
    public void testConfirmTheLinkAndTitleOnThePage_OpenWeatherMap() throws InterruptedException {
        String expectedResult1 = "https://openweathermap.org/guide";

        String expectedResult2 = "OpenWeatherMap API guide - OpenWeatherMap";

        openBaseURL();
        waitForGrayFrameDisappeared();

       click(GUIDE_MENU, getWait5());

        String actualResult1 = getDriver().getCurrentUrl();
        String actualResult2 = getDriver().getTitle();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
    }
//    @Ignore
//    @Test
//    public void testUnitsOfMeasurement_ImperialFMphConfirmFLondon() throws InterruptedException {
//        String url = "https://openweathermap.org/";
//        String fTempSymbol = "°F";
//        String expectedResult = "°F";
//
//        getDriver().get(url);
//        getDriver().manage().window().maximize();
//        Thread.sleep(10000);
//
//        WebElement pushImperialFahrenheit = getDriver().findElement(By.xpath("//div[@class = 'switch-container']//div"
//                + "[text()= 'Imperial: °F, mph']")
//        );
//        pushImperialFahrenheit.click();
//
//        WebElement tempInF = getDriver().findElement(By.xpath("//div[@class='current-temp']/span")
//        );
//        String tempF = tempInF.getText();
//        String actualResult = tempF.substring((tempF.length() - 2));
//
//        Assert.assertTrue(tempInF.getText().contains(fTempSymbol));
//        Assert.assertEquals(actualResult, expectedResult);
//}
//    @Ignore
//    @Test
//    public void testCheckPresenceOfTreeSubMenusInTheHead () throws InterruptedException {
//        String url = "https://openweathermap.org/";
//
//        String expectedResult1 = "FAQ";
//        String expectedResult2 = "How to start";
//        String expectedResult3 = "Ask a question";
//
//        getDriver().get(url);
//        Thread.sleep(10000);
//
//        WebElement searchSupport = getDriver().findElement(By.xpath("//div[@id = 'support-dropdown']")
//        );
//        searchSupport.click();
//        Thread.sleep(2000);
//
//        WebElement findSub1 = getDriver().findElement(By.xpath("//ul[@class ='dropdown-menu dropdown-visible']//"
//                + "a[@href = '/faq']")
//        );
//
//        WebElement findSub2 = getDriver().findElement(By.xpath("//ul[@class ='dropdown-menu dropdown-visible']//"
//                + "a[@href = '/appid']")
//        );
//
//        WebElement findSub3 = getDriver().findElement(By.xpath("//ul[@class ='dropdown-menu dropdown-visible']//"
//                + "a[text() = 'Ask a question']")
//        );
//
//        Assert.assertEquals(getDriver().findElements(By.xpath("//ul[@id = 'support-dropdown-menu']/li")).size(), 3);
//
//        String actualResult1 = findSub1.getText();
//        String actualResult2 = findSub2.getText();
//        String actualResult3 = findSub3.getText();
//
//        Assert.assertEquals(actualResult1, expectedResult1);
//        Assert.assertEquals(actualResult2, expectedResult2);
//        Assert.assertEquals(actualResult3, expectedResult3);
//    }
}
