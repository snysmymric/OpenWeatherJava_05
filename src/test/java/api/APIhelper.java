package api;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class APIhelper extends BaseTest {

    @Test
    public void testFindAllBrokenLinks() throws Exception{

        getDriver().get("https://openweathermap.org/");

        List<String> brokenLinks = new ArrayList<>();
        List<WebElement> allLinks = getDriver().findElements(By.tagName("a"));
        for(WebElement link:allLinks){
            try {
                String urlLink = link.getAttribute("href");
                URL url = new URL(urlLink);
                HttpURLConnection httpURLConnect=(HttpURLConnection)url.openConnection();
                httpURLConnect.setConnectTimeout(5000);
                httpURLConnect.connect();

                if (httpURLConnect.getResponseCode() < 400) {
                    continue;
                } else {
                    brokenLinks.add(urlLink);
                };
            }catch (Exception e) {
            }
        }
        Assert.assertTrue(brokenLinks.size() == 0);
    }
}
