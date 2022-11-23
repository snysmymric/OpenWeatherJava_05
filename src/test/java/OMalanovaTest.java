import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

public class OMalanovaTest extends BaseTest {
    final static By SEARCH_CITY_FIELD = By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']");
    final static By SEARCH_BUTTON = By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']");
    final static By H2_CITY_COUNTRY_HEADER = By.xpath("//div[@id = 'weather-widget']//h2");
    final static By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
    final static By PARIS_FR_CHOICE_IN_DROPDOWN_MENU = By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']");
    final static By TEMP = By.xpath("//span[@class='heading']");
    final static By SWITCH_TEMP_F = By.xpath("//div[text()='Imperial: °F, mph']");

    @Test
    public void testCheckCorrectTemperatureConversion_WhenSwitchTempUnitButton(){
        String cityName = "Paris";

        openBaseURL();

        String oldH2Header = getText(H2_CITY_COUNTRY_HEADER);

        click(SEARCH_CITY_FIELD);
        input(cityName, SEARCH_CITY_FIELD);
        click(SEARCH_BUTTON);
        waitElementToBeVisible(SEARCH_DROPDOWN_MENU);
        click(PARIS_FR_CHOICE_IN_DROPDOWN_MENU);
        waitTextToBeChanged(H2_CITY_COUNTRY_HEADER, oldH2Header);

        String currentTempCstr = getText(TEMP);
        int currentTempCint = Integer.parseInt(currentTempCstr.substring(0, currentTempCstr.length() - 2)) * 2 + 30;

        click(SWITCH_TEMP_F);
        waitForGrayContainerDisappeared();

        String currentTempFstr = getText(TEMP);
        int currentTempFint = Integer.parseInt(currentTempFstr.substring(0, currentTempFstr.length() - 2));

        boolean difference = (Math.abs(currentTempFint - currentTempCint) <= 1);

        Assert.assertTrue(difference);
    }
}
