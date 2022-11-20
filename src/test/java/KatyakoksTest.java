import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import base.BaseTest;

import java.util.List;

@Ignore
public class KatyakoksTest extends BaseTest {

    final static String BASE_URL = "https://openweathermap.org/";

    final static By PARTNERS_MENU = By.xpath("//div[@id='desktop-menu']//a[@href='/examples']");
    final static By PARTNERS_AND_SOLUTIONS_H2 = By.xpath("//div//section//h2");
    final static By SUBMENU_LIST = By.xpath("//div[@class='doc-container']/div/nav/ul/li");
    final static By LINK_HEADER = By.xpath("//div[@class='doc-container']/div/nav/ul/li/a[@href]");

    private void openBaseURL() {
        getDriver().get(BASE_URL);
    }

    private void waitForGrayFrameDisappeared() {
        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("owm-loader-container")));
    }

    private String getText(By by, WebDriver driver) {

        return driver.findElement(by).getText();
    }

    private List <WebElement> linksList (By by, WebDriver driver){

        return driver.findElements(by);
    }

    private void click(By by, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    private void waitElementToBeVisible(By by, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    @Test
    public void testSubmenu_PartnersAndSolutionsPage(){
        openBaseURL();
        waitForGrayFrameDisappeared();
        click(PARTNERS_MENU, getWait5());
        waitElementToBeVisible(SUBMENU_LIST, getWait10());

        for (WebElement link : linksList(SUBMENU_LIST, getDriver())) {
            link.click();
            waitElementToBeVisible(PARTNERS_AND_SOLUTIONS_H2, getWait10());
            String actualResult = getText(LINK_HEADER, getDriver());
            String expectedResult = getText(PARTNERS_AND_SOLUTIONS_H2, getDriver());
            Assert.assertEquals(actualResult, expectedResult);
        }
    }
}
