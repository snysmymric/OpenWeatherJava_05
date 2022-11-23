import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

public class AlinaIvanovaTest extends BaseTest {
    private final By MENU_OUR_INITIATIVES = By.xpath("//div[@id= 'desktop-menu']//a[@href= '/our-initiatives']");

    @Test
    public void testOurInitiativesMenu() {

        final String expectedLink = "https://openweathermap.org/our-initiatives";

        openBaseURL();
        click(MENU_OUR_INITIATIVES);

        String actualLink = getCurrentURL();

        Assert.assertEquals(actualLink, expectedLink);
    }
}
