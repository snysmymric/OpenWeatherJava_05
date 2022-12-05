package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomeMarketplacePage extends HomeFooterMenuPage {

    @FindBy(className = "button-round")
    private List<WebElement> allButtons;

    @FindBy(xpath = "//ul[@id='desktop-menu']//a[@href='/history_bulks/new']")
    private WebElement historyBulkMenu;

    public HomeMarketplacePage(WebDriver driver) {
        super(driver);
    }

    public HomeMarketplacePage switchToMarketplaceWindow() {
        switchToAnotherWindow();

        return this;
    }

    public List<WebElement> getAllButtons() {

        return allButtons;
    }

    public boolean areAllButtonsVisibleAndClickable() {

        return allElementsVisibleAndClickable(allButtons);
    }

    public HomeHistoryBulksNewPage clickHistoryBulkMenu() {
        click(historyBulkMenu);

        return new HomeHistoryBulksNewPage(getDriver());
    }
}
