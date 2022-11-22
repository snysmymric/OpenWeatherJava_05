import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import java.util.List;

public class ElviraKh1Test extends BaseTest {

    private final By SUPPORT_MENU_ITEM = By.xpath("//div[@id='support-dropdown']");
    private final By SUB_SUPPORT_MENU_ITEMS = By.xpath("//ul[@id='support-dropdown-menu']//li");

    @Ignore
    @Test
    public void testSupportSubMenu() {
        final int cntSubSupportMenu = 3;
        final String expectedSupportMenu = "Support";
        final String[] expectedSupportSubMenus = {"FAQ", "How to start", "Ask a question"};

        openBaseURL();

        click(SUPPORT_MENU_ITEM);

        List<WebElement> subSupportMenus = getListOfElements(SUB_SUPPORT_MENU_ITEMS);

        Assert.assertEquals(getText(SUPPORT_MENU_ITEM), expectedSupportMenu);
        Assert.assertEquals(subSupportMenus.size(), cntSubSupportMenu);
        int i = 0;
        for (WebElement actualSubMenu : subSupportMenus) {
            Assert.assertTrue(actualSubMenu.isDisplayed());
            Assert.assertEquals(actualSubMenu.getText(), expectedSupportSubMenus[i++]);
        }
    }
}