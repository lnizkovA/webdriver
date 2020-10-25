package com.epam.googlecalculatortests;

import com.epam.googlecalculator.driver.DriverSingleton;
import com.epam.googlecalculator.util.TestListener;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.Collections;

@Listeners({TestListener.class})
public class TestBase {

    protected  WebDriver driver;

    @BeforeTest()
    public void setUp()
    {
        driver = DriverSingleton.getDriver();
    }


    @AfterTest(alwaysRun = true)
    public void stopBrowser()
    {
        DriverSingleton.closeDriver();
    }

}
