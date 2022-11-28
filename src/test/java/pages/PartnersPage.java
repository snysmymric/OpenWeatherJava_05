package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PartnersPage extends FooterMenuPage {

    @FindBy(xpath = "//div[@class='col-sm-7']/h1")
    private WebElement searchBreadcrumbTitle;

    @FindBy(xpath = "//a[@href='#apache']")
    private WebElement apacheCamelHyperlink;

    @FindBy(xpath = "//a[@href='http://camel.apache.org/weather.html']")
    private WebElement seeOnWebsiteButton;

    public PartnersPage(WebDriver driver) {
        super(driver);
    }

    public String getText_InnerText() {

        return getText(searchBreadcrumbTitle);
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
}
