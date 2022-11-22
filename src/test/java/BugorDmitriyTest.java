import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import base.BaseTest;

import java.util.List;


public class BugorDmitriyTest extends BaseTest {
    final static By H2_CITY_COUNTRY_HEADER = By.xpath("//div[@id = 'weather-widget']//h2");
    final static By SEARCH_CITY_FIELD = By.xpath("//div[@id='weather-widget']//input[@placeholder='Search city']");
    final static By SEARCH_BUTTON = By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']");
    final static By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
    final static By PARIS_FR_CHOICE_DROPDOWN_MENU = By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']");
    final static By WE_USE_COOKIES = By.xpath("//div[@class='stick-footer-panel__container']//p[text()='We use cookies which are essential "
            + "for the site to work. We also use non-essential cookies to help us improve our services. Any data collected is anonymised. "
            + "You can allow all cookies or manage them individually.']");
    final static By ALLOW_ALL = By.xpath("//button[@class='stick-footer-panel__link']");
    final static By MANAGE_COOKIES = By.xpath("//div[@class='stick-footer-panel__btn-container']//a");
    final static By HEADING_SUPPORT_DROPDOWN = By.xpath("//div[@id='support-dropdown']");
    final static By FAQ = By.xpath("//ul[@class='dropdown-menu dropdown-visible']//a [@href='/faq']");
    final static By HOW_TO_START = By.xpath("//ul[@class='dropdown-menu dropdown-visible']//li/a[@href='/appid']");
    final static By ASK_A_QUESTION = By.xpath("//ul[@class='dropdown-menu dropdown-visible']//a[text()='Ask a question']");
    final static By SUPPORT_DROPDOWN_MENU = By.xpath("//ul[@id='support-dropdown-menu']/*");
    final static By DASHBOARD_MENU = By.xpath("//div[@id='desktop-menu']/ul/li[3]/a");

    
            @Test
        public void testH2TextWhenSearchingCityCountry() {

            String cityName = "Paris";
            String expectedResult = "Paris, FR";

            openBaseURL();

            String oldH2Header = getText(H2_CITY_COUNTRY_HEADER);
            click(SEARCH_CITY_FIELD);
            input(cityName, SEARCH_CITY_FIELD);
            click(SEARCH_BUTTON);
            waitElementToBeVisible(SEARCH_DROPDOWN_MENU);
            click(PARIS_FR_CHOICE_DROPDOWN_MENU);
            waitTextToBeChanged(H2_CITY_COUNTRY_HEADER, oldH2Header);

            String actualResult = getText(H2_CITY_COUNTRY_HEADER);

            Assert.assertEquals(actualResult, expectedResult);
    }

            @Test
        public void testConfirmTextAboutCookies() {

            String expectedResult1 = "We use cookies which are essential for the site to work. We also use non-essential"
                    + " cookies to help us improve our services. Any data collected is anonymised. You can allow all "
                    + "cookies or manage them individually.";
            String expectedResult2 = "Allow all";
            String expectedResult3 = "Manage cookies";

            openBaseURL();

            String actualResult1WeUseCookies = getText(WE_USE_COOKIES);
            String actualResult2AllowAll = getText(ALLOW_ALL);
            String actualResult3ManageCookies = getText(MANAGE_COOKIES);

            Assert.assertEquals(actualResult1WeUseCookies, expectedResult1);
            Assert.assertEquals(actualResult2AllowAll, expectedResult2);
            Assert.assertEquals(actualResult3ManageCookies, expectedResult3);
    }

            @Test
        public void testMenuSupport() {

            int expectedResult = 3;
            String expectedResult1 = "FAQ";
            String expectedResult2 = "How to start";
            String expectedResult3 = "Ask a question";

            openBaseURL();

            click(HEADING_SUPPORT_DROPDOWN);
            int actualResult = seeAllElementAndCount (SUPPORT_DROPDOWN_MENU);

            String actualResult1FAQ = getText(FAQ);
            String actualResult2HowToStart = getText(HOW_TO_START);
            String actualResult3AskAQuestion = getText(ASK_A_QUESTION);

            Assert.assertEquals(actualResult, expectedResult);
            Assert.assertEquals(actualResult1FAQ, expectedResult1);
            Assert.assertEquals(actualResult2HowToStart, expectedResult2);
            Assert.assertEquals(actualResult3AskAQuestion, expectedResult3);
    }

            @Test
        public void testDashboardMenu(){

            String expectedResult = "https://openweathermap.org/weather-dashboard";

            openBaseURL();

            click(DASHBOARD_MENU);

            String actualResult = getDriver().getCurrentUrl();

            Assert.assertEquals(actualResult, expectedResult);
    }
   }
