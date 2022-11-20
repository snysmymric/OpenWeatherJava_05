import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import base.BaseTest;

@Ignore
public class YuriiOrmsonTest extends BaseTest {
    final static String BASE_URL = "https://openweathermap.org/";
    final static By A_HREF_PRICE = By.xpath("//div[@id = 'desktop-menu']//a[@href = '/price']");
    final static By A_HREF_CURRENT_PROFESSIONAL_SUBSCRIPTIONS = By.xpath(
            "//main//p//a[@href ='#current' and contains(text(), 'professional subscriptions')]");
    final static By H1 = By.xpath("//h1");
    final static By BTN_BLOCK_TRANSPARENT_ROUND = By.xpath("//a[@class = 'btn_block transparent round']");
    final static By SIGN_IN = By.xpath(
            "//div[@id = 'desktop-menu']//a[@href = 'https://openweathermap.org/home/sign_in']");
    final static By INPUT_GROUP_USER_EMAIL = By.xpath(
            "//div[@class = 'input-group']/input[@id = 'user_email']");
    final static By INPUT_GROUP_USER_PASSWORD = By.xpath(
            "//div[@class = 'input-group']/input[@id = 'user_password']");
    final static By SUBMIT = By.xpath(
            "//div[@class = 'sign-form']/form/input[@data-disable-with = 'Create User']");
    final static By USER_DROPDOWN = By.xpath("//div[@id = 'user-dropdown']");
    final static By A_HREF_HOME = By.xpath("//li[@class = 'with-dropdown user-li']//a[@href = '/home']");
    final static By PASSWORD_FORM_PASSWORD = By.xpath(
            "//div[@class = 'col-sm-8']/input[@id = 'password_form_password']");
    final static By PASSWORD_FORM_PASSWORD_CONFIRMATION = By.xpath(
            "//div[@class = 'col-sm-8']/input[@id = 'password_form_password_confirmation']");
    final static By CHANGE_PASSWORD = By.xpath(
            "//div[@class = 'col-xs-9']//input[@value = 'Change Password']");
    final static By PANEL_BODY = By.xpath("//div[@class = 'panel-body']");
    final static String email = "kihara.karelly@eledeen.org";
    final static String password = "Pa$$w0rd!";
    private void openBaseURL() {
        getDriver().get(BASE_URL);
    }
    private void waitForGrayFrameDisappeared() {
        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("owm-loader-container")));
    }
    private void click(By by,  WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }
    private String getText(By by, WebDriver driver) {
        return driver.findElement(by).getText();
    }
    private int countElements(By by, WebDriver driver) {
        return driver.findElements(by).size();
    }
    private void input(String text, By by, WebDriver driver) {
        driver.findElement(by).sendKeys(text);
    }
    @Test
    public void testH1BreadcrumbTitle_WhenOpenPricingPage() {
        String expectedResult = "Pricing";

        openBaseURL();
        waitForGrayFrameDisappeared();
        click(A_HREF_PRICE,getWait5());
        waitForGrayFrameDisappeared();

        String actualResult = getText(H1,getDriver());

        Assert.assertEquals(actualResult,expectedResult);
    }
    @Test
    public void testUrl_WhenOpenPricingPage() {
        String expectedResult = "https://openweathermap.org/price";

        openBaseURL();
        waitForGrayFrameDisappeared();
        click(A_HREF_PRICE,getWait5());
        waitForGrayFrameDisappeared();

        String actualResult = getDriver().getCurrentUrl();

        Assert.assertEquals(actualResult,expectedResult);
    }
    @Test
    public void testTabTitle_WhenOpenPricingPage() {
        String expectedResult = "Pricing - OpenWeatherMap";

        openBaseURL();
        waitForGrayFrameDisappeared();
        click(A_HREF_PRICE,getWait5());
        waitForGrayFrameDisappeared();

        String actualResult = getDriver().getTitle();

        Assert.assertEquals(actualResult,expectedResult);
    }
    @Test
    public void testScrollToProfessionalCollections_WhenClickOnProfessionalSubscriptions() {
        String expectedResult = "https://openweathermap.org/price#current";

        openBaseURL();
        waitForGrayFrameDisappeared();
        click(A_HREF_PRICE,getWait5());
        waitForGrayFrameDisappeared();
        click(A_HREF_CURRENT_PROFESSIONAL_SUBSCRIPTIONS,getWait5());

        String actualResult = getDriver().getCurrentUrl();

        Assert.assertEquals(actualResult,expectedResult);
    }
    @Test
    public void testBtnBlock_WhenOpenPricingPage() {
        int expectedResult = 19;

        openBaseURL();
        waitForGrayFrameDisappeared();
        click(A_HREF_PRICE,getWait5());
        waitForGrayFrameDisappeared();
        click(A_HREF_CURRENT_PROFESSIONAL_SUBSCRIPTIONS,getWait5());
        int actualResult = countElements(BTN_BLOCK_TRANSPARENT_ROUND,getDriver());

        Assert.assertEquals(actualResult,expectedResult);
    }
    @Test
    public void testChangePasswordSuccessfully_WhenOpenUserSettings() {
        String expectedResult = "Password was changed successfully";
        String newPassword = "Pa$$www0rd!";
        String oldPassword = "Pa$$w0rd!";

        openBaseURL();
        waitForGrayFrameDisappeared();
        click(SIGN_IN, getWait10());
        click(INPUT_GROUP_USER_EMAIL,getWait5());
        input(email,INPUT_GROUP_USER_EMAIL,getDriver());
        click(INPUT_GROUP_USER_PASSWORD,getWait5());
        input(password,INPUT_GROUP_USER_PASSWORD,getDriver());
        click(SUBMIT,getWait10());
        click(USER_DROPDOWN,getWait10());
        click(A_HREF_HOME,getWait5());
        click(PASSWORD_FORM_PASSWORD,getWait5());
        input(newPassword,PASSWORD_FORM_PASSWORD,getDriver());
        click(PASSWORD_FORM_PASSWORD_CONFIRMATION,getWait5());
        input(newPassword,PASSWORD_FORM_PASSWORD_CONFIRMATION,getDriver());
        click(CHANGE_PASSWORD,getWait5());

        String actualResult = getText(PANEL_BODY,getDriver());

        Assert.assertEquals(actualResult,expectedResult);

        click(PASSWORD_FORM_PASSWORD,getWait5());
        input(oldPassword,PASSWORD_FORM_PASSWORD,getDriver());
        click(PASSWORD_FORM_PASSWORD_CONFIRMATION,getWait5());
        input(oldPassword,PASSWORD_FORM_PASSWORD_CONFIRMATION,getDriver());
        click(CHANGE_PASSWORD,getWait5());
    }
}
