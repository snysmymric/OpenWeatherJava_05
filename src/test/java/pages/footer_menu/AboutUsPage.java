package pages.footer_menu;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base_abstract.FooterMenuPage;

import java.util.List;

public class AboutUsPage extends FooterMenuPage {

    @FindBy(xpath = "//div[@class = 'about-us']//h1")
    private WebElement aboutUsPageHeader;

    @FindBy(xpath = "//h2[text()= 'Where-to']")
    private WebElement whereToH2tag;

    @FindBy(xpath = "//div[@class='section where-to']//div[1]/a")
    private List<WebElement> optionsUnderWhereTo;

    public AboutUsPage(WebDriver driver) {
        super(driver);
    }

    public AboutUsPage waitForAboutUsPageHeaderBeVisible() {
        wait10ElementToBeVisible(aboutUsPageHeader);

        return new AboutUsPage(getDriver());
    }

    public AboutUsPage scrollToWhereTo() {
        scrollByVisibleElement(whereToH2tag);

        return this;
    }

    public List<String> getOptionsText() {

        return getTexts(optionsUnderWhereTo);
    }

    public AboutUsPage waitAllOptionsAreVisibleAndClickable() {
        allElementsVisibleAndClickable(optionsUnderWhereTo);

        return this;
    }
}
