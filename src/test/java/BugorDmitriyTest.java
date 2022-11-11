import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class BugorDmitriyTest extends BaseTest {
    @Test
    public void testH2TextWhenSearchingCityCountry() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        getDriver().get(url);
        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().maximize();

        Thread.sleep(10000);

        WebElement searchCityField = getDriver().findElement(
                By.xpath("//div[@id='weather-widget']//input[@placeholder='Search city']")
        );
        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchButton = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']")
        );
        searchButton.click();

        Thread.sleep(1000);
        WebElement parisFRChoiceDropdownMenu = getDriver().findElement(
                By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']")
        );
        parisFRChoiceDropdownMenu.click();

        WebElement h2CityCountryHeader = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//h2")
        );
        Thread.sleep(1000);
        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }
            @Test
        public void testConfirmText() throws InterruptedException {
            String url = "https://openweathermap.org/";
            String expectedResult1 = "We use cookies which are essential for the site to work. We also use non-essential"
                    + " cookies to help us improve our services. Any data collected is anonymised. You can allow all "
                    + "cookies or manage them individually.";
            String expectedResult2 = "Allow all";
            String expectedResult3 = "Manage cookies";

            getDriver().get(url);
            Thread.sleep(10000);

            String actualResult1 = getDriver().findElement(
                    By.xpath("//div[@class='stick-footer-panel__container']//p[text()='We use cookies which are essential for the site to work. We also use non-essential cookies to help us improve our services. Any data collected is anonymised. You can allow all cookies or manage them individually.']")
            ).getText();

            String actualResultAllowAll = getDriver().findElement(
                    By.xpath("//button[@class='stick-footer-panel__link']")).getText();

            String actualResultManageCookies = getDriver().findElement(
                    By.xpath("//div[@class='stick-footer-panel__btn-container']//a")).getText();

            Assert.assertEquals(actualResult1, expectedResult1);
            Assert.assertEquals(actualResultAllowAll, expectedResult2);
            Assert.assertEquals(actualResultManageCookies, expectedResult3);
    }
   }
