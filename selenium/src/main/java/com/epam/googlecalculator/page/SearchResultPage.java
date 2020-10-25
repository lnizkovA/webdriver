package com.epam.googlecalculator.page;

import com.epam.googlecalculator.util.Utils;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


import java.util.List;
import java.util.NoSuchElementException;


public class SearchResultPage extends AbstractPage {

    private String searchURL = "https://cloud.google.com/products/calculator";

    private final Logger logger = LogManager.getRootLogger();

    @FindBy(xpath = "//*[@class='gsc-webResult gsc-result']")
    private List<WebElement> searchResult;

    @FindBy(xpath = "//a[@href = 'https://cloud.google.com/products/calculator']")
    private WebElement linkToCalculator;

    public SearchResultPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    protected SearchResultPage openPage() {
        return this;
    }

    public boolean isSearchResultPresent() {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(searchResult));
            if (!searchResult.isEmpty()) {
                logger.info("Search result has size = "+searchResult.size());
                return true;
            } else {
                logger.error("Search result NULL.");
                return false;
            }
        } catch (NoSuchElementException | TimeoutException e) {
            logger.error("Search result NULL.");
            return false;
        }

    }

    public GoogleCloudPlatformPricingCalculatorPage openCalculatorFromSearchResult() {
        wait.until(ExpectedConditions.visibilityOf(linkToCalculator));
        linkToCalculator.click();
        logger.info("'Google Cloud Platform Pricing Calculator' page opened via search result.");
        return new GoogleCloudPlatformPricingCalculatorPage(driver);

    }
}
