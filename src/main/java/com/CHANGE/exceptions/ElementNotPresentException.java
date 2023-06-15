package com.CHANGE.exceptions;

public class ElementNotPresentException extends RuntimeException {

    public ElementNotPresentException(String elementLocator) {
        super("WITH LOCATOR" + elementLocator);
    }

    public ElementNotPresentException(String elementLocator, int index) {
        super(elementLocator + " BY " + index + " INDEX");
    }

    @Override
    public String getMessage() {

        return createMessage(super.getMessage());
    }

    private String createMessage(String originalMessage) {

        return "ELEMENT  { " + originalMessage + " } IS NOT PRESENT ON PAGE" + '\n';
    }

    private String createMessage(String originalMessage, int index) {

        return "ELEMENT WITH LOCATOR { " + originalMessage + " } IS NOT PRESENT ON PAGE" + '\n';
    }


}
