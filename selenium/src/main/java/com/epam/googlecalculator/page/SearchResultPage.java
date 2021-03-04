package com.epam.googlecalculator.page;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;
import java.util.NoSuchElementException;


public class SearchResultPage extends AbstractPage {

    public static final String TERM = "Google Cloud Platform Pricing Calculator";

    @FindBy(xpath = "//*[@class='gsc-webResult gsc-result']")
    private List<WebElement> searchResult;

    @FindBy(css = "a.gs-title")
    private List<WebElement> searchResultTitles;

    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected SearchResultPage openPage() {
        return this;
    }

    public boolean isSearchResultPresent() {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(searchResult));
            if (!searchResult.isEmpty()) {
                log.info("Search result has size = "+searchResult.size());
                return true;
            } else {
                log.error("Search result NULL.");
                return false;
            }
        } catch (NoSuchElementException | TimeoutException e) {
            log.error("Search result NULL.");
            return false;
        }

    }

    public AbstractPage openCalculatorFromSearchResult() {
        wait.until(ExpectedConditions.visibilityOfAllElements(searchResult));
        for(WebElement element : searchResultTitles){
            System.out.println(element.getText());
            if (element.getText().trim().equals(TERM)) {
                element.click();
                log.info("'Google Cloud Platform Pricing Calculator' page opened via search result.");
                return new GoogleCloudPlatformPricingCalculatorPage(driver);
            }
        }
        log.error("Error! "+TERM+" page is NOT present in search result!");
        return this;
    }
}
