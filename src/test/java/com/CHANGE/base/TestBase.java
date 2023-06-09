package com.CHANGE.base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import lombok.SneakyThrows;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import static com.CHANGE.utils.DriverUtils.driver;

public class TestBase {

    private static AppiumDriverLocalService appiumService;
    private final String platform;

    public TestBase(String platform) {
        this.platform = platform;
    }

    @BeforeMethod(alwaysRun = true)
    protected void startDriver() {
        // Set desired capabilities for the mobile device
        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, platform);
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, getDeviceUDID());
        caps.setCapability("automationName", "uiautomator2");
        caps.setCapability("appPackage", "com.CHANGE");
        caps.setCapability("appActivity", "com.CHANGE.CHANGEActivity");
        caps.setCapability("autoGrantPermissions", "true");
        caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 300);
        caps.setCapability(MobileCapabilityType.APP, "src/main/resources/app/CHANGE.apk");

        // Create the driver instance
        driver = new AppiumDriver(startAppiumServer(), caps);
    }

    private static URL startAppiumServer() {
        // Configure the Appium service builder
        AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder();
        serviceBuilder.usingAnyFreePort();  // Use any available free port

        // Start the Appium server
        appiumService = AppiumDriverLocalService.buildService(serviceBuilder);
        appiumService.start();

        // Retrieve the Appium server URL
        URL appiumServerURL = appiumService.getUrl();
        System.out.println("Appium Server started at: " + appiumServerURL.toString());

        // Additional setup steps, if needed
        return appiumServerURL;
    }

    private static void stopAppiumServer() {
        if (appiumService != null && appiumService.isRunning()) {
            appiumService.stop();
            System.out.println("Appium Server stopped");
        }
    }

    @SneakyThrows
    private String getDeviceUDID() {
        for (int i = 0; i < 5; i++) {
            Process process = Runtime.getRuntime().exec("adb devices");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.endsWith("device")) {
                    return line.split("\t")[0];
                }
            }
        }
        throw new Exception("No connected devices found.");
    }

    @AfterMethod(alwaysRun = true)
    protected void quitDriver() {
        if (driver != null) {
            driver.quit();
            stopAppiumServer();
        }
    }
}
