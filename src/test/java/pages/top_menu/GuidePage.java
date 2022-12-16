package pages.top_menu;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.RoadRiskAPIPage;
import pages.base_abstract.FooterMenuPage;

import java.util.List;

public class GuidePage extends FooterMenuPage {

    @FindBy(xpath = "//div[@class='container']//li/a[@href = '/']")
    private WebElement homeGuideLink;

    @FindBy(xpath = "//a[@href='/api/solar-radiation']")
    private WebElement solarRadiationLink;

    @FindBy(xpath = "//h2[contains(text(),'Dedicated weather products')]")
    private WebElement dedicatedWeatherProductsHeader;

    @FindBy(xpath = "//p/a[@href= '/api/road-risk']")
    private WebElement roadRiskAPILink;

    @FindBy(xpath = "//a[text()='Learn more']")
    private List<WebElement> buttonsLearnMoreList;

    public GuidePage(WebDriver driver) {
        super(driver);
    }

    public int getButtonsLearnMoreAmount() {

        return getListSize(buttonsLearnMoreList);
    }

    public GuidePage clickHomeGuideLink() {
        click(homeGuideLink);

        return this;
    }

    public GuidePage clickSolarRadiationLink() {
        click(solarRadiationLink);

        return this;
    }

    public RoadRiskAPIPage clickRoadRiskAPILink(){
        click(roadRiskAPILink);

        return new RoadRiskAPIPage(getDriver());
    }

    public GuidePage scrollToDedicatedWeatherProducts() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView();", dedicatedWeatherProductsHeader);
        return this;
    }

}
