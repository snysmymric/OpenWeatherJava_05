import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

import java.util.List;

public class DenSebrovskyTest extends BaseTest {

    final static By SUPPORT_BUTTON = By.xpath("//div[@id='support-dropdown']");
    final static By SUPPORT_DROPDOWN_MENU = By.xpath("//ul[@id='support-dropdown-menu']");
    final static By SUBMENU_BUTTONS_AMOUNT = By.xpath("//ul[@id='support-dropdown-menu']/*");
    final static By MENU_PRICING_BUTTON = By.xpath("//div[@id='desktop-menu']//a[@href='/price']");
    final static By FAQ_BUTTON = By.xpath("//a[@href='/faq#onecall']");
    private final By FAQ_H3_HEADER = By.xpath("//div[@class='col-sm-12']/section/h3");

    @Test
    public void testSupportMenuIsClickableAndHas3Submenues() {
        final int expectedSubmenuButtonsAmount = 3;

        openBaseURL();
        click(SUPPORT_BUTTON);
        waitElementToBeVisible(SUPPORT_DROPDOWN_MENU);
        Assert.assertTrue(getDriver().findElement(SUPPORT_DROPDOWN_MENU).isDisplayed());

        int actualSubmenuButtonsAmount = getDriver().findElements(SUBMENU_BUTTONS_AMOUNT).size();
        Assert.assertEquals(actualSubmenuButtonsAmount, expectedSubmenuButtonsAmount);

        click(SUPPORT_BUTTON);
        Assert.assertFalse(getDriver().findElement(SUPPORT_DROPDOWN_MENU).isDisplayed());
    }

    @Test
    public void test_CheckH3HeadersAmountOnTheFAQPage() {
        final int expectedH3HeadersAmount = 10;

        openBaseURL();
        click(MENU_PRICING_BUTTON);
        click(FAQ_BUTTON);
        List<WebElement> H3HeadersAmount = getDriver().findElements(FAQ_H3_HEADER);
        int actualH3HeadersAmount = H3HeadersAmount.size();

        Assert.assertEquals(actualH3HeadersAmount, expectedH3HeadersAmount);
    }
}
