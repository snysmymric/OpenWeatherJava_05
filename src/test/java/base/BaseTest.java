package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;
import utils.ReportUtils;
import utils.TestUtils;

import java.lang.reflect.Method;
import java.time.Duration;

public abstract class BaseTest {
    private WebDriver driver;
    private WebDriverWait webDriverWait20;
    private WebDriverWait webDriverWait10;

    public final String BASE_URL = TestUtils.getBaseUrl();

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
}