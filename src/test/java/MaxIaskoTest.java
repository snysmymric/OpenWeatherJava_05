import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
public class MaxIaskoTest extends BaseTest {

    @Test
    public void testSearch30OrangeButtons() throws InterruptedException {

        final String url = "https://openweathermap.org/";
        final int expectedResult = 30;
        getDriver().get(url);

        Thread.sleep(10000);
        WebElement searchApiPage = getDriver().findElement(
                By.xpath("//div[@id = 'desktop-menu']//a[text()= 'API']"));
        searchApiPage.click();

        int actualResult = getDriver().findElements(
                By.xpath("//a[contains(@class, 'btn_block orange round') " +
                        "or contains(@class, 'ow-btn round btn-orange')]")).size();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testConfirmLinkNoChangeByLogoClick() throws InterruptedException {

        final String url = "https://openweathermap.org/";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement searchLogoField = getDriver().findElement(By.xpath("//a[@href ='/']"));
        searchLogoField.click();

        String actualResult = getDriver().getCurrentUrl();
        Assert.assertEquals(actualResult, url);
    }

    @Test
    public void testConfirmTextInLink() throws InterruptedException {
        final String url = "http://openweathermap.org/";
        final String expectedResult0 = "Rome";
        final String expectedResult1 = "find";

        getDriver().get(url);
        Thread.sleep(7000);

        WebElement searchWhetherInYourCityField = getDriver().findElement(
                By.xpath("//div[@id='desktop-menu']/form/input[@type ='text']"));
        searchWhetherInYourCityField.click();
        searchWhetherInYourCityField.sendKeys(expectedResult0);
        searchWhetherInYourCityField.sendKeys(Keys.ENTER);

        String currentUrl = getDriver().getCurrentUrl();
        Assert.assertEquals(currentUrl.contains(expectedResult0), currentUrl.contains(expectedResult1));

        String mainWindows = getDriver().getWindowHandle();
        for (String windowsHandle : getDriver().getWindowHandles()) {
            if (!mainWindows.contentEquals(windowsHandle)) {
                getDriver().switchTo().window(windowsHandle);
                break;
            }
        }
        WebElement searchFieldText = getDriver().findElement(
                By.xpath("//div/input[@id='search_str']"));

        String actualResult = searchFieldText.getAttribute("value");
        Assert.assertEquals(expectedResult0, actualResult);
    }
}






