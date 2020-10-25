package com.epam.pastebincom.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PastebinHomePage {

    private WebDriver driver;
    private WebDriverWait wait;
    private final Logger logger = LogManager.getRootLogger();

    private static final String PASTEBIN_HOME_URL = "http://pastebin.com";

    @FindBy(css="textarea[id='postform-text']")
    private WebElement textArea;

    @FindBy(css ="input[id='postform-name']")
    private WebElement pasteTitle;

    @FindBy(css = "span[id='select2-postform-expiration-container']")
    private WebElement pasteExpiration;

    @FindBy(css= "span[class ='select2-selection select2-selection--single'][aria-labelledby='select2-postform-expiration-container']")
    private WebElement expitationValues;

    @FindBy(css="span[id='select2-postform-format-container']")
    private WebElement highlighting;

    @FindBy(css ="input[class='select2-search__field'][aria-controls='select2-postform-format-results']")
    private WebElement inputHighlightingValue;

    @FindBy(css ="li[id='select2-postform-format-result-rkl6-8']")
    private WebElement highlightingValueBash;

    @FindBy(css = "button[class='btn -big']")
    private WebElement createNewPasteButton;

    public PastebinHomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver,10);
    }

    public PastebinHomePage openPage(){
        driver.navigate().to(PASTEBIN_HOME_URL);
        wait.until(ExpectedConditions.visibilityOf(textArea));
        logger.info("Main page  opened");
        return this;
    }

    public PastebinHomePage typeText(String text){
        wait.until(ExpectedConditions.visibilityOf(textArea));
        textArea.sendKeys(text);
        logger.info("Text is typed");
        return this;
    }

    public PastebinHomePage typeTextFromFile(String file){
        wait.until(ExpectedConditions.visibilityOf(textArea));
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line ;
            while ((line = br.readLine() ) != null) {
                textArea.sendKeys(line);
                textArea.sendKeys(Keys.ENTER);
            }
            logger.info("Text from file is typed");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    public PastebinHomePage selectPasteExpirationValue10Minutes(){
        wait.until(ExpectedConditions.visibilityOf(pasteExpiration));
        pasteExpiration.click();
        wait.until(ExpectedConditions.visibilityOf(expitationValues));
        Actions builder = new Actions(driver);
        Action action = builder.moveToElement(expitationValues)
                .sendKeys(Keys.DOWN)
                .sendKeys(Keys.DOWN)
                .sendKeys(Keys.ENTER).build();
        action.perform();
        logger.info("Expiration value '10 minutes' selected");
        System.out.println("expitationValues.getText() = "+ expitationValues.getText());
        return this;
    }

    public PastebinHomePage typeTitle(String title){
        wait.until(ExpectedConditions.visibilityOf(pasteTitle));
        pasteTitle.sendKeys(title);
        logger.info("Title is typed");
        return this;
    }

    public PastebinHomePage selectHighlightingValue(String language){
        wait.until(ExpectedConditions.visibilityOf(highlighting));
        highlighting.click();
        wait.until(ExpectedConditions.visibilityOf(inputHighlightingValue));
        Actions builder = new Actions(driver);
        Action action = builder
                .moveToElement(inputHighlightingValue)
                .sendKeys(language)
                .sendKeys(Keys.ENTER)
                .build();
        action.perform();
        logger.info("Bash language selected");
        return this;
    }

    public PastebinWithCreatedPastePage clickCreateNewPasteButton(){
        wait.until(ExpectedConditions.elementToBeClickable(createNewPasteButton));
        createNewPasteButton.click();
        logger.info("'Create new Paste' button clicked");
        return new PastebinWithCreatedPastePage(driver);
    }


}
