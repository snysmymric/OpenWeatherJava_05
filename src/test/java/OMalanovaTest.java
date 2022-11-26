import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class OMalanovaTest extends BaseTest {
    private final By SEARCH_CITY_FIELD = By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']");
    private final By SEARCH_BUTTON = By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']");
    private final By H2_CITY_COUNTRY_HEADER = By.xpath("//div[@id = 'weather-widget']//h2");
    private final By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
    private final By PARIS_FR_CHOICE_IN_DROPDOWN_MENU = By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']");
    private final By TEMP = By.xpath("//span[@class='heading']");
    private final By SWITCH_TEMP_F = By.xpath("//div[text()='Imperial: °F, mph']");
    private final By CURRENT_TIME = By.xpath("//div[@id='weather-widget']//span[@class='orange-text'][text()]");

    @Test
    public void testCheckCorrectTemperatureConversion_WhenSwitchTempUnitButton(){
        openBaseURL();

        String currentTempCstr = getText(TEMP);
        int switchTempCtoF = Integer.parseInt(currentTempCstr.substring(0, currentTempCstr.length() - 2)) * 2 + 30;

        click(SWITCH_TEMP_F);
        waitForGrayContainerDisappeared();

        String currentTempFstr = getText(TEMP);
        int currentTempFint = Integer.parseInt(currentTempFstr.substring(0, currentTempFstr.length() - 2));

        boolean difference = (Math.abs(currentTempFint - switchTempCtoF) <= 1);

        Assert.assertTrue(difference);
    }

    @Test
    public void testCheckTimeValueOnTimelineOfWidgetMap() {
        openBaseURL();

        List<WebElement> list = getDriver().findElements(By.xpath("//div[@class='time-container']"));

        List<LocalTime> expectedTimes = new ArrayList<>();
        for (int i = 0; i <= 4; i++) {
            expectedTimes.add(LocalTime.parse(getText(CURRENT_TIME).substring(8, 13)).plusMinutes(i * 15));
        }

        List<LocalTime> actualTimes = new ArrayList<>();
        int i = 4;
        while (i <= 64) {
            for (WebElement element : list) {
                actualTimes.add(LocalTime.parse(element.getText().substring(i, i + 5)));
            }
            i += 14;
        }

        Assert.assertEquals(actualTimes, expectedTimes);
    }
}
