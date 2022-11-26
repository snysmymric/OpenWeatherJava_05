import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import base.BaseTest;

import java.util.ArrayList;
import java.util.List;

public class ViktoriyaEDTest extends BaseTest {
    private final By SUPPORT_BUTTON = By.xpath("//div[@id='support-dropdown']");
    private final By DIFFERENT_WEATHER_BUTTON = By.xpath("//span[@class='control-el owm-switch']");
    private final By DIFFERENT_WEATHER_POP_UP = By.xpath("//div[@class='pop-up-container']");
    private final By SUPPORT_DROPDOWN = By.xpath("//ul[@id='support-dropdown-menu']");
    private final By SUPPORT_SUBMENUS_LIST = By.xpath("//ul[@id='support-dropdown-menu']/li/a");
    final static By SUPPORT_MENU_FAQ = By.xpath("//ul[@id='support-dropdown-menu']//a[@href='/faq']");
    final static By SUPPORT_MENU_HOW_TO_START = By.xpath("//ul[@id='support-dropdown-menu']//a[@href='/appid']");
    final static By SUPPORT_MENU_ASK_A_QUESTION = By.xpath("//ul[@id='support-dropdown-menu']//a[@target='_blank']");
    final static String[] SUBMENU_NAMES = {"FAQ", "How to start", "Ask a question"};
    final static By WIDGET_BUTTON = By.xpath("//div[@class='section-content']/ul/li/a[@href='/widgets-constructor']");
    final static By YOUR_API_KEY_FIELD = By.id("api-key");
    final static By YOUR_CITY_NAME_FIELD = By.id("city-name");
    final static By SEARCH_CITY_BUTTON = By.id("search-city");

    @Test
    public void test_SupportMenuIsClickable() {
        openBaseURL();
        click(SUPPORT_BUTTON);

        Assert.assertTrue(getTextByAttribute(
                SUPPORT_DROPDOWN, "class").equals("dropdown-menu dropdown-visible"));

        click(SUPPORT_BUTTON);

        Assert.assertTrue(getTextByAttribute(
                SUPPORT_DROPDOWN, "class").equals("dropdown-menu"));
    }

    @Test
    public void test_SupportMenuHas3Submenus() {

        openBaseURL();
        click(SUPPORT_BUTTON);
        List<WebElement> supportSubmenuList = getListOfElements(SUPPORT_SUBMENUS_LIST);

        int listSize = getListSize(SUPPORT_SUBMENUS_LIST);

        Assert.assertTrue(listSize == 3);

        for (int i = 0; i < listSize; i++) {
            Assert.assertTrue(supportSubmenuList.get(i).getText().equals(SUBMENU_NAMES[i]));
        }
    }

    @Test
    public void test_SupportMenuSubmenusAreClickableAndRedirectedToTheRightPage() {

        openBaseURL();
        click20(SUPPORT_BUTTON);
        click20(SUPPORT_MENU_FAQ);

        Assert.assertTrue(getDriver().getCurrentUrl().contains("https://openweathermap.org/faq"));

        click20(SUPPORT_BUTTON);
        click20(SUPPORT_MENU_HOW_TO_START);

        Assert.assertTrue(getDriver().getCurrentUrl().contains("https://openweathermap.org/appid"));

        click20(SUPPORT_BUTTON);
        click20(SUPPORT_MENU_ASK_A_QUESTION);
        List<String> tabs = new ArrayList<>(getDriver().getWindowHandles());

        Assert.assertEquals(tabs.size(), 2);

        getDriver().switchTo().window(tabs.get(1));
        getWait10().until(ExpectedConditions.not(ExpectedConditions.urlMatches(BASE_URL)));

        Assert.assertTrue(getCurrentURL().contains("https://home.openweathermap.org/questions"));
    }

    @Test
    public void test_VerifyPopupWindowIsShownWhenClickingDifferentWeatherButton() {

        openBaseURL();
        click(DIFFERENT_WEATHER_BUTTON);

        Assert.assertTrue(getDriver().findElement(DIFFERENT_WEATHER_POP_UP).isDisplayed());
    }

    @Test
    public void test_WidgetsIsClickableAndRedirectsUserToTheWidgetsPage() {

        final String expectedWidgetsPage = "https://openweathermap.org/widgets-constructor";

        openBaseURL();
        scrollToPageBottom();
        click20(WIDGET_BUTTON);

        Assert.assertTrue(getCurrentURL().contains(expectedWidgetsPage));
    }

    @Test
    public void test_VerifySelectYourCityFieldContainsChosenCity() {

        final String key = "20cbbe5f82ae947874eb39f29f8ffbe1";
        final String city = "Rome";

        openBaseURL();
        scrollToPageBottom();
        click20(WIDGET_BUTTON);

        click(YOUR_API_KEY_FIELD);
        clear(YOUR_API_KEY_FIELD);
        input(key, YOUR_API_KEY_FIELD);


        click(YOUR_CITY_NAME_FIELD);

        String oldCity = getDriver().findElement(By.xpath("//h2[@class='widget-left-menu__header']")).getText();

        clear(YOUR_CITY_NAME_FIELD);
        input(city, YOUR_CITY_NAME_FIELD);
        click20(SEARCH_CITY_BUTTON);

        waitTextToBeChanged(By.xpath("//h2[@class='widget-left-menu__header']"), oldCity);

        List<WebElement> selectYourCityList = getListOfElements(By.xpath("//ul[@id='city-list']//span"));
        Assert.assertTrue(selectYourCityList.size() == 5);

        List<String> cityNameList = getElemntsText(By.xpath("//ul[@id='city-list']//span"));

        for (int i = 0; i < cityNameList.size(); i++) {
            Assert.assertTrue(cityNameList.get(i).contains(city));
        }
    }
}
