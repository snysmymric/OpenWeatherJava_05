package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PartnersPage extends FooterMenuPage {

    @FindBy(xpath = "//div[@class='col-sm-7']/h1")
    private WebElement h1Header;

    @FindBy(xpath = "//a[@href='#apache']")
    private WebElement apacheCamelHyperlink;

    @FindBy(xpath = "//a[@href='http://camel.apache.org/weather.html']")
    private WebElement seeOnWebsiteButton;

    @FindBy(xpath = "//div[@class = 'doc-container']//li")
    private List<WebElement> rightSideLinks;

    public PartnersPage(WebDriver driver) {
        super(driver);
    }

    public String getPageHeader() {

        return getText(h1Header);
    }

    public PartnersPage clickApacheCamelHyperLink() {
        click20(apacheCamelHyperlink);

        return this;
    }

    public PartnersPage clickSeeOnWebsiteButton() {
        click20(seeOnWebsiteButton);

        return this;
    }

    public void switchToPartnerWindow() {
        switchToAnotherWindow();
    }

    public List<String> getRightSideLinksText(){
        return getTexts(rightSideLinks);
    }
}
