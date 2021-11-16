package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BBCPage {

    public WebDriver driver;


    public BBCPage(WebDriver driver){
        this.driver = driver;

    }

    public String getTitle(){

        return driver.getTitle();

    }

    public WebElement getTopNavigation(){

        return  driver.findElement(By.id("orb-nav-links"));

    }

    public String getDateBbc(){

        return  driver.findElement(By.xpath("//*[@id=\"page\"]/section[2]/h2/text()")).getText();

    }

    public String getUrlNews(){

        return  driver.findElement(By.cssSelector("#orb-nav-links > ul > li.orb-nav-newsdotcom > a")).getAttribute("href");

    }

    public void getSearch(String Search){

        WebElement Element = (WebElement) driver.findElements(By.id("orb-search-q"));
        Element.sendKeys(Search);
        Element.sendKeys(Keys.ENTER);
    }

}
