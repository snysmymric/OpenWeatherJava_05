package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FAQPage extends FooterMenuPage {

    @FindBy(xpath = "//div/ol/li[@class='breadcrumb__leaf']")
    private WebElement breadCrumbsFAQ;

    public FAQPage(WebDriver driver) {
        super(driver);
    }

    public String getTextBreadCrumbsFAQ() {

        return getText(breadCrumbsFAQ);
    }
}
