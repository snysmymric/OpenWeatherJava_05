package old_tests;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.How;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

public class DmswlpkTest extends BaseTest {

    private static final By DIFFERENT_WEATHER_POPUP_WINDOW = By.xpath("//div[@class='pop-up-container']");
    private static final By DIFFERENT_WEATHER_BTN = By.xpath("//span[contains(text(),'Different')]");
    private static final By DIFFERENT_WEATHER_POPUP_WINDOW_H3_HEADER = By.id("dialogTitle");
    private static final By SUBSCRIPTION_FOOTER = By.xpath("//p[contains(text(),'Subscription')]");
    private static final By HOW_TO_START_FOOTER = By.xpath("//div[@onclick='toggleFooterSection(event)']//a[@href='/appid']");

    @Test
    public void testDifferentWeatherWindowPopups() {
        openBaseURL();

        String expectedHeader = "Different weather";

        click(DIFFERENT_WEATHER_BTN);

        Assert.assertTrue(isDisplayed(DIFFERENT_WEATHER_POPUP_WINDOW));
        Assert.assertEquals(getText(DIFFERENT_WEATHER_POPUP_WINDOW_H3_HEADER), expectedHeader);
    }

    @Test
    public void testHowToStartLink_FooterSubscription() {
        openBaseURL();

        String expectedResult = BASE_URL + "appid";
        String expectedResultTitle = "How to start to work with Openweather API - OpenWeatherMap";

        scrollByVisibleElement(HOW_TO_START_FOOTER);
        click(HOW_TO_START_FOOTER);

        Assert.assertEquals(getCurrentURL(), expectedResult);
        Assert.assertEquals(getTitle(), expectedResultTitle);
    }
}
