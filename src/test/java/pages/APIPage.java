package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class APIPage extends FooterMenuPage {
    @FindBy(xpath = "//a[contains(@class,'orange')]")
    private List<WebElement> orangeButtons;

    public APIPage(WebDriver driver) {
        super(driver);
    }

    public int getOrangeButtonsAmount() {

        return getListSize(orangeButtons);
    }
}
