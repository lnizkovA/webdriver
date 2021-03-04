package com.epam.googlecalculatortests;

import com.epam.googlecalculator.page.GoogleCloudHomePage;
import com.epam.googlecalculator.page.GoogleCloudPlatformPricingCalculatorPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.epam.googlecalculatortests.TestSearchResultPage.TERM;

public class TestOpenGoogleCalculatorViaSearch extends TestBase {

    private GoogleCloudHomePage homePage;

    @BeforeClass(alwaysRun = true)
    public void testClassSetUp() {
        homePage = new GoogleCloudHomePage(driver);
    }


    @Test
    public void testOpenGoogleCloudPlatformPricingCalculatorFromSearchResult() {
        System.out.println("RUN testOpenGoogleCloudPlatformPricingCalculatorFromSearchResult");
        GoogleCloudPlatformPricingCalculatorPage calculatorPage = (GoogleCloudPlatformPricingCalculatorPage) homePage
                .openPage()
                .searchTerm(driver, TERM)
                .openCalculatorFromSearchResult();
        boolean expectedResult = calculatorPage.isOpenGoogleCalculator();
        Assert.assertTrue(expectedResult, "Google Calculator is not OPENed");
    }

}
