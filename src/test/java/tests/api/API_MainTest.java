package tests.api;

import api.Alert;
import api.ApiHelpers;
import api.CaptureNetworkTraffic;
import base.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MainPage;
import utils.DateTimeUtils;

import java.io.IOException;
import java.net.*;
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
    final static String PARIS_URL = "https://openweathermap.org/data/2.5/"
            + "onecall?lat=48.8534&lon=2.3488&units=metric&appid=439d4b804bc8187953eb36d2a8c26a02";

    @Test
    public void test_API_CNTRequest_OpenBaseURL() {
        List<String> requests = new CaptureNetworkTraffic()
                .setUpDevTool(getDriver())
                .captureHttpRequestsContain("weather");

        openBaseURL();

        Assert.assertNotNull(requests);
        for (int i = 0; i < requests.size(); i += 2) {
            Assert.assertEquals(requests.get(i), "GET");
        }
        for (int i = 1; i < requests.size(); i += 2) {
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
        for (int i = 0; i < responses.size(); i += 4) {
            Assert.assertEquals(responses.get(i), "200");
        }
        for (int i = 1; i < responses.size(); i += 4) {
            Assert.assertEquals(responses.get(i), "OK");
        }
        for (int i = 2; i < responses.size(); i += 4) {
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
                .inputSearchCriteria("Paris")
                .clickSearchButton();

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

    @Test
    public void test_API_HttpRequestResponse_WhenSearchingCityCountry() {
        try {
            final HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(PARIS_URL))
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

        final JSONObject obj = new JSONObject(response.body());
        String alertText = obj.getJSONArray("alerts").getJSONObject(0).getString("event");

        String alertTextFromUI = openBaseURL()
                .clickSearchCityField()
                .inputSearchCriteria("Paris")
                .clickSearchButton()
                .clickParisInDropDownList()
                .getWeatherAlertText();

        if (alertText != null && !alertText.isEmpty() && !alertText.isBlank()) {
            Assert.assertEquals(alertTextFromUI.trim(), alertText.trim());
        }
    }

    @Test
    public void test_API_RAResponse_WhenSearchingCityCountry() {
        final List<Alert> alerts = given()
                .when()
                .contentType(ContentType.JSON)
                .get(PARIS_URL)
                .then().log().all()
                .extract().body().jsonPath().getList("alerts", Alert.class);

        for (Alert alert : alerts) {
            alertText = alert.getEvent();
        }

        String alertTextFromUI = openBaseURL()
                .clickSearchCityField()
                .inputSearchCriteria("Paris")
                .clickSearchButton()
                .clickParisInDropDownList()
                .getWeatherAlertText();

        if (alertText != null && !alertText.isEmpty() && !alertText.isBlank()) {
            Assert.assertEquals(alertTextFromUI.trim(), alertText.trim());
        }
    }

    @Test
    public void testRAResponse_8DayForecastWeatherDescription() {
        final String cityName = "Paris";

        final Response response = given()
                .when()
                .contentType(ContentType.JSON)
                .get(PARIS_URL)
                .then()
                .log().all()
                .extract().response();

        final List<JSONObject> daily = response.jsonPath().get("daily");

        for (int i = 0; i < daily.size(); i++) {
            weatherDescriptionList.add(
                    response.jsonPath()
                            .get(String.format("daily[%d].weather[0].description", i))
            );
        }

        final String oldCityName = openBaseURL().getCityCountryName();

        final List<String> weatherDescriptionFromUI = new MainPage(getDriver())
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
            final HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(PARIS_URL))
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

        final JSONObject obj = new JSONObject(response.body());

        final String expectedApiResult = ApiHelpers.getEightDaysForecastCalendarWithSeparator(obj).replaceAll("..$", "");

        final String oldCityName = openBaseURL().getCityCountryName();

        final List<String> uiResult = new MainPage(getDriver())
                .clickSearchCityField()
                .inputSearchCriteria("Paris")
                .clickSearchButton()
                .clickParisInDropDownList()
                .waitForCityCountryNameChanged(oldCityName)
                .getListOfEightDaysDataText();

        String actualUIResult = ApiHelpers.getFormattedResult(uiResult);

        if (!actualUIResult.isEmpty() && !actualUIResult.isBlank()) {
            Assert.assertEquals(actualUIResult, expectedApiResult);
        }
    }

    @Test
    public void test_UIEightDaysForecastCalendarOnCurrentDateFrom_API_HttpResponse() {
        try {
            final HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(PARIS_URL))
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

        final JSONObject obj = new JSONObject(response.body());

        final String[] dowMonDate = ApiHelpers.getEightDaysForecastCalendar(obj).trim().split(" ");
        final String dowText = dowMonDate[0];
        final int monNum = DateTimeUtils.returnMonth(dowMonDate[1]);
        final int date = Integer.parseInt(dowMonDate[2]);

        final String expectedApiResult =
                DateTimeUtils
                        .getEightDaysFromDate(dowText, monNum, date, Year.now()
                                .getValue());

        final String oldCityName = openBaseURL().getCityCountryName();

        final List<String> uiResult = new MainPage(getDriver())
                .clickSearchCityField()
                .inputSearchCriteria("Paris")
                .clickSearchButton()
                .clickParisInDropDownList()
                .waitForCityCountryNameChanged(oldCityName)
                .getListOfEightDaysDataText();

        String actualUiResult = ApiHelpers.getFormattedResult(uiResult);

        if (!actualUiResult.isEmpty() && !actualUiResult.isBlank()) {
            Assert.assertEquals(actualUiResult, expectedApiResult);
        }
    }

    @Test
    public void testAllLinksAreNotBroken() {
        final List<WebElement> allLinks = openBaseURL().getAllLinks();

        List<String> brokenLinks = new ArrayList<>();

        for (WebElement link : allLinks) {
            String urlLink = link.getAttribute("href");

            try {
                URL url = new URL(urlLink);

                HttpURLConnection httpURLConnect = (HttpURLConnection) url.openConnection();
                httpURLConnect.setConnectTimeout(5000);
                httpURLConnect.connect();

                if (httpURLConnect.getResponseCode() >= 400) {
                    brokenLinks.add(urlLink);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Assert.assertEquals(brokenLinks.size(), 0);
    }
}
