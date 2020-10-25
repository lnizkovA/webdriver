package com.epam.googlecalculatortests;

import com.epam.googlecalculator.page.GoogleCloudHomePage;
import com.epam.googlecalculator.page.GoogleCloudPlatformPricingCalculatorPage;
import com.epam.googlecalculator.page.TenMiniteEmailPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestOpenGoogleCalculatorViaSearch extends TestBase {

    private static final String TERM = "Google Cloud Platform Pricing Calculator";

    private GoogleCloudHomePage homePage;

    @BeforeClass(alwaysRun = true)
    public void testClassSetUp() {
        homePage = new GoogleCloudHomePage(driver);
    }


    @Test
    public void testOpenGoogleCloudPlatformPricingCalculatorFromSearchResult() {
        System.out.println("RUN testOpenGoogleCloudPlatformPricingCalculatorFromSearchResult");
        boolean expectedResult = homePage
                .openPage()
                .searchTerm(driver, TERM)
                .openCalculatorFromSearchResult()
                .isOpenGoogleCalculator();
        Assert.assertTrue(expectedResult, "Google Calculator is not OPENed");
    }

}
