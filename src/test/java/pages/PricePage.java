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
    private List<WebElement> sectionAlertsSubHeadersOfFirstColumn;

    @FindBy(xpath = "//section[@id='alerts']//h4[text()='By request']")
    private List<WebElement> sectionAlertsSubHeadersOfSecondColumn;

    @FindBy(xpath = "//section[@id='alerts']//h4[@class = 'before-buttons' and @id = 'solar_radiation']")
    private  WebElement sectionAlertsSubHeadersOfSecondColumnFirstSubHeader;

    @FindBy(xpath = "//section[@id = 'alerts']//tbody/tr[1]/th[1]/p[1]")
    private WebElement sectionAlertsBodyOfFirstCellInFirstRowPartOne;

    @FindBy(xpath = "//section[@id = 'alerts']//tbody/tr[1]/th[1]/p[2]")
    private WebElement sectionAlertsBodyOfFirstCellInFirstRowPartTwo;

    @FindBy(xpath = "//section[@id='current']/div/div/table/thead/tr/th/h3/b")
    private List<WebElement> weatherAndForecastsCollections;

    @FindBy(xpath = "//h2")
    private List<WebElement> pricingPageH2Headers;

    @FindBy(xpath = "//a[@class = 'btn_like btn-orange owm-block-mainpage__btn']")
    private List<WebElement> orangeButtons;

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

    public String getAlertsH2Header() {

        return getText(alertsH2Header);
    }

    public List<String> getSectionAlertsSubHeadersOfFirstColumn() {

        return getTexts(sectionAlertsSubHeadersOfFirstColumn);
    }

    public int getByRequestSubHeadersAmount() {

        return getListSize(sectionAlertsSubHeadersOfSecondColumn);
    }

    public PricePage waitGetRequestToBeChanged(String oldHeader) {
        waitTextToBeChanged(sectionAlertsSubHeadersOfSecondColumnFirstSubHeader, oldHeader);

        return this;
    }

    public List<String> getHeadersTransparentButtons() {

        return getTrimTexts(transparentButtons);
    }

    public List<String> getListPricingPageH2Headers() {

        return getTexts(pricingPageH2Headers);
    }

    public int getListPricingPageH2HeadersSize() {

        return getListSize(pricingPageH2Headers);
    }

    public List<String> getHeadersOrangeButtons() {

        return getTrimTexts(orangeButtons);
    }

    public int getOrangeButtonsAmount() {

        return getListSize(orangeButtons);
    }
}
