import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.ArrayList;

public class YermeksTest extends BaseTest {

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
        Thread.sleep(7000);

        WebElement parisFRChoiceInDropdownMenu = getDriver().findElement(
                By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']")
        );
        parisFRChoiceInDropdownMenu.click();

        WebElement h2CityCountryHeader = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//h2")
        );
        Thread.sleep(7000);
        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testPanelWithTextAndTwoButtons_WhenOpenTheBaseURL() throws InterruptedException {


        String url = "https://openweathermap.org/";
        String expectedResultPanelWithText = "We use cookies which are essential for the site to work. " +
                "We also use non-essential cookies to help us improve our services. " +
                "Any data collected is anonymised. You can allow all cookies or manage them individually.";
        String expectedResultButtonAllowAll = "Allow all";
        String expectedResultButtonManageCookies = "Manage cookies";

        getDriver().get(url);
        ;
        Thread.sleep(8000);

        Assert.assertTrue(getDriver().findElement(By.className(
                "stick-footer-panel__container")).isDisplayed());

        WebElement panelWithText = getDriver().findElement(By.className(
                "stick-footer-panel__description"));

        WebElement buttonAllowAll = getDriver().findElement(By.xpath(
                "//div//button[text()='Allow all']"));

        WebElement buttonManageCookies = getDriver().findElement
                (By.xpath("//div[@class='stick-footer-panel__btn-container'"
                        + "]/*[text()=' Manage cookies ']"));

        String actualResultPanelWithText = panelWithText.getText();
        String actualResultButtonAllowAll = buttonAllowAll.getText();
        String actualResultButtonManageCookies = buttonManageCookies.getText();

        Assert.assertEquals(actualResultPanelWithText, expectedResultPanelWithText);
        Assert.assertEquals(actualResultButtonAllowAll, expectedResultButtonAllowAll);
        Assert.assertEquals(actualResultButtonManageCookies, expectedResultButtonManageCookies);
        Assert.assertTrue(buttonAllowAll.isDisplayed());
        Assert.assertTrue(buttonManageCookies.isDisplayed());

    }

}
