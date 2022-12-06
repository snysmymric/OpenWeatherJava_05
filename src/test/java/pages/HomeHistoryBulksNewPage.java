package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomeHistoryBulksNewPage extends HomeMarketplacePage{

    @FindBy(xpath = "//span[text()='Weather Parameters:']")
    private WebElement weatherParametersButton;

    @FindBy(xpath = "//div[@class='owm-check-box-group columns']//label")
    private List<WebElement> weatherParametersElements;

    public HomeHistoryBulksNewPage(WebDriver driver) {
        super(driver);
    }

    public HomeHistoryBulksNewPage clickWeatherParametersButton() {
        click(weatherParametersButton);

        return this;
    }

    public HomeHistoryBulksNewPage uncheckAllWeatherParameters() {
        clickAllElements(weatherParametersElements);

        return this;
    }

    public boolean allCheckmarksAreNotSelectedInTheWeatherParameters() {

        return checkingForUnselectedElements(weatherParametersElements);
    }

    public HomeHistoryBulksNewPage uncheckNotAllWeatherParameters(String textContains) {
        for (WebElement element : weatherParametersElements) {
            if (!element.getText().toLowerCase().contains(textContains)) {
                element.click();
            }
        }

        return this;
    }
}
