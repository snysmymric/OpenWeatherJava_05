package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

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
}
