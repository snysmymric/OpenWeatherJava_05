import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

public class ViacheslavAnisimovTest extends BaseTest {
    final static String BASE_URL = "https://openweathermap.org/";

    final static By H_2_CITY_COUNTRY_HEADER = By.xpath("//div[@id = 'weather-widget']//h2");
    final static By SEARCH_CITY_FIELD = By.xpath("//div[@id='weather-widget']//input[@placeholder = 'Search city']");
    final static By SEARCH_BUTTON = By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']");
    final static By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
    final static By PARIS_FR_CHOICE_IN_DROPDOWN_MENU = By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']");
    final static By HAMBURGER_MENU = By.id("hamburger");
    final static By LINK_TO_GUIDE_IN_HAMBURGER_MENU = By.xpath("//ul[@id='mobile-menu']//a[@href='/guide']");


    private void openBaseURL() {
        getDriver().get(BASE_URL);
    }

    private void waitForGrayFrameDisappeared() {
        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("own-loader-container"))
        );
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

    @Test
    public void testH2TagText_WhenSearchingCityCountry() {
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        openBaseURL();
        waitForGrayFrameDisappeared();

        String oldH2Header = getText(H_2_CITY_COUNTRY_HEADER, getDriver());

        click(SEARCH_CITY_FIELD, getWait5());
        input(cityName, SEARCH_CITY_FIELD, getDriver());
        click(SEARCH_BUTTON, getWait10());
        waitElementToBeVisible(SEARCH_DROPDOWN_MENU, getWait10());
        click(PARIS_FR_CHOICE_IN_DROPDOWN_MENU, getWait10());
        waitTextToBeChanged(H_2_CITY_COUNTRY_HEADER, oldH2Header, getDriver(), getWait10());

        String actualResult = getText(H_2_CITY_COUNTRY_HEADER, getDriver());

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testHamburgerMenuAndGuidePageTitle() throws InterruptedException {
        String expectedResult1 = "https://openweathermap.org/guide";
        String expectedResult2 = "OpenWeatherMap API guide - OpenWeatherMap";

        getDriver().manage().window().setSize(new Dimension(1024, 970));

        openBaseURL();
        waitForGrayFrameDisappeared();
        click(HAMBURGER_MENU, getWait10());
        click(LINK_TO_GUIDE_IN_HAMBURGER_MENU, getWait10());

        String actualResult1 = getDriver().getCurrentUrl();
        String actualResult2 = getDriver().getTitle();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
    }

    @Ignore
    @Test

    public void testMarketplaceMenuIsClickableOpenCustomWeatherProducts() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String marketplaceMenuText = "Marketplace";
        String expectedResult1 = "https://home.openweathermap.org/marketplace";
        String expectedResult2 = "Custom Weather Products";

        getDriver().get(url);
        Thread.sleep(10000);

        String originalWindow = getDriver().getWindowHandle();
        assert getDriver().getWindowHandles().size() == 1;

        WebElement guideMenu = getDriver().findElement(By.linkText(marketplaceMenuText));
        guideMenu.click();

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(numberOfWindowsToBe(2));
        for (String windowHandle : getDriver().getWindowHandles()) {
            if(!originalWindow.contentEquals(windowHandle)) {
                getDriver().switchTo().window(windowHandle);
                break;
            }
        }

        String actualResult1 = getDriver().getCurrentUrl();
        String actualResult2 = getDriver().findElement(
                By.cssSelector("h1")
        ).getText();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);

    }
}
