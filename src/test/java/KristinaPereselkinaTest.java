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


public class KristinaPereselkinaTest extends BaseTest {

    final static String BASE_URL = "https://openweathermap.org/";

    final static By A_HREF_PRICE = By.xpath("//div[@id = 'desktop-menu']//a[@href = '/price']");
    final static By H2 = By.xpath("//h2[text()='Professional collections']");
    final static By PROFFESSIONAL_COLLECTION_TABLE_HEAD = By.xpath("//h3/b");
    final static By H2_HEADER_NAME_PROFESSIONAL_COLLECTION = By.xpath("//h2[text()= 'Current weather and forecasts collection']");

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

    private List<String> getExpectedListProfessionalCollectionTableHead() {
        List<String> expectedList = new ArrayList<>();
        expectedList.add("Free");
        expectedList.add("Startup");
        expectedList.add("Developer");
        expectedList.add("Professional");
        expectedList.add("Enterprise");

        return expectedList;
    }

    private List<String> getActualListProfessionalCollectionTableHead(By locator) {
        List<String> actualListItems = new ArrayList<>();
        List<WebElement> listItemsInMenu = getDriver().findElements(locator);
        for (WebElement items : listItemsInMenu) {
            actualListItems.add(items.getText());
        }

        return actualListItems;
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

        List<String> actualList = getActualListProfessionalCollectionTableHead(PROFFESSIONAL_COLLECTION_TABLE_HEAD);

        Assert.assertEquals(actualList, expectedList);
    }
}
