import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class EkaterinalizinaTest extends BaseTest {

    private static final String URL ="https://openweathermap.org/";
    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        getDriver().get(URL);
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

        Thread.sleep(10000);

        WebElement parisFrhoiseInDropDownMenu = getDriver().findElement(
                By.xpath("//ul[@class ='search-dropdown-menu']/li/span[text() = 'Paris, FR ']")
        );

        parisFrhoiseInDropDownMenu.click();

        WebElement h2CityCountHeader = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//h2")
        );

        Thread.sleep(10000);

        String actualResult = h2CityCountHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testTemperatureChangedInToF() throws InterruptedException {
        String fTempSymbol = "°F";

        getDriver().get(URL);
        Thread.sleep(10000);

        WebElement ButtonTemperatureFarenheit = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//div[text() = 'Imperial: °F, mph']"));
        ButtonTemperatureFarenheit.click();

        Thread.sleep(10000);

        WebElement tempF = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//span[@class ='heading']"));

        Assert.assertTrue(tempF.getText().contains(fTempSymbol));
    }

    @Test
    public void testTwoButtonsCookies() throws InterruptedException {
        getDriver().get(URL);

        Thread.sleep(10000);
        String expectedResult1 = "We use cookies which are essential for the site to work." +
                " We also use non-essential cookies to help us improve our services." +
                " Any data collected is anonymised. You can allow all cookies" +
                " or manage them individually.";
        String expectedResult2 = "Allow all";
        String expectedResult3 = "Manage cookies";

        WebElement cookiesText = getDriver().findElement(
                By.xpath("//div[@id = 'stick-footer-panel']//p[contains(text (), 'We use cookies which')]"));
        String actualResult1 = cookiesText.getText();

        WebElement cookiesButton1 = getDriver().findElement(
                By.xpath("//div[@id = 'stick-footer-panel']//button[text() = 'Allow all']"));
        WebElement cookiesButton2 = getDriver().findElement
                (By.xpath("//div[@id = 'stick-footer-panel']//a[text() = ' Manage cookies ']"));
        String actualResult2 = cookiesButton1.getText();
        String actualResult3 = cookiesButton2.getText();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
        Assert.assertEquals(actualResult3, expectedResult3);
    }

    @Test
    public void testSupportMenuHasThreeSubmenu() throws InterruptedException {
        getDriver().get(URL);

        Thread.sleep(10000);

        String expectedResult1 = "Support";
        String expectedResult2 = "FAQ";
        String expectedResult3 = "How to start";
        String expectedResult4 = "Ask a question";

        WebElement supportMenu = getDriver().findElement(
                By.xpath("//div[@id = 'support-dropdown']"));
        String actualResult1 = supportMenu.getText();

        supportMenu.click();

        Thread.sleep(10000);

        WebElement supportSubMenuFAQ = getDriver().findElement(
                By.xpath("//ul[@id=\"support-dropdown-menu\"]//a[text() = 'FAQ']"));
        String actualResult2 = supportSubMenuFAQ.getText();

        WebElement supportSubMenuHowToStart = getDriver().findElement(
                By.xpath("//ul[@id=\"support-dropdown-menu\"]//a[text() = 'How to start']"));
        String actualResult3 = supportSubMenuHowToStart.getText();

        WebElement supportSubMenuAskAQuestion = getDriver().findElement(By.xpath("//ul[@id=\"support-dropdown-menu\"]//a[text() = 'Ask a question']"));
        String actualResult4 = supportSubMenuAskAQuestion.getText();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
        Assert.assertEquals(actualResult3, expectedResult3);
        Assert.assertEquals(actualResult4, expectedResult4);
    }

    @Test
    public void testHeadersH1andH2OnMainPage() throws InterruptedException {
        getDriver().get(URL);

        Thread.sleep(10000);

        String expectedResult1 = "OpenWeather";
        boolean actualResult1 = getDriver().findElement(
                By.xpath("//span [text () = 'Weather forecasts, nowcasts and history in a fast and elegant way']")).isDisplayed();
        boolean actualResult2 = getDriver().findElement(
                By.xpath("//h1/span[text() ='OpenWeather']")).isDisplayed();

        Assert.assertTrue(actualResult1 && actualResult2);
    }
}
