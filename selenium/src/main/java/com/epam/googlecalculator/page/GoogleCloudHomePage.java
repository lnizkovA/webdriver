package com.epam.googlecalculator.page;

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


public class GoogleCloudHomePage extends AbstractPage {

    private static final String HOME_URL = "https://cloud.google.com/";

    @FindBy(xpath = "//div[@class='devsite-searchbox']")
    private WebElement searchButton;

    @FindBy(xpath = "//div[@class='devsite-search-background']")
    private WebElement searchField;

    public GoogleCloudHomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public GoogleCloudHomePage openPage() {
        driver.navigate().to(HOME_URL);
        log.info("Page '"+driver.getTitle()+"' opened.");
        return this;
    }

    public SearchResultPage searchTerm(WebDriver driver, String term)  {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        searchButton.click();
        Actions builder = new Actions(driver);
        Action seriesOfActions = builder.moveToElement(searchButton)
                .click()
                .sendKeys(searchField, term)
                .sendKeys(Keys.ENTER)
                .build();
        seriesOfActions.perform();
        log.info("Search with term condition started.");
        return new SearchResultPage(driver);
    }
}
