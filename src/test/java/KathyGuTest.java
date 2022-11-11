import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class KathyGuTest extends BaseTest {
    @Test

    public void testFaqHowToStartAskQuestion_InSupportMenu_first () throws InterruptedException {

        String url = "https://openweathermap.org";
        String textFaq = "FAQ";
        String textHowToStart = "How to start";
        String textAskQuestion = "Ask a question";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement menuSupport = getDriver().findElement(By.id("support-dropdown"));
        menuSupport.click();

        Assert.assertEquals(
                getDriver().findElements(By.xpath("//ul[@id = 'support-dropdown-menu']/li"))
                        .size(), 3);

        Assert.assertTrue(
                getDriver().findElement(
                        By.xpath("//ul[@id = 'support-dropdown-menu']/li/a[text() = '" + textFaq + "']"
                        )
                ).isDisplayed()
        );

        Assert.assertTrue(
                getDriver().findElement(
                        By.xpath("//ul[@id = 'support-dropdown-menu']/li/a[text() = '" + textHowToStart + "']"
                        )
                ).isDisplayed()
        );

        Assert.assertTrue(
                getDriver().findElement(
                        By.xpath("//ul[@id = 'support-dropdown-menu']/li/a[text() = '" + textAskQuestion + "']"
                        )
                ).isDisplayed()
        );
    }
}
