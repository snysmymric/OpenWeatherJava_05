package pages.base_abstract;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import pages.home.HomePage;
import pages.home.HomeSignInPage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public abstract class BasePage {
    private WebDriver driver;
    private WebDriverWait webDriverWait20;
    private WebDriverWait webDriverWait10;
    private Actions actions;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    abstract HomePage signIn();
    abstract HomeSignInPage signOut();

    protected WebDriver getDriver() {
        return driver;
    }

    protected WebDriverWait getWait20() {
        if (webDriverWait20 == null) {
            webDriverWait20 = new WebDriverWait(driver, Duration.ofSeconds(20));
        }

        return webDriverWait20;
    }

    protected WebDriverWait getWait10() {
        if (webDriverWait10 == null) {
            webDriverWait10 = new WebDriverWait(driver, Duration.ofSeconds(10));
        }

        return webDriverWait10;
    }

    protected Actions getActions() {
        if (actions == null) {
            actions = new Actions(driver);
        }

        return actions;
    }

    public String getTitle() {

        return getDriver().getTitle();
    }

    public String getCurrentURL() {

        return getDriver().getCurrentUrl();
    }

    protected String getText(WebElement element) {
        if (!element.getText().isEmpty()) {
            wait10ElementToBeVisible(element);
        }

        return element.getText();
    }

    protected List<String> getTexts(List<WebElement> elements) {
        List<String> texts = new ArrayList<>();

        for (WebElement element : elements) {
            texts.add(getText(element));
        }

        return texts;
    }

    protected List<String> getTrimTexts(List<WebElement> elements) {
        List<String> texts = new ArrayList<>();

        for (WebElement element : elements) {
            texts.add(getText(element).trim());
        }

        return texts;
    }

    public String getAttribute(WebElement element, String attribute) {
        if (!element.getText().isEmpty()) {
            wait10ElementToBeVisible(element);
        }

        return element.getAttribute(attribute);
    }

    protected String getBackgroundColor(WebElement element) {
        wait10ElementToBeVisible(element);

        return element.getCssValue("background-color");
    }

    protected List<WebElement> getListOfVisibleElements(List<WebElement> list, int listSize) {
        List<WebElement> visibleElements = new ArrayList<>();

        if (!(list.size() > 0)) {
            getWait20().until(ExpectedConditions.visibilityOfAllElements(list));
            for (WebElement element : list) {
                if (!element.isDisplayed() && !element.isEnabled()) {
                    getWait20().until(ExpectedConditions.visibilityOf(element));
                    visibleElements.add(element);
                }
            }
        } else if (list.size() == listSize) {

            return  list;
        }

        return visibleElements;
    }

    protected String getFontSize(WebElement element) {
        wait10ElementToBeVisible(element);

        return element.getCssValue("font-size");
    }

    public int getListSize(List<WebElement> list) {

        return list.size();
    }

    protected List<String> getListText(List<WebElement> list) {
        if (list.size() > 0) {
            getWait20().until(ExpectedConditions.visibilityOfAllElements(list));
            List<String> textList = new ArrayList<>();
            for (WebElement element : list) {
                if (element.isEnabled() && element.isDisplayed()) {
                    textList.add(element.getText());
                }
            }

            return textList;
        }

        return new ArrayList<>();
    }

    protected String getBackgroundColorInHEX(WebElement element) {

        return Color.fromString(getBackgroundColor(element)).asHex();
    }

    protected void setNewDimensionOfWindow(int width, int height){
        getDriver().manage().window().setSize(new Dimension(width, height));
    }

    protected void click(WebElement element) {
        wait10ElementToBeVisible(element);
        wait10ElementToBeClickable(element).click();
    }

    protected void goBack() {
        getDriver().navigate().back();
    }

    protected void clickByJavaScript(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    protected void click20(WebElement element) {
        wait20ElementToBeVisible(element);
        wait20ElementToBeClickable(element).click();
    }

    protected void switchToAnotherWindow() {
        String originalWindow = getDriver().getWindowHandle();

        for (String windowHandle : getDriver().getWindowHandles()) {
            if (!originalWindow.equals(windowHandle) && getDriver().getWindowHandles().size() == 2) {
                getDriver().switchTo().window(windowHandle);
                break;
            }
        }
    }

    protected void scrollByVisibleElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    protected void input(String text, WebElement element) {
        element.sendKeys(text);
    }

    protected void inputAndEnter(WebElement element, String text) {
        getWait10().until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(text, Keys.ENTER);
    }

    protected void clickAllElements(List<WebElement> elements) {
        List<WebElement> allElements = new ArrayList<>(elements);

        for (WebElement checkedElement : allElements) {
            if (checkedElement.isEnabled() && checkedElement.isDisplayed()) {
                wait10ElementToBeVisible(checkedElement);
                wait10ElementToBeClickable(checkedElement).click();
            }
        }
    }

    protected void clear(WebElement element) {

        element.clear();
    }

    protected void clickAKey(WebElement element, Keys key) {
        getWait10().until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(key);
    }

    protected void inputText(WebElement element, String text) {
        click(element);
        clear(element);
        input(text, element);
    }

    protected void waitForTextNotToBeEmpty(WebElement element) {
        while (element.getText() == null || element.getText().length() < 1) {
            getWait20().until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(element, "")));
        }
    }

    protected boolean isDisplayedElement(WebElement element) {

        return element.isDisplayed();
    }

    protected boolean allElementsVisibleAndClickable(List<WebElement> elements) {
        List<WebElement> allElements = new ArrayList<>(elements);
        int elementsSize = elements.size();
        int count = 0;

        for (WebElement checkedElement : allElements) {
            if (checkedElement.isEnabled() && checkedElement.isDisplayed()) {
                wait10ElementToBeClickable(checkedElement);
                count++;
            }
        }

        return elementsSize == count;
    }

    protected void wait10ElementToBeVisible(WebElement element) {
        getWait10().until(ExpectedConditions.visibilityOf(element));
    }

    protected void wait20ElementToBeVisible(WebElement element) {
        getWait20().until(ExpectedConditions.visibilityOf(element));
    }

    protected WebElement wait10ElementToBeClickable(WebElement element) {

        return getWait10().until(ExpectedConditions.elementToBeClickable(element));
    }

    protected WebElement wait20ElementToBeClickable(WebElement element) {

        return getWait20().until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void waitForUrlContains(String text) {
        getWait10().until(ExpectedConditions.urlContains(text));
    }

    protected void waitTextToBeChanged(WebElement element, String text) {
        getWait10().until(ExpectedConditions
                .not(ExpectedConditions.textToBePresentInElement(element, text)));
    }

    protected boolean isTextContains(String text, WebElement element) {

        return getText(element).contains(text);
    }

    protected boolean isListContains(String text, List<String> list) {
        boolean result = true;

        for (String s : list) {
            result = result && s.contains(text);
        }

        return result;
    }

    protected boolean isListContains(List<String> shortList, List<String> longList) {
        boolean result = true;

        for (int i = 0; i < shortList.size(); i++) {
            result = result && longList.get(i).contains(shortList.get(i));
        }

        return result;
    }

    protected boolean isElementsInListDisplayed( List<WebElement> list) {
        boolean result = true;

        for (WebElement element : list) {
            result = result && element.isDisplayed();
        }

        return result;
    }

    protected boolean currentUrlIsContainsText(String text) {

        return getDriver().getCurrentUrl().contains(text);
    }

    protected boolean areElementsUnselected(List<WebElement> elements) {
        List<WebElement> allElements = new ArrayList<>(elements);
        int elementsSize = elements.size();
        int count = 0;

        for (WebElement checkbox : allElements) {
            if (!checkbox.findElement(By.cssSelector("*, :after, :before")).isSelected()) {
                count++;
            } else {
                Reporter.log(getText(checkbox), true);
            }
        }

        return elementsSize == count;
    }

    protected int convertStringToInt(String text) {

        return Integer.parseInt(text);
    }

    protected void selectOption(WebElement element, String text) {
        Select option = new Select(element);
        option.selectByValue(text);
    }

    protected boolean isListEqualsExpectedList(List<String> list, List<String> expectedList) {
        boolean equals = false;

        if (list.size() == expectedList.size()) {
            int count = 0;
            for (int i = 0; i < expectedList.size(); i++) {
                if (list.get(i).equals(expectedList.get(i))) {
                    count++;
                }
            }

            if (count == expectedList.size()) {
                equals = true;
            }
        }

        return equals;
    }

    public List<String> getTrimTexts(List<WebElement> elements) {
        List<String> texts = new ArrayList<>();

        for (WebElement element : elements) {
            texts.add(getText(element).trim());
        }

        return texts;
    }

    public void goBack() {
        getDriver().navigate().back();
    }

    protected List<WebElement> getListOfVisibleElements(List<WebElement> list, int listSize) {
        List<WebElement> visibleElements = new ArrayList<>();

        if (!(list.size() > 0)) {
            getWait20().until(ExpectedConditions.visibilityOfAllElements(list));
            for (WebElement element : list) {
                if (!element.isDisplayed() && !element.isEnabled()) {
                    getWait20().until(ExpectedConditions.visibilityOf(element));
                    visibleElements.add(element);
                }
            }
        } else if (list.size() == listSize) {

            return  list;
        }
        return visibleElements;
    }

    //driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
}
