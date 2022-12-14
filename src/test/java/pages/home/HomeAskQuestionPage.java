package pages.home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base_abstract.FooterMenuPage;

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

    @FindBy(xpath = "//input[@id = 'question_form_is_user_true']")
    private WebElement yesRadioButton;

    @FindBy(xpath = "//input[@id = 'question_form_is_user_false']")
    private WebElement noRadioButton;

    @FindBy(xpath = "//input[@id='question_form_email']/following-sibling::span[@class = 'help-block']")
    private WebElement emailHelpBlock;

    @FindBy(xpath = "//select[@id = 'question_form_subject']/following-sibling::span[@class = 'help-block']")
    private WebElement subjectHelpBlock;

    @FindBy(xpath = "//select[@id = 'question_form_subject']//option[@value= 'Sales']")
    private WebElement salesSubjectSelect;

    @FindBy(xpath = "//select[@id = 'question_form_subject']//option[@value= 'Data Issue']")
    private WebElement dataIssueSubjectSelect;

    @FindBy(xpath = "//select[@id = 'question_form_subject']//option[@value= 'Tech Issue']")
    private WebElement techIssueSubjectSelect;

    @FindBy(xpath = "//select[@id = 'question_form_subject']//option[@value= 'Initiatives']")
    private WebElement initiativesSubjectSelect;

    @FindBy(xpath = "//select[@id = 'question_form_subject']//option[@value= 'Other']")
    private WebElement otherSubjectSelect;

    @FindBy(xpath = "//textarea[@id = 'question_form_message']/following-sibling::span[@class = 'help-block']")
    private WebElement messageHelpBlock;

    @FindBy(xpath = "//div[@id='rc-anchor-container']")
    private WebElement captchaContainer;

    @FindBy(xpath = "//div[@class = 'recaptcha-checkbox-checkmark']")
    private WebElement captchaCkeckbox;

    @FindBy(xpath = "//div[@class = 'has-error']/div[@class = 'help-block']")
    private WebElement captchaHelpBlock;

    @FindBy(xpath = "//div[@id='prompt']")
    private WebElement enterYourAccountMessage;

    @FindBy(xpath = "//div[@class = 'panel-body']")
    private WebElement questionSentMessage;

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

    public HomeAskQuestionPage clickYesRadioButton() {
        click(yesRadioButton);

        return new HomeAskQuestionPage(getDriver());
    }

    public HomeAskQuestionPage selectSubject() {
        click(subjectTextbox);

        return new HomeAskQuestionPage(getDriver());
    }

    public HomeAskQuestionPage selectTechQuestionsInSubjectSubmenu() {
        click(subjectTextbox);
        click(techIssueSubjectSelect);

        return new HomeAskQuestionPage(getDriver());
    }


    public String getRadioButtonText() {

        return getText(enterYourAccountMessage);
    }

}
