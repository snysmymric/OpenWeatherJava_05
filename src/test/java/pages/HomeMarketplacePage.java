package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomeMarketplacePage extends HomeFooterMenuPage {

    @FindBy(className = "button-round")
    private List<WebElement> allButtons;

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

}
