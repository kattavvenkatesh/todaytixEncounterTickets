package com.todayTix.applicationTest;

import com.todayTix.api.GetAvailableTicket;
import com.todayTix.baseTest.BaseTest;
import com.todayTix.pages.BasketPage;
import com.todayTix.pages.BookingPage;
import com.todayTix.pages.BookingSeatPlanPage;
import com.todayTix.pages.HomePage;
import org.testng.Assert;
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

public class BookingEncoreRestrictedTicketTest extends BaseTest {
    public Map<String,Object> restrictedViewApiRespsponseData;
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
        restrictedViewApiRespsponseData = availableTicket.getAvailableSeatswithRestrictedView();
    }

    @BeforeMethod
    public void setUp(Method method) throws MalformedURLException {
        if(method.getName().equals("getAvailableSeatApi")) {
            launchBrowser();
        }
    }

    @Test(dependsOnMethods = "getAvailableSeatApi")
    public void bookRestrictedViewEncounterTicketsTest() throws InterruptedException, MalformedURLException {
        String Date = (String) restrictedViewApiRespsponseData.get("newFormatAvailabledate");
        String Time = (String) restrictedViewApiRespsponseData.get("availableTime");
        String row = (String) restrictedViewApiRespsponseData.get("rowName");
        int seat = (Integer) restrictedViewApiRespsponseData.get("seatNumbers");
        HomePage homePage = new HomePage(driver);
        homePage.acceptTheCookies();
        homePage.selectTinaShow((String) properties.get("showName"));
        BookingPage bookingPage = new BookingPage(driver);
        bookingPage.chooseTheDate(Date,Time);
        BookingSeatPlanPage bookingSeatPlanPage = new BookingSeatPlanPage(driver);
        bookingSeatPlanPage.addSeatsToBasket(row,seat);
        BasketPage basketPage = new BasketPage(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(6000);
        Assert.assertEquals(basketPage.getImportantSeatInfo(),row);
        tearDown();
    }
}