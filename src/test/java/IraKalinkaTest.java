import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import base.BaseTest;

public class IraKalinkaTest extends BaseTest {
     @Test
    public void testTitleTagAndUrL() {
        final String pageTitle = "Сurrent weather and forecast - OpenWeatherMap";
        openBaseURL();
        getWait20();
        Assert.assertEquals(getTitle(), pageTitle);
        Assert.assertEquals(getCurrentURL(), BASE_URL);
    }
}
