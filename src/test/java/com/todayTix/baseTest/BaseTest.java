package com.todayTix.baseTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver;
    Properties prop = new Properties();

    public WebDriver getDriver() {
        return driver;
    }

    public void launchBrowser() throws MalformedURLException {
        File file = new File("src/test/resources/datafile.properties");
        FileInputStream fileInput = null;
        try {
            fileInput = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //load properties file
        try {
            prop.load(fileInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.setProperty("webdriver.chrome.driver", prop.getProperty("ChromePath"));
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        System.out.println(driver);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get(prop.getProperty("URL"));
    }

    public void tearDown() {
        driver.close();
    }
}