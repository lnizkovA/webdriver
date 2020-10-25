package com.epam.pastebincomtests;

import com.epam.pastebincom.page.PastebinHomePage;
import com.epam.pastebincom.page.PastebinWithCreatedPastePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestPastebinPasteFileWithBashCode extends TestBase{

    private PastebinHomePage pastebinHomePage;
    private PastebinWithCreatedPastePage pastebinWithCreatedPastePage;

    @BeforeClass
    public void testSetUp(){
        pastebinHomePage = new PastebinHomePage(driver);
        pastebinWithCreatedPastePage = new PastebinWithCreatedPastePage(driver);
    }

    @Parameters({"title", "fileName", "highlightingLanguage"})
    @Test
    public void testCreatePasteWithBashCode(String title, String fileName, String highlightingLanguage){
        pastebinHomePage.openPage()
                .typeTextFromFile(fileName)
                .selectHighlightingValue(highlightingLanguage)
                .selectPasteExpirationValue10Minutes()
                .typeTitle(title)
                .clickCreateNewPasteButton();
        Assert.assertEquals(pastebinWithCreatedPastePage.getTitleOfPaste(), title, "Title of created paste is NOT correct!");
    }

    @Parameters({"fileName"})
    @Test (dependsOnMethods = "testCreatePasteWithBashCode")
    public void testIsFilesEqual(String fileName) {
        Assert.assertTrue(pastebinWithCreatedPastePage.isCodeEqualsToPastedText(fileName), "Code of generated page and input text are DIFFERENT!");
    }

    @Test(dependsOnMethods = "testCreatePasteWithBashCode")
    public void testCheckIsBashSyntax(){
        Assert.assertTrue(pastebinWithCreatedPastePage.isSyntaxBash(), "Paste code doen NOT belong to Bash syntax!");
    }


}
