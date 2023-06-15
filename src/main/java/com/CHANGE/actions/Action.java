package com.CHANGE.actions;

import com.CHANGE.utils.DriverUtils;
import com.CHANGE.utils.WaitUtils;
import org.openqa.selenium.*;

import java.util.List;

import static com.CHANGE.utils.WaitUtils.getWaitUtils;

public class Action {

    private Action() {
    }


    /**
     * Waiting for element appearing and get text from it
     *
     * @param element WebElement
     * @return The text of element
     */
    public static String getText(WebElement element) {
        getWaitUtils().waitUntilElementAppear(element);
        return element.getText();
    }

    /**
     * Waiting for element appearing and get value from it
     *
     * @param element
     * @return The value of element
     */
    public static String getValue(WebElement element) {
        getWaitUtils().waitUntilElementAppear(element);
        return element.getText();
    }


    /**
     * Check if the element contains the text.
     *
     * @param element WebElement
     * @param text    String
     * @return True or False.
     */
    public static boolean isTextContainedInElement(WebElement element, String text) {
        return getText(element).contains(text);
    }

    /**
     * Set value to the WebElement.
     *
     * @param element WebElement
     * @param value   String
     */
    public static void setValue(WebElement element, String value) {
        getWaitUtils().waitUntilElementVisible(element).sendKeys(value);
    }

    /**
     * Click on the webElement
     *
     * @param element webElement
     */
    public static void click(WebElement element) {
        getWaitUtils().waitUntilElementAppear(element);
        element.click();
    }

    /**
     * Click on the webElement multiple times
     *
     * @param element webElement
     * @param times   int
     */
    public static void click(WebElement element, int times) {
        for (int i = 0; i < times; i++) {
            click(element);
        }
    }

    /**
     * Check if the screen contains the text.
     *
     * @param text String
     * @return True or False.
     */
    public static boolean isTextContainsOnTheScreen(String text) {
        return DriverUtils.driver.getPageSource().contains(text);
    }

    /**
     * @param element WebElement
     * @return the center point of the given element
     */
    public static Point getCenter(WebElement element) {
        Rectangle rectangle = element.getRect();
        int x = rectangle.getX() + rectangle.getWidth() / 2;
        int y = rectangle.getY() + rectangle.getHeight() / 2;
        return new Point(x, y);
    }

    /**
     * Find elements.
     *
     * @param by
     * @return The list of webElements that matches the selector
     */
    public static List<WebElement> findElements(By by) {
        return DriverUtils.driver.findElements(by);
    }

    /**
     * Check if element displayed or not
     *
     * @param by
     * @return The result of checking(true or false)
     */
    public static boolean isElementDisplayed(By by) {
        try {
            WaitUtils.getShortWaitUtils().waitUntilElementVisible(by);
            return DriverUtils.driver.findElement(by).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if element is displayed
     *
     * @return The result of checking(true or false)
     */
    public static boolean isElementDisplayed(WebElement element) {
        try {
            WaitUtils.getShortWaitUtils().waitUntilElementVisible(element);
            return element.isDisplayed();
        } catch (Exception ignored) {
            return false;
        }
    }

    /**
     * Clean text
     *
     * @param element WebElement
     */
    public static void cleanText(WebElement element) {
        element.clear();
    }

    /**
     * Clean and type text
     *
     * @param element WebElement
     * @param text    Text to type
     */
    public static void clickAndType(WebElement element, String text) {
        click(element);
        cleanText(element);
        type(element, text);
    }

    /**
     * Type text without cleaning
     *
     * @param element WebElement
     * @param text    Text to type
     */
    public static void type(WebElement element, String text) {
        element.sendKeys(text);
    }

    public static WebElement getElementById(String id) {
        By expectedStepNumberId = By.id(id);
        return getWaitUtils().waitUntilElementAppear(DriverUtils.driver.findElement(expectedStepNumberId));
    }


    /**
     * Check if element selected
     *
     * @param element WebElement
     */
    public static boolean isElementSelected(WebElement element) {
        WaitUtils.getWaitUtils().waitUntilElementAppear(element);
        return element.isSelected();
    }


    public static boolean isElementSelected(By by) {
        WebElement element = DriverUtils.driver.findElement(by);
        WaitUtils.getWaitUtils().waitUntilElementAppear(element);
        return element.isSelected();
    }

    /**
     * Check if element enabled
     *
     * @param element WebElement
     */
    public static boolean isElementEnabled(WebElement element) {
        return element.isEnabled();
    }

    private static Point getScreenCenter() {
        Dimension dimension = DriverUtils.driver.manage().window().getSize();
        int x = dimension.getWidth() / 2;
        int y = dimension.getHeight() / 2;
        return new Point(x, y);
    }

    /**
     * Click on text.
     *
     * @param text String
     */
    public static void clickOnText(String text) {
        clickOnText(text);
    }


}
