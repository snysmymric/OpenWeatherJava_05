package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomeAskQuestionPage extends FooterMenuPage {

    @FindBy(id = "question_form_email")
    private WebElement emailTextbox;

    @FindBy(id = "question_form_subject")
    private WebElement subjectTextbox;

    @FindBy(id = "question_form_message")
    private WebElement messageTextbox;

    @FindBy(xpath = "//div[@class='col-sm-8']//input[@type='submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//div[@class='help-block']")
    private WebElement errorMessage;

    public HomeAskQuestionPage(WebDriver driver) {
        super(driver);
    }

    public HomeAskQuestionPage inputTextInEmailTextbox(String text) {
        input(text, emailTextbox);
        return new HomeAskQuestionPage(getDriver());
    }

    public HomeAskQuestionPage selectSubject(String text) {
        selectOption(subjectTextbox, text);
        return new HomeAskQuestionPage(getDriver());
    }

    public HomeAskQuestionPage inputTextInMessageTextbox(String text) {
        input(text, messageTextbox);
        return new HomeAskQuestionPage(getDriver());
    }

    public HomeAskQuestionPage clickOnSubmitButton(){
        click(submitButton);
        return new HomeAskQuestionPage(getDriver());
    }

    public String getErrorMessageText(){
        return getText(errorMessage);
    }
}
