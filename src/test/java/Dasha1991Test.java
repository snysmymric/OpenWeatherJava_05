import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

public class Dasha1991Test extends BaseTest {

     final static  By NAME_WEBSITE_H1 = By.xpath
             ("//div[@class='mobile-padding']//span[@class = 'orange-text']");
     final static By NAME_H2_HEADER = By.xpath
             ("//div[@class = 'mobile-padding']//span[@class = 'white-text']");

    final static By LOGO = By.xpath("//img[@src='/themes/openweathermap/assets/img/logo_white_cropped.png']");
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
}
