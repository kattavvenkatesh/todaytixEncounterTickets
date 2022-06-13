package com.todayTix.pages;

import com.todayTix.basePage.Page;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;


public class BookingSeatPlanPage extends Page {

    public BookingSeatPlanPage(WebDriver driver) {
        super(driver);
    }

    private By addtoBasketBtn = By.xpath("//span[text()='Add to Basket']");
    private final String GRAND_SEAT_SECTION = "GRAND CIRCLE";

    public void addSeatsToBasket(String row, int seat) throws InterruptedException {
        selectSeat(row,seat);
        driver.switchTo().parentFrame();
        clickontheElement(addtoBasketBtn);
    }

    private void selectSeat(String row, int seat) throws InterruptedException {
        Thread.sleep(5000);
        switchToIframes();
        Actions ac = new Actions(driver);
        ac.moveToElement(driver.findElement(By.className("chart-layer"))).build().perform();
        Thread.sleep(5000);
        while(true) {
            String text = driver.findElement(By.cssSelector("#cursorTooltipContent .label-details .label")).getText();
            if(text.equalsIgnoreCase(GRAND_SEAT_SECTION)) {
                break;
            }
            ac.moveByOffset(0, 50).build().perform();
            Thread.sleep(2000);
        }
        ac.moveByOffset(0, 70).build().perform();
        ac.click().build().perform();
        selectSeatInGrandCircle(row,seat);
    }

    public void selectSeatInGrandCircle(String row, int seat) throws InterruptedException {

        Actions ac = new Actions(driver);
        goToRow(row,ac);
        goToColumn(seat,ac);

    }
    private void goToColumn(int seatNumber,Actions ac) throws InterruptedException {
        String currentColumn = driver.findElement(By.xpath("//div[@id='cursorTooltipContent']//span[@class='value'][2]")).getText();

        if(seatNumber == Integer.parseInt(currentColumn)) {
            ac.click().build().perform();
            return;
        }
        int moveOffSet = findColumnMoveOffSet(Integer.parseInt(currentColumn),seatNumber);
        while(true) {
            ac.moveByOffset(moveOffSet, 0).build().perform();
            try {
                currentColumn = driver.findElement(By.xpath("//div[@id='cursorTooltipContent']//span[@class='value'][2]")).getText();
                if(seatNumber == Integer.parseInt(currentColumn)) {
                    ac.click().build().perform();
                    return;
                }
            } catch (Exception e) {
                Thread.sleep(1000);
            }
        }
    }

    private int findColumnMoveOffSet(int currentSeat, int actualSeat) {
        if(actualSeat>currentSeat)
            return -10;
        return 10;
    }

    public void goToRow(String rowName,Actions ac) throws InterruptedException {
        ac.moveToElement(driver.findElement(By.className("chart-layer"))).build().perform();
        while (true) {
            ac.moveByOffset(0, 10).build().perform();
            try {
                String currentRow = driver.findElement(By.xpath("//div[@id='cursorTooltipContent']//span[@class='value'][1]")).getText();
                if(currentRow.equalsIgnoreCase(rowName)) {
                    break;
                }
            } catch (Exception e) {
                Thread.sleep(1000);
            }
        }
    }
}