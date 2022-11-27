package old_tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

import java.util.List;

public class DenSebrovskyTest extends BaseTest {

    private final By SUPPORT_BUTTON = By.xpath("//div[@id='support-dropdown']");
    private final By SUPPORT_DROPDOWN_MENU = By.xpath("//ul[@id='support-dropdown-menu']");
    private final By SUBMENU_BUTTONS_AMOUNT = By.xpath("//ul[@id='support-dropdown-menu']/*");
    private final By MENU_PRICING_BUTTON = By.xpath("//div[@id='desktop-menu']//a[@href='/price']");
    private final By FAQ_BUTTON = By.xpath("//a[@href='/faq#onecall']");
    private final By FAQ_H3_HEADER = By.xpath("//div[@class='col-sm-12']/section/h3");
    private final By FAQ_CONTAINERS = By.xpath("//p[@class='question-heading']");
    private final By OPENED_FAQ_CONTAINER_FIELD = By.xpath("//div[@class='col-sm-12']//div[@class='question-content']");

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
        int actualH3HeadersAmount = getListSize(FAQ_H3_HEADER);

        Assert.assertEquals(actualH3HeadersAmount, expectedH3HeadersAmount);
    }

    @Test
    public void test_CheckIfAllContainersAreClickable() {
        openBaseURL();
        click(MENU_PRICING_BUTTON);
        click(FAQ_BUTTON);

        List<WebElement> FAQContainers = getListOfElements(FAQ_CONTAINERS);
        List<WebElement> FAQContainersInsideField = getListOfElements(OPENED_FAQ_CONTAINER_FIELD);

        if (FAQContainers.size() == FAQContainersInsideField.size()) {
            for (int i = 0; i < FAQContainers.size(); i++) {
                scrollToElement(FAQContainers.get(i));
                clickByJS(FAQContainers.get(i));
                Assert.assertTrue(FAQContainersInsideField.get(i).isDisplayed());
            }
        } else {
            Assert.fail();
        }
    }
}
