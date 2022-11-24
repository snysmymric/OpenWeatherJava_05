import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import base.BaseTest;

import java.util.ArrayList;
import java.util.List;


public class ElNov686Test extends BaseTest {
    private final By SEARCH_CITY_FIELD = By
            .xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']");
    private final By SEARCH_BUTTON = By.xpath("//div[@id='weather-widget']//button[@type='submit']");
    private final By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
    private final By PARIS_FR_FROM_DROPDOWN_MENU = By
            .xpath("//ul[@class = 'search-dropdown-menu']/li/span[text ()= 'Paris, FR ']");
    private final By H2_CITYNAME_HEADER = By.xpath("//div[@id = 'weather-widget']//h2");
    private final By H3 = By.xpath("//h3");


    @Test
    public void testH2TagTextWhenSearchingCityCountry() {
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        openBaseURL();
        waitForGrayContainerDisappeared();

        String oldH2Header = getText(H2_CITYNAME_HEADER);

        click(SEARCH_CITY_FIELD);
        input(cityName, SEARCH_CITY_FIELD);
        click(SEARCH_BUTTON);
        waitElementToBeVisible(SEARCH_DROPDOWN_MENU);
        click(PARIS_FR_FROM_DROPDOWN_MENU);
        waitTextToBeChanged(H2_CITYNAME_HEADER, oldH2Header);

        String actualResult = getText(H2_CITYNAME_HEADER);

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testTemperatureImperialFahrenheitVerify() {
        String expectedResult = "F";

        openBaseURL();
        waitForGrayContainerDisappeared();

        click(By.xpath("//div[@class='switch-container']//div[text()='Imperial: °F, mph']"));
        String actualResult = getText(By.xpath("//span[@class='heading']"));

        actualResult = actualResult.substring(actualResult.length() - 1);

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testMenuAPI_Verify30orangeButtons() {
        int expectedResult = 30;

        openBaseURL();
        waitForGrayContainerDisappeared();

        click(By.xpath("//div[@id='desktop-menu']//a[normalize-space()='API']"));
        int actualResult = countOrangeButtons(By.xpath("//a[contains(@class, 'btn_block orange round') " +
                "or contains(@class, 'ow-btn round btn-orange')]"));

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testMainNavBarMenuPartnersMenuisclickable() {
        String expectedResult = "Partners and solutions - OpenWeatherMap";

        openBaseURL();
        waitForGrayContainerDisappeared();
        click(By.xpath("//div[@id='desktop-menu']//a[normalize-space()='Partners']"));
        getWait20();

        String actualResult = getDriver().getTitle();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testStartWeatherLocationVerifyLocationButtonShowingDefaultLocation() {
        String expectedResult1 = "Location unavailable. Displaying default location: London";
        String expectedResult2 = "London, GB";

        openBaseURL();
        waitForGrayContainerDisappeared();

        click(By.xpath("//div[@class='control-el']//*[name()='svg']"));

        String actualResult1 = getText(By
                .xpath("//span[contains(text(),'Location unavailable. Displaying default location:')]"));
        String actualResult2 = getText(By
                .xpath("//h2[normalize-space()='London, GB']"));

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
    }

    @Test
    public void testMainSupportHowToStart_CountAllH3() {
        openBaseURL();
        waitForGrayContainerDisappeared();
        click(By.xpath("//div[@id='support-dropdown']"));
        switchToAnotherWindow(getDriver());
        click(By.xpath("//ul[@id='support-dropdown-menu']//a[normalize-space()='How to start']"));
        getWait20();

        List<String> texts = new ArrayList<>();

        for (WebElement element : getListOfElements(H3)) {
            texts.add(element.getText());
        }

        Assert.assertFalse(texts.size() == 0);
    }
}

