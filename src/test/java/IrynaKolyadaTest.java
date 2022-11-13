import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.Set;

@Ignore
public class IrynaKolyadaTest extends BaseTest {
    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement searchCityField = getDriver().findElement(
                By.xpath("//div[@id='weather-widget']//input[@placeholder='Search city']"));

        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement seatchButton = getDriver().findElement(
                By.xpath("//div[@id='weather-widget']//button[@type='submit']"));
        seatchButton.click();

        Thread.sleep(1000);
        WebElement parisFRChoiceInDropdownMenu = getDriver().findElement(
                By.xpath("//ul[@class='search-dropdown-menu']//li//span[text() = 'Paris, FR ']"
                ));

        parisFRChoiceInDropdownMenu.click();

        WebElement h2CityCountryHeader = getDriver().findElement(
                By.xpath("//div[@id='weather-widget']//h2"));

        Thread.sleep(2000);

        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);

    }
    @Test
    public void testTemperatureInFahrenheit() throws InterruptedException {
        String url = "https://openweathermap.org/";
        Boolean expectedResult = true;
        String symbolF = "°F";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement temperatureF = getDriver().findElement
                (By.xpath("//div[text()='Imperial: °F, mph']"));

        temperatureF.click();
        Thread.sleep(2000);
        WebElement imageTempF = getDriver().findElement(By.xpath("//div[@class ='current-temp']"));
        Boolean actualResult = imageTempF.getText().contains(symbolF);
        Assert.assertEquals(actualResult,expectedResult);

    }
    @Test
    public void testConfirmFooterText() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedResult = "We use cookies which are essential for the site to work. " +
                "We also use non-essential cookies to help us improve our services. " +
                "Any data collected is anonymised. You can allow all cookies or manage them individually.";
        String expectedResult1 = "Allow all";
        String expectedResult2 = "Manage cookies";

        getDriver().get(url);
        Thread.sleep(10000);
        Assert.assertTrue(getDriver().findElement
                (By.xpath("//div[@id='stick-footer-panel']//div[@class='container']")).isDisplayed());

        WebElement textFooter = getDriver().findElement(By.className("stick-footer-panel__description"));
        String actualResult = textFooter.getText();
        Assert.assertEquals(actualResult, expectedResult);

        Assert.assertEquals(getDriver().findElements
                (By.xpath("//div[@class='stick-footer-panel__btn-container']/*")).size(),2);

        WebElement buttonAllowAllFooter = getDriver().findElement(By.xpath("//button[@type='button']"));
        String actualResult1 = buttonAllowAllFooter.getText();

        Assert.assertEquals(actualResult1, expectedResult1);
        WebElement buttonManageCookiesFooter =getDriver().findElement
                (By.xpath("//a[@href='/cookies-settings']"));

        Assert.assertEquals(buttonManageCookiesFooter.getText(), expectedResult2);
    }

    @Test
    public void testCheckSubmenuSupport() throws InterruptedException {
        getDriver().get("https://openweathermap.org/");
        Thread.sleep(10000);

        WebElement headerButtonSuppot = getDriver().findElement(By.id("support-dropdown"));
        Thread.sleep(2000);
        headerButtonSuppot.click();
        Assert.assertEquals(getDriver().findElements
                (By.xpath("//ul[@id='support-dropdown-menu']/li")).size(),3);

        WebElement supportMenuFAQ = getDriver().findElement
                (By.xpath("//ul[@id='support-dropdown-menu']//a[@href='/faq']"));
        Assert.assertEquals(supportMenuFAQ.getText(),"FAQ");
        Thread.sleep(1000);

        WebElement supportMenuHowToStart = getDriver().findElement
                (By.xpath("//ul[@id='support-dropdown-menu']//a[@href='/appid']"));
        Assert.assertEquals(supportMenuHowToStart.getText(),"How to start");

        Thread.sleep(2000);
        WebElement supportMenuAskQuestion = getDriver().findElement
                (By.xpath("//ul[@id='support-dropdown-menu']//a[@target='_blank']"));
        Assert.assertEquals(supportMenuAskQuestion.getText(),"Ask a question");
    }

    @Test
    public void testisTemperatureConvertFromFtoC() throws InterruptedException {
        getDriver().get("https://openweathermap.org/");
        Thread.sleep(10000);

        WebElement tempF = getDriver().findElement(By.xpath("//div[text()='Imperial: °F, mph']"));
        Thread.sleep(2000);
        tempF.click();
        WebElement tempC = getDriver().findElement(By.xpath("//div[text()='Metric: °C, m/s']"));
        Thread.sleep(2000);
        tempC.click();
        boolean iconC = getDriver().findElement(By.xpath("//span[@class='heading']")).getText().contains("°C");
        Thread.sleep(2000);

        Assert.assertTrue(iconC);
    }
    @Test

    public void testBoxAskQuestionVerifyCaptchaMessage() throws InterruptedException {
        String expectedResult = "reCAPTCHA verification failed, please try again.";
        getDriver().get("https://openweathermap.org/");
        Thread.sleep(10000);

        WebElement headerButtonSuppot = getDriver().findElement(By.id("support-dropdown"));
        Thread.sleep(3000);
        headerButtonSuppot.click();

        String parent_window = getDriver().getWindowHandle();

        WebElement supportMenuAskQuestion = getDriver().findElement
                (By.xpath("//ul[@id='support-dropdown-menu']//a[@target='_blank']"));
        supportMenuAskQuestion.click();
        Thread.sleep(5000);

        Set<String> windows = getDriver().getWindowHandles();
        for (String child_window : windows) {
            if (!parent_window.equals(child_window)) {
                getDriver().switchTo().window(child_window);
            }
        }

        WebElement fieldEmail = getDriver().findElement
                (By.xpath("//input[@id='question_form_email']"));
        fieldEmail.click();
        fieldEmail.sendKeys("iryna@gmail.com");

        WebElement fieldSubject = getDriver().findElement(By.id("question_form_subject"));
        fieldSubject.click();
        fieldSubject.sendKeys("Other");
        Thread.sleep(2000);

        WebElement message = getDriver().findElement(By.id("question_form_message"));
        message.click();
        message.sendKeys("Hello world!");

        WebElement buttonSubmit = getDriver().findElement(By.xpath("//input[@class='btn btn-default']"));
        buttonSubmit.click();

        WebElement capchamessage = getDriver().findElement(By.xpath("//div[@class='help-block']"));

        Assert.assertEquals(capchamessage.getText(), expectedResult);
    }
    @Test
    public void testWebpageAfterReloadHasTheSameURl() throws InterruptedException {
        getDriver().get("https://openweathermap.org/");
        Thread.sleep(10000);

        WebElement logo = getDriver().findElement(By.xpath("//li[@class='logo']"));
        Thread.sleep(2000);
        logo.click();
        boolean linkPage = getDriver().getCurrentUrl().equals("https://openweathermap.org/");

        Assert.assertTrue(linkPage);
    }
    @Test
    public void testVerifyWordFindAndRome() throws InterruptedException {
        getDriver().get("https://openweathermap.org/");
        Thread.sleep(10000);

        getDriver().findElement
                (By.xpath("//input[@placeholder='Weather in your city']")).sendKeys
                ("Rome" + Keys.ENTER);
        boolean wordFindInLink = getDriver().getCurrentUrl().contains("find");

        Assert.assertTrue(wordFindInLink);

        boolean wordRomeInLink = getDriver().getCurrentUrl().contains("Rome");

        Assert.assertTrue(wordRomeInLink);

        Thread.sleep(2000);
        WebElement searchRome = getDriver().findElement
                (By.xpath("//div//input[@value = 'Rome']"));
        boolean actualResult = searchRome.toString().contains("Rome");

        Assert.assertTrue(actualResult);
    }

}
