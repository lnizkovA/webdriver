package com.epam.googlecalculator.page;

import com.epam.googlecalculator.util.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.NoSuchElementException;

public class GoogleCloudPlatformPricingCalculatorPage extends AbstractPage {

    private final String GOOGLE_PRODUCTS_CALCULATOR_URL = "https://cloud.google.com/products/calculator";

    private final Logger logger =  LogManager.getRootLogger();

    @FindBy(xpath = "*[normalize-space(text()='Google Cloud Pricing Calculator')]")
    // [@class='md-toolbar-tools flex-gt-sm-50']
    private WebElement page;

    @FindBy(xpath = "//div[@class='tab-holder compute' and @title='Compute Engine']")
    private WebElement computeEngine;

    @FindBy(css = "input[ng-model='listingCtrl.computeServer.quantity']")
    private WebElement numberOfInstance;

    @FindBy(css = "input[ng-model='listingCtrl.computeServer.label']")
    private WebElement whatAreTheseInstancesFor;

    @FindBy(css = "md-select[ng-model='listingCtrl.computeServer.class']")
    private WebElement machineClass;

    @FindBy(css = "div[class*='md-clickable'] md-option[value='regular'] div[class='md-text']")
    private WebElement machineClassOption;

    @FindBy(css = "md-select[ng-model='listingCtrl.computeServer.series']")
    private WebElement series;

    @FindBy(css = "md-option[ng-repeat='item in listingCtrl.computeServerGenerationOptions[listingCtrl.computeServer.family]'][value='n1'] div")
    private WebElement seriesOption;

    @FindBy(css = "md-select[ng-model='listingCtrl.computeServer.instance']")
    private WebElement machineType;

    @FindBy(css = "div[class*='md-clickable'] md-option[value='CP-COMPUTEENGINE-VMIMAGE-N1-STANDARD-8'] div[class*='md-text']")
    private WebElement machineTypeOption;

    @FindBy(xpath = "//md-checkbox[@aria-label='Add GPUs']")
    private WebElement addGPUs;

    @FindBy(css = "md-select[ng-model*='gpuCount']")
    private WebElement numberOfGPUs;

    @FindBy(css = "md-option[ng-repeat*='gpuType'][value='1'] div")
    private WebElement numberOfGPUsOption;

    @FindBy(css = "md-select[ng-model*='gpuType']")
    private WebElement typeOfGPUs;

    @FindBy(css = "md-option[ng-repeat*='gpuList'][value='NVIDIA_TESLA_V100'] div")
    private WebElement typeOfGPUsOption;

    @FindBy(css = "md-select[ng-model='listingCtrl.computeServer.ssd']")
    private WebElement localSSD;

    @FindBy(css = "md-option[ng-repeat*='supportedSsd'][value='2'] div")
    private WebElement localSSDOption;

    @FindBy(css = "md-select[ng-model='listingCtrl.computeServer.location']")
    private WebElement datacenterLocation;

    @FindBy(css = "div[aria-hidden='false'] md-option[ng-repeat*='fullRegionList'][value='europe-west3']")
    private WebElement datacenterLocationOption;

    @FindBy(css = "md-select[ng-model='listingCtrl.computeServer.cud']")
    private WebElement committedUsage;

    @FindBy(css = "div[class*='md-clickable'] md-option[class='md-ink-ripple'][value='1'] div[class='md-text']")
    private WebElement committedUsageOption;

    @FindBy(css = "button[aria-label='Add to Estimate']")
    private WebElement addToEstimate;

    @FindBy(css = "h2[class='md-title'] b[class='ng-binding'")
    private WebElement totalEstimatedCost;

    @FindBy(css = "md-list-item[class*='md-1-line md-no-proxy'] div[class='md-list-item-text ng-binding']")
    private List<WebElement> estimatedData;

    @FindBy(css= "button[aria-label='Email Estimate']")
    private WebElement emailEstimateButton;

    @FindBy(css = "form[name='emailForm']")
    private WebElement emailYourEstimateForm;

    @FindBy(css= "input[ng-model='emailQuote.user.email']")
    private WebElement emailInputField;

    @FindBy(css= "button[aria-label='Send Email']")
    private WebElement sendEmailButton;



    public GoogleCloudPlatformPricingCalculatorPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    protected GoogleCloudPlatformPricingCalculatorPage openPage() {
        driver.navigate().to(GOOGLE_PRODUCTS_CALCULATOR_URL);
        logger.info("Page '"+driver.getTitle()+"' opened.");
        return this;
    }

    public boolean isOpenGoogleCalculator() {
        try {
            wait.until(ExpectedConditions.titleIs(driver.getTitle()));
            logger.info("driver.getCurrentUrl() = " + driver.getCurrentUrl());
            return driver.getCurrentUrl().equals(GOOGLE_PRODUCTS_CALCULATOR_URL);
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }

    public GoogleCloudPlatformPricingCalculatorPage openComputeEngineTab() {
        switchToFrame();
        wait.until(ExpectedConditions.visibilityOf(computeEngine));
        computeEngine.click();
        logger.info("Tab 'Compute Engine' selected.");
        return this;
    }

    public GoogleCloudPlatformPricingCalculatorPage selectNumberOfInstance(int number) {
        numberOfInstance.sendKeys(Integer.toString(number));
        logger.info("Number of instances = "+number+" is typed.");
        return this;
    }

    public GoogleCloudPlatformPricingCalculatorPage typeWhatAreTheseInstancesFor(String line) {
        whatAreTheseInstancesFor.sendKeys(line);
        logger.info("what Are These Instances For = "+line);
        return this;
    }

    public GoogleCloudPlatformPricingCalculatorPage selectMachineClass() {
        machineClass.click();
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("md-option[class='md-ink-ripple'][value='regular'] div[class='md-text']")));
        wait.until(ExpectedConditions.visibilityOf(machineClassOption));
        logger.info("Machine Class option '"+machineClassOption.getText()+"' selected.");
        machineClassOption.click();
        return this;
    }

    public GoogleCloudPlatformPricingCalculatorPage selectSeries() {
        series.click();
        wait.until(ExpectedConditions.visibilityOf(seriesOption));
        logger.info("Series option '"+seriesOption.getText()+"' selected.");
        seriesOption.click();
        return this;
    }


    public GoogleCloudPlatformPricingCalculatorPage selectMachineType() {
        machineType.click();
        wait.until(ExpectedConditions.visibilityOf(machineTypeOption));
        logger.info("Machine Type option '"+machineTypeOption.getText()+"' selected.");
        machineTypeOption.click();
        return this;
    }

    public GoogleCloudPlatformPricingCalculatorPage checkAddGPUs() {
        addGPUs.click();
        logger.info("Checkbox 'Add GPUs' is selected" + addGPUs.getAttribute("aria-checked"));
        return this;
    }

    public GoogleCloudPlatformPricingCalculatorPage selectNumberOfGPUs() {
        numberOfGPUs.click();
        wait.until(ExpectedConditions.visibilityOf(numberOfGPUsOption));
        logger.info("Number of GPUs option '"+numberOfGPUsOption.getText()+"' selected.");
        numberOfGPUsOption.click();
        return this;
    }

    public GoogleCloudPlatformPricingCalculatorPage selectTypeOfGPUs() {
        typeOfGPUs.click();
        wait.until(ExpectedConditions.visibilityOf(typeOfGPUsOption));
        logger.info("Type of GPUs option '"+typeOfGPUsOption.getText()+"' selected.");
        typeOfGPUsOption.click();
        return this;
    }

    public GoogleCloudPlatformPricingCalculatorPage selectLocalSSD() {
        localSSD.click();
        wait.until(ExpectedConditions.visibilityOf(localSSDOption));
        logger.info("Local SSD option '"+localSSDOption.getText()+"' selected.");
        localSSDOption.click();
        return this;
    }

    public GoogleCloudPlatformPricingCalculatorPage selectDatacenterLocation() {
        datacenterLocation.click();
        wait.until(ExpectedConditions.visibilityOf(datacenterLocationOption));
        logger.info("Datacenter location '"+datacenterLocationOption.getText()+"' selected.");
        datacenterLocationOption.click();
        return this;
    }

    public GoogleCloudPlatformPricingCalculatorPage selectCommittedUsage() {
        committedUsage.click();
        wait.until(ExpectedConditions.visibilityOf(committedUsageOption));
        logger.info("Committed Usage '"+committedUsageOption.getText()+"' selected.");
        committedUsageOption.click();
        return this;
    }

    public void clickAddEstimateButton() {
        addToEstimate.click();
        logger.info("'Add to Estimate' button clicked.");
    }

    public boolean isAddToEstimateButtonClickable() {
        logger.info("'Add To Estimate' Button = " + addToEstimate.isEnabled());
        return addToEstimate.isEnabled();
    }

    public boolean isTotalEstimatedCostAvailable() {
        try {
            wait.until(ExpectedConditions.visibilityOf(totalEstimatedCost));
            logger.info(totalEstimatedCost.getText());
            return totalEstimatedCost.isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            logger.error("Total Estimation Cost is not available.");
            return false;
        }
    }

    public boolean isEstimatedInputDataCorrect(String comparedData) {
       logger.info("Expected compared data = "+comparedData);
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(estimatedData));
            boolean result = false;
            if (!estimatedData.isEmpty()) {
                for (WebElement item : estimatedData) {
                    if (item.getText().contains(comparedData)) {
                        result = true;
                        break;
                    }
                }
            }
            return result;
        }catch (TimeoutException | NoSuchElementException e){
            return false;
        }
    }

    public double getActualEstimatedCost(){
            wait.until(ExpectedConditions.visibilityOf(totalEstimatedCost));
            double actualCost = Utils.convertStringToDouble(totalEstimatedCost.getText());
            logger.info("Actual estimated cost = "+actualCost);
            return actualCost;
    }

    public GoogleCloudPlatformPricingCalculatorPage clickEmailEstimateButton(){
        wait.until(ExpectedConditions.visibilityOf(emailEstimateButton));
        emailEstimateButton.click();
        logger.info("'Email Estimation' button clicked.");
        return this;
    }

    public GoogleCloudPlatformPricingCalculatorPage typeEmailToEmailForm(String email){
        wait.until(ExpectedConditions.visibilityOf(emailYourEstimateForm));
        emailInputField.sendKeys(email);
        logger.info("Email = "+email+" typed to email field. ");
        return this;
    }

    public boolean isYourEstimatedFormAvailable(){
        try {
            wait.until(ExpectedConditions.visibilityOf(emailYourEstimateForm));
            return emailYourEstimateForm.isEnabled();
        }  catch (TimeoutException | NoSuchElementException e) {
            logger.error("'Email your estimation form' isnot available.");
            return false;
        }
    }

    public void clickSendEmailButton(){
        wait.until(ExpectedConditions.visibilityOf(sendEmailButton));
        sendEmailButton.click();
    }

    public void switchToFrame() {
        driver.switchTo().frame(0);
        driver.switchTo().frame(driver.findElement(By.id("myFrame")));
        logger.info("Switch to frame.");
    }

    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }
}
