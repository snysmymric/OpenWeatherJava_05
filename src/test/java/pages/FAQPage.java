package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class FAQPage extends FooterMenuPage {

    @FindBy(className = "breadcrumb__leaf")
    private WebElement frequentlyAskedQuestionsHeader;

    @FindBy(xpath = "//div[@class='col-sm-12']/section/h3")
    private List<WebElement> H3HeadersList;

    public FAQPage(WebDriver driver) {
        super(driver);
    }

    public String getFrequentlyAskedQuestionsHeader() {

        return getText(frequentlyAskedQuestionsHeader);
    }

    public int getH3HeadersAmount() {

        return getListSize(H3HeadersList);
    }
}
