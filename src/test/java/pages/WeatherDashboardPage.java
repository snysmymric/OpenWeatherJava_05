package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class WeatherDashboardPage extends FooterMenuPage {


    public WeatherDashboardPage(WebDriver driver) {
        super(driver);
    }
    @FindBy (xpath="//div[@id='footer-website']//div[@class='section-content']//ul//li//a[@href='/weather-dashboard']")
    private WebElement dashboardMenu;

    public WeatherDashboardPage clickDashboardMenu(){
        click(dashboardMenu);

        return this;
    }
    public WeatherDashboardPage scrollToDashboardLink() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollTo(0, 5200)");

        return this;
    }
}
