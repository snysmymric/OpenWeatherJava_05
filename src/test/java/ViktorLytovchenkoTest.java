import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

    public class ViktorLytovchenkoTest extends BaseTest {


        @Ignore
        @Test
        public void testTypeInSearchBarRome() throws InterruptedException {
            String url = "https://openweathermap.org/";
            getDriver().get(url);
            Thread.sleep(10000);
            String cityName = "Rome";
            String expectedResult = "https://openweathermap.org/find?q=Rome";
            String expectedResult1 = "Rome";

            WebElement weatherInYourCity = getDriver().findElement(
                    By.xpath("//div[@id = 'desktop-menu']/form/input[1]")
            );
            weatherInYourCity.click();
            weatherInYourCity.sendKeys(cityName);
            weatherInYourCity.submit();
            String actualResult = getDriver().getCurrentUrl();

            WebElement theWordIsWritten = getDriver().findElement(
                    By.id("search_str")
            );
            String actualResult1 = theWordIsWritten.getAttribute("value");

            Assert.assertEquals(actualResult, expectedResult);
            Assert.assertEquals(actualResult1, expectedResult1);
        }

        @Ignore
        @Test
        public void checkSiteReload() throws InterruptedException {
            String url = "https://openweathermap.org/";
            getDriver().get(url);
            Thread.sleep(10000);

            WebElement imageLogoSite = getDriver().findElement(
                    By.xpath("//body[@class = 'body-orange']//ul[@id = 'first-level-nav']//a/img")
            );
            imageLogoSite.click();
            Thread.sleep(10000);

            String expectedResult = "https://openweathermap.org/";
            String actualResult = getDriver().getCurrentUrl();

            Assert.assertEquals(actualResult, expectedResult);
        }

    }


