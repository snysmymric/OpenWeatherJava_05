package old_tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Year;
import java.util.List;

public class GdikhsanovTest extends BaseTest {

    private final static String BASE_URL = "https://openweathermap.org/";
    private final static By H1_PRICING_PAGE = By.className("breadcrumb-title");
    private final static By MENU_PRICING_BUTTON = By.xpath("//div[@id='desktop-menu']//a[@href='/price']");
    private final static By MENU_GUIDE_BUTTON = By.xpath("//div[@id='desktop-menu']//a[@href='/guide']");
    private final static By LIST_OF_EIGHT_DAYS_DATA = By.xpath("//ul[@class='day-list']/li/span");

    @Test
    public void testTitleAndUrl_WhenGoToGuide_gdikhsanov() {

        final String expectedUrl = String.format("%sguide", BASE_URL);
        final String expectedTitle = "OpenWeatherMap API guide - OpenWeatherMap";

        openBaseURL();
        click(MENU_GUIDE_BUTTON);

        String currentUrl = getDriver().getCurrentUrl();
        String currentTitle = getDriver().getTitle();

        Assert.assertEquals(currentUrl, expectedUrl);
        Assert.assertEquals(currentTitle, expectedTitle);
    }

    @Test
    public void testVerifyThePagePricingOpened_WhenClickPricingBtnAtMenu_gdikhsanov() {

        final String expectedUrl = String.format("%sprice", BASE_URL);
        final String expectedH1Text = "Pricing";

        openBaseURL();

        click(MENU_PRICING_BUTTON);

        String currentUrl = getDriver().getCurrentUrl();
        String currentH1Text = getText(H1_PRICING_PAGE);

        Assert.assertEquals(currentUrl, expectedUrl);
        Assert.assertEquals(currentH1Text, expectedH1Text);
    }

    @Test
    public void testCorrect8DaysForecastCalendarSequence_gdikhsanov() {

        openBaseURL();
        List<String> listOfEightDaysData = getListText(LIST_OF_EIGHT_DAYS_DATA);
        final String actualResult = listOfEightDaysData.toString().substring(1, listOfEightDaysData.toString().length() - 1);

        final String[] dowMonDate = listOfEightDaysData.get(0).split(" ");
        final String dowText = dowMonDate[0].substring(0, 3);
        final int monNum = returnMonth(dowMonDate[1]);
        final int date = Integer.parseInt(dowMonDate[2]);

        String expectedResult = printEightDaysFromDate(dowText, monNum, date, Year.now().getValue());

        Assert.assertEquals(actualResult, expectedResult);
    }
}