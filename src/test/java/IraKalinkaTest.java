import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IraKalinkaTest extends BaseTest {

    private final By SUPPORT_LINK = By.id("support-dropdown");
    private final By DROPDOWN_MENU  = By.xpath("//li[@class='with-dropdown']/ul[@class='dropdown-menu dropdown-visible']");
    private final By TAG_NAME = By.tagName("a");

    @Test
    public void testTitleTagAndUrL() {
        final String pageTitle = "Сurrent weather and forecast - OpenWeatherMap";
        openBaseURL();
        getWait20();
        Assert.assertEquals(getTitle(), pageTitle);
        Assert.assertEquals(getCurrentURL(), BASE_URL);
    }

    @Test
    public void testSupportMenuHasLinks() {
       final List<String> expectedList = List.of("FAQ", "How to start", "Ask a question");
       openBaseURL();
       click(SUPPORT_LINK);
       Assert.assertEquals(getLinksList(DROPDOWN_MENU, TAG_NAME), expectedList);
    }

















}
