package old_tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

public class KartavenkoPavelTest extends BaseTest {
    private final static By API_LINK = By.xpath("//ul[@id='first-level-nav']//a[@href='/api']");
    private final static By AMOUNT_OF_ORANGE_BUTTONS = By.xpath("//a[contains(@class,'orange')]");
    private final static By NAV_BAR_SEARCH_FIELD = By.xpath("//div//input[@name='q']");
    private final static By SEARCH_FIELD_WEATHER_IN_YOUR_CITY = By.id("search_str");

    @Test
    public void testThirtyOrangeButtons() {
        final int expectedAmountOfButtons = 30;

        openBaseURL();

        click(API_LINK);
        int actualAmountOfButtons = getListSize(AMOUNT_OF_ORANGE_BUTTONS);

        Assert.assertEquals(actualAmountOfButtons, expectedAmountOfButtons);
    }

    @Test
    public void testNavBarSearchField() {
        final String expectedCityName = "Rome";
        final String expectedPathParameter = "find";
        final String attributeName = "value";

        openBaseURL();

        input(expectedCityName + "\n", NAV_BAR_SEARCH_FIELD);
        String path = getDriver().getCurrentUrl();
        boolean actualQueryPath = path.contains(expectedPathParameter) && path.contains(expectedCityName);
        String actualCityName = getTextByAttribute(SEARCH_FIELD_WEATHER_IN_YOUR_CITY, attributeName);

        Assert.assertTrue(actualQueryPath);
        Assert.assertEquals(actualCityName, expectedCityName);
    }
}
