//import org.testng.Assert;
//import org.testng.annotations.Ignore;
//import org.testng.annotations.Test;
//import base.BaseTest;
//
//@Ignore
//public class YanDadaelovTest extends BaseTest {
//
//    final static String BASE_URL = "https://openweathermap.org/";
//    final static String START_PAGE_TITLE = "Сurrent weather and forecast - OpenWeatherMap";
//
//
//    private void openBaseURL() {
//        getDriver().get(BASE_URL);
//    }
//
//    private String getCurrentUrl() {
//
//        return getDriver().getCurrentUrl();
//    }
//
//    private String getCurrentTitle() {
//
//        return getDriver().getTitle();
//    }
//
//
//    @Test
//    public void testStartPageUrl() {
//        openBaseURL();
//        getWait5();
//        String actualResult_currentUrl = getCurrentUrl();
//
//        Assert.assertEquals(actualResult_currentUrl, BASE_URL);
//    }
//
//    @Test
//    public void testStartPageTitleText() {
//        openBaseURL();
//        getWait5();
//        String actualResult_startPageTitleText = getCurrentTitle();
//
//        Assert.assertEquals(actualResult_startPageTitleText, START_PAGE_TITLE);
//    }
//}
