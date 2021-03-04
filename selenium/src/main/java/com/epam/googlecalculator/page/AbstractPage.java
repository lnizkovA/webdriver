package com.epam.googlecalculator.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    protected Logger log = LogManager.getRootLogger();

    protected final int WAIT_TIMEOUT_SECONDS = 20;

    protected AbstractPage(WebDriver driver)
    {
        this.driver = driver;
        wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);
        PageFactory.initElements(driver, this);
    }

    protected abstract AbstractPage openPage();
}
