import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
public class LiudmilaPlucciTest extends BaseTest {

    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        getDriver().get(url);
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
                By.xpath("//ul[@class=\"search-dropdown-menu\"]//li//span[text() = 'Paris, FR ']")
        );
        parisFRChoiceInDropdownMenu.click();

        WebElement h2CityCountryHeader = getDriver().findElement(
                By.xpath("//div[@id='weather-widget']//h2")
        );
        Thread.sleep(2000);

        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult,expectedResult);

    }
    @Test
    public void testApproveGuidePage() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedResult1 = "https://openweathermap.org/guide";
        String expectedResult2 = "OpenWeatherMap API guide - OpenWeatherMap";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement ButtonGuide = getDriver().findElement(
                By.xpath("//a[@href='/guide']"));

        ButtonGuide.click();

        String actualResult2 = getDriver().getTitle();
        String actualResult1 = getDriver().getCurrentUrl();

        Assert.assertEquals(actualResult1,expectedResult1);
        Assert.assertEquals(actualResult2,expectedResult2);
    }
    @Test
    public void testApproveTwoButtonsInPanel() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedResult = "We use cookies which are essential for the site to work. We also use non-essential cookies to help us improve our services. Any data collected is anonymised. You can allow all cookies or manage them individually.";
        getDriver().get(url);

        Thread.sleep(10000);
        WebElement textElement = getDriver().findElement(
                By.className("stick-footer-panel__description"));

        WebElement buttonAllowAll = getDriver().findElement(By.xpath("//button[text()='Allow all']"));
        WebElement buttonManageCookies = getDriver().findElement(
                By.xpath("//a[@href='/cookies-settings']"));

        Assert.assertEquals(buttonAllowAll.getText(),"Allow all");
        Assert.assertEquals(buttonManageCookies.getText(),"Manage cookies");
        Assert.assertEquals(textElement.getText(),expectedResult);
    }

    @Test
    public void testApproveMenuSupportWithThreeSubMenu() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedResultFAQ = "FAQ";
        String expectedResultHowToStart = "How to start";
        String expectedResultAskAQuestion = "Ask a question";

        getDriver().get(url);
        Thread.sleep(7000);
        WebElement supportDropdown = getDriver().findElement(By.xpath("//div[@id='support-dropdown']"));
        supportDropdown.click();
        Thread.sleep(3000);

        WebElement checkIfTextFAQIsPresent = getDriver().findElement(
                By.xpath("//ul[@id='support-dropdown-menu']//a[@href='/faq']"));
        WebElement checkIfTextHowToStartIsPresent = getDriver().findElement(
                By.xpath("//ul[@id='support-dropdown-menu']//a[@href='/appid']"));
        WebElement checkIfTextAskAQuestionIsPresent = getDriver().findElement(By.xpath(
                "//ul[@id='support-dropdown-menu']//a[@href='https://home.openweathermap.org/questions']"));

        Assert.assertEquals(checkIfTextFAQIsPresent.getText(), expectedResultFAQ);
        Assert.assertEquals(checkIfTextHowToStartIsPresent.getText(), expectedResultHowToStart);
        Assert.assertEquals(checkIfTextAskAQuestionIsPresent.getText(), expectedResultAskAQuestion);
    }
}
