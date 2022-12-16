package pages.top_menu;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base_abstract.FooterMenuPage;

import java.util.List;

public class PricePage extends FooterMenuPage {

    @FindBy(xpath = "//h1")
    private WebElement pricingPageH1Header;

    @FindBy(xpath = "//a[@class = 'btn_block transparent round']")
    private List<WebElement> transparentButtons;

    @FindBy(xpath = "//section[@id = 'alerts']/h2")
    private WebElement alertsHeader;

    @FindBy(xpath = "//section[@id = 'alerts']//tbody/tr/th/h4/a")
    private List<WebElement> alertsSubHeaders;

    @FindBy(xpath = "//section[@id='alerts']//h4[text()='By request']")
    private List<WebElement> alertsPriceByRequest;

    @FindBy(id = "solar_radiation")
    private  WebElement alertPriceSolarRadiationAPIText;

    @FindBy(xpath = "//section[@id='current']//table/thead//h3/b")
    private List<WebElement> weatherAndForecastsCollections;

    @FindBy(xpath = "//h2")
    private List<WebElement> pricingPageH2Headers;

    @FindBy(xpath = "//a[@class = 'btn_like btn-orange owm-block-mainpage__btn']")
    private List<WebElement> orangeButtons;

    public PricePage(WebDriver driver) {
        super(driver);
    }

    public String getHeaderText() {

        return getText(pricingPageH1Header);
    }

    public String getAlertsH2Header() {

        return getText(alertsHeader);
    }

    public List<String> getCollectionsNames() {

        return getTexts(weatherAndForecastsCollections);
    }

    public List<String> getAlertsSubHeaders() {

        return getTexts(alertsSubHeaders);
    }

    public List<String> getHeadersTransparentButtons() {

        return getTrimmedTexts(transparentButtons);
    }

    public List<String> getListPricingPageH2Headers() {

        return getTexts(pricingPageH2Headers);
    }

    public int getListPricingPageH2HeadersSize() {

        return getListSize(pricingPageH2Headers);
    }

    public List<String> getHeadersOrangeButtons() {

        return getTrimmedTexts(orangeButtons);
    }

    public int getByRequestSubHeadersAmount() {

        return getListSize(alertsPriceByRequest);
    }

    public int getOrangeButtonsAmount() {

        return getListSize(orangeButtons);
    }

    public int getTransparentButtonsAmount() {

        return getListSize(transparentButtons);
    }

    public PricePage waitGetRequestToBeChanged(String oldHeader) {
        waitTextToBeChanged(alertPriceSolarRadiationAPIText, oldHeader);

        return this;
    }
}
