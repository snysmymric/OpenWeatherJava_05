import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;


public class DimaZadrutsiyTest extends BaseTest {

        final static String BASE_URL = "https://openweathermap.org/";

        private void openBaseURL() {getDriver().get(BASE_URL);}
        private void waitForGrayFrameDisappeared() {
            getWait20().until(ExpectedConditions.invisibilityOfElementLocated(
                    By.className("owm-loader-container")));
        }
        private void click(String whereToClick,  WebDriverWait wait) {
            wait.until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath(whereToClick))));
            wait.until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.xpath(whereToClick)))).click();
        }
        private void seeElement(String element){
            getDriver().findElement(By.xpath(element));
        }
        private String getText(String seeLoading, String attribute) {

            return getDriver().findElement(By.xpath(seeLoading)).getAttribute(attribute);
        }
    @Test
    public void testUpdatePage() throws InterruptedException {
        String expectedResult = "Loading";

        openBaseURL();
        waitForGrayFrameDisappeared();

        click("//li[@class='logo']", getWait5());

        seeElement("//div[@aria-label='Loading']");

        String actualResult = getText("//div[@aria-label='Loading']", "aria-label");

        Assert.assertEquals(actualResult, expectedResult);
    }
    @Ignore
    @Test
    public void testIconCurrentLocation() throws InterruptedException {

        String url = "https://openweathermap.org/";

        String cityName = "Norway";
        String expectedResult = "London, GB";

        getDriver().get(url);

        Thread.sleep(10000);
        WebElement findCity = getDriver().findElement(By.xpath("//div//input[@placeholder='Search city']"));
        findCity.click();
        findCity.sendKeys(cityName);
        findCity.sendKeys(Keys.ENTER);

        Thread.sleep(4000);
        WebElement chooseCity = getDriver().findElement(By.xpath("//ul//span[text()='45.787, -87.904']"));
        chooseCity.click();

        Thread.sleep(4000);
        WebElement pressCurrentLocation = getDriver().findElement(By.xpath("//div[@class='control-el']"));
        pressCurrentLocation.click();

        Thread.sleep(7000);
        WebElement actualLocation = getDriver().findElement(By.xpath("//h2[@data-v-3e6e9f12]"));

        String actualResult = actualLocation.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }
}
