package com.epam.pastebincom.page;

import com.epam.pastebincom.util.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.util.List;
import java.util.NoSuchElementException;

public class PastebinWithCreatedPastePage {

    private WebDriver driver;
    private WebDriverWait wait;

    private final Logger logger = LogManager.getRootLogger();

    @FindBy(css = "div[class='info-top']")
    private WebElement infoTop;

    @FindBy(css = "a[class='btn -small h_800']")
    private WebElement selectedLanguage;

    @FindBy(css = "div[class='source'] ol[class='bash'] li div span")
    private List<WebElement> elementsOfLiDivSpanTags;

    @FindBy(css = "div[class='source'] ol[class='bash'] li div ")
    private List<WebElement> elementsOfLiDivTags;

    @FindBy(css = "div[class='source'] ol[class='bash'] li div span following-sibling::text()")
    private List<WebElement> el_1;


    public PastebinWithCreatedPastePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 10);
    }

    public String getTitleOfPaste() {
        wait.until(ExpectedConditions.visibilityOf(infoTop));
        logger.info("Created paste has title = " + infoTop.getText());
        return infoTop.getText();
    }

    public String checkHighlightingLanguage() {
        wait.until(ExpectedConditions.visibilityOf(selectedLanguage));
        return selectedLanguage.getText();
    }

    public boolean isCodeEqualsToPastedText(String fileName) {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(elementsOfLiDivTags));
            logger.info("elementsOfLiDivTag.size() = " + elementsOfLiDivTags.size());
            File file1 = new File(fileName);
            return Utils.isFilesHaveTheSameContent(file1, Utils.writeTextOfWebElementsToFile(elementsOfLiDivTags));
        } catch (IOException | TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    public boolean isSyntaxBash(){
        try{
            wait.until(ExpectedConditions.visibilityOfAllElements(elementsOfLiDivSpanTags));
            return Utils.isColorOfWebElementsCorrect(elementsOfLiDivSpanTags);
        } catch (TimeoutException | NoSuchElementException e){
            return false;
        }
    }


}
