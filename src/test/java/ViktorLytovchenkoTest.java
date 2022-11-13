import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
    public class ViktorLytovchenkoTest extends BaseTest {

        @Test
        public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {
            String url = "https://openweathermap.org/";
            String cityName = "Paris";
            String expectedResult = "Paris, FR";

            getDriver().get(url);
            Thread.sleep(10000);

            WebElement searchCityField = getDriver().findElement(
                    By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']")
            );

            searchCityField.click();
            searchCityField.sendKeys(cityName);
            Thread.sleep(3000);

            WebElement searchButton = getDriver().findElement(
                    By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']")
            );

            searchButton.click();
            Thread.sleep(3000);

            WebElement parisFranceChoiceInDropDownMenu = getDriver().findElement(
                    By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']")
            );
            parisFranceChoiceInDropDownMenu.click();

            WebElement h2CityCountryHeader = getDriver().findElement(
                    By.xpath("//div[@id = 'weather-widget']//h2")
            );

            Thread.sleep(2000);
            String actualResult = h2CityCountryHeader.getText();

            Assert.assertEquals(actualResult, expectedResult);
        }

        @Test
        public void testTypeInSearchBarRome() throws InterruptedException {
            String url = "https://openweathermap.org/";
            getDriver().get(url);
            Thread.sleep(10000);
            String cityName = "Rome";
            String expectedResult = "https://openweathermap.org/find?q=Rome";
            String expectedResult1 = "Rome";

            WebElement weatherInYourcity = getDriver().findElement(
                    By.xpath("//div[@id = 'desktop-menu']/form/input[1]")
            );
            weatherInYourcity.click();
            weatherInYourcity.sendKeys(cityName);
            weatherInYourcity.submit();
            String actualResult = getDriver().getCurrentUrl();

            WebElement theWordIsWritten = getDriver().findElement(
                    By.id("search_str")
            );
            String actualResult1 = theWordIsWritten.getAttribute("value");

            Assert.assertEquals(actualResult, expectedResult);
            Assert.assertEquals(actualResult1, expectedResult1);
        }

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

        @Test
        public void testConfirmWhatOnThePageUseCookies() throws InterruptedException {
            String url = "https://openweathermap.org/";
            String expectedResult1 = "We use cookies which are essential for the site to work. " +
                    "We also use non-essential cookies to help us improve our services. Any data " +
                    "collected is anonymised. You can allow all cookies or manage them individually.";
            String expectedResult2 = "Allow all";
            String expectedResult3 = "Manage cookies";
            getDriver().get(url);
            Thread.sleep(10000);

            WebElement thePageUseCookies = getDriver().findElement(
                    By.xpath("//div[@id = 'stick-footer-panel']//p")
            );

            WebElement thePageAllowAll = getDriver().findElement(
                    By.xpath("//div[@id = 'stick-footer-panel']//button[contains(text(),'Allow all')]")
            );

            WebElement thePageManageCookies = getDriver().findElement(
                    By.xpath("//div[@id = 'stick-footer-panel']//a[contains(text(),' Manage cookies ')]")
            );
            String actualResult1 = thePageUseCookies.getText();
            String actualResult2 = thePageAllowAll.getText();
            String actualResult3 = thePageManageCookies.getText();

            Assert.assertEquals(actualResult1, expectedResult1);
            Assert.assertEquals(actualResult2, expectedResult2);
            Assert.assertEquals(actualResult3, expectedResult3);
        }

    }


