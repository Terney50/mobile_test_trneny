package org.trneny.android.pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.trneny.drivers.AndroidDriverManager.getDriver;

public class ClockPage {

    public void clickClockButton() {
        getDriver().findElement(AppiumBy.xpath("//android.widget.FrameLayout[@content-desc=\"Clock\"]")).click();
        System.out.println("Clicked on the Clock button");
    }

    public void clickAlarmButton() {
        getDriver().findElement(AppiumBy.xpath("//android.widget.FrameLayout[@content-desc=\"Alarm\"]")).click();
        System.out.println("Clicked on the Alarm button");
    }

    public void addCityClockButton() {
        getDriver().findElement(AppiumBy.xpath("//android.widget.Button[@content-desc=\"Add city\"]")).click();
        System.out.println("Clicked on the Add new city button");
    }

    public void addAlarmButton() {
        getDriver().findElement(AppiumBy.xpath("//android.widget.Button[@content-desc=\"Add alarm\"]")).click();
        System.out.println("Clicked on the Add new alarm button");
    }

    public void searchCityClockText(String cityName) {
        getDriver().findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"com.google.android.deskclock:id/open_search_view_edit_text\"]")).sendKeys(cityName);
        getDriver().findElement(AppiumBy.xpath("//android.support.v7.widget.RecyclerView[@resource-id=\"com.google.android.deskclock:id/search_results_view\"]/android.widget.LinearLayout")).click();
        System.out.println("Clicked on the Clock button");
    }

    public void deleteCityClockRow() {
        Point source = getDriver().findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Bratislava\"]")).getLocation();
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);
        swipe.addAction(
                finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), source.x, source.y));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(
                finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), source.x - (source.x * 5), source.y));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(new Pause(finger, Duration.ofMillis(600)));
        getDriver()
                .perform(List.of(swipe));

        System.out.println("Deleted alarm clock");
    }

    public void selectTime(String hourValue, String minutesValue, String amPmValue) {
        getDriver().findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"" + hourValue + " o'clock\"]")).click();
        getDriver().findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"" + minutesValue + " minutes\"]")).click();
        switch (amPmValue) {
            case "AM":
                getDriver().findElement(AppiumBy.xpath("//android.widget.CompoundButton[@resource-id=\"com.google.android.deskclock:id/material_clock_period_am_button\"]")).click();
                break;
            case "PM":
                getDriver().findElement(AppiumBy.xpath("//android.widget.CompoundButton[@resource-id=\"com.google.android.deskclock:id/material_clock_period_pm_button\"]")).click();
                break;
            default:
                System.out.println("I don't understand...");
        }
        getDriver().findElement(AppiumBy.xpath("//android.widget.Button[@resource-id=\"com.google.android.deskclock:id/material_timepicker_ok_button\"]")).click();
        System.out.println("Alarm clock time selected for " + hourValue + ":" + minutesValue + " " + amPmValue);
    }

    public void addAlarmLabel(String alarmLabel) {
        getDriver().findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"No label specified\"]")).click();
        getDriver().findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"com.google.android.deskclock:id/label_input_field\"]")).sendKeys(alarmLabel);
        getDriver().findElement(AppiumBy.xpath("//android.widget.Button[@resource-id=\"android:id/button1\"]")).click();
        System.out.println("Added label for alarm clock");
    }

    public void selectAlarmDays() {
        getDriver().findElement(AppiumBy.xpath("//android.widget.CheckBox[@content-desc=\"Monday\"]")).click();
        getDriver().findElement(AppiumBy.xpath("//android.widget.CheckBox[@content-desc=\"Friday\"]")).click();
        System.out.println("Days for alarm clock selected");
    }

    public void deleteVisibleAlarm() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        getDriver().findElement(AppiumBy.xpath("//android.widget.Button[@resource-id=\"com.google.android.deskclock:id/delete\"]")).click();
        System.out.println("Visible alarm deleted");
    }
}
