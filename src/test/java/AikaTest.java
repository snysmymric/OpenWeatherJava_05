import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

public class AikaTest extends BaseTest {

    private final By SUPPORT_MENU = By.id("support-dropdown");
    private final By SUPPORT_DROPDOWN = By.xpath("//ul[@id='support-dropdown-menu']");
    private final By SUPPORT_DROPDOWN_LIST = By.xpath("//ul[@id='support-dropdown-menu']/li");
    private final By FAQ_BTN_DROPDOWN = By.xpath("//ul[@id ='support-dropdown-menu']//a[text()='FAQ']");
    private final By FAQ_PAGE_H1 = By.xpath("//main//h1");

    @Test
    public void testSupportMenuAndDropdownThreeSubmenuExist() {
        final String dropdownVisibility = "dropdown-visible";
        int expectedDropdownSize = 3;

        openBaseURL();
        click(SUPPORT_MENU);

        Assert.assertTrue(getDriver().findElement(SUPPORT_DROPDOWN)
                .getAttribute("class").contains(dropdownVisibility));
        Assert.assertEquals(getListSize(SUPPORT_DROPDOWN_LIST), expectedDropdownSize);

        click(SUPPORT_MENU);

        Assert.assertFalse(getDriver().findElement(SUPPORT_DROPDOWN)
                .getAttribute("class").contains(dropdownVisibility));
    }

    @Test
    public void testWhenClickedOnFAQItRedirectsToFAQPage() {

        openBaseURL();

        click(SUPPORT_MENU);
        click(FAQ_BTN_DROPDOWN);

        String FAQpage_H1 = getText(FAQ_PAGE_H1);
        String expectedFAQpage_H1 = "Frequently Asked Questions";

        Assert.assertEquals(FAQpage_H1, expectedFAQpage_H1);
    }
}
