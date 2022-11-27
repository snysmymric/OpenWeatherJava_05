package old_tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ElviraKh1Test extends BaseTest {

    private final By SUPPORT_MENU_ITEM = By.xpath("//div[@id='support-dropdown']");
    private final By SUB_SUPPORT_MENU_ITEMS = By.xpath("//ul[@id='support-dropdown-menu']//li");
    private final By BY_WIDGET_CONTROL_LINK_ON_MAIN_PAGE = By.xpath("//div[@id='footer-website']//a[@href='/widgets-constructor']");
    private final By BY_LIST_OF_TYPE_WIDGETS_RADIOBTN = By.xpath("//ul[@class='widget-choose']/li//input");
    private final By BY_LIST_OF_TYPE_WIDGETS_IMG = By.xpath("//ul[@class='widget-choose']/li//img");


    @Test
    public void testSupportSubMenu() {
        final int cntSubSupportMenu = 3;
        final String expectedSupportMenu = "Support";
        final String[] expectedSupportSubMenus = {"FAQ", "How to start", "Ask a question"};

        openBaseURL();

        click20(SUPPORT_MENU_ITEM);
        waitElementToBeVisible(SUB_SUPPORT_MENU_ITEMS);
        List<WebElement> subSupportMenus = getListOfElements(SUB_SUPPORT_MENU_ITEMS);

        Assert.assertEquals(getText(SUPPORT_MENU_ITEM), expectedSupportMenu);
        Assert.assertEquals(subSupportMenus.size(), cntSubSupportMenu);
        int i = 0;
        for (WebElement actualSubMenu : subSupportMenus) {
            Assert.assertTrue(actualSubMenu.isDisplayed());
            Assert.assertEquals(actualSubMenu.getText(), expectedSupportSubMenus[i++]);
        }
    }

    @Test
    public void testWidgetsThreeColorTypeExists() {
        final String[] expectedWidgetsThreeColor = {"brown", "blue", "gray"};

        openBaseURL();
        scrollToPageBottom();
        click20(BY_WIDGET_CONTROL_LINK_ON_MAIN_PAGE);

        waitElementToBeVisible(BY_LIST_OF_TYPE_WIDGETS_IMG);
        List<WebElement> actualListWebElementsTypeWidgetsRbtn = getListOfElements(BY_LIST_OF_TYPE_WIDGETS_RADIOBTN);

        int i = 0;
        for (WebElement actualWebElementTypeWidgetsRbtn : actualListWebElementsTypeWidgetsRbtn) {
            Assert.assertEquals(actualWebElementTypeWidgetsRbtn.getAttribute("id"),
                                 String.format("widget-%s", expectedWidgetsThreeColor[i++]));
        }
    }
}