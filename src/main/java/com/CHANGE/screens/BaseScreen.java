package com.CHANGE.screens;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import java.util.Arrays;

import static com.CHANGE.utils.DriverUtils.driver;

public abstract class BaseScreen<T extends LoadableComponent<T>> extends LoadableComponent<T> {

    public abstract T init();

    @Override
    protected void load() {

    }

    @Override
    protected void isLoaded() throws Error {

    }

    @Override
    public T get() {
        try {
            return super.get();
        } catch (Exception e) {
            throw new RuntimeException(this.getClass().getSimpleName() + " didn't open" +"\n "+ Arrays.toString(e.getStackTrace()));
        }
    }

    protected T initScreen() {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        return get();
    }
}
