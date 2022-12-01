package old_tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import base.BaseTest;

@Ignore
public class HappyStrawberryTest extends BaseTest {

    final static By MENU_PRICING = By.xpath("//div[@id='desktop-menu']//a[@href='/price']");
    final static By PRICING = By.xpath("//div/div/div[1]/h1[@class='breadcrumb-title']");
    final static By SUPPORT = By.xpath("//div[@id=\"support-dropdown\"]");
    final static By FAQ = By.xpath("//ul[@class=\"dropdown-menu dropdown-visible\"]//a[@href='/faq']");
    private final By SUPPORT_DROPDOWNMENU = By.xpath("//ul[@id='support-dropdown-menu']");
    final static By BREADCRUMBS = By.xpath("//div/ol/li[@class='breadcrumb__leaf']");

    private void letsClickElement (WebDriver driver, By by){
        getDriver().findElement(by).click();
    }



    @Test
    public void testMenuPricingIsClickable() {
        openBaseURL();
        waitForGrayContainerDisappeared();
        letsClickElement(getDriver(),MENU_PRICING);

        Assert.assertEquals(getDriver().findElement(PRICING).getText(), "Pricing");


    }
    @Test
    public void testFAQButtonIsClickable() {
        openBaseURL();
        waitForGrayContainerDisappeared();

        letsClickElement(getDriver(),SUPPORT);
        waitElementToBeVisible(SUPPORT_DROPDOWNMENU);
        Assert.assertTrue(getDriver().findElement(SUPPORT_DROPDOWNMENU).isDisplayed());

        letsClickElement(getDriver(),FAQ);
        Assert.assertEquals(getDriver().findElement(BREADCRUMBS).getText(),
                "Frequently Asked Questions");
    }

}
