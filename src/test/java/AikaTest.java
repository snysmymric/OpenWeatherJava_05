//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.testng.Assert;
//import org.testng.annotations.Ignore;
//import org.testng.annotations.Test;
//import base.BaseTest;
//
//import java.util.List;
//
//@Ignore
//public class AikaTest extends BaseTest {
//
//    final static String BASE_URL = "https://openweathermap.org/";
//    final static By SUPPORT_MENU = By.id("support-dropdown");
//    final static By SUPPORT_DROPDOWN = By.xpath("//ul[@id='support-dropdown-menu']");
//
//    private void openBaseURL() {
//        getDriver().get(BASE_URL);
//    }
//
//    private void waitForGrayFrameDisappeared() {
//        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(
//                By.className("owm-loader-container")));
//    }
//
//    private void click(By by, WebDriver driver) {
//        driver.findElement(by).click();
//    }
//
//    @Test
//    public void testSupportMenuAndDropdownThreeSubmenuExist() {
//        String dropdownVisibility = "dropdown-visible";
//        int expectedDropdownSize = 3;
//
//        openBaseURL();
//        waitForGrayFrameDisappeared();
//        click(SUPPORT_MENU, getDriver());
//
//        Assert.assertTrue(getDriver().findElement(SUPPORT_DROPDOWN)
//                .getAttribute("class").contains(dropdownVisibility));
//
//        List<WebElement> dropdownSize = getDriver().findElements(
//                By.xpath("//ul[@id='support-dropdown-menu']/li"));
//
//        Assert.assertEquals(dropdownSize.size(), expectedDropdownSize);
//
//        click(SUPPORT_MENU, getDriver());
//
//        Assert.assertFalse(getDriver().findElement(SUPPORT_DROPDOWN)
//                .getAttribute("class").contains(dropdownVisibility));
//    }
//
//    @Test
//    public void testWhenClickedOnFAQItRedirectsToFAQPage() {
//        openBaseURL();
//        waitForGrayFrameDisappeared();
//        click(SUPPORT_MENU, getDriver());
//        click(By.xpath("//ul[@id ='support-dropdown-menu']//a[text()='FAQ']"), getDriver());
//
//        Assert.assertEquals(getDriver().findElement(By.xpath("//main//h1")).getText(),
//                "Frequently Asked Questions");
//    }
//}
