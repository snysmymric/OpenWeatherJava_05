import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;
import java.util.Set;

@Ignore
public class Tatiana_SH_V_Test extends BaseTest {

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
        Thread.sleep(5000);

        WebElement parisFRChoiceInDropdownMenu = getDriver().findElement(
                By.xpath("//ul[@class = 'search-dropdown-menu']//li/span[text () = 'Paris, FR ']")
        );
        parisFRChoiceInDropdownMenu.click();

        WebElement h2CityCountryHeader = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//h2")
        );
        Thread.sleep(5000);

        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);

    }

    @Test
    public void testGuideTitle() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedResult1 = "https://openweathermap.org/guide";
        String expectedResult2 = "OpenWeatherMap API guide - OpenWeatherMap";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement guideSearch = getDriver().findElement(
                By.xpath("//div/ul/li/a[@href = '/guide']")
        );
        guideSearch.click();
        Thread.sleep(2000);

        String actualResult1 = getDriver().getCurrentUrl();
        Assert.assertEquals(actualResult1, expectedResult1);

        String actualResult2 = getDriver().getTitle();

        Assert.assertEquals(actualResult2, expectedResult2);
    }

    @Test
    public void testSearchImperialF() throws InterruptedException {
        String url = "https://openweathermap.org/";
        Boolean expectedResult = true;

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement searchImperial = getDriver().findElement(
                By.xpath("//div[text ()= 'Imperial: °F, mph']")
        );
        searchImperial.click();
        Thread.sleep(2000);

        WebElement searchF = getDriver().findElement(
                By.xpath("//span[@class='heading']")
        );
        Thread.sleep(3000);

        Boolean actualResult = searchF.getText().contains("°F");

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testTextAllowManageCookies() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedResult1 = "We use cookies which are essential for the site to work. " +
                "We also use non-essential cookies to help us improve our services. " +
                "Any data collected is anonymised. You can allow all cookies or manage them individually.";
        String button1Text = "Allow all";
        String button2Text = " Manage cookies ";

        getDriver().get(url);
        Thread.sleep(10000);

        Assert.assertTrue(getDriver().findElement(By.className("stick-footer-panel__container")).isDisplayed());

        WebElement textCookies = getDriver().findElement(
                By.className("stick-footer-panel__description")
        );
        String actualResult1 = textCookies.getText();

        Assert.assertEquals(actualResult1, expectedResult1);

        Assert.assertEquals(
                getDriver().findElements(By.xpath("//div[@class = 'stick-footer-panel__btn-container']/*")).
                        size(), 2
        );

        Assert.assertTrue(
                getDriver().findElement(
                        By.xpath("//div[@class = 'stick-footer-panel__btn-container']/*[text() = '"
                                + button1Text + "']")
                ).isDisplayed());

        Assert.assertTrue(
                getDriver().findElement(
                        By.xpath("//div[@class = 'stick-footer-panel__btn-container']/*[text() = '"
                                + button2Text + "']")
                ).isDisplayed());
    }

    @Test
    public void testSupportFAQHowToStartAskAQuestion() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedResultFaq = "FAQ";
        String expectedResultStart = "How to start";
        String expectedResultQuestion = "Ask a question";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement buttonSupport = getDriver().findElement(
                By.id("support-dropdown")
        );
        buttonSupport.click();
        Thread.sleep(3000);

        Assert.assertEquals(getDriver().findElements(By.xpath("//ul[@id = 'support-dropdown-menu']/li")).
                size(),3);

        WebElement faq = getDriver().findElement(
                By.xpath("//ul[@class='dropdown-menu dropdown-visible']//a[@href='/faq'][1]")
        );
        String actualResultFaq = faq.getText();

        Assert.assertEquals(actualResultFaq, expectedResultFaq);

        WebElement start = getDriver().findElement(
                By.xpath("//ul[@class='dropdown-menu dropdown-visible']/li/a[@href='/appid']")
        );
        String actualResultStart = start.getText();

        Assert.assertEquals(actualResultStart, expectedResultStart);

        WebElement question = getDriver().findElement(
                By.xpath("//ul[@class='dropdown-menu dropdown-visible']//a[text()='Ask a question']")
        );
        String actualResultQuestion = question.getText();

        Assert.assertEquals(actualResultQuestion, expectedResultQuestion);
    }

    @Test
    public void testSupportEmailSubMes() throws InterruptedException {
        String url = "https://openweathermap.org/";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement buttonSupport = getDriver().findElement(
                By.xpath("//div[text()='Support']")
        );
        Thread.sleep(3000);
        buttonSupport.click();

        String parent_window = getDriver().getWindowHandle();

        WebElement buttonAsk = getDriver().findElement(
                By.xpath("//ul[@id='support-dropdown-menu']//a[@target='_blank']")
        );
        buttonAsk.click();
        Thread.sleep(5000);

        Set<String> windows = getDriver().getWindowHandles();
        for (String child_window : windows) {
            if (!parent_window.equals(child_window)) {
                getDriver().switchTo().window(child_window);
            }
        }

        WebElement email = getDriver().findElement(
                By.id("question_form_email")
        );
        email.click();
        email.sendKeys("renm@gmail.com");
        Thread.sleep(3000);

        WebElement subject = getDriver().findElement(
                By.id("question_form_subject")
        );
        subject.click();
        subject.sendKeys("Other");
        Thread.sleep(3000);

        WebElement message = getDriver().findElement(
                By.id("question_form_message")
        );
        message.click();
        message.sendKeys("Hi");

        WebElement buttonSubmit = getDriver().findElement(
                By.xpath("//input[@class='btn btn-default']")
        );
        buttonSubmit.click();
        Thread.sleep(3000);

        WebElement captcha = getDriver().findElement(
                By.xpath("//div[@class='help-block']")
        );

        Assert.assertEquals(captcha.getText(), "reCAPTCHA verification failed, please try again.");
    }

    @Test
    public void testSearchLogo() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedResult = "https://openweathermap.org/";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement logo = getDriver().findElement(
                By.xpath("//img[@src='/themes/openweathermap/assets/img/logo_white_cropped.png']")
        );
        logo.click();
        Thread.sleep(3000);

        String actualResult = getDriver().getCurrentUrl();

        Assert.assertEquals(actualResult, expectedResult);
    }


}
