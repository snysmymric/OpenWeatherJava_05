import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.List;

@Ignore
public class DenSebrovskyTest extends BaseTest {

    @Test
    public void testFindThirtyOrangeButtons () throws InterruptedException {
        int expectedresult = 30;

        getDriver().get("https://openweathermap.org/");
        Thread.sleep(10000);
        getDriver().findElement(By.xpath(
                "//nav[@id = 'nav-website']//div[@id = 'desktop-menu']//a[@href = '/api']")).click();
        List<WebElement> orangeButtonsList = getDriver().findElements(By.xpath(
                "//a[contains(@class, 'orange')]"));

        Assert.assertEquals(expectedresult, orangeButtonsList.size());
    }

    @Test
    public void testButtonsInSupportMenuWhenOpening() throws InterruptedException {
        int expectedSubmenuButtonsAmount = 3;
        String expectedResultButton1 = "FAQ";
        String expectedResultButton2 = "How to start";
        String expectedResultButton3 = "Ask a question";

        getDriver().get("https://openweathermap.org/");
        Thread.sleep(10000);
        getDriver().findElement(By.xpath("//div[@id = 'support-dropdown']")).click();
        Thread.sleep(3000);

        int actualSubmenuButtonsAmount = getDriver().findElements(By.xpath(
                "//ul[@id = 'support-dropdown-menu']/*")).size();
        WebElement FAQButton = getDriver().findElement(By.xpath(
                "//ul[@id = 'support-dropdown-menu']//a[@href = '/faq']"));
        WebElement HowToStartButton = getDriver().findElement(By.xpath(
                "//ul[@id = 'support-dropdown-menu']//a[@href = '/appid']"));
        WebElement AskAQuestionButton = getDriver().findElement(By.xpath(
                "//ul[@id = 'support-dropdown-menu']//a[@href = 'https://home.openweathermap.org/questions']"));

        Assert.assertEquals(actualSubmenuButtonsAmount, expectedSubmenuButtonsAmount);
        Assert.assertEquals(FAQButton.getText(), expectedResultButton1);
        Assert.assertEquals(HowToStartButton.getText(), expectedResultButton2);
        Assert.assertEquals(AskAQuestionButton.getText(), expectedResultButton3);
    }

}
