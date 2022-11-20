import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.time.Duration;

@Ignore
public class EkaterinalizinaTest extends BaseTest {
    final static String BASE_URL = "https://openweathermap.org/";
    final static By H_2_CITY_COUNT_HEADER =  By.xpath("//div[@id = 'weather-widget']//h2");
    final static By SEARCH_CITY_FIELD =  By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']");
    final static By SEARCH_BUTTON = By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']");
    final static By SEARCH_DROPDOWN_MENU= By.className("search-dropdown-menu");
    final static By PARIS_FR_CHOISE_IN_DROP_DOWN_MENU = By.xpath("//ul[@class ='search-dropdown-menu']/li/span[text() = 'Paris, FR ']");
    final static By MENU_GUIDE_IS_CLICKABLE = By.xpath("//div[@id ='desktop-menu']//a[text() = 'Guide']");
    final static By MENU_GUIDE_IS_DISPLAYED =  By.xpath("//h1[text() = 'Guide']");
    private void openBaseURL(){
        getDriver().get(BASE_URL);
    }
    private void waitForGrayFrameDisappeared(){
        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("owm-loader-container")));
    }

    private String getText(By by, WebDriver driver){
        return driver.findElement(by).getText();
    }

    private void click (By by, WebDriverWait wait){
       wait.until(ExpectedConditions.visibilityOfElementLocated(by));
       wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }
    private void input(String text, By by, WebDriver driver){
        driver.findElement(by).sendKeys(text);
    }
    private void waitElementToBeVisible(By by, WebDriverWait wait){
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
    public void waitTextToBeChanged(By by, String text, WebDriver driver, WebDriverWait wait){
       wait.until(ExpectedConditions
                .not(ExpectedConditions.textToBePresentInElement(driver.findElement(by), text)));
    }
    @Test
    public void testH2TagText_WhenSearchingCityCountry()  {
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        openBaseURL();
        waitForGrayFrameDisappeared();

        String oldH2Header = getText(H_2_CITY_COUNT_HEADER, getDriver());

        click(SEARCH_CITY_FIELD, getWait5());
        input(cityName, SEARCH_CITY_FIELD, getDriver());
        click(SEARCH_BUTTON, getWait5());
        waitElementToBeVisible(SEARCH_DROPDOWN_MENU, getWait10());
        click(PARIS_FR_CHOISE_IN_DROP_DOWN_MENU, getWait10());
        waitTextToBeChanged(H_2_CITY_COUNT_HEADER, oldH2Header, getDriver(), getWait10());

        String actualResult = getText(H_2_CITY_COUNT_HEADER, getDriver());

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Ignore
    @Test
    public void testH2TagText_WhenSearchingCityCountry_lecture()  {
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        getDriver().get("https://openweathermap.org/");

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        //мы используем "явное ожидание" в тех элементах, которые мы пытаемся найти
        //и которые не успевают подгрузится и этот эл-т будет искаться пока не
        //не выполнится определенное условие
        //ExpectedConditions. методы можно вызывать по локатору, а можно по эл-ту
        //мы проверяем, что пока эл-т станет невидимым (invisibility)

        //но потом мы пошли в базовые настройки и создали метод getWait(20), (10), (5)
        // и теперь мы вместо  wait.until(ExpectedConditions.invisibilityOfElementLocated(
        //будем вызывать из файла BaseTest - metod getWait(20).until(ExpectedConditions.invisibilityOfElementLocated(
        //и везде поменяем, где раньше стояли просто wait

        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("owm-loader-container")));
        //такое серое окно, которое появляется на странице и загороживает все
        WebElement h2CityCountHeader = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//h2")
        );
        //cоздаем переменную и сохраняем туда старое значение элемента,
        // которое потом должно изменится
        String oldH2Header = h2CityCountHeader.getText();
        // мы будем ждать пока эл-т станет видимым wait.until(ExpectedConditions.visibility
        getWait5().until(ExpectedConditions
                .visibilityOfElementLocated(
                        By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']"))
        );

        WebElement searchCityField =
                getDriver().findElement(
                        By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']")
                );
        //мы проверим и ждем пока эл-т кликабельный (clicable) и как дождется, мы кликнем
        getWait5().until(ExpectedConditions.elementToBeClickable(searchCityField)).click();

        //Промежуточная проверка, что эл-т виден и появился
        // Assert.assertTrue(searchCityField.isDisplayed());

        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchButton = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']")
        );

        searchButton.click();
        //здесь после эл-та есть полсекундная задержка, чтобы появилось дропдаун меню
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.className("search-dropdown-menu")));

        WebElement parisFrhoiseInDropDownMenu = getDriver().findElement(
                By.xpath("//ul[@class ='search-dropdown-menu']/li/span[text() = 'Paris, FR ']")
        );

        parisFrhoiseInDropDownMenu.click();
        //нам нужно считать элемент только после того,
        // как его состояние после загрузки изменилось
        //в Селениуме нет такого метода, надо его прописать
        //нам нужно сначала считать изначальное состояние эл-та, когда мы его найдем
        //и считать второй раз, но только когда состояние эл-та изменится

//        WebElement h2CityCountHeader = getDriver().findElement(
//                By.xpath("//div[@id = 'weather-widget']//h2")
//        );
        //мы сравниваем старое значение эл-та, сохраненное в переменную с новым
        // и мы ждем пока эти состояния не станут отличаться
        getWait10().until(ExpectedConditions
                .not(ExpectedConditions.textToBePresentInElement(h2CityCountHeader, oldH2Header)));
        String actualResult = h2CityCountHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Ignore
    @Test
    public void testTemperatureChangedInToF() throws InterruptedException {
        String fTempSymbol = "°F";

        openBaseURL();
     //   Thread.sleep(10000);

        WebElement ButtonTemperatureFarenheit = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//div[text() = 'Imperial: °F, mph']"));
        ButtonTemperatureFarenheit.click();

      //  Thread.sleep(10000);

        WebElement tempF = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//span[@class ='heading']"));

        Assert.assertTrue(tempF.getText().contains(fTempSymbol));
    }

    @Ignore
    @Test
    public void testTwoButtonsCookies() throws InterruptedException {
        openBaseURL();

     //   Thread.sleep(10000);
        String expectedResult1 = "We use cookies which are essential for the site to work." +
                " We also use non-essential cookies to help us improve our services." +
                " Any data collected is anonymised. You can allow all cookies" +
                " or manage them individually.";
        String expectedResult2 = "Allow all";
        String expectedResult3 = "Manage cookies";

        WebElement cookiesText = getDriver().findElement(
                By.xpath("//div[@id = 'stick-footer-panel']//p[contains(text (), 'We use cookies which')]"));
        String actualResult1 = cookiesText.getText();

        WebElement cookiesButton1 = getDriver().findElement(
                By.xpath("//div[@id = 'stick-footer-panel']//button[text() = 'Allow all']"));
        WebElement cookiesButton2 = getDriver().findElement
                (By.xpath("//div[@id = 'stick-footer-panel']//a[text() = ' Manage cookies ']"));
        String actualResult2 = cookiesButton1.getText();
        String actualResult3 = cookiesButton2.getText();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
        Assert.assertEquals(actualResult3, expectedResult3);
    }

    @Test
    public void testATMainNavBarMenuGuideIsClickableHappyStrawberryAT_EkaterinaLizina () {
        openBaseURL();
        String expectedResult_Guide = "Guide";
        waitForGrayFrameDisappeared();
        click(MENU_GUIDE_IS_CLICKABLE, getWait10());
        waitElementToBeVisible(MENU_GUIDE_IS_DISPLAYED, getWait5());
        String actualResult = getText(MENU_GUIDE_IS_DISPLAYED, getDriver());

        Assert.assertEquals(actualResult, expectedResult_Guide);
    }


}
