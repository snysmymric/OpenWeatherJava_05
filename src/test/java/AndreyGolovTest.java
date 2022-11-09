import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class AndreyGolovTest extends BaseTest {

    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        getDriver().get(url);
        Thread.sleep(7000);

        WebElement searchCityField = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']")
        );
        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchButton = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']")
        );
        searchButton.click();
        Thread.sleep(1000);

        WebElement parisFRChoiceInDropdownMenu = getDriver().findElement(
                By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']")
        );
        parisFRChoiceInDropdownMenu.click();
        Thread.sleep(1000);

        WebElement h2CityCountryHeader = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//h2")
        );
        Thread.sleep(2000);
        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testShowingTempInFahrenheit_AfterSwitchFromCelsius() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String expectedStringResultCurrentWeatherInFarenheit = "F";
        String tempInFarenheit = "F";

        getDriver().get(url);
        Thread.sleep(7000);

        WebElement showTempInFahrenheit = getDriver().findElement(
                By.xpath("//div[@class = 'option'][contains(text(), 'Imperial')]")
        );
        showTempInFahrenheit.click();

        WebElement currentTempInFahrenheit = getDriver().findElement(
                By.xpath("//span[@class = 'heading']")
        );

        String actualStringResultCurrentWeatherInFarenheit =
                currentTempInFahrenheit.getText().substring(currentTempInFahrenheit.getText().length() - 1);

        Assert.assertEquals(actualStringResultCurrentWeatherInFarenheit, expectedStringResultCurrentWeatherInFarenheit);
        Assert.assertTrue(currentTempInFahrenheit.getText().contains(tempInFarenheit));
    }

    @Test
    public void testCookiesWarningPanelAndTwoButtonsOnItDisplayed_WhenMainPageStarts() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String expectedResultCookiesWarning = "We use cookies which are essential for the site to work. " +
                "We also use non-essential cookies to help us improve our services. " +
                "Any data collected is anonymised. You can allow all cookies or manage them individually.";
        String textOfButton1 = "Allow all";
        String textOfButton2 = "Manage cookies";

        getDriver().get(url);
        Thread.sleep(7000);

        WebElement textCookieWarning = getDriver().findElement(
                By.className("stick-footer-panel__description")
        );
        String actualResultCookiesWarning = textCookieWarning.getText();
        Assert.assertEquals(actualResultCookiesWarning, expectedResultCookiesWarning);

        Assert.assertEquals(
                getDriver().findElements(By.xpath("//div[@class = 'stick-footer-panel__container']/*"))
                        .size(), 2
        );

        Assert.assertTrue(
                getDriver().findElement(
                        By.xpath("//div[@class = 'stick-footer-panel__btn-container']/*[text() = '" + textOfButton1 + "']")
                ).isDisplayed()
        );

        Assert.assertTrue(
                getDriver().findElement(
                        By.xpath("//div[@class = 'stick-footer-panel__btn-container']/*[contains(text(), '"+ textOfButton2 + "')]")
                ).isDisplayed()
        );
    }


}
