package com.example.demo;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Locale;


/*Test Case DMR-2997 udviklet af Martin Mansour*/

public class TC_DMR_2997 {

    WebDriver driver;
    String testName = "";
    //GSSToken logger;
    static Logger logger = Logger.getLogger(TC_DMR_2997.class);


    @Test(dataProvider = "testdata", groups = {"test-include"})
    public void DemoProject(String username, String password, String regnr, String art, String anvendelse) throws Exception {
        long id = Thread.currentThread().getId();

        System.out.println("*******************Begin 2997*********************");

        //ScreenRecorderUtil.startRecord("AutomatiseretDMRTest");
        try {
            //Test step 1: Login
            feXpathClick(driver, "//*[@id=\"goToLogin\"]", "Log på");
            feXpathSendkeys(driver, "//*[@id=\"tastselvNavn\"]", username, "CVR nr");
            feXpathSendkeys(driver, "//*[@id=\"tastselvPass\"]", password, "Password");
            feXpathClick(driver, "//*[@id=\"testlogin_loginbtn\"]", "Login");

            //Test step 2: Vælg syn
            feXpathClick(driver, "/html/body/div[2]/div/div[2]/div/div/ul/li[7]/a", "Syn");
            feXpathClick(driver, "/html/body/div[2]/div/div[2]/div/div/ul/li[7]/ul/li[1]/a",
                    "Indberet registreringsgrundlag - syn");

            //Test step 3: Tast registreringsnummer
            feXpathClick(driver, "//*[@id=\"regnr\"]", "Regnr");
            feXpathSendkeys(driver, "//*[@id=\"soegeord\"]", regnr, "Regnr");
            feXpathClick(driver, "//*[@id=\"fremsoegKtBtn\"]", "Søg");

            //Test step 4: Vælg køretøj
            feXpathClick(driver, "/html/body/div[2]/div/div[1]/div[2]/div[2]/form/div[3]/table/tbody/tr[2]/td[1]",
                    "Køretøj fra list");

            //Test step 5: Tryk næste
            feXpathClick(driver, "//*[@id=\"wizardNextBtn\"]", "Tryk næste");

            //Tjek om Art og Anvendelse er sat som Personbil og Privat personkørsel

            String selectedArt = selectFromDropdown(driver, "//*[@id=\"art\"]", "art dropdown", art);
            Assert.assertEquals(selectedArt, art);

            String selectedAnvendelse = selectFromDropdown(driver, "//*[@id=\"anvendelse\"]", "anvendelse dropdown", anvendelse);
            Assert.assertEquals(selectedAnvendelse, anvendelse);

            //Test step 6: Tryk næste til faneblad 5
            for (int i = 1; i <= 3; i++) {
                feXpathClick(driver, "//*[@id=\"wizardNextBtn\"]", "Tryk næste");
                Thread.sleep(1000);
            }

            //Test step 7: Marker Godkendt, Tryk næste og Godkend oprettelsen
            feXpathClick(driver, "//*[@id=\"txtAngivSynsresultat_0\"]", "Synsresultat");
            Thread.sleep(1000);
            feXpathClick(driver, "//*[@id=\"wizardNextBtn\"]", "Tryk næste");
            Thread.sleep(1000);
            feXpathClick(driver, "//*[@id=\"createSubmit\"]", "Godkend oprettelse");
            Thread.sleep(1000);

            String resultHeader = driver.findElement(By.tagName("h1")).getText();
            Assert.assertEquals(resultHeader, "Kvittering - Registrer registreringssynsresultat");
            Thread.sleep(5000);
            feXpathClick(driver, "//*[@id=\"goToLogout\"]", "Log out click");


        }catch (ArithmeticException e){
            System.out.println(e);
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            throw new Exception();
        }



        //ScreenRecorderUtil.stopRecord();
        System.out.println("*******************Finished 2997**********************");
    }



    public void feXpathClick(WebDriver driver, String destination, String errorMsg) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(destination)));

        try{
            driver.findElement(By.xpath(destination)).click();
        }catch (Exception e){
            throw new NoSuchElementException("Error finding element to click: " + errorMsg);
        }
    }

    public void feXpathSendkeys(WebDriver driver, String destination, String input, String errorMsg) throws Exception{
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(destination)));

        try{
            driver.findElement(By.xpath(destination)).clear();
            driver.findElement(By.xpath(destination)).sendKeys(input);
        }catch (Exception e){
            throw new NoSuchElementException("Error finding element to send keys: " + errorMsg);
        }
    }

    public String selectFromDropdown(WebDriver driver, String destination, String errorMsg, String type) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(destination)));
        boolean staleElement = true;
        String selectedResult = "";
        while (staleElement == true){
            //try {
            try {
                Select result = new Select(driver.findElement(By.xpath(destination)));
                result.selectByVisibleText(type);
                selectedResult = result.getFirstSelectedOption().getText();
                staleElement = false;

            } catch (StaleElementReferenceException e) {
                staleElement = true;
            }

            //} catch (NoSuchElementException e) {
            //  throw new NoSuchElementException("Error finding element in dropdown menu: " + errorMsg);
            //}
        }
        return selectedResult;
    }

    @BeforeMethod
    @Parameters({"test-name"})
    public void beforeTest(String testName){
        this.testName = testName;
        long id = Thread.currentThread().getId();
        System.out.println("Before test-class " + testName + ". Thread id is: "
                + id);
    }

    @BeforeMethod
    void setup(){
        long id = Thread.currentThread().getId();

        String os = System.getProperty("os.name").toLowerCase(Locale.ENGLISH);
        if(os.indexOf("windows") >= 0){
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
            driver = new ChromeDriver();
        }else if(os.indexOf("linux") >= 0){
            System.setProperty("webdriver.chrome.driver", "chromedriver_85");
            System.setProperty("webdriver.chrome.whitelistedIps", "");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--window-size=800,600");
            options.addArguments("--disable-extensions");
            options.addArguments("--proxy-server='direct://'");
            options.addArguments("--proxy-bypass-list=*");
            options.addArguments("--start-maximized");
            options.addArguments("--headless");
            options.addArguments("--disable-gpu");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--no-sandbox");
            options.addArguments("--ignore-certificate-errors");
            options.addArguments("--user-agent=Chrome/85.0.4183.102 (Windows NT 10.0)");
            options.setPageLoadStrategy(PageLoadStrategy.NONE);
            //options.addArguments("--start-maximized");
            driver = new ChromeDriver(options);
        }else{
        }

        driver.get("https://dmrsit1gateway1.skat.dk/dmr-front/dmr.portal");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       // driver.manage().window().maximize();
        //driver.manage().window().setSize(new Dimension(640,360));



        

        
        //driver.manage().deleteAllCookies();
        //driver.get("chrome://settings/clearBrowserData");
        //driver.findElement(By.xpath("//*[@id=\"clearBrowsingDataConfirm\"]")).sendKeys(Keys.ENTER);
        // driver.findElement(By.xpath("//settings-ui")).sendKeys(Keys.ENTER);
    }

    @AfterMethod
    void ProgramTermination(){
        long id = Thread.currentThread().getId();
        driver.quit();
    }

    @DataProvider(name = "testdata")
    public Object[][] TestDataFeed(){

        ReadExcelFile config = new ReadExcelFile("testdata_dmr_1.xlsx");

        int rows = config.getRowCount(0);

        Object[][] inputs = new Object[rows][5];

        for (int i = 0; i < rows; i++){
            inputs[i][0] = config.getData(0, i, 0);
            inputs[i][1] = config.getData(0, i, 1);
            inputs[i][2] = config.getData(0, i, 2);
            inputs[i][3] = config.getData(0, i, 3);
            inputs[i][4] = config.getData(0, i, 4);
        }

        return inputs;
    }
}
