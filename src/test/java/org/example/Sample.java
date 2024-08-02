package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.List;

public class Sample {
    static WebDriver driver;

    public static void main(String... args) {
        System.setProperty("webdriver.chrome.driver", "src/main/java/resource/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        driver.get("https://www.yahoo.com/");

        //Navigation button click
        navBtnClick("Finance");

        navBtnHover("Markets");

        subNavBtnClick("Stocks: Gainers");

        printTopTenRows();

        driver.quit();
    }

    public static void navBtnClick(String navEle) {
          driver.findElement(By.xpath("//nav//a[contains(text(), '" + navEle + "')]")).click();
    }

    public static void navBtnHover(String navEle) {
        WebElement navBtn = driver.findElement(By.xpath("//div[contains(@id,\"ybar-navigation\")]//span[contains(text(),'" + navEle + "')]"));
        Actions action = new Actions(driver);
        action.moveToElement(navBtn).perform();
    }

    public static void subNavBtnClick(String subNavEle) {
        driver.findElement(By.xpath(" //div[contains(@id,\"ybar-navigation\")]//div[contains(text(),'" + subNavEle + "')]")).click();
    }


    private static void printTopTenRows() {
        List<WebElement> rows = driver.findElements(By.xpath("//tbody/tr[position()<11]"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.xpath(".//td"));
            for (WebElement cell : cells) {
                System.out.print(cell.getText() + "\t");
            }
            System.out.println();
        }
    }

}