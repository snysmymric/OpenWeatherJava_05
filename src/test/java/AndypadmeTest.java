import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class AndypadmeTest extends BaseTest {

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
                By.xpath("//div[@id = 'weather-widget']//button[@type='submit']")
        );
        searchButton.click();

        Thread.sleep(1000);

        WebElement parisFranceChoiceInDropdownMenu = getDriver().findElement(
                By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']")
        );

        parisFranceChoiceInDropdownMenu.click();
        Thread.sleep(2000);

        WebElement h2CityCountryHeader = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//h2")
        );

        String actualResult = h2CityCountryHeader.getText();
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testAPIGuideTitle_WhenNavigatingToGuidePage() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String expectedUrl = "https://openweathermap.org/guide";
        String expectedTitle = "OpenWeatherMap API guide - OpenWeatherMap";

        getDriver().get(url);
        Thread.sleep(10000);

        getDriver().findElement(By.xpath("//div[@id = 'desktop-menu']//li/a[@href = '/guide']")).click();
        Thread.sleep(500);

        String actualResult = getDriver().getTitle();
        String actualUrl = getDriver().getCurrentUrl();

        Assert.assertEquals(actualResult, expectedTitle);
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    @Test
    public void testTemperatureMeasurementUnit() throws InterruptedException {

        String url = "https://openweathermap.org/";
        char expectedResult = 'F';

        getDriver().get(url);
        Thread.sleep(10000);

        getDriver().findElement(By.xpath("//div[@id = 'weather-widget']//div[text() = 'Imperial: °F, mph']")).click();
        Thread.sleep(1000);

        WebElement currentTemp = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//div[@class = 'section-content']//span[@class = 'heading']")
        );

        char actualResult = currentTemp.getText().charAt(currentTemp.getText().length()-1);

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testCookiesInfoPanel() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String panelExpectedText = "We use cookies which are essential for the site to work. We also use " +
                "non-essential cookies to help us improve our services. Any data collected is anonymised. You can allow all cookies or manage them individually.";
        String allowButtonExpectedText = "Allow all";
        String manageButtonExpectedText = "Manage cookies";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement panelText = getDriver().findElement(
                By.xpath("//div[@class = 'stick-footer-panel']//p[@class = 'stick-footer-panel__description']")
        );

        WebElement allowButton = getDriver().findElement(
                By.xpath("//div[@class = 'stick-footer-panel']//div[@class = 'stick-footer-panel__btn-container']/button[@class = 'stick-footer-panel__link']")
        );

        WebElement manageButton = getDriver().findElement(
                By.xpath("//div[@class = 'stick-footer-panel']//div[@class = 'stick-footer-panel__btn-container']/a[@class = 'stick-footer-panel__link']")
        );

        String actualPanelText = panelText.getText();
        String actualAllowButton = allowButton.getText();
        String actualManageButton = manageButton.getText();

        Assert.assertEquals(actualPanelText, panelExpectedText);
        Assert.assertEquals(actualAllowButton, allowButtonExpectedText);
        Assert.assertEquals(actualManageButton, manageButtonExpectedText);
    }

    @Test
    public void testSupportMenu() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String expectedFAQMenuItem = "FAQ";
        String expectedHowToStartMenuItem = "How to start";
        String expectedAskAQuestionMenuItem = "Ask a question";

        getDriver().get(url);
        Thread.sleep(10000);

        getDriver().findElement(By.xpath("//div[@id = 'desktop-menu']//div[@id = 'support-dropdown' and text() = 'Support']")).click();

        WebElement faqItem = getDriver().findElement(
                By.xpath("//div[@id = 'desktop-menu']//ul[@id = 'support-dropdown-menu']//a[@href = '/faq']")
        );

        WebElement howToStartItem = getDriver().findElement(
                By.xpath("//div[@id = 'desktop-menu']//ul[@id = 'support-dropdown-menu']//a[@href = '/appid']")
        );

        WebElement askQuestionItem = getDriver().findElement(
                By.xpath("//div[@id = 'desktop-menu']//ul[@id = 'support-dropdown-menu']//a[@href = 'https://home.openweathermap.org/questions']")
        );

        Assert.assertEquals(faqItem.getText(), expectedFAQMenuItem);
        Assert.assertEquals(howToStartItem.getText(), expectedHowToStartMenuItem);
        Assert.assertEquals(askQuestionItem.getText(), expectedAskAQuestionMenuItem);
    }
}