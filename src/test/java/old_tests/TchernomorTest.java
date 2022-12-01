package old_tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.List;

@Ignore
public class TchernomorTest extends BaseTest {

    private final By DIFFERENT_WEATHER_ITEM_BUTTON = By.xpath("//span[@class='control-el owm-switch']");
    private final By LIST_OF_ICONS_IN_POPUP_WINDOW = By.xpath("//ul[@class='icons']/li");
    private final List<String> EXPECTED_LIST_OF_ITEMS_IN_POPUP_WINDOW = List.of("clear sky", "few clouds",
            "overcast clouds", "drizzle", "rain", "shower rain", "thunderstorm", "snow", "mist");

    @Test
    public void testCheckIconsNames() {
        openBaseURL();
        click(DIFFERENT_WEATHER_ITEM_BUTTON);

        Assert.assertEquals(getListText(LIST_OF_ICONS_IN_POPUP_WINDOW), EXPECTED_LIST_OF_ITEMS_IN_POPUP_WINDOW);
    }
}
