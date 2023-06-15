package com.CHANGE.screens;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

import static com.CHANGE.utils.WaitUtils.getWaitUtils;

public class CHANGEScreen extends BaseScreen<CHANGEScreen> {

    @AndroidFindBy(id = "")
    @iOSXCUITFindBy(id = "")
    private WebElement captureButton;

    @AndroidFindBy(id = "")
    @iOSXCUITFindBy(id = "")
    private WebElement galleryButton;

    @Override
    public CHANGEScreen init() {
        return initScreen();
    }

    @Override
    protected void isLoaded() throws Error {
        getWaitUtils().waitUntilElementAppear(captureButton);
    }
}
