import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import base.BaseTest;

@Ignore
public class ViktorLytovchenkoTest extends BaseTest {

        final static String BASE_URL = "https://openweathermap.org/";

        final static By WEATHER_IN_YOUR_CITY_FIELD = By.xpath("//div[@id = 'desktop-menu']/form/input[1]");

        final static By CITY_IN_VALUE = By.xpath("//div[@class = 'form-group']//input[@id = 'search_str']");

        private void openBaseURL() {getDriver().get(BASE_URL);}

        private void waitForGrayFrameDisappeared() {
            getWait20().until(ExpectedConditions.invisibilityOfElementLocated(
                    By.className("owm-loader-container")));}

        private void click(By by,  WebDriverWait wait) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            wait.until(ExpectedConditions.elementToBeClickable(by)).click();
        }

        private void input(String text, By by, WebDriver driver) {
            driver.findElement(by).sendKeys(text);
        }
        private void enter(By by, WebDriver driver) {
            driver.findElement(by).sendKeys(Keys.ENTER);;
        }

        private String getText(By by, WebDriver driver) {

            return driver.findElement(by).getAttribute("value");
        }



        @Test
        public void testTypeInSearchBarRome() {
            String cityName = "Rome";
            String expectedResult = "https://openweathermap.org/find?q=Rome";
            String expectedResult1 = "Rome";

            openBaseURL();
            waitForGrayFrameDisappeared();

            click(WEATHER_IN_YOUR_CITY_FIELD, getWait5());
            input(cityName, WEATHER_IN_YOUR_CITY_FIELD, getDriver());
            enter(WEATHER_IN_YOUR_CITY_FIELD, getDriver());

            String actualResult = getDriver().getCurrentUrl();
            String actualResult1 = getText(CITY_IN_VALUE, getDriver());

            Assert.assertEquals(actualResult, expectedResult);
            Assert.assertEquals(actualResult1, expectedResult1);
        }

        @Ignore
        @Test
        public void checkSiteReload() throws InterruptedException {
            String url = "https://openweathermap.org/";
            getDriver().get(url);
            Thread.sleep(10000);

            WebElement imageLogoSite = getDriver().findElement(
                    By.xpath("//body[@class = 'body-orange']//ul[@id = 'first-level-nav']//a/img")
            );
            imageLogoSite.click();
            Thread.sleep(10000);

            String expectedResult = "https://openweathermap.org/";
            String actualResult = getDriver().getCurrentUrl();

            Assert.assertEquals(actualResult, expectedResult);
        }

    }


