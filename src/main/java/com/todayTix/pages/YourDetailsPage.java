package com.todayTix.pages;

import com.todayTix.basePage.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class YourDetailsPage extends Page {
    public YourDetailsPage(WebDriver driver) {
        super(driver);
    }

    private By firstnameTextField = By.xpath("//div[@class='MuiGrid-root form-group MuiGrid-container MuiGrid-direction-xs-column']//div[2]/input");
    private By lastnameTextField = By.xpath("//input[@placeholder='Last Name']");
    private By emailTextField = By.xpath("//input[@placeholder='Email']");
    private By phoneTextField = By.xpath("//input[@placeholder='Phone']");
    private By postalCodeTextField = By.xpath("//input[@placeholder='Postal code']");
    private By continueToCardDetailsBtn = By.xpath("//span[text()='Continue to card details']");

    public void addContactDetails(String firstName,String lastName, String email,String phone, String postalCode) {
        entertheText(firstnameTextField, firstName);
        entertheText(lastnameTextField, lastName);
        entertheText(emailTextField, email);
        entertheText(phoneTextField, phone);
        entertheText(postalCodeTextField, postalCode);
        clickontheElement(continueToCardDetailsBtn);
    }
}