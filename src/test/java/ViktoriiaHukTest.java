import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class ViktoriiaHukTest extends BaseTest {
    private static final By DESKTOP_MENU_API = By.linkText("API");
    private static final By DESKTOP_MENU_PARTNERS = By.xpath("//div[@id='desktop-menu']//a[@href = '/examples']");
    private static final By ORANGE_BUTTON_IN_API_MENU = By.xpath("//a[contains(@class, 'orange')]");
    private static final By HYPER_LINK_JAVA_IN_MENU_PARTNERS = By.linkText("Java");
    private static final By BUTTON_VIEW_TO_HIT_HUB_IN_JAVA = By.xpath("//section [@id='java']/a");
    private static final By HYPER_LINK_LIST_IN_MENU_PARTNERS = By.xpath("//div[@class = 'doc-container']//ul//a");

    private List<String> getExpectedListHyperlinksInMenuPartners() {
        List<String> expectedMenu = new ArrayList<>();
        expectedMenu.add("Google Weather-Based Campaign Management with OpenWeatherMap API");
        expectedMenu.add("Google Maps JavaScript API based on OpenWeatherMap API");
        expectedMenu.add("OpenWeather current weather data in Mozilla's IoT project");
        expectedMenu.add("Ubuntu");
        expectedMenu.add("Android");
        expectedMenu.add("Leaflet");
        expectedMenu.add("Java");
        expectedMenu.add("Go (golang)");
        expectedMenu.add("JavaScript");
        expectedMenu.add("CMS");
        expectedMenu.add("Raspberry Pi");
        expectedMenu.add("Python");
        expectedMenu.add("PHP");
        expectedMenu.add("Apache Camel");
        expectedMenu.add("Desktop");
        expectedMenu.add("Mobile applications");
        expectedMenu.add("Big library on GitHub");
        return expectedMenu;
    }

    @Test
    public void testWenPageApiHas30OrangeButten() {
        final int expectedResult = 30;

        openBaseURL();
        click(DESKTOP_MENU_API);

        int actualResult = getDriver().findElements(ORANGE_BUTTON_IN_API_MENU).size();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testVerifyActiveButtonViewOnGitHubAndRedirectionToGitHubFromJavaHyperlinkOnPartnersPage() {
        final String expectedResult = "https://github.com/migtavares/owmClient";

        openBaseURL();
        click(DESKTOP_MENU_PARTNERS);
        click(HYPER_LINK_JAVA_IN_MENU_PARTNERS);
        click(BUTTON_VIEW_TO_HIT_HUB_IN_JAVA);
        switchToAnotherWindow(getDriver());

        Assert.assertEquals(getDriver().getCurrentUrl(), expectedResult);
    }

    @Test
    public void testVerifyListHyperlinksOnPartnersPage() {
        List<String> expectedList = getExpectedListHyperlinksInMenuPartners();

        openBaseURL();
        click(DESKTOP_MENU_PARTNERS);

        List<String> actualList = getListText(HYPER_LINK_LIST_IN_MENU_PARTNERS);

        Assert.assertEquals(actualList, expectedList);
    }
}
