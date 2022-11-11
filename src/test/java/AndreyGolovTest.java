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
        Thread.sleep(10000);

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
        Thread.sleep(10000);

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
        Thread.sleep(10000);

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

    @Test
    public void testDropDownSupportMenu() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String firstDropDownSupportMenuElementText = "FAQ";
        String secondDropDownSupportMenuElementText = "How to start";
        String thirdDropDownSupportMenuElementText = "Ask a question";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement supportMenu = getDriver().findElement(
                By.id("support-dropdown")
        );

        supportMenu.click();
        Thread.sleep(10000);

        WebElement dropDownSupportMenu = getDriver().findElement(
                By.id("support-dropdown-menu")
        );

        Assert.assertEquals(
                getDriver().findElements(By.xpath("//ul[@id = 'support-dropdown-menu']/*"))
                        .size(), 3
        );

        Assert.assertEquals(
                getDriver().findElement(By.xpath("//li[@class = 'with-dropdown']//a[@href = '/faq']")).getText()
                , firstDropDownSupportMenuElementText
        );

        Assert.assertEquals(
                getDriver().findElement(By.xpath("//li[@class = 'with-dropdown']//a[@href = '/appid']")).getText()
                , secondDropDownSupportMenuElementText
        );

        Assert.assertEquals(
                getDriver().findElement(By.xpath("//li[@class = 'with-dropdown']//a[@href = 'https://home.openweathermap.org/questions']")).getText()
                , thirdDropDownSupportMenuElementText
        );

    }


}
