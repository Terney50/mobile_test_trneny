package org.trneny.android.tests;

import io.appium.java_client.AppiumBy;
import org.testng.annotations.Test;
import org.trneny.android.pages.ClockPage;
import org.trneny.android.pages.HomePage;

import static org.testng.Assert.assertTrue;
import static org.trneny.drivers.AndroidDriverManager.getDriver;

public class ClockTests extends BaseTest {

    @Test
    public void newClockScenario() {
        final HomePage homePage = new HomePage();
        final ClockPage clockPage = new ClockPage();

        homePage.openClockApp();
        clockPage.clickClockButton();
        clockPage.addCityClockButton();
        clockPage.searchCityClockText("Bratislava, Slovakia");
        assertTrue(getDriver().findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Bratislava\"]")).isDisplayed());
        clockPage.deleteCityClockRow();
    }

    @Test
    public void newAlarmScenario() throws InterruptedException {
        final HomePage homePage = new HomePage();
        final ClockPage clockPage = new ClockPage();

        homePage.openClockApp();
        clockPage.clickAlarmButton();
        clockPage.addAlarmButton();
        clockPage.selectTime("10","25","AM");
        clockPage.addAlarmLabel("Test Alarm");
        clockPage.selectAlarmDays();
        clockPage.deleteVisibleAlarm();
    }
}
