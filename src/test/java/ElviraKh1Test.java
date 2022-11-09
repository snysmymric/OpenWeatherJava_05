import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.List;

public class ElviraKh1Test extends BaseTest {
    private final static String BASE_URL = "https://openweathermap.org/";

    @Test
    public void testSupportSubMenu() throws InterruptedException {
        final int cntSubSupportMenu = 3;
        final String expectedSupportMenu = "Support";
        final String[] expectedSupportSubMenus = {"FAQ", "How to start", "Ask a question"};

        getDriver().get(BASE_URL);
        Thread.sleep(7000);

        WebElement supportMenu = getDriver().findElement(By.xpath("//div[@id='support-dropdown']"));
        supportMenu.click();
        Thread.sleep(7000);
        List<WebElement> subSupportMenus = getDriver().findElements(By.xpath("//ul[@id='support-dropdown-menu']//li"));

        Assert.assertEquals(supportMenu.getText(), expectedSupportMenu);
        Assert.assertEquals(subSupportMenus.size(), cntSubSupportMenu);
        int i = 0;
        for (WebElement actualSubMenu : subSupportMenus) {
            Assert.assertTrue(actualSubMenu.isDisplayed());
            Assert.assertEquals(actualSubMenu.getText(), expectedSupportSubMenus[i++]);
        }
    }
}
