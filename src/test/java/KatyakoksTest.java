//import org.openqa.selenium.By;
//import org.testng.Assert;
//import org.testng.annotations.Ignore;
//import org.testng.annotations.Test;
//import base.BaseTest;
//
//@Ignore
//public class KatyakoksTest extends BaseTest {
//
//    final static By PARTNERS_MENU = By.xpath("//div[@id='desktop-menu']//a[@href='/examples']");
//    final static By PARTNERS_AND_SOLUTIONS_H2 = By.xpath("//div//section//h2");
//    final static By SUBMENU_LIST = By.xpath("//div[@class='doc-container']/div/nav/ul/li");
//
//    @Test
//    public void testSubmenu_PartnersAndSolutionsPage(){
//        openBaseURL();
//        click(PARTNERS_MENU);
//        waitElementToBeVisible(SUBMENU_LIST);
//        waitElementToBeVisible(PARTNERS_AND_SOLUTIONS_H2);
//        Assert.assertEquals(getListText(SUBMENU_LIST), getListText(PARTNERS_AND_SOLUTIONS_H2));
//    }
//}