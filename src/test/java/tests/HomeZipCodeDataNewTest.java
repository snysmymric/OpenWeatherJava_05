package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.home.HomeZipCodeDataNewPage;
import utils.ProjectConstants;

import java.util.List;
import java.util.stream.Collectors;

public class HomeZipCodeDataNewTest extends BaseTest {

    @Test
    public void testListOfStatesIsCorrectAndSorted() {
        final List<String> expectedStatesNames = ProjectConstants.getStatesNames();
        final int expectedStatesAmount = 51;

        HomeZipCodeDataNewPage homeZipCodeDataNewPage = openBaseURL()
                .clickMarketplaceMenu()
                .switchToMarketplaceWindow()
                .clickWeatherDataByStateMenu()
                .clickDropdownSelectStateButton();

        List<String> actualStatesNames = homeZipCodeDataNewPage.getStatesNames();
        List<String> sortedExpectedStates = expectedStatesNames.stream().sorted().collect(Collectors.toList());

        Assert.assertEquals(actualStatesNames.size(), expectedStatesAmount);

        Assert.assertEquals(actualStatesNames, sortedExpectedStates);
    }

    @Test
    public void testListOfYearsIsCorrect() {
        final List<String> expectedYears = ProjectConstants.getExpectedYears();
        final int expectedAmountOfYears = 2;

        List<String> actualYears = openBaseURL()
                .clickMarketplaceMenu()
                .switchToMarketplaceWindow()
                .clickWeatherDataByStateMenu()
                .clickDropdownSelectYearButton()
                .getYearsNames();

        Assert.assertEquals(actualYears.size(), expectedAmountOfYears);

        Assert.assertEquals(actualYears, expectedYears);
    }

    @Test
    public void testPriceForEachStateIsCorrect() {
        final String expectedPriceForVirginia = "1300 $";
        final String stateName = "Virginia";

        String actualPriceForVirginia = openBaseURL()
                .clickMarketplaceMenu()
                .switchToMarketplaceWindow()
                .clickWeatherDataByStateMenu()
                .clickDropdownSelectStateButton()
                .clickState(stateName)
                .getVirginiaTotalPrice();

        Assert.assertEquals(actualPriceForVirginia, expectedPriceForVirginia);
    }

    @Test
    public void testWeatherParametersAreDisplayed() {
        final List<String> expectedWeatherParameters = ProjectConstants.getWeatherParameters();

        List<String> actualWeatherParameters = openBaseURL()
                .clickMarketplaceMenu()
                .switchToMarketplaceWindow()
                .clickWeatherDataByStateMenu()
                .getWeatherParameters();

        Assert.assertEquals(actualWeatherParameters, expectedWeatherParameters);
    }

    @Test
    public void test_PlaceOrderButton_OpensPopUpWindow() {
        final String expectedPopUpHeader = "Order details";
        final String state = "Florida";
        final String year = "2019";

        HomeZipCodeDataNewPage homeZipCodeDataNewPage = openBaseURL()
                .clickMarketplaceMenu()
                .switchToMarketplaceWindow()
                .clickWeatherDataByStateMenu()
                .clickDropdownSelectStateButton()
                .clickState(state)
                .clickDropdownSelectYearButton()
                .clickYear(year)
                .clickPlaceOrder()
                .waitUntilPlaceOrderPopUpIsVisible();

        String actualPopUpHeader = homeZipCodeDataNewPage.getPlaceOrderPopUpHeader();

        Assert.assertTrue(homeZipCodeDataNewPage.isOrderPopUpDisplayed());

        Assert.assertEquals(actualPopUpHeader, expectedPopUpHeader);
    }

    @Test
    public void testUI_OrderDetailsPopUpWindow() {
        final String state = "Florida";
        final String year = "2019";
        final String expectedValue = "current";

        HomeZipCodeDataNewPage homeZipCodeDataNewPage = openBaseURL()
                .clickMarketplaceMenu()
                .switchToMarketplaceWindow()
                .clickWeatherDataByStateMenu()
                .clickDropdownSelectStateButton()
                .clickState(state)
                .clickDropdownSelectYearButton()
                .clickYear(year)
                .clickPlaceOrder()
                .waitUntilPlaceOrderPopUpIsVisible();

        Assert.assertEquals(homeZipCodeDataNewPage.getAttributeTopPopUpWindow1(), expectedValue);

        Assert.assertTrue(homeZipCodeDataNewPage.isNextButtonVisible());
    }

    @Test
    public void test_OrderDetailsPopUpWindow_ParametersAreCorrect() {
        final String expectedState = "Florida";
        final String expectedYear = "2019";
        final String expectedPrice = "1300 $";
        final String expectedFileFormat = "CSV";
        final String expectedUnits = "Standard (Kelvin, hPa, meter/sec)";

        List<String> expectedData = List.of(
                expectedState,
                expectedYear,
                ProjectConstants.getWeatherParametersAsString(),
                expectedFileFormat,
                expectedUnits,
                expectedPrice);

        HomeZipCodeDataNewPage homeZipCodeDataNewPage = openBaseURL()
                .clickMarketplaceMenu()
                .switchToMarketplaceWindow()
                .clickWeatherDataByStateMenu()
                .clickDropdownSelectStateButton()
                .clickState(expectedState)
                .clickDropdownSelectYearButton()
                .clickYear(expectedYear)
                .clickPlaceOrder()
                .waitUntilPlaceOrderPopUpIsVisible();

        Assert.assertEquals(homeZipCodeDataNewPage.getOrderParametersTexts(), expectedData);
    }

    @Test
    public void testUI_BillingDetailsPopUpWindow() {
        final String expectedBillingDetailsHeader = "Billing details";
        final String expectedValue = "current";
        final String state = "Florida";
        final String year = "2019";

        HomeZipCodeDataNewPage homeZipCodeDataNewPage = openBaseURL()
                .clickMarketplaceMenu()
                .switchToMarketplaceWindow()
                .clickWeatherDataByStateMenu()
                .clickDropdownSelectStateButton()
                .clickState(state)
                .clickDropdownSelectYearButton()
                .clickYear(year)
                .clickPlaceOrder()
                .waitUntilPlaceOrderPopUpIsVisible()
                .clickNextButton()
                .waitUntilPlaceOrderPopUpIsVisible();

        Assert.assertEquals(homeZipCodeDataNewPage.getAttributeTopPopUpWindow2(), expectedValue);

        Assert.assertEquals(homeZipCodeDataNewPage.getBillingDetailsHeader(), expectedBillingDetailsHeader);
    }

    @Test
    public void test_BillingDetailsPopUpWindow_FillingIndividualForms() {
        final String expectedBillingAddressHeader = "Billing address";
        final String expectedValue = "current";
        final String expectedState = "Florida";
        final String expectedYear = "2019";
        final String firstName = "Tom";
        final String lastName = "Sawyer";
        final String phoneNumber = "1234567890";
        final String email = "tomsawyer@gmail.com";

        HomeZipCodeDataNewPage homeZipCodeDataNewPage = openBaseURL()
                .clickMarketplaceMenu()
                .switchToMarketplaceWindow()
                .clickWeatherDataByStateMenu()
                .clickDropdownSelectStateButton()
                .clickState(expectedState)
                .clickDropdownSelectYearButton()
                .clickYear(expectedYear)
                .clickPlaceOrder()
                .waitUntilPlaceOrderPopUpIsVisible()
                .clickNextButton()
                .waitUntilPlaceOrderPopUpIsVisible()
                .inputFirstName(firstName)
                .inputLastName(lastName)
                .inputPhone(phoneNumber)
                .inputEmail(email)
                .clickNextButton();

        Assert.assertEquals(homeZipCodeDataNewPage.getAttributeTopPopUpWindow3(), expectedValue);

        Assert.assertEquals(homeZipCodeDataNewPage.getBillingAddressHeader(), expectedBillingAddressHeader);
    }

    @Test
    public void testEmptyFirstName_BillingDetailsPopUpWindow() {
        final String expectedState = "Florida";
        final String expectedYear = "2019";
        final String lastName = "Sawyer";
        final String phoneNumber = "1234567890";
        final String email = "tomsawyer@gmail.com";
        final String expectedFirstName = "";
        final String expectedErrorMessageText = "Cannot be blank";

        HomeZipCodeDataNewPage homeZipCodeDataNewPage = openBaseURL()
                .clickMarketplaceMenu()
                .switchToMarketplaceWindow()
                .clickWeatherDataByStateMenu()
                .clickDropdownSelectStateButton()
                .clickState(expectedState)
                .clickDropdownSelectYearButton()
                .clickYear(expectedYear)
                .clickPlaceOrder()
                .waitUntilPlaceOrderPopUpIsVisible()
                .clickNextButton()
                .waitUntilPlaceOrderPopUpIsVisible()
                .inputLastName(lastName)
                .inputPhone(phoneNumber)
                .inputEmail(email)
                .clickNextButton();

        String actualErrorMessageText = homeZipCodeDataNewPage.getErrorMessageText();

        Assert.assertEquals(homeZipCodeDataNewPage.getFirstNameFieldText(), expectedFirstName);

        Assert.assertEquals(actualErrorMessageText, expectedErrorMessageText);

        Assert.assertEquals(homeZipCodeDataNewPage.isNextButtonSubmiting(), false);
    }

    @Test
    public void testEmptyLastName_BillingDetailsPopUpWindow() {
        final String expectedState = "Florida";
        final String expectedYear = "2019";
        final String firstName = "Tom";
        final String expectedLastName = "";
        final String phoneNumber = "1234567890";
        final String email = "tomsawyer@gmail.com";

        final String expectedErrorMessageText = "Cannot be blank";

        HomeZipCodeDataNewPage homeZipCodeDataNewPage = openBaseURL()
                .clickMarketplaceMenu()
                .switchToMarketplaceWindow()
                .clickWeatherDataByStateMenu()
                .clickDropdownSelectStateButton()
                .clickState(expectedState)
                .clickDropdownSelectYearButton()
                .clickYear(expectedYear)
                .clickPlaceOrder()
                .waitUntilPlaceOrderPopUpIsVisible()
                .clickNextButton()
                .waitUntilPlaceOrderPopUpIsVisible()
                .inputPhone(phoneNumber)
                .inputEmail(email)
                .clickNextButton();

        String actualErrorMessageText = homeZipCodeDataNewPage.getErrorMessageText();

        Assert.assertEquals(homeZipCodeDataNewPage.getLastNameFieldText(), expectedLastName);

        Assert.assertEquals(actualErrorMessageText, expectedErrorMessageText);

        Assert.assertEquals(homeZipCodeDataNewPage.isNextButtonSubmiting(), false);
    }

    @Test
    public void testEmptyPhoneNumber_BillingDetailsPopUpWindow() {
        final String expectedState = "Florida";
        final String expectedYear = "2019";
        final String expectedFirstName = "Tom";
        final String lastName = "Sawyer";
        final String expectedPhoneNumber = "";
        final String email = "tomsawyer@gmail.com";

        final String expectedErrorMessageText = "Cannot be blank";

        HomeZipCodeDataNewPage homeZipCodeDataNewPage = openBaseURL()
                .clickMarketplaceMenu()
                .switchToMarketplaceWindow()
                .clickWeatherDataByStateMenu()
                .clickDropdownSelectStateButton()
                .clickState(expectedState)
                .clickDropdownSelectYearButton()
                .clickYear(expectedYear)
                .clickPlaceOrder()
                .waitUntilPlaceOrderPopUpIsVisible()
                .clickNextButton()
                .waitUntilPlaceOrderPopUpIsVisible()
                .inputLastName(lastName)
                .inputEmail(email)
                .clickNextButton();

        String actualErrorMessageText = homeZipCodeDataNewPage.getErrorMessageText();

        Assert.assertEquals(homeZipCodeDataNewPage.getPhoneNumberFieldText(), expectedPhoneNumber);

        Assert.assertEquals(actualErrorMessageText, expectedErrorMessageText);

        Assert.assertEquals(homeZipCodeDataNewPage.isNextButtonSubmiting(), false);
    }

    @Test
    public void testEmptyEmail_BillingDetailsPopUpWindow() {
        final String expectedState = "Florida";
        final String expectedYear = "2019";
        final String expectedFirstName = "Tom";
        final String lastName = "Sawyer";
        final String phoneNumber = "1234567890";
        final String expectedEmail = "";

        final String expectedErrorMessageText = "Cannot be blank";

        HomeZipCodeDataNewPage homeZipCodeDataNewPage = openBaseURL()
                .clickMarketplaceMenu()
                .switchToMarketplaceWindow()
                .clickWeatherDataByStateMenu()
                .clickDropdownSelectStateButton()
                .clickState(expectedState)
                .clickDropdownSelectYearButton()
                .clickYear(expectedYear)
                .clickPlaceOrder()
                .waitUntilPlaceOrderPopUpIsVisible()
                .clickNextButton()
                .waitUntilPlaceOrderPopUpIsVisible()
                .inputLastName(lastName)
                .inputPhone(phoneNumber)
                .clickNextButton();

        String actualErrorMessageText = homeZipCodeDataNewPage.getErrorMessageText();

        Assert.assertEquals(homeZipCodeDataNewPage.getEmailFieldText(), expectedEmail);

        Assert.assertEquals(actualErrorMessageText, expectedErrorMessageText);

        Assert.assertEquals(homeZipCodeDataNewPage.isNextButtonSubmiting(), false);
    }


}
