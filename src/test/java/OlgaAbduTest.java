import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.ArrayList;

@Ignore
public class OlgaAbduTest extends BaseTest {

    public static String BASE_URL = "https://openweathermap.org/";
    public static By SEARCH_SUPPORT_BUTTON = By.xpath("//div[@id = 'support-dropdown']");
    public static By OPTION_DROPDOWN_FAQ = By.xpath("//ul[@id='support-dropdown-menu']//a[contains(text(),'FAQ')]");
    public static By OPTION_DROPDOWN_HOW_TO_START = By
            .xpath("//ul[@id='support-dropdown-menu']//a[contains(text(),'How to start')]");
    public static By OPTION_DROPDOWN_ASK_QUESTIONS = By
            .xpath("//ul[@id='support-dropdown-menu']//a[contains(text(),'Ask a question')]");

    public static By HEADER_FAQ_PAGE = By.xpath("//h1[contains(text(),'Frequently Asked Questions')]");
    public static By HEADER_HOW_TO_START_PAGE = By.xpath("//h1[contains(text(),'How to start ')]");

    public static By NEW_QUESTION_FORM = By.xpath("//form[@id='new_question_form']");


    private void openBaseURL() {
        getDriver().get(BASE_URL);
    }

    private void waitForGrayFrameDisappeared() {
        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("owm-loader-container")));
    }


    private void click(By by, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    private void waitElementToBeVisible(By by, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    private void switchToNewWindow() {
        ArrayList<String> newTb = new ArrayList<>(getDriver().getWindowHandles());
        getDriver().switchTo().window(newTb.get(1));

    }


    @Test
    public void testSupportButton() {

        openBaseURL();
        waitForGrayFrameDisappeared();

        click(SEARCH_SUPPORT_BUTTON, getWait5());
        click(OPTION_DROPDOWN_FAQ, getWait5());
        waitElementToBeVisible(HEADER_FAQ_PAGE, getWait5());

        click(SEARCH_SUPPORT_BUTTON, getWait5());
        click(OPTION_DROPDOWN_HOW_TO_START, getWait5());
        waitElementToBeVisible(HEADER_HOW_TO_START_PAGE, getWait5());

        click(SEARCH_SUPPORT_BUTTON, getWait5());
        click(OPTION_DROPDOWN_ASK_QUESTIONS, getWait20());
        switchToNewWindow();
        waitElementToBeVisible(NEW_QUESTION_FORM, getWait20());
    }
}
