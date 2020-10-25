package com.epam.pastebincomtests;

import com.epam.pastebincom.page.PastebinHomePage;
import com.epam.pastebincom.page.PastebinWithCreatedPastePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestPastebinTask1 extends TestBase{

    private PastebinHomePage pastebinHomePage;
    private PastebinWithCreatedPastePage pastebinWithCreatedPastePage;

    @BeforeClass
    public void testSetUp(){
        pastebinHomePage = new PastebinHomePage(driver);
        pastebinWithCreatedPastePage = new PastebinWithCreatedPastePage(driver);
    }

    @Parameters({"title", "text"})
    @Test
    public void testCreateNewPaste(String title, String text){
        pastebinHomePage.openPage()
                .typeText(text)
                .selectPasteExpirationValue10Minutes()
                .typeTitle(title)
                .clickCreateNewPasteButton();
        Assert.assertEquals(pastebinWithCreatedPastePage.getTitleOfPaste(), title, "Title of created paste IS NOT correct!");
    }

}
