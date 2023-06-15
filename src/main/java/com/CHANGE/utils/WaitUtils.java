package com.CHANGE.utils;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static com.CHANGE.utils.DriverUtils.driver;

public class WaitUtils {

    private static final int TIMEOUT = 30;
    private static final int LONG_TIMEOUT = 180;
    private static final int SHORT_TIMEOUT = 5;
    private static WebDriverWait wait;

    private WaitUtils() {
    }

    private static WebDriverWait getInstance(int timeout) {
        return (WebDriverWait)
                new WebDriverWait(driver, Duration.ofSeconds(timeout), Duration.ofMillis(50))
                        .ignoring(StaleElementReferenceException.class);
    }

    public static WaitUtils getWaitUtils() {
        wait = getInstance(TIMEOUT);
        return new WaitUtils();
    }

    public static WaitUtils getLongWaitUtils() {
        wait = getInstance(LONG_TIMEOUT);
        return new WaitUtils();
    }

    public static WaitUtils getShortWaitUtils() {
        wait = getInstance(SHORT_TIMEOUT);
        return new WaitUtils();
    }

    public static void threadSleep(int sleepTime) {
        if (sleepTime > 5000) throw new IllegalArgumentException();
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException ignored) {
            // ignored
        }
    }

    public WebElement waitUntilElementAppear(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (StaleElementReferenceException | TimeoutException e) {
            throw new WebDriverException("The Element: " + element + " did not Appear");
        }
        return element;
    }

    public void waitUntilElementDisappear(WebElement element) {
        wait.until(
                new ExpectedCondition<Boolean>() {
                    @Override
                    public @Nullable Boolean apply(@Nullable WebDriver webDriver) {
                        try {
                            return !element.isDisplayed();
                        } catch (Exception e) {
                            return true;
                        }
                    }
                });
    }

    public void waitUntilElementDisappear(List<WebElement> elements, int index) {
        wait.until(
                new ExpectedCondition<Boolean>() {
                    @Override
                    public @Nullable Boolean apply(@Nullable WebDriver webDriver) {
                        try {
                            return !elements.get(index).isDisplayed();
                        } catch (Exception e) {
                            return true;
                        }
                    }
                });
    }

    public void waitUntilElementVisible(By by) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (WebDriverException webDriverException) {
            throw new TimeoutException("Element is not visible");
        }
    }

    public WebElement waitUntilElementVisible(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (WebDriverException webDriverException) {
            throw new TimeoutException("Element is not visible");
        }
        return element;
    }

    public void waitForTextToAppear(String textToAppear, WebElement element) {
        wait.until(ExpectedConditions.textToBePresentInElement(element, textToAppear));
    }

    public void waitForTextToAppear(String textToAppear, List<WebElement> elements, int index) {
        wait.until(ExpectedConditions.textToBePresentInElement(elements.get(index), textToAppear));
    }
}
