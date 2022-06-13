package com.todayTix.api;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.StringUtils;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class GetAvailableTicket {
    public static String DateTime = null;

    public String getTheAvailableTicketDateAndTime() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String todaysDate = LocalDate.now().format(formatter);
        LocalDate addDate = LocalDate.now().plusDays(Long.parseLong("5"));
        String expectedDate = addDate.format(formatter);
        String baseURL = "https://inventory-service.tixuk.io/api/v4/availability/products/6362/quantity/2/from/" + todaysDate + "/to/" + expectedDate + "";
        RestAssured.baseURI = baseURL;
        RequestSpecification request = RestAssured.
                given()
                .header("affiliateId", "encoretickets");
        String dateTime = request.get().path("response[0].datetime");
        DateTime =  dateTime;
        return DateTime;
    }

    public Map<String,Object> getAvailableSeats(){

        String availableDateTime = getTheAvailableTicketDateAndTime();
        String availableDate1 = StringUtils.substringBefore(availableDateTime, "T");
        availableDate1 = availableDate1.replaceAll("-","");
        String availableTime = DateTime;
        availableTime = availableTime.substring(11,16);
        String FormatedavailableTime = availableTime.replace(":","");

        RequestSpecification request;

            String baseURL = "https://inventory-service.tixuk.io/api/v4/products/6362/areas";
            RestAssured.baseURI = baseURL;
             request = RestAssured.
                    given()
                    .param("includeSeatsAttributes", "true")
                    .param("date", availableDate1)
                    .param("time", FormatedavailableTime)
                    .param("quantity", 2);

        String body = request.get().body().asString();
        int seatNumbers = request.get().path("response.areas[0].groupings[0].seatNumberStart");
        String rowName = request.get().path("response.areas[0].groupings[0].row");

        Map<String, Object> bookDetails = new HashMap<>();
        bookDetails.put("seatNumbers",seatNumbers);
        bookDetails.put("rowName",rowName);
        bookDetails.put("availableTime",availableTime);
        String newFormatAvailabledate = availableDate1.substring(availableDate1. length() - 2);
        bookDetails.put("newFormatAvailabledate",newFormatAvailabledate);
        return bookDetails;
    }

    public Map<String,Object> getAvailableSeatswithRestrictedView(){

        String availableDateTime = getTheAvailableTicketDateAndTime();
        String availableDate1 = StringUtils.substringBefore(availableDateTime, "T");
        availableDate1 = availableDate1.replaceAll("-","");
        String availableTime = DateTime;
        availableTime = availableTime.substring(11,16);
        String FormatedavailableTime = availableTime.replace(":","");
        RequestSpecification request;
        String baseURL = "https://inventory-service.tixuk.io/api/v4/products/6362/areas";
        RestAssured.baseURI = baseURL;
        request = RestAssured.
                given()
                .param("includeSeatsAttributes", "true")
                .param("date", availableDate1)
                .param("time", FormatedavailableTime)
                .param("quantity", 2);

        String body = request.get().body().asString();
        int seatNumbers = request.get().path("response.areas[0].groupings[1].seatNumberStart");
        String rowName = request.get().path("response.areas[0].groupings[1].row");
        Map<String, Object> restrictedbookDetails = new HashMap<>();
        restrictedbookDetails.put("seatNumbers",seatNumbers);
        restrictedbookDetails.put("rowName",rowName);
        restrictedbookDetails.put("availableTime",availableTime);
        String newFormatAvailabledate = availableDate1.substring(availableDate1. length() - 2);
        restrictedbookDetails.put("newFormatAvailabledate",newFormatAvailabledate);
        return restrictedbookDetails;
    }
}