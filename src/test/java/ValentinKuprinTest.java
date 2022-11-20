//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.testng.Assert;
//import org.testng.annotations.Ignore;
//import org.testng.annotations.Test;
//import base.BaseTest;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//@Ignore
//public class ValentinKuprinTest extends BaseTest {
//
//    final static String BASE_URL = "https://openweathermap.org/";
//    final static By GRID_CONTAINER_API_ICONS = By.xpath("//div[@class='grid-container api-icons']//a");
//    final static By H2_OUR_NEW_PROJECT_HEADER = By.xpath("//div[@class='section-content no-mobile-padding']//h2");
//    final static By COLUMNS_OUR_NEW_PROJECT = By.xpath("//div[@class='grey-background']");
//
//    private void openBaseUrl() {
//        getDriver().get(BASE_URL);
//    }
//
//    private void waitForGrayFrameDisappeared() {
//        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(
//                By.className("owm-loader-container")));
//    }
//
//    private List<String> apiIconsElements_h3AndSpan(By by) {
//        List<String> h3AndSpanElements = new ArrayList<>();
//
//        for (WebElement temp : getDriver().findElements(by)) {
//            h3AndSpanElements.add(temp.getText());
//        }
//        return h3AndSpanElements;
//    }
//
//    private String getText(By by, WebDriver driver) {
//
//        return driver.findElement(by).getText();
//    }
//
//    private int getColumnCount(By by, WebDriver driver) {
//
//        return driver.findElements(by).size();
//    }
//
//    @Test
//    public void testH3TextApiIconsAndCountOfDays() {
//        final List<String> expectedResult = Arrays.asList("current\n" +
//                "weather\n" +
//                "(current)", "hourly\n" +
//                "forecast\n" +
//                "(4 days)", "daily\n" +
//                "forecast\n" +
//                "(16 days)", "climatic\n" +
//                "forecast\n" +
//                "(30 days)", "historical\n" +
//                "weather\n" +
//                "(1 month, 1 year)");
//
//        openBaseUrl();
//        waitForGrayFrameDisappeared();
//
//        List<String> actualResult = apiIconsElements_h3AndSpan(GRID_CONTAINER_API_ICONS);
//
//        Assert.assertEquals(actualResult, expectedResult);
//    }
//
//    @Test
//    public void testH2TagTextOurNewProjects() {
//        final String expectedResult = "Our new products";
//
//        openBaseUrl();
//        waitForGrayFrameDisappeared();
//        String actualResult = getText(H2_OUR_NEW_PROJECT_HEADER, getDriver());
//
//        Assert.assertEquals(actualResult, expectedResult);
//    }
//
//    @Test
//    public void testCountColumnOurNewProjects() {
//        final int expectedResult = 3;
//
//        openBaseUrl();
//        waitForGrayFrameDisappeared();
//        int actualResult = getColumnCount(COLUMNS_OUR_NEW_PROJECT, getDriver());
//
//        Assert.assertEquals(actualResult, expectedResult);
//    }
//}
