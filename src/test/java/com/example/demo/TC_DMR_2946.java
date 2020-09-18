package com.example.demo;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Locale;

/*Test case DMR-2946 udviklet af Martin Mansour*/

public class TC_DMR_2946 {

    WebDriver driver;
    String selectedForretningsparameter;
    static Logger logger = Logger.getLogger(TC_DMR_2997.class);


    @Test(dataProvider = "testdata", groups = {"test-exclude"})
    public void DemoProject(String username, String password, String regnr, String w_username, String w_password, String konst, String navn,
                            String y1, String y2) throws Exception {
        System.out.println("******************Begin 2946**********************");
        //ScreenRecorderUtil.startRecord("AutomatiseretDMRTest");

        //Step 1: Login
        feXpathClick(driver, "//*[@id=\"goToLogin\"]", "Log på click");

        feXpathSendkeys(driver, "//*[@id=\"tastselvNavn\"]", username, "CVR nr sendkeys");
        feXpathSendkeys(driver, "//*[@id=\"tastselvPass\"]", password, "Password sendkeys");
        feXpathClick(driver, "//*[@id=\"testlogin_loginbtn\"]", "Login click");

        //Step 2: Vælg i venstremenuen Leasing > Reguler leasingafgift
        feXpathClick(driver, "/html/body/div[2]/div/div[2]/div/div/ul/li[10]/a", "Leasing click");
        feXpathClick(driver, "/html/body/div[2]/div/div[2]/div/div/ul/li[10]/ul/li[5]/a", "Reguler leasing click");

        //Step 3: Indtast registreringsnummer
        feXpathClick(driver, "//*[@id=\"regnr\"]", "Regnr click");
        feXpathSendkeys(driver, "//*[@id=\"soegeord\"]", regnr, "Regnr sendkeys");
        feXpathClick(driver, "//*[@id=\"fremsoegKtBtn\"]", "Søg click");
        Thread.sleep(5000);
        //Step 4: Vælg leasingaftalen i  oversigten over leasingaftaler - aftalen er under 3 år gammel
        feXpathClick(driver, "/html/body/div[2]/div/div[1]/div[2]/div[2]/div[2]/table/tbody/tr[2]/td[1]/a/span",
                "Aftale fra list");

        //Step 5: Log ind som Motormedarbejder
        feXpathClick(driver, "//*[@id=\"goToLogout\"]","Log af click");
        feXpathClick(driver, "//*[@id=\"goToLogin\"]", "Log på click");

        feXpathClick(driver, "//*[@id=\"testlogin_skat\"]", "Vælg Skat medarbejder login click");
        feXpathSendkeys(driver, "//*[@id=\"wnr\"]", w_username, "W-nr sendkeys");
        feXpathSendkeys(driver, "//*[@id=\"wnrPass\"]", w_password, "W-nr Password sendkeys");
        feXpathClick(driver, "//*[@id=\"testlogin_loginbtn\"]", "Login click");

        //Step 6: Vælg i venstremenuen Administration > Vis/rediger forretningsparameter
        feXpathClick(driver, "/html/body/div[2]/div/div[2]/div/div/ul/li[6]/a", "Administration click");
        feXpathClick(driver, "/html/body/div[2]/div/div[2]/div/div/ul/li[6]/ul/li[1]/a",
                "Vis/rediger forretningsparameter click");

        //Step 7: Vælg Forretningsparameter "Konstant"
        feXpathClick(driver, "/html/body/div[2]/div/div[1]/div[2]/div[2]/div/div/div[2]/div[1]/table/tbody/tr[43]/td/a/span",
                "Konstant click");

        //Step 8: Find Forretningsparameter "Konstant"
        //Vælg Forretningsparameter "Konstant" i det * markerede felt og skriv ’VaerditabsBeregningFristPeriode’  i Feltet Navn
        //SØG

        selectedForretningsparameter = selectFromDropdown(driver, "//*[@id=\"fremsoegFpvType\"]",
                "Forretningsparameter dropdown", konst);
        Assert.assertEquals(selectedForretningsparameter, konst);

        feXpathSendkeys(driver, "//*[@id=\"fpvSoegeord\"]", navn, "Navn sendkeys");

        feXpathClick(driver, "//*[@id=\"searchButton\"]", "Søg click");

        //Step 9: Klik på ’VaerditabsBeregningFristPeriode’ under søgeresultat
        Thread.sleep(5000);
        feXpathClick(driver, "/html/body/div[2]/div/div[1]/div[2]/div[2]/div/div/div[2]/div[1]/form/div[3]/table/tbody/tr[2]/td[2]/a/span",
                "Forretningsparameter liste click");

        //Step 10: Klik på Vedligehold og ændre -3y til -1y i Feltet "Værdi"
        //NÆSTE

        feXpathClick(driver, "//*[@id=\"vedligehold\"]","Vedligehold click");
        feXpathSendkeys(driver, "//*[@id=\"fieldTextBox_1\"]", y1, "Værdi sendkeys");
        feXpathClick(driver, "//*[@id=\"wizardNextBtn\"]","Næste click");

        //Step 11: Klik "Godkend"
        feXpathClick(driver, "//*[@id=\"godkend\"]","Godkend click");

        //Step 12: Log ind som Leasinggiver
        feXpathClick(driver, "//*[@id=\"goToLogout\"]","Log af click");
        feXpathClick(driver, "//*[@id=\"goToLogin\"]", "Log på click");

        feXpathSendkeys(driver, "//*[@id=\"tastselvNavn\"]", username, "CVR nr sendkeys");
        feXpathSendkeys(driver, "//*[@id=\"tastselvPass\"]", password, "Password sendkeys");
        feXpathClick(driver, "//*[@id=\"testlogin_loginbtn\"]", "Login click");

        //Step 13: Vælg i venstremenuen Leasing > Reguler leasingafgift
        feXpathClick(driver, "/html/body/div[2]/div/div[2]/div/div/ul/li[10]/a", "Leasing click 2 ");
        feXpathClick(driver, "/html/body/div[2]/div/div[2]/div/div/ul/li[10]/ul/li[5]/a", "Reguler leasing click 2");

        //Step 14: Fremsøg samme køretøj igen i Fremsøg køretøj
        String noResult = driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div[2]/div[2]/div[2]")).getText();
        Assert.assertEquals(noResult, "Der er ingen gyldige certifikater tilknyttet det fremsøgte køretøj.");

        //Step 15: Log ind som motormedarbejder
        feXpathClick(driver, "//*[@id=\"goToLogout\"]","Log af click");
        feXpathClick(driver, "//*[@id=\"goToLogin\"]", "Log på click");

        feXpathClick(driver, "//*[@id=\"testlogin_skat\"]", "Vælg Skat medarbejder login click");
        feXpathSendkeys(driver, "//*[@id=\"wnr\"]", w_username, "W-nr sendkeys");
        feXpathSendkeys(driver, "//*[@id=\"wnrPass\"]", w_password, "W-nr Password sendkeys");
        feXpathClick(driver, "//*[@id=\"testlogin_loginbtn\"]", "Login click");

        //Step 16: Vælg i venstremenuen Administration > Vis/rediger forretningsparameter
        feXpathClick(driver, "/html/body/div[2]/div/div[2]/div/div/ul/li[6]/a", "Administration click");
        feXpathClick(driver, "/html/body/div[2]/div/div[2]/div/div/ul/li[6]/ul/li[1]/a",
                "Vis/rediger forretningsparameter click");

        //Step 17: Find Forretningsparameter VaerditabsBeregningFristPeriode,
        // som beskrevet i de forrige steps og ændr den tilbage til 3 år
        feXpathClick(driver, "/html/body/div[2]/div/div[1]/div[2]/div[2]/div/div/div[2]/div[1]/table/tbody/tr[43]/td/a/span",
                "Konstant click 2");

        selectedForretningsparameter = selectFromDropdown(driver, "//*[@id=\"fremsoegFpvType\"]",
                "Forretningsparameter dropdown 2", konst);
        Assert.assertEquals(selectedForretningsparameter, konst);

        feXpathSendkeys(driver, "//*[@id=\"fpvSoegeord\"]", navn, "Navn sendkeys 2");

        feXpathClick(driver, "//*[@id=\"searchButton\"]", "Søg click 2");

        feXpathClick(driver, "/html/body/div[2]/div/div[1]/div[2]/div[2]/div/div/div[2]/div[1]/form/div[3]/table/tbody/tr[2]/td[2]/a/span",
                "Forretningsparameter liste click 2");

        feXpathClick(driver, "//*[@id=\"vedligehold\"]","Vedligehold click 2");
        feXpathSendkeys(driver, "//*[@id=\"fieldTextBox_1\"]", y2, "Værdi sendkeys 2");
        feXpathClick(driver, "//*[@id=\"wizardNextBtn\"]","Næste click 2");

        feXpathClick(driver, "//*[@id=\"godkend\"]","Godkend click 2");

        String resultHeader = driver.findElement(By.tagName("h1")).getText();
        Assert.assertEquals(resultHeader, "Kvittering - Registrer forretningsparameterværdi");
        Thread.sleep(5000);

        feXpathClick(driver, "//*[@id=\"goToLogout\"]", "Log out click");

        //ScreenRecorderUtil.stopRecord();
        System.out.println("******************Finished 2946**********************");
    }

    public void feXpathClick(WebDriver driver, String destination, String errorMsg) throws Exception {
        try{
            driver.findElement(By.xpath(destination)).click();
        }catch (Exception e){
            throw new Exception("Error finding element to click: " + errorMsg);
        }
    }

    public void feXpathSendkeys(WebDriver driver, String destination, String input, String errorMsg) throws Exception{
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(destination)));
        try{
            driver.findElement(By.xpath(destination)).clear();
            driver.findElement(By.xpath(destination)).sendKeys(input);
        }catch (Exception e){
            throw new Exception("Error finding element to send keys: " + errorMsg);
        }
    }

    public String selectFromDropdown(WebDriver driver, String destination, String errorMsg, String type) throws Exception {
        String selectedResult;
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(destination)));
        try{
            Select result = new Select(driver.findElement(By.xpath(destination)));
            result.selectByVisibleText(type);
            selectedResult = result.getFirstSelectedOption().getText();

            return selectedResult;
        }catch(Exception e){
            throw new Exception("Error finding item in dropdown menu: " + errorMsg);
        }
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
            options.addArguments("--headless");
            options.addArguments("--window-size=1920x1080"); //should be enabled for Jenkins
            options.addArguments("--verbose");
            options.addArguments("–-no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.setPageLoadStrategy(PageLoadStrategy.NONE);
            driver = new ChromeDriver(options);
        }else{
        }

        driver.get("https://dmrsit1gateway1.skat.dk/dmr-front/dmr.portal");
       // driver.manage().window().maximize();
        //driver.manage().deleteAllCookies();
        //driver.get("chrome://settings/clearBrowserData");
        //driver.findElement(By.xpath("//*[@id=\"clearBrowsingDataConfirm\"]")).sendKeys(Keys.ENTER);
        // driver.findElement(By.xpath("//settings-ui")).sendKeys(Keys.ENTER);
    }

    @AfterMethod
    void ProgramTermination(){
        driver.quit();
    }

    @DataProvider(name = "testdata")
    public Object[][] TestDataFeed(){

        ReadExcelFile config = new ReadExcelFile("testdata_dmr_2.xlsx");

        int rows = config.getRowCount(0);

        Object[][] inputs = new Object[rows][9];

        for (int i = 0; i < rows; i++){
            inputs[i][0] = config.getData(0, i, 0);
            inputs[i][1] = config.getData(0, i, 1);
            inputs[i][2] = config.getData(0, i, 2);
            inputs[i][3] = config.getData(0, i, 3);
            inputs[i][4] = config.getData(0, i, 4);
            inputs[i][5] = config.getData(0, i, 5);
            inputs[i][6] = config.getData(0, i, 6);
            inputs[i][7] = config.getData(0, i, 7);
            inputs[i][8] = config.getData(0, i, 8);

        }

        return inputs;
    }
}
