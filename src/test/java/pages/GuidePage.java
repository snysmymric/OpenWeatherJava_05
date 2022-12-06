package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GuidePage extends FooterMenuPage {

    @FindBy(xpath = "//div[@class='container']//li/a[@href = '/']")
    private WebElement homeGuideButton;

    @FindBy(xpath = "//p/a[@href= '/api/road-risk']")
    private WebElement roadRiskAPILink;

    public GuidePage(WebDriver driver) {
        super(driver);
    }

    public GuidePage clickHomeGuide() {
        click(homeGuideButton);

        return this;
    }

    public RoadRiskAPIPage clickRoadRiskAPILink(){
        click(roadRiskAPILink);

        return new RoadRiskAPIPage(getDriver());
    }
}
