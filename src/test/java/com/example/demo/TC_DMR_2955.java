package com.example.demo;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Locale;

//import sun.security.jgss.GSSToken;



public class TC_DMR_2955 {

    WebDriver driver;
    String testName = "";
    static Logger logger = Logger.getLogger(TC_DMR_2955.class);
    //private GSSToken logger;

    @Test(dataProvider = "testdata", groups = {"test-include"})
    public void DemoProject(String w_nr, String w_password, String stelnr, String cvr, String cpr) throws Exception {
        System.out.println("******************Begin 2955**********************");
        // ScreenRecorderUtil.startRecord("AutomatiseretDMRTest");
        try {
            w_nr = "w00100";
            cvr = "10157676";
            cpr = "130450-0537";
            w_password = "Cocoo9Hei";
            stelnr = "WBAPX71000B985189";

            //Step 1: Log ind
            feXpathClick(driver, "//*[@id=\"goToLogin\"]", "Log på click");
            feXpathClick(driver, "//*[@id=\"testlogin_skat\"]", "w-nr marker");
            feXpathSendkeys(driver, "//*[@id=\"wnr\"]", w_nr, "w-nr sendkeys");
            feXpathSendkeys(driver, "//*[@id=\"wnrPass\"]", w_password, "w-password sendkeys");
            feXpathClick(driver, "//*[@id=\"testlogin_loginbtn\"]", "Login click");

            //Step 2: MR -> Registreringsafgift -> Fastsæt registreringsafgift
            //
            //*Anmelder:* *BID01*
            //*Årsag:* Andet
            //*Handelspris:* 250.000
            //*Nypris:* 300.000
            //
            //Tryk *Beregn*, *Næste*, *Næste* og *Fastsæt værdi*.

            feXpathClick(driver, "/html/body/div[2]/div/div[2]/div/div/ul/li[6]/a", "Registreringsafgift click");
            feXpathClick(driver, "/html/body/div[2]/div/div[2]/div/div/ul/li[6]/ul/li[1]/a",
                    "Fastsæt registreringsafgift click");
            feXpathClick(driver, "//*[@id=\"stelnr\"]", "Stelnummer click");
            feXpathSendkeys(driver, "//*[@id=\"soegeord\"]", stelnr, "Stelnr sendkeys");
            feXpathClick(driver, "//*[@id=\"fremsoegKtBtn\"]", "Soeg click");
            Thread.sleep(5000);

            if(driver.findElement(By.xpath("//*[@id=\"AnmelderInputId\"]")).isDisplayed()){
                feXpathSendkeys(driver, "//*[@id=\"AnmelderInputId\"]", cvr, "CVR sendkeys");
            }

            String selectedArt = selectFromDropdown(driver, "//*[@id=\"lblAnmeldelseAarsag\"]", "Årsag dropdown", "Andet");
            Assert.assertEquals(selectedArt, "Andet");

            feXpathSendkeys(driver, "//*[@id=\"lblHandelspris\"]", "250.000", "Handelspris sendkeys");
            feXpathSendkeys(driver, "//*[@id=\"lblNypris\"]", "300.000", "Nypris sendkeys");
            feXpathClick(driver, "//*[@id=\"dmr_portlet_registreringsafgiftsdata_btnBeregn\"]", "Beregn click");
            Thread.sleep(5000);

            for (int i = 1; i <= 2; i++) {
                feXpathClick(driver, "//*[@id=\"wizardNextBtn\"]", "Næste click");
                Thread.sleep(2000);
            }

            feXpathClick(driver, "//*[@id=\"lblFastsaetVaerdi\"]", "Fastsæt værdi click");

            String resultHeader = driver.findElement(By.tagName("h1")).getText();
            Assert.assertEquals(resultHeader, "Kvittering - Registrer værdiansættelse på køretøj");
            //Thread.sleep(5000);

            //Step 3: MR -> Leasing -> Opret leasingaftale.
            //Fremsøg *KID01*
            //*Ejer:* *BID01*
            //*Bruger:* *BID02*.
            //*Leasingperiode:* Angivelse af fast periode
            //*Leasing start:* 01-09-2020
            //*Leasing slut:* 31-12-2020
            feXpathClick(driver, "/html/body/div[2]/div/div[2]/div/div/ul/li[12]/a", "Leasing click");
            feXpathClick(driver, "/html/body/div[2]/div/div[2]/div/div/ul/li[12]/ul/li[2]/a", "Opret leasingaftale click");

            feXpathClick(driver, "//*[@id=\"stelnr\"]", "Stelnummer click");
            feXpathSendkeys(driver, "//*[@id=\"soegeord\"]", stelnr, "Stelnr sendkeys");
            feXpathClick(driver, "//*[@id=\"fremsoegKtBtn\"]", "Soeg click");
            Thread.sleep(5000);

            feXpathSendkeys(driver, "//*[@id=\"SelskabInputId\"]", cvr, "Leasingselskab sendkeys");
            Thread.sleep(2000);
            feXpathSendkeys(driver, "//*[@id=\"KundeInputId\"]", cpr, "Kunde sendkeys");
            Thread.sleep(2000);
            feXpathClick(driver, "//*[@id=\"periodeValg_0\"]", "Varighed click");
            Thread.sleep(2000);
            feXpathSendkeys(driver, "//*[@id=\"leasingPeriodeStart\"]", "01-09-2020", "Startdato sendkeys");
            Thread.sleep(2000);
            feXpathSendkeys(driver, "//*[@id=\"leasingPeriodeSlut\"]", "31-12-2020", "Slutdato sendkeys");
            feXpathClick(driver, "//*[@id=\"wizardNextBtn\"]", "Næste 2 click");
            Thread.sleep(2000);
            feXpathClick(driver, "//*[@id=\"btnOpretLeasingCertifikat\"]", "Opret leasingaftale click");

            resultHeader = driver.findElement(By.tagName("h1")).getText();
            Assert.assertEquals(resultHeader, "Kvittering - Opret leasingaftale");

            //Step 4: MR -> Registrering -> Registrer køretøj
            //Fremsøg *KID01*

            feXpathClick(driver, "/html/body/div[2]/div/div[2]/div/div/ul/li[5]/a", "Registrering click");
            feXpathClick(driver, "/html/body/div[2]/div/div[2]/div/div/ul/li[5]/ul/li[1]/a", "Registrer køretøj click");
            feXpathClick(driver, "//*[@id=\"stelnr\"]", "Stelnummer 2 click");
            feXpathSendkeys(driver, "//*[@id=\"soegeord\"]", stelnr, "Stelnr 2 sendkeys");
            feXpathClick(driver, "//*[@id=\"fremsoegKtBtn\"]", "Soeg click");
            feXpathClick(driver, "//*[@id=\"goToLogout\"]", "Log out click");


        }catch(Exception e){
            System.out.println(e);
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            throw new Exception();
        }

        //ScreenRecorderUtil.stopRecord();
        System.out.println("******************Finished 2955**********************");
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
        try{
            try{
                Select result = new Select(driver.findElement(By.xpath(destination)));
                result.selectByVisibleText(type);
                String selectedResult = result.getFirstSelectedOption().getText();

                return selectedResult;
            }catch (ElementNotSelectableException e){
                throw new ElementNotSelectableException("Error! Element not selectable: " + errorMsg);
            }

        }catch(NoSuchElementException e){
            throw new NoSuchElementException("Error finding element in dropdown menu: " + errorMsg);
        }
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
            driver = new ChromeDriver(options);
        }else{
        }

        driver.get("https://dmrsit1gateway1.skat.dk/dmr-front/dmr.portal");
      //  driver.manage().window().maximize();
        //driver.manage().window().setSize(new Dimension(640,360));
    }

    @AfterMethod
    void ProgramTermination(){
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