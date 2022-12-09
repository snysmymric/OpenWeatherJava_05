package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Alina Syniagina
 * Dashboard events page.
 */
public class HomeDashBoardEventsPage extends FooterMenuPage {

    public static final String EXPECTED_TEXT_WHEN_NO_TRIGGERS = "Here you will find notifications if the conditions of the triggers you created will be met.\n" +
            "Please create a trigger so that we can notify you if ";

    @FindBy(xpath = "//button[@class='button-active shadow-none']")
    private WebElement createNewTrigger;

    @FindBy(xpath = "//div[@class='col']//p")
    private WebElement textWhenNoTriggers;

    @FindBy(xpath = "//a[contains(text(),'To triggers')]")
    private WebElement toTriggersButton;

    public HomeDashBoardEventsPage(WebDriver driver) {
        super(driver);
    }

    public HomeDashBoardEventsPage switchToEventsDashBoardWindow() {
        switchToAnotherWindow();

        return this;
    }

    public HomeDashBoardNewTriggerPage clickCreateNewTriggerButton() {
        click(createNewTrigger);

        return new HomeDashBoardNewTriggerPage(getDriver());
    }

    public HomeDashBoardEventsPage deleteTriggers() {
        String bodyText = getDriver().findElement(By.tagName("body")).getText();
        if (bodyText.contains(EXPECTED_TEXT_WHEN_NO_TRIGGERS)) {
            return this;
        }
        clickToTriggersButton()
                .clickToDeleteTrigger()
                .clickToConfirmDeleteTrigger();

        return this;
    }

    public HomeDashBoardTriggersPage clickToTriggersButton() {
        click(toTriggersButton);

        return new HomeDashBoardTriggersPage(getDriver());
    }
}