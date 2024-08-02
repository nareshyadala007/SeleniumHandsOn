package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WikipediaSearchTest {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        // Set up the Chrome driver
        System.setProperty("webdriver.chrome.driver", "src/main/java/resource/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testSearchFunctionality() {
        // Navigate to Wikipedia homepage
        driver.get("https://www.wikipedia.org/");

        // Locate the search box and enter the search term
        WebElement searchBox = driver.findElement(By.id("searchInput"));
        searchBox.sendKeys("Artificial Intelligence");

        // Click the search button
        WebElement searchButton = driver.findElement(By.xpath("//button[@type='submit']"));
        searchButton.click();

        // Verify that the search results page displays the expected article link
        WebElement articleLink = driver.findElement(By.linkText("Artificial intelligence"));
        Assert.assertTrue(articleLink.isDisplayed(), "The link to the 'Artificial Intelligence' article is not displayed in the search results.");
    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}
