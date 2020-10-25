package com.epam.googlecalculatortests;

import com.epam.googlecalculator.page.GoogleCloudHomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestSearchResultPage extends TestBase {

    private final static String TERM = "Google Cloud Platform Pricing Calculator";

    private GoogleCloudHomePage homePage;

    @BeforeClass
    public void testClassSetup() {
        homePage = new GoogleCloudHomePage(driver);
    }

    @Test(priority = 1)
    public void testSearchGoogleCloudPlatformPricingCalculator() {
        System.out.println("RUN testSearchGoogleCloudPlatformPricingCalculator");
        boolean expectedSearchResult = homePage.openPage()
                .searchTerm(driver, TERM)
                .isSearchResultPresent();
        Assert.assertTrue(expectedSearchResult, "Search result NULL");
    }
}
