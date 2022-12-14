package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomeDashBoardTriggersPage extends FooterMenuPage {

    @SuppressWarnings("unused")
    public HomeDashBoardTriggersPage(WebDriver driver) {
        super(driver);
    }

    public HomeDashBoardTriggersPage(WebDriver driver, String originalHandle) {
        super(driver, originalHandle);
    }

    public HomeDashBoardTriggersPage clickToDeleteTrigger() {
        List<WebElement> deleteButtons = getDriver().findElements(
                By.xpath("//div[@class='d-lg-flex d-none text-nowrap col-lg-1']//button[@title='Delete']"));
        deleteButtons.forEach(button -> {
            click(button);
            clickToConfirmDeleteTrigger();
        });
        return this;
    }

    public HomeDashBoardTriggersPage clickToConfirmDeleteTrigger() {
        click(getDriver().findElement(
                By.xpath("//div[@class='text-end']//button[contains(text(),'Delete')]")));
        return this;
    }
}