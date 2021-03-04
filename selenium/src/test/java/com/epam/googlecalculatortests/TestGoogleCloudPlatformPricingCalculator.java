package com.epam.googlecalculatortests;

import com.epam.googlecalculator.page.GoogleCloudHomePage;
import com.epam.googlecalculator.page.GoogleCloudPlatformPricingCalculatorPage;
import com.epam.googlecalculator.page.TenMiniteEmailPage;
import com.epam.googlecalculator.service.TestDataReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.ArrayList;

public class TestGoogleCloudPlatformPricingCalculator extends TestBase {

    public static final String TESTDATA_NUMBER_OF_INSTANCES = "testdata.googlecalculator.numberOfInstances";
    public static final String TESTDATA_MACHINE_CLASS = "testdata.googlecalculator.machineClass";
    public static final String TESTDATA_INSTANCE_TYPE = "testdata.googlecalculator.instanceType";
    public static final String TESTDATA_REGION = "testdata.googlecalculator.region";
    public static final String TESTDATA_LOCAL_SDD = "testdata.googlecalculator.localSDD";
    public static final String TESTDATA_COMMITTED_TERM = "testdata.googlecalculator.committedTerm";
    public static final String TESTDATA_EXPECTED_ESTIMATED_COST = "testdata.googlecalculator.expectedEstimationCost";

    private GoogleCloudPlatformPricingCalculatorPage googleCalculatorPage;
    private TenMiniteEmailPage tenMiniteEmailPage;


    @BeforeClass(alwaysRun = true)
    public void testClassSetUp() {
        googleCalculatorPage = new GoogleCloudPlatformPricingCalculatorPage(driver);
        tenMiniteEmailPage = new TenMiniteEmailPage(driver);
    }

    @Test(priority = 1)
    public void testFillComputeEngineForm() {
        googleCalculatorPage.openPage()
                .openComputeEngineTab()
                .selectNumberOfInstance(Integer.parseInt(TestDataReader.getTestData(TESTDATA_NUMBER_OF_INSTANCES)))
                .typeWhatAreTheseInstancesFor("")
                .selectMachineClass()
                .selectSeries()
                .selectMachineType()
                .checkAddGPUs()
                .selectNumberOfGPUs()
                .selectTypeOfGPUs()
                .selectLocalSSD()
                .selectDatacenterLocation()
                .selectCommittedUsage()
                .clickAddEstimateButton();
        Assert.assertTrue(googleCalculatorPage.isTotalEstimatedCostAvailable(), "Total Estimated Cost IS NOT Avaliable!");
    }

    @Test(dependsOnMethods = "testFillComputeEngineForm",
            priority = 2)
    public void testIsMachineClassInEstimatedFormCorrect() {
        boolean result = googleCalculatorPage.isEstimatedInputDataCorrect(TestDataReader.getTestData(TESTDATA_MACHINE_CLASS));
        Assert.assertTrue(result, "Machine class in estimated form IS NOT Correct!");
    }

    @Test(dependsOnMethods = "testFillComputeEngineForm",
            priority = 3)
    public void testIsInstanceTypeInEstimatedFormCorrect() {
        boolean result = googleCalculatorPage.isEstimatedInputDataCorrect(TestDataReader.getTestData(TESTDATA_INSTANCE_TYPE));
        Assert.assertTrue(result, "Instance type in estimated form IS NOT Correct!");
    }

    @Test(dependsOnMethods = "testFillComputeEngineForm",
            priority = 4)
    public void testIsRegionInEstimatedFormCorrect() {
        boolean result = googleCalculatorPage.isEstimatedInputDataCorrect(TestDataReader.getTestData(TESTDATA_REGION));
        Assert.assertTrue(result, "Region in estimated form IS NOT Correct!");
    }

    @Test(dependsOnMethods = "testFillComputeEngineForm",
            priority = 5)
    public void testIsLocalSSDInEstimatedFormCorrect() {
        boolean result = googleCalculatorPage.isEstimatedInputDataCorrect(TestDataReader.getTestData(TESTDATA_LOCAL_SDD));
        Assert.assertTrue(result, "Local SDD in estimated form IS NOT Correct!");
    }

    @Test(dependsOnMethods = "testFillComputeEngineForm",
            priority = 6)
    public void testIsCommittedTermInEstimatedFormCorrect() {
        boolean result = googleCalculatorPage.isEstimatedInputDataCorrect(TestDataReader.getTestData(TESTDATA_COMMITTED_TERM));
        Assert.assertTrue(result, "Commitment term in estimated form IS NOT Correct!");
    }

    @Test(dependsOnMethods = "testFillComputeEngineForm",
            priority = 7)
    public void testCheckEstimatedCost() {
        double actualResult = googleCalculatorPage.getActualEstimatedCost();
        Assert.assertEquals(Double.parseDouble(TestDataReader.getTestData(TESTDATA_EXPECTED_ESTIMATED_COST)), actualResult
                , "Actual estimated cost IS NOT Correct!");
    }


    @Test(dependsOnMethods = "testFillComputeEngineForm",
            priority = 8)
    public void testIsYourEstimatedFormEnabled() {
        boolean result = googleCalculatorPage.clickEmailEstimateButton()
                .isYourEstimatedFormAvailable();
        Assert.assertTrue(result, "Your Estimated Form is NOT opened");
    }


    @Test(dependsOnMethods = "testIsYourEstimatedFormEnabled",
            priority = 9)
    public void testSendEmailWithEstimatedCost() throws InterruptedException {
        //open new tab in browser
        ((JavascriptExecutor) driver).executeScript("window.open()");
        logger.info("New empty tab in browser opened");

        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        logger.info("Current browser has tabs =  " + tabs.size());

        driver.switchTo().window(tabs.get(1));
        logger.info("Switch to empty tab.");

        String tenMinutEmail = tenMiniteEmailPage.openPage().getTenMinuteEmail();

        driver.switchTo().window(tabs.get(0));
        logger.info("Switch to page '" + driver.getTitle() + "'.");
//        googleCalculatorPage.switchToFrame();
        googleCalculatorPage.typeEmailToEmailForm(tenMinutEmail)
                .clickSendEmailButton();

        driver.switchTo().window(tabs.get(1));
        logger.info("Switch to page '" + driver.getTitle() + "'.");
        Assert.assertTrue(tenMiniteEmailPage.checkInboxCountNumber() > 0, "Email with estimated cost NOT received");
    }

    @Test(dependsOnMethods = "testSendEmailWithEstimatedCost",
            priority = 10)
    public void testIsEstimatedCostInEmailCorrect() {
        double actualEstimatedCostFromEmail = tenMiniteEmailPage.openEmail()
                .getTotalEstimatedCostFromEmail();
        logger.info("Actual estimation cost in email = " + actualEstimatedCostFromEmail);
        Assert.assertEquals(Double.parseDouble(TestDataReader.getTestData(TESTDATA_EXPECTED_ESTIMATED_COST)), actualEstimatedCostFromEmail, "Total Estimated Cost in email NOT correct");
    }

}
