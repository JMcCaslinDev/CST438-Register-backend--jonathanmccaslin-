package com.cst438;

import java.lang.Thread;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

public class RegistrationEndToEndTest {

    WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void registrationTest() {
        // Navigate to the website
        String url = "http://localhost:3000/";
        driver.get(url);

        // TODO: Add your test steps here

        // Example assertion
        System.out.println("Assert True For React App");
        System.out.println(driver.getTitle());
        assertTrue(driver.getTitle().contains("React App"));
        
        
        // Wait for the "Add New Student" button to become clickable
        System.out.println("Waiting 2 seconds for the button to become clickable.");
        WebDriverWait wait = new WebDriverWait(driver, 10); //will wait up to 10 seconds until throw error if found will click.
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.className("MuiButton-root")));
       
        // Click on the button
        button.click();
        
     // Find the name and email input fields in the popup
        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("name")));
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));

        // Enter sample data into the name and email fields
        nameField.sendKeys("Smith John");
        emailField.sendKeys("smith.john@example.com");

        // Close the popup by clicking the "Add" button using XPath
        WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='Add']")));
        addBtn.click();
        
        // Sleep for 3 seconds so it can show it closes the popup from adding the student.
        try { Thread.sleep(3000); } catch (InterruptedException e) { e.printStackTrace(); }

        
        //close driver
        driver.quit();
        
        
        
        
    }
}
