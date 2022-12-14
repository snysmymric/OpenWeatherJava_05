package pages.top_menu;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base_abstract.FooterMenuPage;

import java.util.List;

public class FAQPage extends FooterMenuPage {

    @FindBy(className = "breadcrumb__leaf")
    private WebElement frequentlyAskedQuestionsHeader;

    @FindBy(xpath = "//div[@class='col-sm-12']/section/h3")
    private List<WebElement> H3HeadersList;

    @FindBy(xpath = "//p[@class='question-heading']")
    private List<WebElement> questionsTitle;

    @FindBy(xpath = "//div[@class='question visible']//div[@class='question-content']")
    private WebElement questionsInnerDescription;

    public FAQPage(WebDriver driver) {
        super(driver);
    }

    public String getFrequentlyAskedQuestionsHeader() {

        return getText(frequentlyAskedQuestionsHeader);
    }

    public int getH3HeadersAmount() {

        return getListSize(H3HeadersList);
    }

    public int getOpenedFAQAmount() {
        int openedContainersAmount = 0;

        for (WebElement currentElement : questionsTitle) {
            scrollByVisibleElement(currentElement);
            clickByJavaScript(currentElement);
            if(questionsInnerDescription.isDisplayed()) {
                openedContainersAmount++;
            } else {
                break;
            }
        }

        return openedContainersAmount;
    }
}
