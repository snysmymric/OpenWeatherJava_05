package base;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;
import utils.ReportUtils;
import utils.TestUtils;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class BaseTest {
    public final String BASE_URL = TestUtils.getBaseUrl();
    private WebDriver driver;
    private WebDriverWait webDriverWait20;
    private WebDriverWait webDriverWait10;

    @BeforeSuite
    protected void beforeSuite(ITestContext context) {
        Reporter.log(ReportUtils.getReportHeader(context), true);
    }

    @BeforeMethod
    protected void beforeMethod(Method method, ITestResult result) {
        driver = BaseUtils.createDriver();
        Reporter.log(ReportUtils.END_LINE, true);
        Reporter.log("TEST RUN", true);
        Reporter.log(ReportUtils.getClassNameTestName(method, result), true);
    }

    @AfterMethod
    protected void afterMethod(Method method, ITestResult result) {
        Reporter.log(ReportUtils.getTestStatistics(method, result), true);

        driver.quit();
        webDriverWait20 = null;
        webDriverWait10 = null;
    }

    protected WebDriver getDriver() {
        return driver;
    }

    protected WebDriverWait getWait20() {
        if (webDriverWait20 == null) {
            webDriverWait20 = new WebDriverWait(driver, Duration.ofSeconds(20));
        }

        return webDriverWait20;
    }

    protected WebDriverWait getWait10() {
        if (webDriverWait10 == null) {
            webDriverWait10 = new WebDriverWait(driver, Duration.ofSeconds(10));
        }

        return webDriverWait10;
    }

    public void openBaseURL() {
        getDriver().get(BASE_URL);
        waitForGrayContainerDisappeared();
        waitForElement(By.xpath("//div[@id = 'weather-widget']//h2"));
    }

    public void waitForGrayContainerDisappeared() {
        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("owm-loader-container")));
    }

    public String getText(By by) {

        return getDriver().findElement(by).getText();
    }

    public void click(By by) {
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(by));
        getWait10().until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    public void click20(By by) {
        getWait20().until(ExpectedConditions.visibilityOfElementLocated(by));
        getWait20().until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    public void input(String text, By by) {

        getDriver().findElement(by).sendKeys(text);
    }

    public void waitElementToBeVisible(By by) {
        getWait20().until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void waitTextToBeChanged(By by, String text) {
        getWait10().until(ExpectedConditions
                .not(ExpectedConditions.textToBePresentInElement(driver.findElement(by), text)));
    }

    public boolean isDisplayed(By by) {

        return getDriver().findElement(by).isDisplayed();
    }

    public int countOrangeButtons(By by) {
        return driver.findElements(by).size();
    }

    public String systemDate() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, hh:mma");

        return sdf.format(date).substring(0, 10);
    }

    public String getCurrentURL() {

        return getDriver().getCurrentUrl();
    }

    public List<WebElement> getListOfElements(By by) {

        return getDriver().findElements(by);
    }

    public int getListSize(By by) {

        return getListOfElements(by).size();
    }

    public String getTextByAttribute(By by, String attribute) {

        return getDriver().findElement(by).getAttribute(attribute);
    }

    public int findAllVisibleElements(String xpath, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfAllElements(getDriver().findElement(By.xpath(xpath))));
        List<WebElement> elements = getDriver().findElements(By.xpath(xpath));
        for (WebElement element : elements) {
            wait.until(ExpectedConditions.elementToBeClickable(element));
        }

        return elements.size();
    }

    public void scrollByVisibleElement(By by) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView();", getDriver().findElement(by));
    }

    public void switchToAnotherWindow(WebDriver driver) {
        String originalWindow = driver.getWindowHandle();

        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.equals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    public String getTitle() {
        return getDriver().getTitle();
    }

    public void switchWindow() {
        ArrayList<String> newWindow = new ArrayList<>(getDriver().getWindowHandles());
        getDriver().switchTo().window(newWindow.get(1));
    }

    public int seeAllElementAndCount(By by) {
        getWait20().until(ExpectedConditions.visibilityOfAllElements(getDriver().findElement(by)));
        List<WebElement> allElements = getDriver().findElements(by);
        int count = allElements.size();
        for (WebElement checkedElement : allElements) {
            getWait20().until(ExpectedConditions.elementToBeClickable(checkedElement));
        }

        return count;
    }

    public void inputAndEnter(By by, String text) {
        getWait10().until(ExpectedConditions.visibilityOf(getDriver().findElement(by)));
        getDriver().findElement(by).sendKeys(text, Keys.ENTER);
    }

    public String backgroundColor(By by) {
        getWait10().until(ExpectedConditions.visibilityOf(getDriver().findElement(by)));

        return getDriver().findElement(by).getCssValue("background-color");
    }

    public boolean isElementExists(By by) {

        return getWait10().until(
                ExpectedConditions.visibilityOfElementLocated(by)).isDisplayed();
    }

    public void openURL(String url) {
        getDriver().get(url);
    }

    public boolean isElementEnabled(By by) {

        return getDriver().findElement(by).isEnabled();
    }

    public void jumpToNextWindow() {
        String mainWindows = getDriver().getWindowHandle();
        for (String windowsHandle : getDriver().getWindowHandles()) {
            if (!mainWindows.contentEquals(windowsHandle)) {
                getDriver().switchTo().window(windowsHandle);
                break;
            }
        }
    }

    public boolean isContainsTextInUrl(String text) {
        String currentUrl = getDriver().getCurrentUrl();
        return currentUrl.contains(text);
    }

    public void inputTextAndClickEnter (By by, String text) {
        getDriver().findElement(by).sendKeys(text + Keys.ENTER);
    }
    
    public List<String> getListText(By by) {
        List<String> elements = new ArrayList<>();

        for (WebElement temp : getDriver().findElements(by)) {
            elements.add(temp.getText());
        }

        return elements;
    }
    public void scrollToPageBottom(){
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void waitForElement(By by) {
        getWait20().until(ExpectedConditions.presenceOfElementLocated(by));
    }
}
