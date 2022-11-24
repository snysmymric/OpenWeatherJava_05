import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

public class DenSebrovskyTest extends BaseTest {

    final static By SUPPORT_BUTTON = By.xpath("//div[@id='support-dropdown']");
    final static By SUPPORT_DROPDOWN_MENU = By.xpath("//ul[@id='support-dropdown-menu']");
    final static By SUBMENU_BUTTONS_AMOUNT = By.xpath("//ul[@id='support-dropdown-menu']/*");

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
}
