package com.todayTix.basePage;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Page {
    public WebDriver driver;

    public Page(WebDriver driver){
        this.driver = driver;
    }

    public WebElement waitfortheElement(By by){
        WebDriverWait wait = new WebDriverWait(driver,60);
        return  wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public boolean isElementPresent(By by){
        try{
            driver.findElement(by);
            return true;
        }
        catch(NoSuchElementException e){
            return false;
        }
    }

    public void clickontheElement(By by) {
        try {
            waitfortheElement(by).click();
        }catch (ElementNotInteractableException e){
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("javascript:window.scrollBy(200, 500)");
            waitfortheElement(by).click();
        }
    }

    public void entertheText(By by, String value){
        //driver.findElement(by).clear();
        driver.findElement(by).sendKeys(value);
    }

    public boolean elementisDisplayed(By by, boolean ignoreException) throws InterruptedException {
        if(ignoreException){
            try{
                return driver.findElement(by).isDisplayed();
            }catch(Exception e){
                return  false;
            }
        }else{
            return driver.findElement(by).isDisplayed();
        }
    }

    public String getText(By by){
        waitfortheElement(by);
        String ActualText = driver.findElement(by).getText();
        return  ActualText;
    }

    public String generateRandomString(){
        String random = RandomStringUtils.randomAlphanumeric(8);
        return random;
    }

    public void clickonDropdownOption(By by,String value){
        Select option = new Select(driver.findElement(by));
        option.selectByValue(value);
    }

    public void switchToIframes(){
        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe")));
    }

    public void switchTodefault(){
        driver.switchTo().defaultContent();
    }
}