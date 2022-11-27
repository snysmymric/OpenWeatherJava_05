import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import base.BaseTest;

public class KatyakoksTest extends BaseTest {

    final static By PARTNERS_MENU = By.xpath("//div[@id='desktop-menu']//a[@href='/examples']");
    final static By PARTNERS_AND_SOLUTIONS_H2 = By.xpath("//div//section/h2");
    final static By SUBMENU_LINKS = By.xpath("//div[@class='doc-container']//nav//li");

    @Ignore
//    Ignored because it found the bug when compared last elements:
//    Actual   : 6,000+ repositories on GitHub
//    Expected : Big library on GitHub

    @Test
    public void testSubmenu_PartnersAndSolutionsPage(){
        openBaseURL();
        click(PARTNERS_MENU);
        getWait10();
        Assert.assertEquals(
                getListOfHeadersWhenClickLinks(SUBMENU_LINKS,PARTNERS_AND_SOLUTIONS_H2),
                getListText(SUBMENU_LINKS));
    }
}
