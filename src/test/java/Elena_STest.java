import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;
import java.util.ArrayList;

@Ignore
public class Elena_STest extends BaseTest {
    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement searchCityField = getDriver().findElement(
                By.xpath("//div[@id=\"weather-widget\"]//input[@placeholder='Search city']")
        );
        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchButton = getDriver().findElement(
                By.xpath("//div[@id=\"weather-widget\"]//button[@type='submit']")
        );
        searchButton.click();
        Thread.sleep(1000);
        WebElement choiceDropDownMenuParisFrance = getDriver().findElement(
                By.xpath("//ul[@class='search-dropdown-menu']/li/span[text()='Paris, FR ']")
        );
        choiceDropDownMenuParisFrance.click();
        WebElement h2CityCountryHeader = getDriver().findElement(
                By.xpath("//div[@id='weather-widget']//h2")
        );
        Thread.sleep(5000);
        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult,expectedResult);
        }

    @Test
    public void testTemperatureFormatSelectionInFahrenheit() throws InterruptedException {
        String url = "https://openweathermap.org/";
        getDriver().get(url);

        Thread.sleep(10000);
        WebElement switchToFahrenheit = getDriver().findElement(
                By.xpath("//div[@class='option'][text()='Imperial: °F, mph']")
        );
        switchToFahrenheit.click();
        char expectedResult = 'F';
        WebElement findFahrenheit = getDriver().findElement(
                By.xpath("//span[@class='heading'][contains(text(),'F')]")
        );
        String result = findFahrenheit.getText();
        char actualResult = result.charAt(result.length()-1);

        Assert.assertEquals(actualResult,expectedResult);
    }

    @Test
    public void testOpenWeatherPageGuidMaximize() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedURL = "https://openweathermap.org/guide";
        String expectedResult = "OpenWeatherMap API guide - OpenWeatherMap";
        getDriver().get(url);
        getDriver().manage().window().maximize();
        Thread.sleep(10000);
        WebElement searchButtonGUid = getDriver().findElement(
                By.xpath("//div[@id='desktop-menu']/ul/li/a[@href='/guide']")
        );
        searchButtonGUid.click();
        String actualResultURL = getDriver().getCurrentUrl();
        String actualResult = getDriver().getTitle();

        Assert.assertEquals(actualResultURL,expectedURL);
        Assert.assertEquals(actualResult,expectedResult);
    }

    @Test
    public void testApprovinfButtonsAllow_allAndManage_cookies() throws InterruptedException {
        String url = "https://openweathermap.org/";
        getDriver().get(url);
        Thread.sleep(10000);
        String expectedResultPanelText = "We use cookies which are essential for the site to work. We also use non-essential" +
                " cookies to help us improve our services. Any data collected is anonymised. You can allow all cookies" +
                " or manage them individually.";
        String expectedResultAllowAll = "Allow all";
        String expectedResultManageCookies = "Manage cookies";
        WebElement panelText = getDriver().findElement(
                By.xpath("//p[@class='stick-footer-panel__description']")
        );
        String actualResultPanelText = panelText.getText();
        WebElement buttonAllowAll = getDriver().findElement(
                By.xpath("//button[@class='stick-footer-panel__link']")
        );
        String actualResultAllowAll = buttonAllowAll.getText();
        WebElement buttonManageCookies = getDriver().findElement(
                By.xpath("//a[@class='stick-footer-panel__link'][text()=' Manage cookies ']"));
        String actualResultManageCookies = buttonManageCookies.getText();

        Assert.assertEquals(actualResultPanelText,expectedResultPanelText);
        Assert.assertEquals(actualResultAllowAll,expectedResultAllowAll);
        Assert.assertEquals(actualResultManageCookies,expectedResultManageCookies);
         }

    @Test
    public void textSupportSubmenu() throws InterruptedException {
        String url = "https://openweathermap.org/";
        getDriver().get(url);
        String expectedResultLink1 = "FAQ";
        String expectedResultLink2 = "How to start";
        String expectedResultLink3 = "Ask a question";
        WebElement upperPanelMenuSupport = getDriver().findElement(
                By.xpath("//div[@id='support-dropdown']")
        );
        Thread.sleep(10000);
        upperPanelMenuSupport.click();
        WebElement link1 = getDriver().findElement(
                By.xpath("//ul[@id='support-dropdown-menu']//a[@href=\"/faq\"][text()='FAQ']")
        );
        WebElement link2 = getDriver().findElement(
                By.xpath("//ul[@id='support-dropdown-menu']//a[@href=\"/appid\"][text()='How to start']")
        );
        WebElement link3 = getDriver().findElement(
                By.xpath("//ul[@id='support-dropdown-menu']//a[@href='https://home.openweathermap.org/questions']" +
                        "[text()='Ask a question']")
        );
        String actualResultLink1 = link1.getText();
        String actualResultLink2 = link2.getText();
        String actualResultLink3 = link3.getText();

        Assert.assertEquals(actualResultLink1,expectedResultLink1);
        Assert.assertEquals(actualResultLink2,expectedResultLink2);
        Assert.assertEquals(actualResultLink3,expectedResultLink3);
    }

    @Test
    public void textDisplayCaptureError() throws InterruptedException {
        String url = "https://openweathermap.org/";
        getDriver().get(url);
        String expectedResultErrorText = "reCAPTCHA verification failed, please try again.";

        WebElement upperPanelMenuSupport = getDriver().findElement(
                By.xpath("//div[@id='support-dropdown']")
        );
        Thread.sleep(10000);
        upperPanelMenuSupport.click();

        WebElement askQuestion = getDriver().findElement(
                By.xpath("//ul[@id='support-dropdown-menu']//a[@href='https://home.openweathermap.org/questions']" +
                        "[text()='Ask a question']")
        );
        askQuestion.click();
        Thread.sleep(1000);
        ArrayList tabs = new ArrayList(getDriver().getWindowHandles());
        getDriver().switchTo().window((String) tabs.get(1));

        String email = "email@gmai.com";
        String message = "Message for support test";

        WebElement fieldEmail = getDriver().findElement(
                By.xpath("//input[@id='question_form_email']")
        );
        Thread.sleep(1000);
        fieldEmail.click();
        fieldEmail.sendKeys(email);
        Thread.sleep(1000);
        WebElement valueOfFieldSubject = getDriver().findElement(
                By.xpath("//select[@id='question_form_subject']/option[@value='Other']")
        );
        valueOfFieldSubject.click();
        Thread.sleep(1000);
        WebElement fieldMessage = getDriver().findElement(
                By.xpath("//textarea[@id='question_form_message']")
        );
        fieldMessage.click();
        fieldMessage.sendKeys(message);
         WebElement buttonSubmit = getDriver().findElement(
                By.xpath("//input[@value='Submit']")
        );
        buttonSubmit.click();
        WebElement errorMessageResult = getDriver().findElement(
                By.xpath("//div[@class='help-block']")
        );
        String actualResultErrorText = errorMessageResult.getText();

        Assert.assertEquals(actualResultErrorText,expectedResultErrorText);
    }

    @Ignore
    @Test
    public void testTemperatureFormatSelectionInCelsius() throws InterruptedException {
        String url = "https://openweathermap.org/";
        getDriver().get(url);
        WebElement switchToFahrenheit = getDriver().findElement(
                By.xpath("//div[@class='option'][text()='Imperial: °F, mph']")
        );
        Thread.sleep(7000);
        switchToFahrenheit.click();
        WebElement switchToCelsius = getDriver().findElement(
                By.xpath("//div[@class='option'][text()='Metric: °C, m/s']")
        );
        switchToCelsius.click();
        char expectedResult = 'C';
        WebElement findCelsius = getDriver().findElement(
                By.xpath("//span[@class='heading'][contains(text(),'C')]")
        );
        String result = findCelsius.getText();
        char actualResult = result.charAt(result.length()-1);

        Assert.assertEquals(actualResult,expectedResult);
    }

    @Test
    public void testPageRefresh() throws InterruptedException {
        String url = "https://openweathermap.org/";
        getDriver().get(url);
        Thread.sleep(10000);
        WebElement logoImage = getDriver().findElement(
                By.className("logo")
        );
        logoImage.click();
        String actualResult = getDriver().getCurrentUrl();

        Assert.assertEquals(actualResult,url);
    }

    @Test
    public void testSearchCityPage() throws InterruptedException {
        String url = "https://openweathermap.org/";
        getDriver().get(url);
        Thread.sleep(10000);
        String cityName = "Rome";
        String expectedResultCityURL = "https://openweathermap.org/find?q=Rome";
        String expectedResultCityName = "Rome";
        WebElement fieldSearch = getDriver().findElement(
                By.name("q")
        );
        fieldSearch.click();
        fieldSearch.sendKeys(cityName);
        fieldSearch.submit();
        String actualResultCityURL = getDriver().getCurrentUrl();
        WebElement cityNameIntheField = getDriver().findElement(
                By.id("search_str")
        );
        String actualResultCityName = cityNameIntheField.getAttribute("value");

        Assert.assertEquals(actualResultCityURL,expectedResultCityURL);
        Assert.assertEquals(actualResultCityName,expectedResultCityName);
    }

}

