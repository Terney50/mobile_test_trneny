package org.trneny.android.pages;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.trneny.drivers.AndroidDriverManager.getDriver;

public class HomePage {

    public HomePage() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(10));
    }

    public void openClockApp() {
        getDriver().pressKey(new KeyEvent(AndroidKey.HOME));
        getDriver().activateApp("com.google.android.deskclock");
        System.out.println("Clock opened");
    }
}
