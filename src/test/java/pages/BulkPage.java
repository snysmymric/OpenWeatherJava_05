package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BulkPage extends FooterMenuPage{

    @FindBy(xpath = "//section[@id='how']/h2")
    private WebElement h2Header;

    public BulkPage(WebDriver driver) {

        super(driver);
    }

    public String getH2Header() {

        return getText(h2Header);
    }
}
