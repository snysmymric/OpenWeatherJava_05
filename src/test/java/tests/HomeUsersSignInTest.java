package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomeUsersSignInTest extends BaseTest {

    @Test
    public void testSignOut() {
        String actualSignOutMessage = openBaseURL()
                .signIn()
                .signOut()
                .getNotification();

        Assert.assertEquals(actualSignOutMessage, "You need to sign in or sign up before continuing.");
    }
}
