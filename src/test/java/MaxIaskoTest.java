import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

public class MaxIaskoTest extends BaseTest {
    final static By SEARCH_API_PAGE = By.xpath("//div[@id = 'desktop-menu']//a[text()= 'API']");
    final static By SEARCH_ORANGE_ELEMENT = By.xpath("//a[contains(@class, 'btn_block orange round')" +
            "or contains(@class, 'ow-btn round btn-orange')]");
    final static By SEARCH_LOGO_FIELD = By.xpath("//ul[@id= 'first-level-nav']/li[@class = 'logo']");
    final static By SEARCH_WHETHER_IN_YOUR_CITY_FIELD = By.xpath(
            "//div[@id='desktop-menu']/form/input[@type ='text']");
    final static By SEARCH_FIELD_TEXT = By.xpath("//div/input[@id='search_str']");

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
}






