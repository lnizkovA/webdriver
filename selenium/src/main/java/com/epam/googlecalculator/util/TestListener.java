package com.epam.googlecalculator.util;

import com.epam.googlecalculator.driver.DriverSingleton;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


public class TestListener implements ITestListener {
    private Logger log = LogManager.getRootLogger();

    public void onTestStart(ITestResult iTestResult) {
        log.info(iTestResult.getName()+" test started.");
    }

    public void onTestSuccess(ITestResult iTestResult) {
        log.info(iTestResult.getName()+" test passed successfully.");
    }

    public void onTestFailure(ITestResult iTestResult) {
        log.error(iTestResult.getName()+" test failed.");
        saveScreenshot();
    }

    public void onTestSkipped(ITestResult iTestResult) {
        log.info(iTestResult.getName()+" test skipped.");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    public void onStart(ITestContext iTestContext) {
        log.info(iTestContext.getName()+" testClass initialized.");
    }

    public void onFinish(ITestContext iTestContext) {
        log.info(iTestContext.getName()+" testClass finished.");
    }

    private void saveScreenshot(){
        File screenCapture = ((TakesScreenshot) DriverSingleton
                .getDriver())
                .getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenCapture, new File(
                    ".//target/screenshots/"
                            + getCurrentTimeAsString() +
                            ".png"));
        } catch (IOException e) {
            log.error("Failed to save screenshot: " + e.getLocalizedMessage());
        }
    }

    private String getCurrentTimeAsString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "uuuu-MM-dd_HH-mm-ss" );
        return ZonedDateTime.now().format(formatter);
    }
}
