package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomeSignInPage extends FooterMenuPage {

    @FindBy (xpath = "//h3[@class='first-child']")
    private WebElement H3HeaderWelcomeMassage;

    public HomeSignInPage(WebDriver driver) {
        super(driver);
    }

    public String getWelcomeMassageOnSignInPage(){

        return getText(H3HeaderWelcomeMassage);

    }
}
