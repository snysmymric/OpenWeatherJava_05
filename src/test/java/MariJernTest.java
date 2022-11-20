import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;

import org.testng.annotations.Test;
import base.BaseTest;

import java.util.ArrayList;
import java.util.List;

@Ignore
public class MariJernTest extends BaseTest {

    final static String BASE_URL = "https://openweathermap.org/";
    final static String BLOG_URL = "https://openweather.co.uk/blog/category/weather";
    final static By MENU_BLOG = By.xpath("//div[@id='desktop-menu']//a[@href='https://openweather.co.uk/blog/category/weather']");
    final static By BLOG_CATEGORIES = By.xpath("//li[@class='post-filters__item']");

    private void openBaseURL() {
        getDriver().get(BASE_URL);
    }

    private void clickMenu_Blog() {
        getDriver().findElement(MENU_BLOG)
                .click();
    }

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

        Assert.assertEquals(getDriver().getCurrentUrl(), BLOG_URL);
    }

    @Test
    public void testVerifyBlogPageArticleCategoriesCount() {
        int expectedArticleCategoriesCount = 6;

        openBaseURL();
        waitForGrayFrameDisappeared();
        clickMenu_Blog();
        swithToUKWindow();

        List<WebElement> ArticleCategories = getDriver().findElements(BLOG_CATEGORIES);
        for (int i = 0; i < ArticleCategories.size(); i++) {

            Assert.assertEquals((ArticleCategories.size()), expectedArticleCategoriesCount);
        }
    }
}