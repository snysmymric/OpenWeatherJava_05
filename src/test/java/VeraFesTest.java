import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class VeraFesTest extends BaseTest {

    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        getDriver().get(url);
        Thread.sleep(7000);

        WebElement searchCityField = getDriver().findElement(
                By.xpath("//div[@id='weather-widget']//input[@placeholder='Search city']")
        );
        searchCityField.click();
        searchCityField.sendKeys(cityName);
        Thread.sleep(7000);

        WebElement searchButton = getDriver().findElement(By.xpath("//button[@type='submit']"));
        searchButton.click();

        Thread.sleep(7000);

        WebElement parisFRChoiceInDropdownMenu = getDriver().findElement(
                By.xpath("//ul[@class='search-dropdown-menu']/li/span[text()='Paris, FR ']"));
        parisFRChoiceInDropdownMenu.click();

        WebElement h2CityCountryHeader  = getDriver().findElement(By.xpath("//div[@id='weather-widget']//h2"));

        Thread.sleep(7000);

        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult,expectedResult);
    }

    @Test
    public void testConfirmLinkAndTitleOnGuidePage_OpenWeatherMap() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String guideMenuText = "Guide";
        String expectedResult1 = "https://openweathermap.org/guide";

        String expectedResult2 = "OpenWeatherMap API guide - OpenWeatherMap";

        getDriver().get(url);
        Thread.sleep(7000);

        WebElement guideMenu = getDriver().findElement(By.linkText(guideMenuText));
        guideMenu.click();

        String actualResult1 = getDriver().getCurrentUrl();
        String actualResult2 = getDriver().getTitle();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
    }

}
