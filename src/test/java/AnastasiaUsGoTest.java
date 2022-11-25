import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import base.BaseTest;

public class AnastasiaUsGoTest extends BaseTest {

    private final By SIGN_IN_LINK = By.xpath(
            "//a[@href='https://openweathermap.org/home/sign_in' and contains(text(), \"Sign in\")]"
    );

    @Test
    public void testSignInLink_IsClickable() {
        final String expectedSignInLink = "https://home.openweathermap.org/users/sign_in";

        openBaseURL();
        click(SIGN_IN_LINK);
        waitURLToBeChanged(BASE_URL);

        Assert.assertEquals(getCurrentURL(), expectedSignInLink);
    }
}
