package old_tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import base.BaseTest;

@Ignore
public class AnastasiaUsGoTest extends BaseTest {

    private final By SIGN_IN_LINK = By.xpath(
            "//a[@href='https://openweathermap.org/home/sign_in' and contains(text(), \"Sign in\")]"
    );
    private final By PRICING_FOOTER_LINK = By.xpath("//div[@class='section-content']//a[@href='/price']");
    private final By PRICING_TITLE = By.className("breadcrumb-title");

    @Test
    public void testSignInLink_IsClickable() {
        final String expectedSignInLink = "https://home.openweathermap.org/users/sign_in";

        openBaseURL();
        click(SIGN_IN_LINK);
        waitURLToBeChanged(BASE_URL);

        Assert.assertEquals(getCurrentURL(), expectedSignInLink);
    }

    @Test
    public void testPrisingFooter_IsClickable(){
        String expectedPricingFooterLink = "https://openweathermap.org/price";
        String expectedTextTitle = "Pricing";

        openBaseURL();
        scrollToPageBottom();
        click(PRICING_FOOTER_LINK);
        waitURLToBeChanged(BASE_URL);

        Assert.assertEquals(getCurrentURL(), expectedPricingFooterLink);
        Assert.assertEquals(getText(PRICING_TITLE), expectedTextTitle);
    }
}
