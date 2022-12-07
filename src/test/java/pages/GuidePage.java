package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GuidePage extends FooterMenuPage {

    @FindBy(xpath = "//div[@class='container']//li/a[@href = '/']")
    private WebElement homeGuideButton;

    @FindBy(xpath = "/html/body/main/div[2]/div/div/ol/ul[5]/li[1]/a")
    private WebElement solarRadiationLink;

    @FindBy(xpath = "/html/body/main/div[2]/div/div/ol/h2[1]")
    private WebElement DedicatedWeatherProducts;

    @FindBy(xpath = "//p/a[@href= '/api/road-risk']")
    private WebElement roadRiskAPILink;

    public GuidePage(WebDriver driver) {
        super(driver);
    }

    public GuidePage clickHomeGuide() {
        click(homeGuideButton);

        return this;
    }

    public GuidePage clickSolarRadiationLink() {
        click(solarRadiationLink);

        return this;
    }

    public GuidePage scrollToDedicatedWeatherProducts() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView();", DedicatedWeatherProducts);
        return this;
    }

    public RoadRiskAPIPage clickRoadRiskAPILink(){
        click(roadRiskAPILink);

        return new RoadRiskAPIPage(getDriver());
    }
}
