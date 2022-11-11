import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class IriSamoTest extends BaseTest {

    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement searchCity = getDriver().findElement(
                By.xpath("//input[@placeholder='Search city']")
        );
        Thread.sleep(1000);
        searchCity.click();
        searchCity.sendKeys(cityName);
        Thread.sleep(1000);
        WebElement buttonSearch = getDriver().findElement(
                By.xpath("//button[@type='submit']")
        );
        buttonSearch.click();
        Thread.sleep(1000);
        WebElement parisFRChoiceInDropdownMenu = getDriver().findElement(
                By.xpath("//ul[@class='search-dropdown-menu']"
                        + "//span[text()='Paris, FR ']")
        );
        parisFRChoiceInDropdownMenu.click();
        WebElement h2CityCountryHeader = getDriver().findElement(
                By.xpath("//div[@id=\"weather-widget\"]//h2")
        );
        Thread.sleep(2000);

        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testTabGuideUrlTitle() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedTitle = "OpenWeatherMap API guide - OpenWeatherMap";
        String expectedUrl = "https://openweathermap.org/guide";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement tabGuide = getDriver().findElement(
                By.xpath("//a[@href='/guide']")
        );
        tabGuide.click();
        Thread.sleep(1000);

        String actualTitle = getDriver().getTitle();
        String actualUrl = getDriver().getCurrentUrl();

        Assert.assertEquals(actualTitle, expectedTitle);
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    @Test
    public void testUnitOfMeasurementFahrenheit() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedMeasurement = "°F";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement areaMeasurementImperial = getDriver().findElement(
                By.xpath("//div[@class='switch-container']"
                        + "/div[text()='Imperial: °F, mph']")
        );
        areaMeasurementImperial.click();
        Thread.sleep(2000);

        String  currentTemp = getDriver().findElement(
                By.xpath("//div[@class='current-temp']//span")
        ).getText();
        String actualMeasurement = currentTemp.substring(currentTemp.length()-2);

        Assert.assertEquals(actualMeasurement, expectedMeasurement);
    }

    @Test
    public void testCookiesMessage() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedMessage = "We use cookies which are essential for the site to work. "
                + "We also use non-essential cookies to help us improve our services. "
                + "Any data collected is anonymised. You can allow all cookies or manage "
                + "them individually.";
        int expectedQuantityButtons = 2;

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement panelMessage = getDriver().findElement(
                By.xpath("//div[@id='stick-footer-panel']//p")
        );
        String actualText = panelMessage.getText();

        int btn = getDriver().findElements(
                By.xpath("//div/*[@class='stick-footer-panel__link']")
        ).size();

        Assert.assertEquals(btn, expectedQuantityButtons);
        Assert.assertEquals(actualText, expectedMessage);
    }

    @Test
    public void testMenuTabSupport() throws InterruptedException {
        String url = "https://openweathermap.org/";
        int expectedQuantityLines = 3;
        String expectedDropMenuFirstLine = "FAQ";
        String expectedDropMenuSecondLine = "How to start";
        String expectedDropMenuLastLine = "Ask a question";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement tabSupport = getDriver().findElement(By.id("support-dropdown"));
        tabSupport.click();

        int lines = getDriver().findElements(
                By.xpath("//*[@id=\"support-dropdown-menu\"]/li/a")
        ).size();

        WebElement dropMenuFirstLine = getDriver().findElement(
                By.xpath("//div/ul/li/ul//li/a[@href='/faq']")
        );
        String actualDropMenuFirstLine = dropMenuFirstLine.getText();
        WebElement dropMenuSecondLine = getDriver().findElement(
                By.xpath("//div/ul/li/ul//li/a[@href='/appid']")
        );
        String actualDropMenuSecondLine = dropMenuSecondLine.getText();
        WebElement dropMenuLastLine = getDriver().findElement(
                By.xpath("//div/ul/li/ul//li/a"
                        + "[@href='https://home.openweathermap.org/questions']")
        );
        String actualDropMenuLastLine = dropMenuLastLine.getText();

        Assert.assertEquals(lines, expectedQuantityLines);
        Assert.assertEquals(actualDropMenuFirstLine, expectedDropMenuFirstLine);
        Assert.assertEquals(actualDropMenuSecondLine, expectedDropMenuSecondLine);
        Assert.assertEquals(actualDropMenuLastLine, expectedDropMenuLastLine);
    }

    @Test
    public void testAskAQuestionWithoutCAPTCHA() throws InterruptedException {
        String originalWindow = getDriver().getWindowHandle();
        String url = "https://openweathermap.org/";
        String expectedTextHelpBlock = "reCAPTCHA verification failed, please try again.";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement tabSupport = getDriver().findElement(By.id("support-dropdown"));
        tabSupport.click();
        WebElement dropMenuLastLine = getDriver().findElement(
                By.xpath("//div/ul/li/ul//li/a"
                        + "[@href='https://home.openweathermap.org/questions']")
        );
        dropMenuLastLine.click();
        Thread.sleep(10000);

        for (String windowHandle : getDriver().getWindowHandles()) {
            if(!originalWindow.contentEquals(windowHandle)) {
                getDriver().switchTo().window(windowHandle);
                break;
            }
        }

        WebElement boxMail = getDriver().findElement(By.id("question_form_email"));
        boxMail.click();
        boxMail.clear();
        boxMail.sendKeys("test369852147IS@gmail.com");

        WebElement boxSubject = getDriver().findElement(By.id("question_form_subject"));
        boxSubject.click();

        WebElement subjectOptionOther = getDriver().findElement(
                By.xpath("//*[@id=\"question_form_subject\"]"
                        + "//option[@value='Other']")
        );
        subjectOptionOther.click();

        WebElement boxMessage = getDriver().findElement(By.id("question_form_message"));
        boxMessage.click();
        boxMessage.clear();
        boxMessage.sendKeys("question message");

        WebElement buttonSubmit = getDriver().findElement(By.name("commit"));
        buttonSubmit.click();
        Thread.sleep(2000);

        WebElement helpBlock = getDriver().findElement(
                By.xpath("//*[@id=\"new_question_form\"]/div[9]/div[1]/div")
        );
        String actualTextHelpBlock = helpBlock.getText();

        Assert.assertEquals(actualTextHelpBlock, expectedTextHelpBlock);
    }
}