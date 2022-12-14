package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base_abstract.FooterMenuPage;

public class CurrentWeatherPage extends FooterMenuPage {

    @FindBy(xpath = "//section[@id='geo']/div[@class='api']/code")
    private WebElement apiCallCode;

    public CurrentWeatherPage(WebDriver driver) {
        super(driver);
    }

    public boolean isAPICallCodeDisplayed() {
        wait10ElementToBeVisible(apiCallCode);

        return isDisplayedElement(apiCallCode);
    }

    public String getAPICallCode() {

        return getText(apiCallCode);
    }
}
