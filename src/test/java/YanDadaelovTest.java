import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;


public class YanDadaelovTest extends BaseTest {
    @Test
    public void testStartPageUrl() {
        openBaseURL();
        final String actual_URL = getCurrentURL();

        Assert.assertEquals(actual_URL, BASE_URL);
    }

    @Test
    public void testStartPageTitleText() {
        final String startPageTitle = "Сurrent weather and forecast - OpenWeatherMap";

        openBaseURL();
        final String actual_titleText = getTitle();

        Assert.assertEquals(actual_titleText, startPageTitle);
    }
}
