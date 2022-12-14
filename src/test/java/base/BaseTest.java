package base;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import pages.MainPage;
import utils.ReportUtils;
import utils.TestUtils;

import java.lang.reflect.Method;
import java.time.Duration;

public abstract class BaseTest {
    public final String BASE_URL = TestUtils.getBaseUrl();
    private WebDriver driver;
    private WebDriverWait webDriverWait20;

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

    public MainPage openBaseURL() {
        getDriver().get(BASE_URL);
        waitForGrayContainerDisappeared();

        if (reloadPageIfElementNotFound(By.xpath("//div[@id = 'weather-widget']//h2"))) {
            Reporter.log("BaseURL page was loaded successfully ", true);
        } else {
            Reporter.log("!!!!! Error !!!!! BaseURL page was NOT loaded. \n "
                    + "Cancel current run and Re-Run jobs\n", true);
        }

        return new MainPage(getDriver());
    }

    private boolean reloadPageIfElementNotFound(By by) {
        int count = 0;

        while (count <= 3 && !(isElementExists(by))) {
            getDriver().navigate().refresh();
            Reporter.log("Re-loading base URL page", true);
            waitForGrayContainerDisappeared();
            count++;
        }

        return isElementExists(by);
    }

    private void waitForGrayContainerDisappeared() {
        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("owm-loader-container")));
    }

    private boolean isElementExists(By by) {
        boolean isExists = true;
        try {
            driver.findElement(by);
        } catch (NoSuchElementException e) {
            isExists = false;
        }

        return isExists;
    }

    public String getExternalPageTitle() {

        return getDriver().getTitle();
    }

    public String getExternalPageURL() {

        return getDriver().getCurrentUrl();
    }
}
