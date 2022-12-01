package old_tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

@Ignore
public class MargaritaEvdTest extends BaseTest {

    private final By GUIDE_MENU_BUTTON = By.xpath("//div[@id='desktop-menu']//a[@href = '/guide']");
    private final By SOLAR_RADIATION_API_LINK = By.xpath(
            "//p[text()='Our weather products are accessible via fast, reliable ']/a[.='Solar Radiation API']");

    @Test
    public void testGuideSolarRadiationApiIsClickableAndLinkIsOpened(){
        String expectedLink = "https://openweathermap.org/api/solar-radiarion";
        openBaseURL();
        click(GUIDE_MENU_BUTTON);
        click(SOLAR_RADIATION_API_LINK);

        String actualLink = getCurrentURL();

        Assert.assertEquals(actualLink,expectedLink);
    }
}