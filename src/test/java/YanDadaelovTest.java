import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;


public class YanDadaelovTest extends BaseTest {

    final static String BASE_URL = "https://openweathermap.org/";


    private void openBaseURL() {
        getDriver().get(BASE_URL);
    }

    private String getCurrentUrl() {

        return getDriver().getCurrentUrl();
    }


    @Test
    public void testStartPageUrl() {
        openBaseURL();
        getWait5();
        String actualResult_currentURl = getCurrentUrl();

        Assert.assertEquals(actualResult_currentURl, BASE_URL);
    }
}
