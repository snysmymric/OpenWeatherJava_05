package old_tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

@Ignore
public class KristinaPereselkinaTest extends BaseTest {
    static final By A_HREF_PRICE = By.xpath("//div[@id='desktop-menu']//a[@href='/price']");
    static final By H2 = By.xpath("//h2[text()='Professional collections']");
    static final By PROFFESSIONAL_COLLECTION_NAMES = By.xpath("//h3/b");
    static final By H2_HEADER_PROFESSIONAL_COLLECTION = By.xpath("//h2[text()='Current weather and forecasts collection']");
    static final By BUTTON_SUBSCRIPTION = By.xpath("//div/table//th/a");
    static final By FREE_COLLECTION_PRODUCTS = By.xpath("//div/table//td[1]/p");
    static final By STARTUP_COLLECTION_PRODUCTS = By.xpath(" //div/table//td[2]/p");
    static final By DISABLED_PRODUCTS_FREE_SUBSCRIPTION = By.xpath("//div/table//td[1]/p[@style='color:#e0e0e0;']");
    static final By DISABLED_PRODUCTS_STARTUP_SUBSCRIPTION = By.xpath("//div/table//td[2]/p[@style='color:#e0e0e0;']");

    static private List<String> getProfessionalSubscriptionPlans() {
        List<String> expectedList = new ArrayList<>();
        expectedList.add("Free");
        expectedList.add("Startup");
        expectedList.add("Developer");
        expectedList.add("Professional");
        expectedList.add("Enterprise");

        return expectedList;
    }
    private List<String> getProfessionalCollectionProducts() {
        List<String> expectedList = new ArrayList<>();
        expectedList.add("Current Weather");
        expectedList.add("3-hour Forecast 5 days");
        expectedList.add("Hourly Forecast 4 days");
        expectedList.add("Daily Forecast 16 days");
        expectedList.add("Climatic Forecast 30 days");
        expectedList.add("Bulk Download");
        expectedList.add("Basic weather maps");
        expectedList.add("Historical maps");
        expectedList.add("Global Precipitation Map - Historical data");
        expectedList.add("Weather Dashboard");
        expectedList.add("Road Risk API (basic configuration)");
        expectedList.add("Air Pollution API");
        expectedList.add("Geocoding API");
        expectedList.add("Weather widgets");
        expectedList.add("Uptime 95%");

        return expectedList;
    }
    private List<String> getSubscriptionButtons() {
        List<String> expectedList = new ArrayList<>();
        expectedList.add("Get API key");
        expectedList.add("Subscribe");
        expectedList.add("Subscribe");
        expectedList.add("Subscribe");
        expectedList.add("Subscribe");

        return expectedList;
    }
    private List<String> getDisabledProductsFreeCollection() {
        List<String> expectedList = new ArrayList<>();
        expectedList.add("Hourly Forecast 4 days");
        expectedList.add("Daily Forecast 16 days");
        expectedList.add("Climatic Forecast 30 days");
        expectedList.add("Bulk Download");
        expectedList.add("Historical maps");
        expectedList.add("Global Precipitation Map - Historical data");
        expectedList.add("Road Risk API (basic configuration)");

        return expectedList;
    }

    private List<String> getDisabledProductsStartUpCollection() {
        List<String> expectedList = new ArrayList<>();
        expectedList.add("Hourly Forecast 4 days");
        expectedList.add("Climatic Forecast 30 days");
        expectedList.add("Bulk Download");
        expectedList.add("Historical maps");
        expectedList.add("Global Precipitation Map - Historical data");
        expectedList.add("Road Risk API (basic configuration)");

        return expectedList;
    }

    @Test
    public void testH2ProfessionalcollectionsText() {
        final String expectedResult = "Professional collections";

        openBaseURL();

        waitForGrayContainerDisappeared();
        click(A_HREF_PRICE);

        String actualResult = getText(H2);

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testH2CurrentWeatherAndForecastsCollectionText() {
        final String expectedResult = "Current weather and forecasts collection";

        openBaseURL();

        waitForGrayContainerDisappeared();
        click(A_HREF_PRICE);

        String actualResult = getText(H2_HEADER_PROFESSIONAL_COLLECTION);

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testSubscriptionPlan() {
        List<String> expectedList = getProfessionalSubscriptionPlans();

        openBaseURL();

        waitForGrayContainerDisappeared();
        click(A_HREF_PRICE);

        List<String> actualList = getListText(PROFFESSIONAL_COLLECTION_NAMES);

        Assert.assertEquals(actualList, expectedList);
    }

    @Test
    public void testProfessionalSubscriptionButtons() {
        List<String> expectedList = getSubscriptionButtons();

        openBaseURL();

        waitForGrayContainerDisappeared();
        click(A_HREF_PRICE);

        List<String> actualList = getListText(BUTTON_SUBSCRIPTION);

        Assert.assertEquals(actualList, expectedList);
    }

    @Test
    public void testFreeSubscriptionProducts() {
        List<String> expectedList = getProfessionalCollectionProducts();

        openBaseURL();

        waitForGrayContainerDisappeared();
        click(A_HREF_PRICE);

        List<String> actualList = getListText(FREE_COLLECTION_PRODUCTS);

        Assert.assertEquals(actualList, expectedList);
    }

    @Test
    public void testDisabledProductsOnFreeSubscription() {
        List<String> expectedList = getDisabledProductsFreeCollection();

        openBaseURL();

        waitForGrayContainerDisappeared();
        click(A_HREF_PRICE);

        List<String> actualList = getListText(DISABLED_PRODUCTS_FREE_SUBSCRIPTION);

        Assert.assertEquals(actualList, expectedList);
    }

    @Test
    public void testDisabledProductsStartUpSubscription() {
        List<String> expectedList = getDisabledProductsStartUpCollection();

        openBaseURL();

        waitForGrayContainerDisappeared();
        click(A_HREF_PRICE);

        List<String> actualList = getListText(DISABLED_PRODUCTS_STARTUP_SUBSCRIPTION);

        Assert.assertEquals(actualList, expectedList);
    }

    @Test
    public void testStartupSubscriptionProducts() {
        List<String> expectedList = getProfessionalCollectionProducts();

        openBaseURL();
        waitForGrayContainerDisappeared();
        click(A_HREF_PRICE);

        List<String> actualList = getListText(STARTUP_COLLECTION_PRODUCTS);

        Assert.assertEquals(actualList, expectedList);
    }
}
