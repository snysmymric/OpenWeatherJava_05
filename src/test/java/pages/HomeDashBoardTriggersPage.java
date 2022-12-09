package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomeDashBoardTriggersPage extends FooterMenuPage {

    @FindBy(xpath = "//div[@class='d-lg-flex d-none text-nowrap col-lg-1']//button[@title='Delete']")
    private WebElement deleteTrigger;

    @FindBy(xpath = "//div[@class='text-end']//button[contains(text(),'Delete')]")
    private WebElement confirmDeleteTrigger;
    public HomeDashBoardTriggersPage(WebDriver driver) {
        super(driver);
    }

    public HomeDashBoardTriggersPage clickToDeleteTrigger() {
        click(deleteTrigger);

        return this;
    }

    public HomeDashBoardTriggersPage clickToConfirmDeleteTrigger() {
        click(confirmDeleteTrigger);

        return this;
    }
}