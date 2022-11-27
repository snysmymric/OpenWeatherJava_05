package old_tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

public class Dasha1991Test extends BaseTest {

     final static  By NAME_WEBSITE_H1 = By.xpath
             ("//div[@class='mobile-padding']//span[@class = 'orange-text']");
     final static By NAME_H2_HEADER = By.xpath
             ("//div[@class = 'mobile-padding']//span[@class = 'white-text']");
    final static By LOGO = By.xpath
            ("//img[@src='/themes/openweathermap/assets/img/logo_white_cropped.png']");
    final static By NAVIGATION_BAR = By.xpath("//nav[@id = 'nav-website']");
    final static By SEARCH_FIELD = By.xpath("//div[@id = 'desktop-menu']//input[@name='q']");
    final static By GUIDE_BUTTON = By.xpath("//div[@id='desktop-menu']//a[@href='/guide']");
    final static By API_BUTTON = By.xpath("//div[@id='desktop-menu']//a[@href='/api']");
    final static By DASHBOARD_BUTTON = By.xpath
            ("//div[@id='desktop-menu']//a[@href='/weather-dashboard']");
    final static By MARKETPLACE_BUTTON = By.xpath
            ("//div[@id='desktop-menu']//a[@href='https://home.openweathermap.org/marketplace']");
    final static By PRICING_BUTTON = By.xpath("//div[@id='desktop-menu']//a[@href='/price']");
    final static By MAPS_BUTTON = By.xpath("//div[@id='desktop-menu']//a[@href='/weathermap']");
    final static By OUR_INITIATIVES_BUTTON = By.xpath("//div[@id='desktop-menu']//a[@href='/our-initiatives']");
    final static By PARTNERS_BUTTON = By.xpath("//div[@id='desktop-menu']//a[@href='/examples']");
    final static By BLOG_BUTTON = By.xpath("//div[@id='desktop-menu']//a[@href='https://openweather.co.uk/blog/category/weather']");
    final static By FOR_BUSINESS_BUTTON = By.xpath("//div[@id='desktop-menu']//a[@class='marketplace']");
    final static By SING_IN_BUTTON = By.xpath("//div[@id='desktop-menu']//a[@href='https://openweathermap.org/home/sign_in']");
    final static By SUPPORT_BUTTON = By.xpath("//div[@id='support-dropdown']");

    @Test
    public void testHeaderStartPage() {
        final String expectedResultH1 = "OpenWeather";
        final String expectedResultH2 = "Weather forecasts, nowcasts and history in a fast and elegant way";

         openBaseURL();

         String actualResultH1 = getText(NAME_WEBSITE_H1);
         String actualResultH2 = getText(NAME_H2_HEADER);

         Assert.assertEquals(actualResultH1,expectedResultH1);
         Assert.assertEquals(actualResultH2,expectedResultH2);
    }

    @Test
    public void testNameOfURL() {
        final String expectedResultURL = "https://openweathermap.org/";
        final String expectedResultTitle = "Сurrent weather and forecast - OpenWeatherMap";

         openBaseURL();
         click(LOGO);

         Assert.assertEquals(getCurrentURL(),expectedResultURL);
         Assert.assertEquals(getTitle(),expectedResultTitle);
    }

    @Test
    public void testAllElementsExist() {

      openBaseURL();

        Assert.assertTrue(isElementExists(NAVIGATION_BAR));
        Assert.assertTrue(isElementExists(LOGO));
        Assert.assertTrue(isElementExists(SEARCH_FIELD));
        Assert.assertTrue(isElementExists(GUIDE_BUTTON));
        Assert.assertTrue(isElementExists(API_BUTTON));
        Assert.assertTrue(isElementExists(DASHBOARD_BUTTON));
        Assert.assertTrue(isElementExists(MARKETPLACE_BUTTON));
        Assert.assertTrue(isElementExists(PRICING_BUTTON));
        Assert.assertTrue(isElementExists(MAPS_BUTTON));
        Assert.assertTrue(isElementExists(OUR_INITIATIVES_BUTTON));
        Assert.assertTrue(isElementExists(PARTNERS_BUTTON));
        Assert.assertTrue(isElementExists(BLOG_BUTTON));
        Assert.assertTrue(isElementExists(FOR_BUSINESS_BUTTON));
        Assert.assertTrue(isElementExists(SING_IN_BUTTON));
        Assert.assertTrue(isElementExists(SUPPORT_BUTTON));
    }
}
