package com.epam.googlecalculator.page;

import com.epam.googlecalculator.util.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

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

    @FindBy(css = "button[aria-label='Add to Estimate']:enabled")
    private WebElement addToEstimate;

    @FindBy(css = "h2[class='md-title'] b[class='ng-binding']")
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
        return selectWebElementAndItsOption(machineClass, machineClassOption);
    }

    public GoogleCloudPlatformPricingCalculatorPage selectSeries() {
        return selectWebElementAndItsOption(series, seriesOption);
    }


    public GoogleCloudPlatformPricingCalculatorPage selectMachineType() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView();", machineClass);
        return selectWebElementAndItsOption(machineType, machineTypeOption);
    }

    public GoogleCloudPlatformPricingCalculatorPage checkAddGPUs() {
        addGPUs.click();
        logger.info("Checkbox 'Add GPUs' is selected" + addGPUs.getAttribute("aria-checked"));
        return this;
    }

    public GoogleCloudPlatformPricingCalculatorPage selectNumberOfGPUs() {
        return selectWebElementAndItsOption(numberOfGPUs, numberOfGPUsOption);
    }

    public GoogleCloudPlatformPricingCalculatorPage selectTypeOfGPUs() {
        return selectWebElementAndItsOption(typeOfGPUs, typeOfGPUsOption);
    }

    public GoogleCloudPlatformPricingCalculatorPage selectLocalSSD() {
        return selectWebElementAndItsOption(localSSD, localSSDOption);
    }

    public GoogleCloudPlatformPricingCalculatorPage selectDatacenterLocation() {
        return selectWebElementAndItsOption(datacenterLocation, datacenterLocationOption);
    }

    public GoogleCloudPlatformPricingCalculatorPage selectCommittedUsage() {
        if (System.getProperty("browser").equals("firefox")) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", committedUsage);
        }
        logger.info("SIZE:" + driver.findElements(By.cssSelector("md-select[ng-model='listingCtrl.computeServer.cud']")).size());
        return selectWebElementAndItsOption(committedUsage, committedUsageOption);
    }

    public void clickAddEstimateButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addToEstimate));
        logger.info("addToEstimate.isEnabled(): " + addToEstimate.isEnabled());
        addToEstimate.click();
        logger.info("'Add to Estimate' button clicked.");
    }

    public boolean isAddToEstimateButtonClickable() {
        logger.info("'Add To Estimate' Button = " + addToEstimate.isEnabled());
        return addToEstimate.isEnabled();
    }

    public boolean isTotalEstimatedCostAvailable() {
        try {
//            ((JavascriptExecutor) driver).executeScript(
//                    "arguments[0].scrollIntoView();", totalEstimatedCost);
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
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView();", emailYourEstimateForm);
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
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.switchTo().frame(0);
        driver.switchTo().frame(driver.findElement(By.id("myFrame")));
        logger.info("Switch to frame.");
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    private GoogleCloudPlatformPricingCalculatorPage selectWebElementAndItsOption(WebElement element, WebElement elementOption){
        element.click();
        wait.until(ExpectedConditions.visibilityOf(elementOption));
        logger.info("'"+elementOption.getText()+"' selected.");
        elementOption.click();
        return this;
    }

    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }
}
