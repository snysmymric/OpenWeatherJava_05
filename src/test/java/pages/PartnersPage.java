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

    public String getTitle() {

        return getDriver().getTitle();
    }

    public String getCurrentURL() {

        return getDriver().getCurrentUrl();
    }

    public String getText_InnerText() {

        return getText(searchBreadcrumbTitle);
    }

    public PartnersPage waitElementToBeVisible() {
        wait20ElementToBeVisible(searchBreadcrumbTitle);

        return this;
    }

    public PartnersPage clickApacheCamelHyperLink() {
        click20(apacheCamelHyperlink);

        return this;
    }

    public PartnersPage clickOnSeeOnWebsiteButton() {
        click20(seeOnWebsiteButton);

        return this;
    }

    public PartnersPage switchToAnotherWindow() {
        switchToAnotherWindow(getDriver());

        return this;
    }
}
