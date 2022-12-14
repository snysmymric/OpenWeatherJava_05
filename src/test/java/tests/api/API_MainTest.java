package tests.api;

import api.Alert;
import api.CaptureNetworkTraffic;
import base.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import pages.MainPage;
import utils.DateTimeUtils;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import static io.restassured.RestAssured.given;

public class API_MainTest extends BaseTest {
    static HttpResponse<String> response;
    static String alertText;
    static List<String> weatherDescriptionList = new ArrayList<>();

    @Test
    public void test_API_CNTRequest_OpenBaseURL() {

        List<String> requests = new CaptureNetworkTraffic()
                .setUpDevTool(getDriver())
                .captureHttpRequestsContain("weather");

        openBaseURL();

        Assert.assertNotNull(requests);
        for(int i = 0; i < requests.size(); i += 2) {
            Assert.assertEquals(requests.get(i), "GET");
        }
        for(int i = 1; i < requests.size(); i += 2) {
            Assert.assertTrue(requests.get(i).contains("openweathermap.org/"));
        }
    }

    @Test
    public void test_API_CNTResponse_OpenBaseURL() {

        List<String> responses = new CaptureNetworkTraffic()
                .setUpDevTool(getDriver())
                .captureHttpResponsesContain("weather");

        openBaseURL();

        Assert.assertNotNull(responses);
        for(int i = 0; i < responses.size(); i += 4) {
            Assert.assertEquals(responses.get(i), "200");
        }
        for(int i = 1; i < responses.size(); i += 4) {
            Assert.assertEquals(responses.get(i), "OK");
        }
        for(int i = 2; i < responses.size(); i += 4) {
            Assert.assertTrue(responses.get(i).contains("openweathermap.org/"));
        }

        Assert.assertTrue(Double.parseDouble(responses.get(3).substring(10, 14)) <= 3);
    }

    @Test
    public void test_API_CNTRequests_WhenSearchingCityCountry() {
        List<String> requestsSearchButton = new CaptureNetworkTraffic()
                .setUpDevTool(getDriver())
                .captureHttpRequestsContain("weather");

        MainPage mainPage = openBaseURL()
                .clickSearchCityField()
                .inputSearchCriteria("Paris");

        mainPage.clickSearchButton();

        Assert.assertNotNull(requestsSearchButton);
        Assert.assertEquals(requestsSearchButton.get(requestsSearchButton.size() - 2), "GET");
        Assert.assertTrue(requestsSearchButton.get(requestsSearchButton.size() - 1)
                .contains("openweathermap.org/data/2.5/find?q=Paris"));

        mainPage.clickParisInDropDownList();

        Assert.assertNotNull(requestsSearchButton);
        Assert.assertEquals(requestsSearchButton.get(requestsSearchButton.size() - 2), "GET");
        Assert.assertTrue(requestsSearchButton.get(requestsSearchButton.size() - 1)
                .contains("openweathermap.org/data/2.5/onecall?lat=48.8534&lon=2.3488"));
    }

    @Ignore
    @Test
    public void test_API_HttpRequestResponse_WhenSearchingCityCountry() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://openweathermap.org/data/2.5"
                            + "/onecall?lat=48.8534&lon=2.3488&units=metric&appid=439d4b804bc8187953eb36d2a8c26a02"))
                    .GET()
                    .build();

            response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println(response.body());

        } catch (URISyntaxException | InterruptedException | IOException e) {
            e.printStackTrace();
        }

        Assert.assertNotNull(response);
        Assert.assertNotNull(response.body());
        Assert.assertEquals(response.statusCode(), 200);

        JSONObject obj = new JSONObject(response.body());
        String alertText = obj.getJSONArray("alerts").getJSONObject(0).getString("event");

        String alertTextFromUI = openBaseURL()
                .clickSearchCityField()
                .inputSearchCriteria("Paris").clickSearchButton()
                .clickParisInDropDownList().getWeatherAlertText();

        if(alertText != null && !alertText.isEmpty() && !alertText.isBlank()) {
            Assert.assertEquals(alertTextFromUI.trim(), alertText.trim());
        }
    }

    @Ignore
    @Test
    public void test_API_RAResponse_WhenSearchingCityCountry() {
        final String url = "https://openweathermap.org/data/2.5"
                + "/onecall?lat=48.8534&lon=2.3488&units=metric&appid=439d4b804bc8187953eb36d2a8c26a02";

        List<Alert> alerts = given()
                .when()
                .contentType(ContentType.JSON)
                .get(url)
                .then().log().all()
                .extract().body().jsonPath().getList("alerts", Alert.class);
        
        for (Alert alert : alerts) {
            alertText = alert.getEvent();
        }

        String alertTextFromUI = openBaseURL()
                .clickSearchCityField()
                .inputSearchCriteria("Paris").clickSearchButton()
                .clickParisInDropDownList().getWeatherAlertText();

        if(alertText != null && !alertText.isEmpty() && !alertText.isBlank()) {
            Assert.assertEquals(alertTextFromUI.trim(), alertText.trim());
        }
    }

    @Test
    public void testRAResponse_8DayForecastWeatherDescription() {
        final String URL = "https://openweathermap.org/data/2.5/onecall?lat=48.8534&lon=2.3488&units=metric&appid=439d4b804bc8187953eb36d2a8c26a02";
        final String cityName = "Paris";

        Response response = given()
                .when()
                .contentType(ContentType.JSON)
                .get(URL)
                .then()
                .log().all()
                .extract().response();

        List<JSONObject> daily = response.jsonPath().get("daily");

        for (int i = 0; i < daily.size(); i++) {
            weatherDescriptionList.add(response.jsonPath().get(String.format("daily[%d].weather[0].description", i)));
        }

        String oldCityName = openBaseURL().getCityCountryName();
        List<String> weatherDescriptionFromUI = new MainPage(getDriver())
                .clickSearchCityField()
                .inputSearchCriteria(cityName)
                .clickSearchButton()
                .clickParisInDropDownList()
                .waitForCityCountryNameChanged(oldCityName)
                .getListWeatherDescriptionText();

        if (!daily.isEmpty() && !weatherDescriptionFromUI.isEmpty() && daily.size() == weatherDescriptionFromUI.size()) {
            Assert.assertEquals(weatherDescriptionFromUI, weatherDescriptionList);
        }
    }

    @Test
    public void test_API_HttpResponse_AndUIView_OfEightDaysForecastCalendar() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://openweathermap.org/data/2.5/onecall?lat=48.8534&lon=2.3488&units=metric&appid=439d4b804bc8187953eb36d2a8c26a02"))
                    .GET()
                    .build();

            response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());

        } catch (URISyntaxException | InterruptedException | IOException e) {
            e.printStackTrace();
        }

        Assert.assertNotNull(response);
        Assert.assertNotNull(response.body());
        Assert.assertEquals(response.statusCode(), 200);

        JSONObject obj = new JSONObject(response.body());

        StringBuilder eightDaysForecastCalendar = new StringBuilder();

        String separator;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 3; j++) {
                separator = (j == 1) ? " " : ", ";

                eightDaysForecastCalendar.append(
                        new java.util.Date((long) ((obj.getJSONArray("daily").getJSONObject(i).getLong("dt")) * 1000))
                                .toString().split(" ")[j]).append(separator);
            }
        }

        String expectedApiResult = eightDaysForecastCalendar.toString().replaceAll("..$", "");

        String oldCityName = openBaseURL().getCityCountryName();

        String actualUiResult = new MainPage(getDriver())
                .clickSearchCityField()
                .inputSearchCriteria("Paris")
                .clickSearchButton()
                .clickParisInDropDownList()
                .waitForCityCountryNameChanged(oldCityName)
                .getListOfEightDaysDataText().toString().replaceAll(".$", "").replaceAll("^.", "");

        if (actualUiResult != null && !actualUiResult.isEmpty() && !actualUiResult.isBlank()) {
            Assert.assertEquals(actualUiResult, expectedApiResult);
        }
    }

    @Test
    public void test_UIEightDaysForecastCalendarOnCurrentDateFrom_API_HttpResponse() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://openweathermap.org/data/2.5/onecall?lat=48.8534&lon=2.3488&units=metric&appid=439d4b804bc8187953eb36d2a8c26a02"))
                    .GET()
                    .build();

            response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());

        } catch (URISyntaxException | InterruptedException | IOException e) {
            e.printStackTrace();
        }

        Assert.assertNotNull(response);
        Assert.assertNotNull(response.body());
        Assert.assertEquals(response.statusCode(), 200);

        JSONObject obj = new JSONObject(response.body());

        StringBuilder eightDaysForecastCalendar = new StringBuilder();

        for (int j = 0; j < 3; j++) {

            eightDaysForecastCalendar.append(
                    new java.util.Date((long) ((obj.getJSONArray("daily").getJSONObject(0).getLong("dt")) * 1000))
                            .toString().split(" ")[j]).append(" ");
        }

        final String[] dowMonDate = eightDaysForecastCalendar.toString().trim().split(" ");
        final String dowText = dowMonDate[0];
        final int monNum = DateTimeUtils.returnMonth(dowMonDate[1]);
        final int date = Integer.parseInt(dowMonDate[2]);

        String expectedApiResult = DateTimeUtils.getEightDaysFromDate(dowText, monNum, date, Year.now().getValue());

        String oldCityName = openBaseURL().getCityCountryName();

        String actualUiResult = new MainPage(getDriver())
                .clickSearchCityField()
                .inputSearchCriteria("Paris")
                .clickSearchButton()
                .clickParisInDropDownList()
                .waitForCityCountryNameChanged(oldCityName)
                .getListOfEightDaysDataText().toString().replaceAll(".$", "").replaceAll("^.", "");

        if (actualUiResult != null && !actualUiResult.isEmpty() && !actualUiResult.isBlank()) {
            Assert.assertEquals(actualUiResult, expectedApiResult);
        }
    }

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
