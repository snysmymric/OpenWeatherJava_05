package pages.home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base_abstract.FooterMenuPage;

import java.util.List;

public class HomeAPIKeysPage extends FooterMenuPage {

    @FindBy(id = "api_key_form_name")
    private WebElement inputAPIKeysName;

    @FindBy(xpath = "//tbody/tr[1]/td[2]")//"//tbody/tr/td[contains(text(),'Default')]"
    private WebElement defaultAPIKeyName;

    @FindBy(xpath = "//input[@class='button-round dark']")
    private WebElement generateButton;

    @FindBy(xpath = " //div[contains(text(),'API key was created successfully')]")
    private WebElement apiKeysCreatedConfirmationMessage;

    @FindBy(xpath = " //div[contains(text(),'API key was deleted successfully')]")
    private WebElement apiKeysDeletedConfirmationMessage;

    @FindBy(css = ".fa.fa-remove")
    private List<WebElement> xButtons;

    @FindBy(xpath = "//table[@class='material_table api-keys']/tbody/tr")
    private List<WebElement> apiKeys;

    @FindBy(xpath = "//table[@class='material_table api-keys']/tbody/tr/td[2]")
    private List<WebElement> apiKeysNames;

    @FindBy(xpath = "//table[@class='material_table api-keys']/tbody/tr/td[3]")
    private List<WebElement> apiKeysStatuses;

    public HomeAPIKeysPage(WebDriver driver) {
        super(driver);
    }
    public HomeAPIKeysPage clickGenerateKeysButton() {
        click(generateButton);

        return this;
    }

    public String confirmMessageAppears() {
        return getText(apiKeysCreatedConfirmationMessage);
    }

    public HomeAPIKeysPage clickAPIKeyNameField() {
        click(inputAPIKeysName);

        return this;
    }

    public String confirmationMessageDeleted() {
        return getText(apiKeysDeletedConfirmationMessage);
    }

    public HomeAPIKeysPage inputNewName(String text) {
        input(text, inputAPIKeysName);

        return this;
    }

    public int getAmountOfExistingAPIKeys() {
        return apiKeys.size();
    }

    public String getLastAPIKeyName() {
        return getText(apiKeysNames.get(apiKeysNames.size() - 1));
    }

    public String getLastAPIKeyStatus() {
        return getText(apiKeysStatuses.get(apiKeysStatuses.size() - 1));
    }

    public HomeAPIKeysPage deleteLastAPIKey() {
        xButtons.get(xButtons.size() - 1).click();

        return this;
    }

    public HomeAPIKeysPage confirmAPIKeyDeletion() {
        getDriver().switchTo().alert().accept();

        return this;
    }
}
