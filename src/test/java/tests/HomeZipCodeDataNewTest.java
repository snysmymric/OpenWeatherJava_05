package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pages.HomeZipCodeDataNewPage;
import utils.ProjectConstants;

import java.util.List;
import java.util.stream.Collectors;


public class HomeZipCodeDataNewTest extends BaseTest {

    @Test
    public void testListOfStatesIsCorrectAndSorted() {
        final List<String> expectedStatesNames = List.of("Alabama", "Alaska", "Arizona", "Arkansas", "California",
                "Colorado", "Connecticut", "Delaware", "District of Columbia", "Florida", "Georgia", "Hawai`i", "Idaho",
                "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts",
                "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire",
                "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon",
                "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont",
                "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming");
        final int expectedStatesAmount = 51;
        final String expectedAttributeValue = "form-group owm-selector mb-0 open";

        HomeZipCodeDataNewPage homeZipCodeDataNewPage = openBaseURL()
                .clickMarketplaceMenu()
                .switchToMarketplaceWindow()
                .clickWeatherDataByStateMenu()
                .clickDropdownSelectStateButton();

        Assert.assertEquals(homeZipCodeDataNewPage.getDropdownSelectCityAttribute(), expectedAttributeValue);

        List<String> actualStatesNames = homeZipCodeDataNewPage.getStatesNames();
        Reporter.log("State list size is " + actualStatesNames.size() + "\nlist of states ---" + actualStatesNames, true);
        List<String> sortedExpectedStates = expectedStatesNames.stream().sorted().collect(Collectors.toList());

        Assert.assertEquals(actualStatesNames.size(), expectedStatesAmount);

        Assert.assertEquals(actualStatesNames, sortedExpectedStates);
    }

    @Test
    public void testListOfYearsIsCorrect() {
        final List<String> expectedYears = List.of("2018", "2019");
        final int expectedAmountOfYears = 2;

        final String expectedAttributeValue = "form-group owm-selector mb-0 open";

        HomeZipCodeDataNewPage homeZipCodeDataNewPage = openBaseURL()
                .clickMarketplaceMenu()
                .switchToMarketplaceWindow()
                .clickWeatherDataByStateMenu()
                .clickDropdownSelectYearButton();

        Assert.assertEquals(homeZipCodeDataNewPage.getDropdownSelectYearAttribute(), expectedAttributeValue);

        List<String> actualYears = homeZipCodeDataNewPage.getYearsNames();
        Reporter.log("Years list size is " + actualYears.size() + "\nlist of years ---" + actualYears, true);

        Assert.assertEquals(actualYears.size(), expectedAmountOfYears);

        Assert.assertEquals(actualYears, expectedYears);
    }

    @Test
    public void testThePriceForEachStateIsCorrect() {
        final String expectedPriceForVirginia = "1300 $";

        String actualPriceForVirginia = openBaseURL()
                .clickMarketplaceMenu()
                .switchToMarketplaceWindow()
                .clickWeatherDataByStateMenu()
                .clickDropdownSelectStateButton()
                .clickOnVirginia()
                .getVirginiaTotalPrice();

        Assert.assertEquals(actualPriceForVirginia, expectedPriceForVirginia);
    }

    @Test
    public void testWeatherParametersAreDisplayed() {
        final List<String> expectedWeatherParameters = List.of("Temperature", "Min temperature",
                "Max temperature", "Feels like", "Wind (speed, direction)", "Pressure", "Humidity", "Clouds",
                "Weather conditions", "Rain", "Snow");

        List<String> actualWeatherParameters = openBaseURL()
                .clickMarketplaceMenu()
                .switchToMarketplaceWindow()
                .clickWeatherDataByStateMenu()
                .getWeatherParameters();

        Assert.assertEquals(actualWeatherParameters, expectedWeatherParameters);
    }

    @Test
    public void testButtonPlaceOrderOpensPopUpWindow() {
        final String expectedPopUpHeader = "Order details";

        String actualPopUpHeader = openBaseURL()
                .clickMarketplaceMenu()
                .switchToMarketplaceWindow()
                .clickWeatherDataByStateMenu()
                .clickDropdownSelectStateButton()
                .pickFloridaState()
                .clickDropdownSelectYearButton()
                .pick2019Year()
                .clickPlaceOrder()
                .waitUntilPlaceOrderPopUpIsVisible()
                .getPlaceOrderPopUpHeader();

        Assert.assertEquals(actualPopUpHeader, expectedPopUpHeader);
    }

    @Test
    public void testAfterPLaceOrderParametersAreCorrect() {
        final String expectedState = "Florida";
        final String expectedYear = "2019";
        final String expectedPrice = "1300 $";

        HomeZipCodeDataNewPage homeZipCodeDataNewPage = openBaseURL()
                .clickMarketplaceMenu()
                .switchToMarketplaceWindow()
                .clickWeatherDataByStateMenu()
                .clickDropdownSelectStateButton()
                .pickFloridaState()
                .clickDropdownSelectYearButton()
                .pick2019Year()
                .clickPlaceOrder()
                .waitUntilPlaceOrderPopUpIsVisible();

        Assert.assertEquals(homeZipCodeDataNewPage.getOrderParametersTexts().get(0), expectedState);
        Assert.assertEquals(homeZipCodeDataNewPage.getOrderParametersTexts().get(1), expectedYear);
        Assert.assertEquals(homeZipCodeDataNewPage.getOrderParametersTexts().get(5), expectedPrice);
    }

    @Test
    public void testPlaceOrderBillingDetails() {
        final String expectedBillingDetailsHeader = "Billing details";
        final String expectedBillingAddressHeader = "Billing address";
        final String firstName = ProjectConstants.getFirstName();
        final String lastName = ProjectConstants.getLastName();
        final String phoneNumber = ProjectConstants.getPhoneNumber();
        final String email = ProjectConstants.getEmail();

        HomeZipCodeDataNewPage homeZipCodeDataNewPage = openBaseURL()
                .clickMarketplaceMenu()
                .switchToMarketplaceWindow()
                .clickWeatherDataByStateMenu()
                .clickDropdownSelectStateButton()
                .pickFloridaState()
                .clickDropdownSelectYearButton()
                .pick2019Year()
                .clickPlaceOrder()
                .waitUntilPlaceOrderPopUpIsVisible()
                .clickNextButton()
                .waitUntilPlaceOrderPopUpIsVisible();

        Assert.assertEquals(homeZipCodeDataNewPage.getBillingDetailsHeader(), expectedBillingDetailsHeader);

        homeZipCodeDataNewPage
                .inputFirstName(firstName)
                .inputLastName(lastName)
                .inputPhone(phoneNumber)
                .inputEmail(email)
                .clickNextButton();

        Assert.assertEquals(homeZipCodeDataNewPage.getBillingAddressHeader(), expectedBillingAddressHeader);
    }
}
