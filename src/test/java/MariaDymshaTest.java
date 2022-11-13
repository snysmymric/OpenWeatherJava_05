import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
public class MariaDymshaTest extends BaseTest {

    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";
        getDriver().get(url);
        
        Thread.sleep(7000);
        
        WebElement searchCityField = getDriver().findElement(
                By.xpath("//div[@id = \"weather-widget\"]//input[@placeholder=\"Search city\"]")
        );
        searchCityField.click();
        searchCityField.sendKeys(cityName);
        
        WebElement searchButton = getDriver().findElement(
                By.xpath("//div//button[@type = \"submit\"]")
        );
        searchButton.click();

        Thread.sleep(3000);

        WebElement parisFRChoiceInDropdownMenu = getDriver().findElement(
                By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']")
        );
        parisFRChoiceInDropdownMenu.click();
        
        WebElement h2CityCountryHeader = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//h2")
        );

        Thread.sleep(3000);

        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(expectedResult, actualResult);
    }
    
    @Test
    public void testImperial() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedResult = "°F";
        String fTempSymbol = "°F";
        getDriver().get(url);

        Thread.sleep(7000);
        
        WebElement menuImperial =  getDriver().findElement(
                By.xpath("//div[@class = 'option']/following-sibling::div")
        );
        menuImperial.click();

        Thread.sleep(3000);

        WebElement tempF =  getDriver().findElement(
                By.xpath("//div[@class = 'current-temp']/span")
        );
        
        String tempInF = tempF.getText();
        String actualResult = tempInF.substring((tempInF.length() - 2));

        Assert.assertTrue(tempF.getText().contains(fTempSymbol));
        Assert.assertEquals(actualResult, expectedResult);
    }
    
    @Test
    public void testOpenAndClickToGuide() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedResult1 = "https://openweathermap.org/guide";
        String expectedResult2 = "OpenWeatherMap API guide - OpenWeatherMap";
        getDriver().get(url);

        Thread.sleep(7000);

        WebElement searchButtonInMenu = getDriver().findElement(
                By.xpath("//li//a[text() = 'Guide']")
        );
        searchButtonInMenu.click();
        
        Thread.sleep(3000);
        
        String actualResult1 = getDriver().getCurrentUrl();
        String actualResult2 = getDriver().getTitle();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
    }
    
    @Test
    public void testSupportHaveThreeButton() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedResultFAQ = "FAQ";
        String expectedResultHowToStart = "How to start";
        String expectedResultAskAQuestion = "Ask a question";
        getDriver().get(url);

        Thread.sleep(7000);

        WebElement supportDropdown = getDriver().findElement(
                By.xpath("//div[@id='support-dropdown']")
        );
        supportDropdown.click();

        Thread.sleep(3000);

        WebElement checkIfTextFAQIsPresent = getDriver().findElement(
                By.xpath("//ul//li//a[@href='/faq']")
        );

        String actualResultIfTextFAQIsPresent = checkIfTextFAQIsPresent.getText();

        WebElement checkIfTextHowToStartIsPresent = getDriver().findElement(
                By.xpath("//li//li//a[@href = '/appid']")
        );

        String actualResultIfTextHowToStartIsPresent = checkIfTextHowToStartIsPresent.getText();

        WebElement checkIfTextAskAQuestionIsPresent = getDriver().findElement(
                By.xpath("//li//li//a[@href = 'https://home.openweathermap.org/questions']")
        );

        String actualResultIfTextAskAQuestionIsPresent = checkIfTextAskAQuestionIsPresent.getText();

        Assert.assertEquals(actualResultIfTextHowToStartIsPresent, expectedResultHowToStart);
        Assert.assertEquals(actualResultIfTextFAQIsPresent, expectedResultFAQ);
        Assert.assertEquals(actualResultIfTextAskAQuestionIsPresent, expectedResultAskAQuestion);
    }
    
    @Test
    public void testChangingTemperatureValues() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String temp = "°C";

        getDriver().get(url);
        Thread.sleep(7000);

        WebElement clickOnImperial = getDriver().findElement(
                By.xpath("//div[text()='Imperial: °F, mph']")
        );
        clickOnImperial.click();
        Thread.sleep(2000);

        WebElement clickOnMetric = getDriver().findElement(
                By.xpath("//div[text()='Metric: °C, m/s']")
        );
        clickOnMetric.click();
        Thread.sleep(2000);

        String tempC = getDriver().findElement(
                By.xpath("//span[@class = 'heading']")
        ).getText();

        Boolean actualResult = tempC.contains(temp);

        Assert.assertTrue(actualResult);
    }
}

