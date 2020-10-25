package com.epam.pastebincomtests;

import com.epam.googlecalculator.util.TestListener;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

@Listeners({TestListener.class})
public class TestBase {
    protected static WebDriver driver;

    @BeforeSuite()
    public static WebDriver setUp()
    {
        if (null == driver) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
        return driver;
    }


    @AfterSuite(alwaysRun = true)
    public static void quitBrowser()
    {
        driver.quit();
        driver =null;
    }

}
