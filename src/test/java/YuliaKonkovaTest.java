import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class YuliaKonkovaTest extends BaseTest {
    final static String BASE_URL = "https://openweathermap.org/";
    final static By H2_CITY_NAME_HEADER = By.xpath("//div[@id='weather-widget']//h2");
    final static By SEARCH_CITY_FIELD = By.xpath("//div[@id = 'weather-widget']//input[@placeholder='Search city']");
    final static By BUTTON = By.xpath("//div[@id='weather-widget']//button[@type='submit']");
    final static By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
    final static By PARIS_FR_CHOICE_IN_DROPDOWN_MENU =
            By.xpath("//ul[@class='search-dropdown-menu']/li/span[text()='Paris, FR ']");

    final static By SUPPORT_DROPDOWNMENU_IN_NAVBAR = By.xpath("//div[@id='support-dropdown']");
    final static By SUPPORT_DROPDOWNMENU = By.xpath("//ul[@id='support-dropdown-menu']");
    final static By SUBMENUES_OF_SUPPORT_LIST = By.xpath("//ul[@class='dropdown-menu dropdown-visible']/li");
    final static By FIRST_SUBMENUE_OF_SUPPORT =
            By.xpath("//ul[@class='dropdown-menu dropdown-visible']/li/a[@href='/faq']");
    final static By SECOND_SUBMENUE_OF_SUPPORT =
            By.xpath("//ul[@class='dropdown-menu dropdown-visible']/li/a[@href='/appid']");
    final static By THIRD_SUBMENUE_OF_SUPPORT =
            By.xpath("//ul[@class='dropdown-menu dropdown-visible']/li" +
                    "/a[@href='https://home.openweathermap.org/questions']");

    @Test
    public void testH2TagText_WhenSearchingCityCountry(){
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        openBaseURL();

        String oldH2Header = getText(H2_CITY_NAME_HEADER);

        click(SEARCH_CITY_FIELD);
        input(cityName, SEARCH_CITY_FIELD);
        click(BUTTON);
        waitElementToBeVisible(SEARCH_DROPDOWN_MENU);
        click(PARIS_FR_CHOICE_IN_DROPDOWN_MENU);
        waitTextToBeChanged(H2_CITY_NAME_HEADER, oldH2Header);

        String actualResult = getText(H2_CITY_NAME_HEADER);

        Assert.assertEquals(actualResult,expectedResult);
    }

    @Test
    public void testMenuSupportHasThreeSubmenus() {
        int expectedNumberOfSubmenues = 3;
        String expectedFirstSubmenue = "FAQ";
        String expectedSecondSubmenue = "How to start";
        String expectedThirdSubmenue = "Ask a question";

        openBaseURL();

        click(SUPPORT_DROPDOWNMENU_IN_NAVBAR);
        waitElementToBeVisible(SUBMENUES_OF_SUPPORT_LIST);
        Assert.assertTrue(getDriver().findElement(SUPPORT_DROPDOWNMENU).isDisplayed());

        int actualNumberOfSubmenues = getDriver().findElements(SUBMENUES_OF_SUPPORT_LIST).size();
        Assert.assertEquals(actualNumberOfSubmenues,expectedNumberOfSubmenues);

        Assert.assertEquals(getText(FIRST_SUBMENUE_OF_SUPPORT),expectedFirstSubmenue);
        Assert.assertEquals(getText(SECOND_SUBMENUE_OF_SUPPORT), expectedSecondSubmenue);
        Assert.assertEquals(getText(THIRD_SUBMENUE_OF_SUPPORT), expectedThirdSubmenue);

        click(SUPPORT_DROPDOWNMENU_IN_NAVBAR);
        Assert.assertFalse(getDriver().findElement(SUPPORT_DROPDOWNMENU).isDisplayed());
    }
}
