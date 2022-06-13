package com.todayTix.pages;

import com.todayTix.basePage.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class BookingPage extends Page {

    public BookingPage(WebDriver driver) {
        super(driver);
    }

    private By selectDate = By.xpath("");
    private By selectTime = By.xpath("");
    private By getTicketsBtn = By.xpath("//div[@class='full-fat-calendar__redirect-button gr-col gr-col--redirect-button full-fat-cta']//button[@type='submit'][1]");

    public void chooseTheDate(String date, String time) throws InterruptedException {
        Thread.sleep(3000);
        clickontheElement(By.xpath("//div[@class='dayContainer']//span[text()="+date+"]"));
        clickontheElement(By.xpath("//label[@class='f-label__wrapper c-quick-search__timeslot full-fat-calendar__timeslot-label']//span[text()='"+time+"']"));
        clickontheElement(getTicketsBtn);
    }
}
