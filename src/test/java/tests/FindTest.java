package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FindPage;

public class FindTest extends BaseTest {

    @Test
    public void testNavBarSearchField() {
        final String expectedCityName = "Rome";
        final String attributeName = "value";

        String actualCityName = openBaseURL()
                .inputSearchCriteriaIntoSearchFieldAndEnter(expectedCityName)
                .getSearchFieldValue(attributeName);

        Assert.assertEquals(actualCityName, expectedCityName);
    }

    @Test
    public void testEnterValidCityNameSearchFieldTopMenuAndNavigate_toFindPage() {
        final String baseUrl = "https://openweathermap.org/";
        final String cityName = "Barcelona";
        final String attributeName = "value";

        String actualCityNameSearchField_FindPage = openBaseURL()
                .clickAndClearSearchFieldTopMenu()
                .inputSearchCriteriaIntoSearchFieldAndEnter(cityName)
                .getSearchFieldValue(attributeName);

        FindPage findPage = new FindPage(getDriver());

        Assert.assertNotEquals(baseUrl, getDriver().getCurrentUrl());
        Assert.assertEquals(actualCityNameSearchField_FindPage, cityName);
        Assert.assertTrue(findPage.IsContainsCurrentUrlCityName(cityName));
    }
}
