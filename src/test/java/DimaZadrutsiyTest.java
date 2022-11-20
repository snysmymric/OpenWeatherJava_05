import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.List;

@Ignore
public class DimaZadrutsiyTest extends BaseTest {

    final static String BASE_URL = "https://openweathermap.org/";

    private void openBaseURL() {
        getDriver().get(BASE_URL);
    }

    private void waitForGrayFrameDisappeared() {
        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("owm-loader-container")));
    }

    private void click(String whereToClick, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath(whereToClick))));
        wait.until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.xpath(whereToClick)))).click();
    }

    private boolean seeElement(String element) {

        return getDriver().findElement(By.xpath(element)).isDisplayed();
    }

    private boolean seeElement(String element, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath(element))));

        return true;
    }

    private int seeAllElementAndCount(String whichElement, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfAllElements(getDriver().findElement(By.xpath(whichElement))));
        List<WebElement> allElements = getDriver().findElements(By.xpath(whichElement));
        int count = allElements.size();
        for (WebElement checkedElement : allElements) {
            wait.until(ExpectedConditions.elementToBeClickable(checkedElement));
        }

        return count;
    }

    private String getText(String where, String attribute) {

        return getDriver().findElement(By.xpath(where)).getAttribute(attribute);
    }

    private String getText(String where) {

        return getDriver().findElement(By.xpath(where)).getText();
    }

    private void input(String element, String what, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath(element))));
        getDriver().findElement(By.xpath(element)).sendKeys(what, Keys.ENTER);
    }

    private void chooseFromDropDownMenu(String whatChoose, WebDriverWait wait) {
        getDriver().findElement(By.xpath(whatChoose));
        wait.until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.xpath(whatChoose)))).click();
    }

    private void pressButton(String whichButton, WebDriverWait wait) {
        wait.until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.xpath(whichButton)))).click();
    }

    private String backgroundColor(String element, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath(element))));

        return getDriver().findElement(By.xpath(element)).getCssValue("background-color");
    }

    @Test
    public void testUpdatePage() {
        String expectedResult = "Loading";

        openBaseURL();
        waitForGrayFrameDisappeared();

        click("//li[@class='logo']", getWait5());

        String actualResult = getText("//div[@aria-label='Loading']", "aria-label");

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testIconCurrentLocation() {
        String cityName = "Norway";

        String expectedResult = "London, GB";

        openBaseURL();
        waitForGrayFrameDisappeared();

        click("//div//input[@placeholder='Search city']", getWait5());
        input("//div//input[@placeholder='Search city']", cityName, getWait10());
        chooseFromDropDownMenu("//ul//span[text()='45.787, -87.904']", getWait5());
        pressButton("//div[@class='control-el']", getWait10());

        String actualResult = getText("//h2[@data-v-3e6e9f12]");

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testAllIconsAreShownAndClickable() {
        int expectedResult = 9;

        openBaseURL();
        waitForGrayFrameDisappeared();

        click("//span[text()='Different Weather?']", getWait5());

        int actualResult = seeAllElementAndCount("//ul[@class='icons']/li", getWait5());

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void test_X_ButtonIsShown() {
        boolean expectedResult = true;

        openBaseURL();
        waitForGrayFrameDisappeared();

        click("//span[text()='Different Weather?']", getWait5());

        boolean actualResult = seeElement("//div[@class='pop-up-container']//*[name()='path' and @fill='#8a8a8a']");

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testSendButtonIsShown() {

        openBaseURL();
        waitForGrayFrameDisappeared();

        click("//span[text()='Different Weather?']", getWait5());

        boolean actualResult = seeElement("//button[text()='Send']");

        Assert.assertTrue(actualResult);
    }

    @Test
    public void testNoticeText_WhenCityNameFromRandomSymbols() {
        String cityName = "Rrr";

        final String expectedResult = "No results for Rrr";

        openBaseURL();
        waitForGrayFrameDisappeared();
        input("//input[@placeholder='Search city']", cityName, getWait10());

        String actualResult = getText("//span[text()='No results for Rrr']");

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void test_X_buttonColorGreen() {
        String cityName = "Rrr";

        final String expectedResult = "rgba(120, 203, 191, 0.8)";

        openBaseURL();
        waitForGrayFrameDisappeared();
        input("//input[@placeholder='Search city']", cityName, getWait10());

        String actualResult = backgroundColor("//div[@class='widget-notification']", getWait10());

        Assert.assertEquals(actualResult, expectedResult);
    }
}
