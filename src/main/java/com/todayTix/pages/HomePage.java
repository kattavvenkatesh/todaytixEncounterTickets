package com.todayTix.pages;

import com.todayTix.basePage.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends Page {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    private By cookiesGotItBtn = By.xpath("//a[@aria-label='dismiss cookie message']");
    private By searchTextfield = By.xpath("//input[@type='search']");
    private By ticketsDropdown = By.xpath("//select[@id='edit-no-of-tickets']");
    private By findTicketsBtn = By.xpath("//button[@class='o-btn o-btn--block o-btn__cta o-btn__cta--primary c-quick-search__btn t-btn-super ']");
    private By tinaShowOption = By.xpath("//div[@class='c-quick-search__item-content']");

    public void acceptTheCookies(){
        clickontheElement(cookiesGotItBtn);
    }

    public void selectTinaShow(String showName){
        entertheText(searchTextfield,showName);
        clickontheElement(tinaShowOption);
        clickontheElement(findTicketsBtn);
    }
}