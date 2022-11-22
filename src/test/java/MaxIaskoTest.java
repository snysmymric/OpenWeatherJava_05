import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

import java.util.List;

public class MaxIaskoTest extends BaseTest {
    final static By SEARCH_API_PAGE = By.xpath("//div[@id = 'desktop-menu']//a[text()= 'API']");
    final static By SEARCH_ORANGE_ELEMENT = By.xpath("//a[contains(@class, 'btn_block orange round')" +
            "or contains(@class, 'ow-btn round btn-orange')]");
    final static By SEARCH_LOGO_FIELD = By.xpath("//ul[@id= 'first-level-nav']/li[@class = 'logo']");
    final static By SEARCH_WHETHER_IN_YOUR_CITY_FIELD = By.xpath(
            "//div[@id='desktop-menu']/form/input[@type ='text']");
    final static By SEARCH_FIELD_TEXT = By.xpath("//div/input[@id='search_str']");
    final static By SEARCH_BLOG_FOOTER_LINK = By.xpath(
            "//div[@class='footer-section not-foldable']/div/ul//li/a[@target = '_blank']");
    final static By SEARCH_ELEMENTS_CONTAINER = By.xpath("//ul[@id= 'blog-categories']/li");

    @Test
    public void testSearch30OrangeButtons() {
        final int expectedResult = 30;

        openBaseURL();
        click(SEARCH_API_PAGE);

        Assert.assertEquals(countOrangeButtons(SEARCH_ORANGE_ELEMENT), expectedResult);
    }

    @Test
    public void testConfirmLinkNoChangeByLogoClick() {

        openBaseURL();
        click(SEARCH_LOGO_FIELD);

        Assert.assertEquals(getCurrentURL(), BASE_URL);
    }

    @Test
    public void testConfirmTextInLink() {
        final String name = "value";
        final String expectedResult0 = "Rome";
        final String expectedResult1 = "find";

        openBaseURL();
        inputAndEnter(SEARCH_WHETHER_IN_YOUR_CITY_FIELD, expectedResult0);

        Assert.assertTrue(isContainsTextInUrl(expectedResult0));
        Assert.assertTrue(isContainsTextInUrl(expectedResult1));

        jumpToNextWindow();

        Assert.assertEquals(getTextByAttribute(SEARCH_FIELD_TEXT, name), expectedResult0);
    }

    @Test
    public void test6LinksPresentEnable() {
        final List<String> expectedLinkList = List.of(
                "ALL", "WEATHER", "AGRO", "PLATFORM", "TECHNOLOGIES", "TEAM&COMPANY");

        openBaseURL();

        scrollByVisibleElement(SEARCH_BLOG_FOOTER_LINK);
        click(SEARCH_BLOG_FOOTER_LINK);
        jumpToNextWindow();

        Assert.assertEquals(getListSize(SEARCH_ELEMENTS_CONTAINER), expectedLinkList.size());

        Assert.assertEquals(getListText(SEARCH_ELEMENTS_CONTAINER), expectedLinkList);

        Assert.assertTrue(isElementsListActive(SEARCH_ELEMENTS_CONTAINER));
    }
}






