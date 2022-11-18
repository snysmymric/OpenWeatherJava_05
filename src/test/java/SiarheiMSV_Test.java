import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class SiarheiMSV_Test extends BaseTest {

    final static String BASE_URL = "https://openweathermap.org/";

    final static By SEARCH_BUTTON_DASHBOARD = By.xpath("//div[@id = 'desktop-menu']//a[@href='/weather-dashboard']");

    private void openBaseURL() {
        getDriver().get(BASE_URL);
    }

    private void waitForGrayFrameDisappeared() {
        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("owm-loader-container")));
    }

    private void click(By by,  WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }


    @Test

    public void RFMainNavBarMenuDashboard_testTitleText_WhenChooseMenuDashboard_SiarheiMSV (){

        String expectedResult1 = "https://openweathermap.org/weather-dashboard";
        String expectedResult2 = "Weather dashboard - OpenWeatherMap";

        openBaseURL();
        waitForGrayFrameDisappeared();

        click(SEARCH_BUTTON_DASHBOARD, getWait20());

        String actualResult1 = getDriver().getCurrentUrl();
        Assert.assertEquals(actualResult1, expectedResult1);

        String actualResult2 = getDriver().getTitle();
        Assert.assertEquals(actualResult2, expectedResult2);
    }
}
