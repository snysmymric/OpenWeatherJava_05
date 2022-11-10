import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class AnzhelikaBaaTest extends BaseTest {
    @Test
    public void testMessageReCaptchaFailed_WhenNoCaptchaConfirmed() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String eMail = "tester@test.com";
        String subject = "Other";
        String message = "Hello";
        String expectedResult = "reCAPTCHA verification failed, please try again.";

        getDriver().manage().window().maximize();
        getDriver().get(url);
        Thread.sleep(10000);

        WebElement menuSupport = getDriver().findElement(By.xpath("//div[@id='support-dropdown']"));
        menuSupport.click();

        WebElement dDownAskAQuestion = getDriver().findElement(
                By.xpath("//ul[@id='support-dropdown-menu']/li[3]/a[@href='https://home.openweathermap.org/questions']")
        );
        dDownAskAQuestion.click();

        for (String winHandle: getDriver().getWindowHandles()) {
            getDriver().switchTo().window(winHandle);
        }
        WebElement emailField = getDriver().findElement(
                By.xpath("//div[@class='col-sm-8']/input[@id='question_form_email']"));
        emailField.click();
        emailField.sendKeys(eMail);

        WebElement subjectField = getDriver().findElement(By.id("question_form_subject"));
        subjectField.click();
        subjectField.sendKeys(subject);

        WebElement messageField = getDriver().findElement(By.id("question_form_message"));
        messageField.click();
        messageField.sendKeys(message);

        WebElement submit = getDriver().findElement(By.xpath("//input[@class='btn btn-default']"));
        submit.click();

        WebElement reCaptcha = getDriver().findElement(By.xpath("//div[@class='help-block']"));

        String actualResult = reCaptcha.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }
}
