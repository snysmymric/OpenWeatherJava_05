package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AboutUsPage extends FooterMenuPage {

    @FindBy(xpath = "//div[@class = 'about-us']//h1")
    private WebElement aboutUsPageHeader;

    public AboutUsPage(WebDriver driver) {
        super(driver);
    }

    public AboutUsPage waitForAboutUsPageHeaderBeVisible(){
        wait10ElementToBeVisible(aboutUsPageHeader);

        return new AboutUsPage(getDriver());
    }
}
