package old_tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import org.openqa.selenium.Keys;

@Ignore
public class SafOlgaTest extends BaseTest {
    final static By SEARCH_CITY_FIELD = By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']");

    private void inputKeys(String text, By by) {
       getDriver().findElement(by).sendKeys(text + Keys.ENTER);
    }
    @Test
    public void testNoResultsMassegeInSearchBar() {

        String cityName = "pome,";
        String expectedResult = "No results for pome,";

        openBaseURL();
        click(SEARCH_CITY_FIELD);
        inputKeys(cityName, SEARCH_CITY_FIELD);

        WebElement messageError = getDriver().findElement(
                By.xpath("//div[@class='widget-notification']"));

        String actualResult = messageError.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }
}
