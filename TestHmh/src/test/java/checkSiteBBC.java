import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobjects.BBCPage;
import pageobjects.SearchPage;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class checkSiteBBC {

    private WebDriver driver;
    private final String baseUrl = "https://www.bbc.com/";
    private final String driverPath = "C:/chromedriver.exe";
    private BBCPage receivedPage;
    private SearchPage resultPage;

    @BeforeAll
    public void config(){
        System.setProperty("webdriver.chrome.driver",driverPath);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @AfterAll
    public void tearDown () {
        driver.close();
    }

    @Test
    @DisplayName("When get  site then return if the title is correct")
    public void checkTitle(){

        driver.get(baseUrl);
        String TitleExpected = "BBC - Homepage";
        String TitleReceived;

        receivedPage = new BBCPage(driver);
        TitleReceived = receivedPage.getTitle();

        assertThat(TitleReceived,containsString(TitleExpected));

    }

    @Test
    @DisplayName("When get  site BBC then check if return the top navigation")
    public void checkTopNavigation(){

        driver.get(baseUrl);
        WebElement TopNavigation;

        receivedPage = new BBCPage(driver);
        TopNavigation = receivedPage.getTopNavigation();

        assertThat(TopNavigation,is(notNullValue()));

    }

    @Test
    @DisplayName("When get  site BBC then check if return the correct Date time")
    public void checkDateTime(){

        driver.get(baseUrl);
        String DateBbc;
        String DateNow;
        Date  Now = new Date();
        receivedPage = new BBCPage(driver);
        DateBbc = receivedPage.getDateBbc();
        DateNow = Now.toString();
       assertThat(DateBbc,containsString(DateNow));

    }

    @Test
    @DisplayName("When go top sub site NEWS then check if return the correct URL")
    public void checkURLNews(){


        driver.get(baseUrl);
        String URLNews = "https://www.bbc.com/news";
        String URLReceived;

        receivedPage = new BBCPage(driver);
        URLReceived = receivedPage.getUrlNews();

        assertThat(URLReceived,containsString(URLNews));

    }

    @Test
    @DisplayName("When do a search in BBC site  then check if return the correct search ")
    public void checkSearch (){


        driver.get(baseUrl);
        String Search  = "Houghton Mifflin Harcourt";
        receivedPage = new BBCPage(driver);

        receivedPage.getSearch(Search);
        List<WebElement> elements = driver.findElements(By.cssSelector("#main-content > div:nth-child(1) > div.ssrcss-1v7bxtk-StyledContainer.enjd40x0 > div > div > ul"));
        assertThat(elements.isEmpty(),is(false));

    }


}
