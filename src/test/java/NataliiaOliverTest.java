import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class NataliiaOliverTest extends BaseTest {

    private static final String URL = "https://openweathermap.org/";

    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        getDriver().get(URL);
        Thread.sleep(10000);

        WebElement searchCityField = getDriver().findElement(
                By.xpath("//div[@id='weather-widget']//input[@placeholder='Search city']")
        );
        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchButton = getDriver().findElement(
                By.xpath("//div[@id='weather-widget']//button[@type='submit']")
        );
        searchButton.click();

        Thread.sleep(1000);

        WebElement parisFRChoiceInDropdownMenu = getDriver().findElement(
                By.xpath("//ul[@class='search-dropdown-menu']/li/span[text()='Paris, FR ']")
        );
        parisFRChoiceInDropdownMenu.click();

        WebElement h2CityCountryHeader = getDriver().findElement(
                By.xpath("//div[@id='weather-widget']//h2")
        );

        Thread.sleep(2000);
        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testPressImperialButtonF_TempIsdIsDisplayedInF() throws InterruptedException {
        String expectedResult = "°F";

        getDriver().get(URL);
        Thread.sleep(10000);

        WebElement searchImperial = getDriver().findElement(
                By.xpath("//div[@id='weather-widget']//div[text()='Imperial: °F, mph']")
        );
        searchImperial.click();

        Thread.sleep(2000);

        WebElement tempF = getDriver().findElement(By.xpath("//div[@class='current-temp']/span[@class='heading']"));
        String tempInF = tempF.getText();

        Assert.assertTrue(tempInF.contains(expectedResult));
    }

    @Test
    public void testFindingCookiePanelAndTwoButtons() throws InterruptedException {
        String expectedResult1 = "We use cookies which are essential for the site to work. We also use non-essential "
                + "cookies to help us improve our services. Any data collected is anonymised. You can allow all cookies "
                + "or manage them individually.";
        String expectedResult2 = "Allow all";
        String expectedResult3 = "Manage cookies";

        getDriver().get(URL);
        Thread.sleep(10000);

        WebElement footerCookiePanel = getDriver().findElement(
                By.className("stick-footer-panel__description"));
        String actualResult1 = footerCookiePanel.getText();

        WebElement footerButtonAllow = getDriver().findElement(
                By.xpath("//div[@id='stick-footer-panel']//button[text()='Allow all']"));
        String actualResult2 = footerButtonAllow.getText();

        WebElement footerButtonManage = getDriver().findElement(
                By.xpath("//div[@id='stick-footer-panel']//a[@href='/cookies-settings']"));
        String actualResult3 = footerButtonManage.getText();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
        Assert.assertEquals(actualResult3, expectedResult3);
    }
}
