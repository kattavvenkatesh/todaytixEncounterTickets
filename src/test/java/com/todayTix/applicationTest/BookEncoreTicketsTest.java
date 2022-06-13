package com.todayTix.applicationTest;

import com.todayTix.api.GetAvailableTicket;
import com.todayTix.baseTest.BaseTest;
import com.todayTix.pages.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BookEncoreTicketsTest extends BaseTest {
    public  Map<String,Object> apiRespsponseData;
    Properties properties;

    @BeforeClass
    public void loadTestData() throws IOException {
        properties = new Properties();
        InputStream iStream = getClass().getClassLoader().getResourceAsStream("datafile.properties");
        properties.load(iStream);
    }

    @Test
    public void getAvailableSeatApi(){
        GetAvailableTicket availableTicket = new GetAvailableTicket();
        apiRespsponseData = availableTicket.getAvailableSeats();
    }

    @BeforeMethod
    public void setUp(Method method) throws MalformedURLException {
        if(method.getName().equals("getAvailableSeatApi")) {
            launchBrowser();
        }
    }

    @Test(dependsOnMethods = "getAvailableSeatApi")
    public void bookEncounterTicketsTest() throws InterruptedException {
        String Date = (String) apiRespsponseData.get("newFormatAvailabledate");
        String Time = (String) apiRespsponseData.get("availableTime");
        String row = (String) apiRespsponseData.get("rowName");
        int seat = (Integer) apiRespsponseData.get("seatNumbers");
        HomePage homePage = new HomePage(driver);
        homePage.acceptTheCookies();
        homePage.selectTinaShow((String) properties.get("showName"));
        BookingPage bookingPage = new BookingPage(driver);
        bookingPage.chooseTheDate(Date,Time);
        BookingSeatPlanPage bookingSeatPlanPage = new BookingSeatPlanPage(driver);
        bookingSeatPlanPage.addSeatsToBasket(row,seat);
        BasketPage basketPage = new BasketPage(driver);
        basketPage.continueToCheckout();
        YourDetailsPage yourDetailsPage = new YourDetailsPage(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(6000);
        yourDetailsPage.addContactDetails((String) properties.get("firstName"),(String) properties.get("lastName"),(String) properties.get("email"),(String) properties.get("phone"),(String) properties.get("postal"));
        tearDown();
    }
}