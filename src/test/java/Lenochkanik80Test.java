import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class Lenochkanik80Test extends BaseTest {

    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement searchCityField = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//input[@placeholder= 'Search city']")
        );
        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchButton = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']")
        );
        searchButton.click();

        Thread.sleep(1000);

        WebElement parisFRChoiceInDropdownMenu = getDriver().findElement
                (By.xpath("//ul[@class='search-dropdown-menu']/li/span[text() = 'Paris, FR ']")
                );
        parisFRChoiceInDropdownMenu.click();

        WebElement h2CityCountryHeader = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//h2")
        );

        Thread.sleep(2000);
        String actualResult = h2CityCountryHeader.getText();


        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testIfThreeSupportSubmenuTextsArePresent() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedResultFAQ = "FAQ";
        String expectedResultHowToStart = "How to start";
        String expectedResultAskAQuestion = "Ask a question";

        getDriver().get(url);
        getDriver().manage().window().maximize();
        Thread.sleep(10000);

        WebElement SupportElementInMenu = getDriver().findElement(
                By.xpath("//div[@id = 'support-dropdown']")
        );
        SupportElementInMenu.click();

        WebElement checkIfFAQTextPresent = getDriver().findElement(
                By.xpath("//ul[@class='dropdown-menu dropdown-visible']//a[@href = '/faq']")
        );
        WebElement checkIfHowToStartTestPresent = getDriver().findElement(
                By.xpath("//ul[@class='dropdown-menu dropdown-visible']//a[@href='/appid']")
        );

        WebElement checkIfAskAQuestionTestPresent = getDriver().findElement(
                By.xpath("//ul[@class='dropdown-menu dropdown-visible']//a[@href = 'https://home.openweathermap.org/questions']")
        );

        String actualResultFAQ = checkIfFAQTextPresent.getText();
        String actualResultHowToStart = checkIfHowToStartTestPresent.getText();
        String actualResultAskAQuestion = checkIfAskAQuestionTestPresent.getText();

        Assert.assertEquals(actualResultFAQ, expectedResultFAQ);
        Assert.assertEquals(actualResultHowToStart, expectedResultHowToStart);
        Assert.assertEquals(actualResultAskAQuestion, expectedResultAskAQuestion);
    }
}
