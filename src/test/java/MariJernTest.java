import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.ArrayList;

public class MariJernTest extends BaseTest {

    final static String BASE_URL = "https://openweathermap.org/";
    final static String BLOG_URL = "https://openweather.co.uk/blog/category/weather";
    final static By MENU_BLOG = By.xpath("//div[@id='desktop-menu']//a[@href='https://openweather.co.uk/blog/category/weather']");

    private void openBaseURL() {
        getDriver().get(BASE_URL);
    }

    private void clickMenu_Blog() { getDriver().findElement(MENU_BLOG).click(); }

    private void waitForGrayFrameDisappeared() {
        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("owm-loader-container")));
    }

    private void swithToUKWindow() {
        ArrayList<String> UK_Window = new ArrayList<>(getDriver().getWindowHandles());
        getDriver().switchTo().window(UK_Window.get(1));
    }

    @Test
    public void testMenuBlogIsClickable() {

        openBaseURL();
        waitForGrayFrameDisappeared();

        clickMenu_Blog();
        swithToUKWindow();

        Assert.assertEquals(getDriver().getCurrentUrl(),BLOG_URL);
    }
}