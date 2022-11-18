import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class KristinaPereselkinaTest extends BaseTest {

    final static String BASE_URL = "https://openweathermap.org/";

    final static By A_HREF_PRICE = By.xpath("//div[@id = 'desktop-menu']//a[@href = '/price']");
    final static By H2 = By.xpath("//h2[text()='Professional collections']");
    final static By PROFFESSIONAL_COLLECTION_TABLE_HEAD = By.xpath("//h3/b");
    final static By H2_HEADER_NAME_PROFESSIONAL_COLLECTION = By.xpath("//h2[text()= 'Current weather and forecasts collection']");
    final static By COLLECTION_SUBSCRIPTION_PAYMENT = By.xpath("//div/table//th/h4/b");
    final static By BUTTON_SUBSCRIPTION = By.xpath("//div/table//th/a");
    final static By COLLECTION_PRODUCTS = By.xpath("//div/table//td[1]/p");


    private List<String> getExpectedListProfessionalCollectionTableHead() {
        List<String> expectedList = new ArrayList<>();
        expectedList.add("Free");
        expectedList.add("Startup");
        expectedList.add("Developer");
        expectedList.add("Professional");
        expectedList.add("Enterprise");

        return expectedList;
    }

    private List<String> getExpectedProductList() {
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
    private List<String> getExpectedListCollectionSubscribtionPayment() {
        List<String> expectedList = new ArrayList<>();
        expectedList.add("40 USD");
        expectedList.add("180 USD");
        expectedList.add("470 USD");
        expectedList.add("2000 USD");

        return expectedList;
    }

    private List<String> getExpectedListButtonSubscription() {
        List<String> expectedList = new ArrayList<>();
        expectedList.add("Get API key");
        expectedList.add("Subscribe");
        expectedList.add("Subscribe");
        expectedList.add("Subscribe");
        expectedList.add("Subscribe");

        return expectedList;
    }

    private List<String> getActualList(By locator) {
        List<String> actualListItems = new ArrayList<>();
        List<WebElement> listItemsInMenu = getDriver().findElements(locator);
        for (WebElement items : listItemsInMenu) {
            actualListItems.add(items.getText());
        }
        actualListItems = actualListItems.stream().map(String::trim).collect(Collectors.toList());

        return actualListItems;
    }

    private void openBaseURL() {
        getDriver().get(BASE_URL);
    }

    private void waitForGrayFrameDisappeared() {
        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(By.className("owm-loader-container")));
    }

    private void click(By by, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    private String getText(By by, WebDriver driver) {

        return driver.findElement(by).getText();
    }


    @Test
    public void testH2HeadlineProfessionalcollections() {

        final String expectedResult = "Professional collections";

        openBaseURL();
        waitForGrayFrameDisappeared();
        click(A_HREF_PRICE, getWait5());

        String actualResult = getText(H2, getDriver());

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testH2ForecastsCollectionHeaderText() {

        final String expectedResult = "Current weather and forecasts collection";

        openBaseURL();
        waitForGrayFrameDisappeared();
        click(A_HREF_PRICE, getWait5());

        String actualResult = getText(H2_HEADER_NAME_PROFESSIONAL_COLLECTION, getDriver());

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testH2CurrentWeatherAndForecastsCollectionHeader() {

        List<String> expectedList = getExpectedListProfessionalCollectionTableHead();

        openBaseURL();
        waitForGrayFrameDisappeared();
        click(A_HREF_PRICE, getWait5());

        List<String> actualList = getActualList(PROFFESSIONAL_COLLECTION_TABLE_HEAD);

        Assert.assertEquals(actualList, expectedList);
    }

    @Test
    public void testCollectionSubscribtionPayment() {

        List<String> expectedList = getExpectedListCollectionSubscribtionPayment();

        openBaseURL();
        waitForGrayFrameDisappeared();
        click(A_HREF_PRICE, getWait5());

        List<String> actualList = getActualList(COLLECTION_SUBSCRIPTION_PAYMENT);

        Assert.assertEquals(actualList, expectedList);
    }

    @Test
    public void testListOfProductsForProfessionalCollection() {

        List<String> expectedList = getExpectedProductList();

        openBaseURL();
        waitForGrayFrameDisappeared();
        click(A_HREF_PRICE, getWait5());

        List<String> actualList = getActualList(COLLECTION_PRODUCTS);

        Assert.assertEquals(actualList, expectedList);
    }

    @Test
    public void testSubscriptionButtonForProfessionalCollection() {

        List<String> expectedList = getExpectedListButtonSubscription();

        openBaseURL();
        waitForGrayFrameDisappeared();
        click(A_HREF_PRICE, getWait5());

        List<String> actualList = getActualList(BUTTON_SUBSCRIPTION);

        Assert.assertEquals(actualList, expectedList);
    }
}
