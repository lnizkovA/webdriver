package com.epam.googlecalculator.page;

import com.epam.googlecalculator.util.Utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class TenMiniteEmailPage extends AbstractPage{

    private static final String TEN_MINUTE_EMAIL = "https://10minutemail.com/";

    @FindBy(css = "div[id='copy_address']")
    private WebElement clickToCopyAddress;

    @FindBy(css = "span[id='inbox_count_number']")
    private WebElement inboxCount;

    @FindBy(css = "div[class='small_subject']")
    private WebElement emailSubject;

    @FindBy(css = "table[class='quote'] tr:last-of-type td:last-of-type h3")
    private WebElement totalCostInEmail;

    public TenMiniteEmailPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public TenMiniteEmailPage openPage() {
        driver.navigate().to(TEN_MINUTE_EMAIL);
        log.info("Page '"+driver.getTitle()+"' opened.");
        return this;
    }

    public String getTenMinuteEmail() throws InterruptedException {
        String email = "";
        Thread.sleep(2000);
        clickToCopyAddress.click();
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable content = clipboard.getContents(null);
        boolean hasTransferableText = (content != null) && content.isDataFlavorSupported(DataFlavor.stringFlavor);
        if (hasTransferableText) {
            try{
                email = (String) content.getTransferData(DataFlavor.stringFlavor);
            } catch (UnsupportedFlavorException | IOException e){
                e.printStackTrace();
            }
        }
        log.info(" Generated 10minutemail = " + email);
        return email;
    }

    public int checkInboxCountNumber() {
        wait.until(ExpectedConditions.visibilityOf(emailSubject));
        log.info("Inbox has "+inboxCount.getText()+" email(s)");
        return Integer.parseInt(inboxCount.getText());
    }

    public TenMiniteEmailPage openEmail() {
        wait.until(ExpectedConditions.visibilityOf(emailSubject));
        emailSubject.click();
        log.info("Received email opened.");
        return this;
    }

    public double getTotalEstimatedCostFromEmail() {
        return Utils.convertStringToDouble(totalCostInEmail.getText());
    }


}
