package old_tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;


public class AllaIgTest extends BaseTest {
    final static By MENU_GUIDE = By.xpath("//div/ul//li/a[@href='/guide']");
    final static By GUIDE_TITLE = By.className("breadcrumb-title");

    @Test
    public void testUrlAndTitle_WhenGuidePress() {

        String expectedResultUrl = "https://openweathermap.org/guide";
        String expectedResultTitle = "Guide";

        openBaseURL();
        click20(MENU_GUIDE);

        String actualResultTitle = getText(GUIDE_TITLE);

        Assert.assertEquals(getDriver().getCurrentUrl(), expectedResultUrl);
        Assert.assertEquals(actualResultTitle, expectedResultTitle);
    }
}
