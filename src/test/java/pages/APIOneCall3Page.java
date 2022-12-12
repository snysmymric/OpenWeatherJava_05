package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class APIOneCall3Page extends FooterMenuPage {

    @FindBy(xpath = "//section[@id='how']//tbody/*")
    private List<WebElement> APIOneCall3Parameteres;

    public APIOneCall3Page(WebDriver driver) {
        super(driver);
    }

    public List<String> getAPIOneCall3Parametres() {
        List<String> APIOneCall3ParametresText = new ArrayList<>();

        for (int i=0;i<getListSize(APIOneCall3Parameteres);i++) {
            APIOneCall3ParametresText.add(getText(APIOneCall3Parameteres.get(i)));
        }
        return APIOneCall3ParametresText;
    }
}
