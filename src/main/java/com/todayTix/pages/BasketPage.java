package com.todayTix.pages;

import com.todayTix.basePage.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasketPage extends Page {
    public BasketPage(WebDriver driver) {
        super(driver);
    }

    private By creditOrDebitOption = By.xpath("//h3[text()='Credit or Debit Card']");
    private By continueToCheckoutBtn = By.xpath("//span[text()='Continue to checkout']");
    private By seatInfo = By.xpath("//span[text()='Important seat info']");
    private By seatNumber = By.xpath("//div[@class='seat-info__seat-group']/p/strong[2]");

    public void continueToCheckout(){
        clickontheElement(creditOrDebitOption);
        clickontheElement(continueToCheckoutBtn);
    }

    public String getImportantSeatInfo(){
        clickontheElement(seatInfo);
        String actualSeatNumber = getText(seatNumber);
        return actualSeatNumber;
    }
}