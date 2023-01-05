package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base_abstract.FooterMenuPage;

import java.util.ArrayList;
import java.util.List;

public class APIOneCall3Page extends FooterMenuPage {

    @FindBy(xpath = "//section[@id='how']//tbody/*")
    private List<WebElement> APIOneCall3Parameters;

    public APIOneCall3Page(WebDriver driver) {
        super(driver);
    }

    public List<String> getADIOneCall3Parameters() {
        List<String> APIOneCall3ParametersText = new ArrayList<>();

        for (int i = 0; i < getListSize(APIOneCall3Parameters); i++) {
            APIOneCall3ParametersText.add(getText(APIOneCall3Parameters.get(i)));
        }

        return APIOneCall3ParametersText;
    }
}
