package pages.top_menu;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base_abstract.FooterMenuPage;

import java.util.List;

public class PartnersPage extends FooterMenuPage {

    @FindBy(xpath = "//h1[@class='breadcrumb-title']")
    private WebElement partnersPageHeader;

    @FindBy(xpath = "//a[@href='#apache']")
    private WebElement apacheCamellink;

    @FindBy(xpath = "//a[@href='http://camel.apache.org/weather.html']")
    private WebElement seeOnWebsiteButton;

    @FindBy(xpath = "//div[@class = 'doc-container']//li")
    private List<WebElement> rightSideLinks;

    public PartnersPage(WebDriver driver) {
        super(driver);
    }

    public String getPageHeader() {

        return getText(partnersPageHeader);
    }

    public List<String> getRightSideLinksText(){

        return getTexts(rightSideLinks);
    }

    public PartnersPage clickApacheCamelHyperLink() {
        click20(apacheCamellink);

        return this;
    }

    public PartnersPage clickSeeOnWebsiteButton() {
        click20(seeOnWebsiteButton);

        return this;
    }

    public void switchToPartnerWindow() {

        switchToAnotherWindow();
    }

}
