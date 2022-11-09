import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

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
            Thread.sleep(2000);

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
    }


