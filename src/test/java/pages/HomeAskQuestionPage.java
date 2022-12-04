package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomeAskQuestionPage extends FooterMenuPage {

    @FindBy(xpath = "//h4[@class = 'headline']")
    private WebElement askQuestionPageHeader;

    public HomeAskQuestionPage(WebDriver driver) {
        super(driver);
    }

    public HomeAskQuestionPage waitForAskQuestionPageHeaderBeVisible(){
        wait10ElementToBeVisible(askQuestionPageHeader);

        return new HomeAskQuestionPage(getDriver());
    }
}
