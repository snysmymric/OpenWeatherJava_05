
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class Elena_STest extends BaseTest {
    final static String BASE_URL ="https://openweathermap.org/";
    private void openBaseUrl(){
        getDriver().get(BASE_URL);
    }
    @Test
    public void testOpenWeatherStartPageOpened(){
        String expectedResultTitle = "Сurrent weather and forecast - OpenWeatherMap";
        String expectedResultUrl = "https://openweathermap.org/";
        openBaseUrl();
        String actualResultTitle = getDriver().getTitle();
        String actualResultUrl = getDriver().getCurrentUrl();

        Assert.assertEquals(actualResultTitle,expectedResultTitle);
        Assert.assertEquals(actualResultUrl,expectedResultUrl);
    }

}

