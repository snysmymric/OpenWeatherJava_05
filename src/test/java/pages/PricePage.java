package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PricePage extends FooterMenuPage {

    @FindBy(xpath = "//h1")
    private WebElement pricingPageHeader;

    @FindBy(xpath = "//a[@class = 'btn_block transparent round']")
    private List<WebElement> transparentButtons;

    @FindBy(xpath = "//section[@id = 'alerts']/h2")
    private WebElement alertsH2Header;

    @FindBy(xpath = "//section[@id = 'alerts']//tbody/tr/th/h4/a")
    private WebElement sectionAlertsSubHeadersOfFirstColumn;

    @FindBy(xpath = "//section[@id = 'alerts']//tr/th[2]/h4[contains(text(), 'By request')]")
    private WebElement sectionAlertsSubHeadersOfSecondColumn;

    @FindBy(xpath = "//section[@id = 'alerts']//tbody/tr[1]/th[1]/p[1]")
    private WebElement sectionAlertsBodyOfFirstCellInFirstRowPartOne;

    @FindBy(xpath = "//section[@id = 'alerts']//tbody/tr[1]/th[1]/p[2]")
    private WebElement sectionAlertsBodyOfFirstCellInFirstRowPartTwo;

    @FindBy(xpath = "//section[@id='current']/div/div/table/thead/tr/th/h3/b")
    private List<WebElement> weatherAndForecastsCollections;

    public PricePage(WebDriver driver) {
        super(driver);
    }

    public int getTransparentButtonsAmount() {

        return getListSize(transparentButtons);
    }

    public String getHeaderText() {

        return getText(pricingPageHeader);
    }

    public List<String> getCollectionsNames() {

        return getTexts(weatherAndForecastsCollections);
    }
}
