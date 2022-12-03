package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FAQPage extends FooterMenuPage {

    @FindBy(className = "breadcrumb__leaf")
    private WebElement frequentlyAskedQuestionsHeader;

    public FAQPage(WebDriver driver) {
        super(driver);
    }

    public String getFrequentlyAskedQuestionsHeader() {

        return getText(frequentlyAskedQuestionsHeader);
    }
}
