import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
public class OlgaKorolenkoTest extends BaseTest {
    final static String BAZE_URL = "https://openweathermap.org/";

    final static By H_2_CITY_CONTRY_HEADER = By.xpath("//div[@id = 'weather-widget']//h2");
    final static By SEARCH_CITY_FIELD = By.xpath("//div[@id ='weather-widget']//input[@placeholder = 'Search city']");
    final static By SEARCH_BUTTON = By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']");
    final static By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
    final static By PARIS_FR_CHOUICE_ID_DROPDOWN_MENU =  By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() ='Paris, FR ']");

    private void openBaseURL(){
        getDriver().get(BAZE_URL);
    }

    private void waitForGrayFrameDisappeared(){
        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("own-loader-container")));
    }

    private String getText(By by, WebDriver driver){

       return driver.findElement(by).getText();
    }

    private void click(By by, WebDriverWait wait){
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    private void input(String text, By by, WebDriver driver) {
        driver.findElement(by).sendKeys(text);
    }

    private void waitElementToBeVisible(By by, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    private void waitTextToBeChanged(By by, String text, WebDriver  driver, WebDriverWait wait) {
        wait.until(ExpectedConditions
                .not(ExpectedConditions.textToBePresentInElement(driver.findElement(by), text)));
    }

    @Test
    public void testH2TagText_WhenSearchingCityCountry() {
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        openBaseURL();
        waitForGrayFrameDisappeared();
        
        String oldH2Header = getText(H_2_CITY_CONTRY_HEADER,getDriver());

        click(SEARCH_CITY_FIELD,getWait5());
        input(cityName, SEARCH_CITY_FIELD, getDriver());
        click(SEARCH_BUTTON, getWait5());
        waitElementToBeVisible(SEARCH_DROPDOWN_MENU, getWait10());
        click(PARIS_FR_CHOUICE_ID_DROPDOWN_MENU,getWait10());
        waitTextToBeChanged(H_2_CITY_CONTRY_HEADER, oldH2Header, getDriver(), getWait10() );

        String actualResult = getText(H_2_CITY_CONTRY_HEADER, getDriver());

        Assert.assertEquals(actualResult,expectedResult);
    }
}
