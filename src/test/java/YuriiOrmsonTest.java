import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

import java.util.ArrayList;
import java.util.List;

public class YuriiOrmsonTest extends BaseTest {
    private final By A_HREF_PRICE = By.xpath("//div[@id = 'desktop-menu']//a[@href = '/price']");
    private final By A_HREF_CURRENT_PROFESSIONAL_SUBSCRIPTIONS = By.xpath(
            "//main//p//a[@href ='#current' and contains(text(), 'professional subscriptions')]");
    private final By H1 = By.xpath("//h1");
    private final By BTN_BLOCK_TRANSPARENT_ROUND = By.xpath("//a[@class = 'btn_block transparent round']");
    private final By SIGN_IN = By.xpath(
            "//div[@id = 'desktop-menu']//a[@href = 'https://openweathermap.org/home/sign_in']");
    private final By INPUT_GROUP_USER_EMAIL = By.xpath(
            "//div[@class = 'input-group']/input[@id = 'user_email']");
    private final By INPUT_GROUP_USER_PASSWORD = By.xpath(
            "//div[@class = 'input-group']/input[@id = 'user_password']");
    private final By SUBMIT = By.xpath(
            "//div[@class = 'sign-form']/form/input[@data-disable-with = 'Create User']");
    private final By USER_DROPDOWN = By.xpath("//div[@id = 'user-dropdown']");
    private final By A_HREF_HOME = By.xpath("//li[@class = 'with-dropdown user-li']//a[@href = '/home']");
    private final By PASSWORD_FORM_PASSWORD = By.xpath(
            "//div[@class = 'col-sm-8']/input[@id = 'password_form_password']");
    private final By PASSWORD_FORM_PASSWORD_CONFIRMATION = By.xpath(
            "//div[@class = 'col-sm-8']/input[@id = 'password_form_password_confirmation']");
    private final By CHANGE_PASSWORD = By.xpath(
            "//div[@class = 'col-xs-9']//input[@value = 'Change Password']");
    private final By PANEL_BODY = By.xpath("//div[@class = 'panel-body']");
    private final By ALERTS_H2 = By.xpath("//section[@id = 'alerts']/h2");
    private final By SPECIAL_PRODUCTS_SUB_HEADERS = By.xpath(
            "//section[@id = 'alerts']//tbody/tr/th/h4/a");
    private final By SPECIAL_PRODUCTS_GET_REQUEST = By.xpath(
            "//section[@id = 'alerts']//tr/th[2]/h4[contains(text(), 'By request')]");
    private final By SOLAR_RADIATION_API_BODY_PSTYLE_1 = By.xpath(
            "//section[@id = 'alerts']//tbody/tr[1]/th[1]/p[1]");
    private final By SOLAR_RADIATION_API_BODY_PSTYLE_2 = By.xpath(
            "//section[@id = 'alerts']//tbody/tr[1]/th[1]/p[2]");

    static private List<String> getSpecialProductsSubHeaders() {
        List<String> expectedList = new ArrayList<>();
        expectedList.add("Solar Radiation API");
        expectedList.add("Solar Radiation API - Historical data");
        expectedList.add("Global Weather Alerts Push notifications");
        expectedList.add("Road Risk API (advanced configuration)");
        expectedList.add("Global Precipitation Map - Forecast and historical data");
        expectedList.add("Weather Maps 2.0 with 1-hour step");

        return expectedList;
    }

    static private List<String> getSpecialProductsByRequestHeaders() {
        List<String> expectedList = new ArrayList<>();
        expectedList.add("By request");
        expectedList.add("By request");
        expectedList.add("By request");
        expectedList.add("By request");

        return expectedList;
    }

    @Test
    public void testH1BreadcrumbTitle_WhenOpenPricingPage() {
        String expectedResult = "Pricing";

        openBaseURL();

        click(A_HREF_PRICE);
        waitForGrayContainerDisappeared();

        String actualResult = getText(H1);

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testUrl_WhenOpenPricingPage() {
        String expectedResult = "https://openweathermap.org/price";

        openBaseURL();

        click(A_HREF_PRICE);
        waitForGrayContainerDisappeared();

        String actualResult = getDriver().getCurrentUrl();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testTabTitle_WhenOpenPricingPage() {
        String expectedResult = "Pricing - OpenWeatherMap";

        openBaseURL();

        click(A_HREF_PRICE);
        waitForGrayContainerDisappeared();

        String actualResult = getDriver().getTitle();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testScrollToProfessionalCollections_WhenClickOnProfessionalSubscriptions() {
        String expectedResult = "https://openweathermap.org/price#current";

        openBaseURL();

        click(A_HREF_PRICE);
        waitForGrayContainerDisappeared();
        click(A_HREF_CURRENT_PROFESSIONAL_SUBSCRIPTIONS);

        String actualResult = getDriver().getCurrentUrl();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testBtnBlock_WhenOpenPricingPage() {
        int expectedResult = 19;

        openBaseURL();

        click(A_HREF_PRICE);
        waitForGrayContainerDisappeared();
        click(A_HREF_CURRENT_PROFESSIONAL_SUBSCRIPTIONS);
        int actualResult = countElements(BTN_BLOCK_TRANSPARENT_ROUND);

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testChangePasswordSuccessfully_WhenOpenUserSettings() {
        String expectedResult = "Password was changed successfully";
        String newPassword = "Pa$$www0rd!";
        String oldPassword = "Pa$$w0rd!";
        String email = "kihara.karelly@eledeen.org";
        String password = "Pa$$w0rd!";

        openBaseURL();

        click20(SIGN_IN);
        click20(INPUT_GROUP_USER_EMAIL);
        input(email, INPUT_GROUP_USER_EMAIL);
        click20(INPUT_GROUP_USER_PASSWORD);
        input(password, INPUT_GROUP_USER_PASSWORD);
        click(SUBMIT);
        click20(USER_DROPDOWN);
        click(A_HREF_HOME);
        click(PASSWORD_FORM_PASSWORD);
        input(newPassword, PASSWORD_FORM_PASSWORD);
        click(PASSWORD_FORM_PASSWORD_CONFIRMATION);
        input(newPassword, PASSWORD_FORM_PASSWORD_CONFIRMATION);
        click(CHANGE_PASSWORD);

        String actualResult = getText(PANEL_BODY);

        Assert.assertEquals(actualResult, expectedResult);

        click(PASSWORD_FORM_PASSWORD);
        input(oldPassword, PASSWORD_FORM_PASSWORD);
        click(PASSWORD_FORM_PASSWORD_CONFIRMATION);
        input(oldPassword, PASSWORD_FORM_PASSWORD_CONFIRMATION);
        click(CHANGE_PASSWORD);
    }

    @Test
    public void testH2AlertsAnchorEl_WhenOpenPricingPage() {
        String expectedResult = "Special products";

        openBaseURL();

        click(A_HREF_PRICE);
        waitForGrayContainerDisappeared();

        String actualResult = getText(ALERTS_H2);

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testSectionIdAlertsAnchorEl_SubHeadersOfSection() {
        List<String> expectedList = getSpecialProductsSubHeaders();

        openBaseURL();

        click(A_HREF_PRICE);
        waitForGrayContainerDisappeared();

        List<String> actualList = getListText(SPECIAL_PRODUCTS_SUB_HEADERS);

        Assert.assertEquals(actualList, expectedList);
    }

    @Test
    public void testSectionIdAlertsAnchorEl_countByRequest() {
        int expectedResult = 4;

        openBaseURL();

        click(A_HREF_PRICE);
        waitForGrayContainerDisappeared();

        int actualResult = countElements(SPECIAL_PRODUCTS_GET_REQUEST);

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testSectionIdAlertsAnchorEl_GetRequestHeaders() {
        List<String> expectedList = getSpecialProductsByRequestHeaders();

        openBaseURL();

        click(A_HREF_PRICE);
        waitForGrayContainerDisappeared();

        List<String> actualList = getListText(SPECIAL_PRODUCTS_GET_REQUEST);

        Assert.assertEquals(actualList, expectedList);
    }

    @Test
    public void testSolarRadiationAPI_Body_H4PstyleFirst() {
        String expectedResult = "was developed to evaluate solar energy performance at any point on the globe";

        openBaseURL();

        click(A_HREF_PRICE);
        waitForGrayContainerDisappeared();

        String actualResult = getText(SOLAR_RADIATION_API_BODY_PSTYLE_1);

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testSolarRadiationAPI_Body_H4PstyleSecond() {
        String expectedResult = "Current and forecast solar radiation data\n" +
                "Unlimited locations around the globe\n" +
                "1,000 API calls per day";

        openBaseURL();

        click(A_HREF_PRICE);
        waitForGrayContainerDisappeared();

        String actualResult = getText(SOLAR_RADIATION_API_BODY_PSTYLE_2);

        Assert.assertEquals(actualResult, expectedResult);
    }
}