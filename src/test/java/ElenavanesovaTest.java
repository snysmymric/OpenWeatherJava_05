import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

public class ElenavanesovaTest extends BaseTest {
    final String BASE_URL = "https://openweathermap.org/";
    final By H_2_CITY_NAME_HEADER = By.xpath( "//div[@id = 'weather-widget']//h2");
    final By SEARCH_CITY_FIELD = By.xpath("//div[@id = \"weather-widget\"]//input[@placeholder='Search city']");
    final By SEARCH_BUTTON = By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']");
    final By SEARCH_DROPDOWN_MENU =  By.className("search-dropdown-menu");
    final By PARIS_FR_CHOICE_IN_DROPDOWN_MENU =  By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']");
    final By SEARCH_GUIDE = By.xpath("//div//a[@href='/guide']");
    final By GUIDE_TITLE = By.className("breadcrumb-title");
    final By GUIDE_HOME_BUTTON = By.xpath("//div[@class='container']//li//a[@href='/']");
    final By GUIDE_HOME_GUIDE = By.xpath("//Li[@class='breadcrumb__leaf']");

    @Test
    public void testH2TagText_WhenSearchingCityCountry() {
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        openBaseURL();

        String oldH2Header = getText(H_2_CITY_NAME_HEADER);

        click(SEARCH_CITY_FIELD);
        input(cityName, SEARCH_CITY_FIELD);
        click(SEARCH_BUTTON);
        waitElementToBeVisible(SEARCH_DROPDOWN_MENU);
        click(PARIS_FR_CHOICE_IN_DROPDOWN_MENU);
        waitTextToBeChanged(H_2_CITY_NAME_HEADER, oldH2Header);


        String actualResult = getText(H_2_CITY_NAME_HEADER);

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testOpenAndClickToGuide(){
        String expectedResult = "Guide";
        String expectedResult1 = "https://openweathermap.org/guide";

        openBaseURL();
        click(SEARCH_GUIDE);

        String actualResul = getText(GUIDE_TITLE);
        String actualResul1 = getCurrentURL();

        Assert.assertEquals(actualResul, expectedResult);
        Assert.assertEquals(actualResul1,expectedResult1);
    }

    @Test
    public void  testTextHomeGuideButtonShown (){
        String expectedButtonGuideHomeShown = "Home";
        String expectedButtonGuideHomeShownGuide = "Guide";
        openBaseURL();
        click(SEARCH_GUIDE);

        getCurrentURL();
        Assert.assertNotEquals(getCurrentURL(),BASE_URL);

        Assert.assertTrue(isElementExists(GUIDE_HOME_BUTTON));
        Assert.assertTrue(isElementExists(GUIDE_HOME_GUIDE));

        Assert.assertTrue(isDisplayed(GUIDE_HOME_BUTTON));
        Assert.assertTrue(isDisplayed(GUIDE_HOME_GUIDE));

        String actualButtonGuideHomeShow = getText(GUIDE_HOME_BUTTON);
        String actualButtonGuideHomeGuideShow = getText(GUIDE_HOME_GUIDE);

        Assert.assertEquals(actualButtonGuideHomeShow,expectedButtonGuideHomeShown);
        Assert.assertEquals(actualButtonGuideHomeGuideShow,expectedButtonGuideHomeShownGuide);
    }
}
