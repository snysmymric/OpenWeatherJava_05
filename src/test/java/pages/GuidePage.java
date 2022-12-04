package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GuidePage extends FooterMenuPage {

    @FindBy(xpath = "//div[@class='container']//li/a[@href = '/']")
    private WebElement homeGuideButton;

    public GuidePage(WebDriver driver) {
        super(driver);
    }

    public GuidePage clickHomeGuide() {
        click(homeGuideButton);

        return this;
    }
}
