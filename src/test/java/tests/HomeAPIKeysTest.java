package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import pages.HomeAPIKeysPage;

import java.util.UUID;

public class HomeAPIKeysTest extends BaseTest {

    final static String API_KEYS_NAME = String.valueOf(UUID.randomUUID()).substring(0, 8);

    @Test
    public void testAPIKeysGenerated() {
        final String expectedNotificationMessage = "API key was created successfully";
        final String expectedAPIKeyStatus = "Active";

        HomeAPIKeysPage homeAPIKeysPage = openBaseURL()
                .signIn()
                .clickAPIKeysTab();

        int oldAmountOfExistingKeys = homeAPIKeysPage.getAmountOfExistingAPIKeys();

        homeAPIKeysPage
                .clickAPIKeyNameField()
                .inputNewName(API_KEYS_NAME)
                .clickGenerateKeysButton();

        Assert.assertEquals(homeAPIKeysPage.getAmountOfExistingAPIKeys(), oldAmountOfExistingKeys + 1);
        Assert.assertEquals(homeAPIKeysPage.getLastAPIKeyName(), API_KEYS_NAME);
        Assert.assertEquals(homeAPIKeysPage.getLastAPIKeyStatus(), expectedAPIKeyStatus);
        Assert.assertEquals(homeAPIKeysPage.confirmMessageAppears(), expectedNotificationMessage);
    }

    @Ignore

    @Test(dependsOnMethods = "testAPIKeysGenerated")
    public void testAPIKeysDeleted() {

        final String expectedNotificationMessage = "API key was deleted successfully";

        HomeAPIKeysPage homeAPIKeysPage = openBaseURL()
                .signIn()
                .clickAPIKeysTab();

        int oldAmountOfExistingKeys = homeAPIKeysPage.getAmountOfExistingAPIKeys();

        homeAPIKeysPage
                .deleteLastAPIKey()
                .confirmAPIKeyDeletion();

        Assert.assertEquals(homeAPIKeysPage.getAmountOfExistingAPIKeys(), oldAmountOfExistingKeys - 1);
        Assert.assertNotEquals(homeAPIKeysPage.getLastAPIKeyName(), API_KEYS_NAME);
        Assert.assertEquals(homeAPIKeysPage.confirmationMessageDeleted(), expectedNotificationMessage);
    }
}
