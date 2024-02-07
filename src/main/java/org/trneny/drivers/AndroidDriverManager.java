package org.trneny.drivers;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static org.trneny.server.AppiumServerManager.startServer;
import static org.trneny.server.AppiumServerManager.stopServer;

public class AndroidDriverManager {
    private static final ThreadLocal<AndroidDriver> DRIVER = new ThreadLocal<>();
    private static final Logger LOG = LogManager.getLogger("DriverManager.class");

    public static void createAndroidDriver() {
        startServer("android");
        try {
            setDriver(new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), uiAutomator2OptionsChrome()));
        } catch (final MalformedURLException e) {
            throw new RuntimeException(e);
        }
        setupDriverTimeouts();
    }

    public static AndroidDriver getDriver() {
        return AndroidDriverManager.DRIVER.get();
    }

    public static void quitSession() {
        if (null != DRIVER.get()) {
            LOG.info("Closing the driver...");
            getDriver().quit();
            DRIVER.remove();
            stopServer();
        }
    }

    private static void setDriver(final AndroidDriver driver) {
        AndroidDriverManager.DRIVER.set(driver);
    }

    private static void setupDriverTimeouts() {
        getDriver().manage()
                .timeouts()
                .implicitlyWait(Duration.ofSeconds(5));
    }

    private static UiAutomator2Options uiAutomator2OptionsChrome() {

        final UiAutomator2Options uiAutomator2Options;
        uiAutomator2Options = new UiAutomator2Options().setAvd("Medium_Phone_API_34")
                .setAvdLaunchTimeout(Duration.ofSeconds(300))
                .setAvdReadyTimeout(Duration.ofSeconds(100))
                .setDeviceName("Medium_Phone_API_34")
                .setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2)
                .setAutoGrantPermissions(true)
                .setNoReset(false);

        return uiAutomator2Options;
    }
}