package old_tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

import java.util.Arrays;
import java.util.List;

public class ValentinKuprinTest extends BaseTest {
    final static By GRID_CONTAINER_API_ICONS = By.xpath("//div[@class='grid-container api-icons']//a");
    final static By H2_OUR_NEW_PROJECT_HEADER = By.xpath("//div[@class='section-content no-mobile-padding']//h2");
    final static By COLUMNS_OUR_NEW_PROJECT = By.xpath("//div[@class='grey-background']");

    @Test
    public void testH3TextApiIconsAndCountOfDays() {
        final List<String> expectedResult = Arrays.asList("current\n" +
                "weather\n" +
                "(current)", "hourly\n" +
                "forecast\n" +
                "(4 days)", "daily\n" +
                "forecast\n" +
                "(16 days)", "climatic\n" +
                "forecast\n" +
                "(30 days)", "historical\n" +
                "weather\n" +
                "(1 month, 1 year)");

        openBaseURL();

        List<String> actualResult = getListText(GRID_CONTAINER_API_ICONS);

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testH2TagTextOurNewProjects() {
        final String expectedResult = "Our new products";

        openBaseURL();
        String actualResult = getText(H2_OUR_NEW_PROJECT_HEADER);

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testCountColumnOurNewProjects() {
        final int expectedResult = 3;

        openBaseURL();
        int actualResult = getListSize(COLUMNS_OUR_NEW_PROJECT);

        Assert.assertEquals(actualResult, expectedResult);
    }
}
