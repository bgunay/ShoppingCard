package com.burhan.test.webstore.frontend;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumLoginFrontendTest {

    private WebDriver browser;

    @Before
    public void setup() {
        browser = new FirefoxDriver();
    }

    @Test
    public void startTest() {
        browser.get("http://localhost:8080/ShoppingCard/login");

        // Will throw exception if elements not found
        browser.findElement(By.id("username")).sendKeys("admin");
        browser.findElement(By.id("password")).sendKeys("admin");

        browser.findElement(By.id("loginBtn")).click();
        browser.findElement(By.id("account")).click();

        assertEquals("admin", browser.findElement(By.id("loginUser")).getAttribute("value"));
    }

    @After
    public void tearDown() {
        browser.close();
    }
}
