package old_tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;
import java.util.List;


public class MariJernTest extends BaseTest {

    private final static String BLOG_URL = "https://openweather.co.uk/blog/category/weather";
    private final By MENU_BLOG = By.xpath("//div[@id='desktop-menu']//a[@href='https://openweather.co.uk/blog/category/weather']");
    private final By BLOG_CATEGORIES = By.xpath("//li[@class='post-filters__item']");

    @Test
    public void testMenuBlogIsClickable() {

        openBaseURL();
        click(MENU_BLOG);
        switchWindow();

        Assert.assertEquals(getDriver().getCurrentUrl(), BLOG_URL);
    }

    @Test
    public void testVerifyBlogPageArticleCategoriesCount() {
        int expectedArticleCategoriesCount = 6;

        openBaseURL();
        click(MENU_BLOG);
        switchWindow();

        List<WebElement> ArticleCategories = getDriver().findElements(BLOG_CATEGORIES);
        for (int i = 0; i < ArticleCategories.size(); i++) {

        Assert.assertEquals((ArticleCategories.size()), expectedArticleCategoriesCount);
        }
    }
}