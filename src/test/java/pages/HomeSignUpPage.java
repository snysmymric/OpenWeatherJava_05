package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomeSignUpPage extends FooterMenuPage {

    @FindBy(xpath = "//div[@class='sign-form']//input[@id='user_username']")
    private WebElement usernameField;

    @FindBy(xpath = "//div[@class='sign-form']//input[@id='user_email']")
    private WebElement enterEmail;

    @FindBy(xpath = "//div[@class='sign-form']//input[@id='user_password']")
    private WebElement userPassword;

    @FindBy(xpath = "//div[@class='sign-form']//input[@id='user_password_confirmation']")
    private WebElement repeatPassword;

    @FindBy(xpath = "//div[@class='sign-form']//input[@id='agreement_is_age_confirmed']")
    private WebElement ageConfirmCheckbox;

    @FindBy(xpath = "//div[@class='sign-form']//input[@id='agreement_is_accepted']")
    private WebElement agreementCheckbox;

    @FindBy(xpath = "//div[@class='sign-form']//input[@id='mailing_system']")
    private WebElement mailingSystemCheckbox;

    @FindBy(xpath = "//div[@class='sign-form']//input[@id='mailing_product']")
    private WebElement mailingProductCheckbox;

    @FindBy(xpath = "//div[@class='sign-form']//input[@id='mailing_news']")
    private WebElement mailingNewsCheckbox;

    @FindBy(xpath = "//body[@class='body-orange']")
    private WebElement bodyOrange;

    @FindBy(xpath = "//span[@id='recaptcha-anchor']")
    private WebElement captchaCheckbox;

    @FindBy(xpath = "//div[@class='sign-form']//input[@value='Create Account']")
    private WebElement createAccountButton;

    @FindBy(xpath = "//div[@class='has-error']/div[@class='help-block']")
    private WebElement errorCaptchaMessage;

    public HomeSignUpPage(WebDriver driver) {
        super(driver);
    }

    protected WebElement getCaptchaCheckbox() {

        return captchaCheckbox;
    }

    public HomeSignUpPage scrollByCaptchaCheckbox() {
        scrollByVisibleElement(getCaptchaCheckbox());

        return this;
    }

    public HomeSignUpPage waitCaptchaToBeClickable() {
        wait20ElementToBeClickable(getCaptchaCheckbox());

        return this;
    }

    public HomeSignUpPage clickCaptchaCheckbox() {
        click(captchaCheckbox);
        getWait10();

        return this;
    }

    public HomeSignUpPage clickClearInputNewUsername() {
        click(usernameField);
        usernameField.clear();
        String username = "Tester";
        input(username, usernameField);

        return this;
    }

    public HomeSignUpPage clickClearInputNewUserEmail() {
        click(enterEmail);
        enterEmail.clear();
        String email = "jka59433@xcoxc.com";
        input(email, enterEmail);

        return this;
    }

    public HomeSignUpPage clickClearInputNewUserPassword() {
        click(userPassword);
        userPassword.clear();
        String password = "Tester12#";
        input(password, userPassword);

        return this;
    }

    public HomeSignUpPage clickClearInputRepeatPassword() {
        click(repeatPassword);
        repeatPassword.clear();
        String password = "Tester12#";
        input(password, repeatPassword);

        return this;
    }

    public HomeSignUpPage clickAgeConfirmCheckbox() {
        click(ageConfirmCheckbox);

        return this;
    }

    public HomeSignUpPage clickAgreementCheckbox() {
        click(agreementCheckbox);

        return this;
    }

    public void clickCreateAccountButton() {
        click(createAccountButton);
    }

    public String getErrorCaptchaMessage() {

        return getText(errorCaptchaMessage);
    }
}
