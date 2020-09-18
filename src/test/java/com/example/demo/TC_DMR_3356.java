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
import java.util.Random;

//import sun.security.jgss.GSSToken;

public class TC_DMR_3356 {

    WebDriver driver;
    String testName = "";
    static Logger logger = Logger.getLogger(TC_DMR_3356.class);
    //private GSSToken logger;


    @Test(dataProvider = "testdata", groups = {"test-include"})
    public void DemoProject(String username, String password, String regnr, String art, String anvendelse) throws Exception  {
        System.out.println("******************Begin 3356**********************");
        //ScreenRecorderUtil.startRecord("AutomatiseretDMRTest");

        try{
        //Step 1: Login
        feXpathClick(driver, "//*[@id=\"goToLogin\"]", "Log på");
        feXpathSendkeys(driver, "//*[@id=\"tastselvNavn\"]", "10068622", "CVR nr");
        feXpathSendkeys(driver, "//*[@id=\"tastselvPass\"]", "Qudoraj78!", "Password");
        feXpathClick(driver, "//*[@id=\"testlogin_loginbtn\"]", "Login");

        //Step 2: I ventremenu vælges "Køretøjsoprettelse -> Opret køretøj"
        feXpathClick(driver, "/html/body/div[2]/div/div[2]/div/div/ul/li[3]/a", "Køretøjsoprettelse");
        feXpathClick(driver, "/html/body/div[2]/div/div[2]/div/div/ul/li[3]/ul/li[1]/a", "Opret køretøj");

        //Step 3: Vælg Ud fra Certificate of Conformity (CoC).
        //
        //Vælg næste.
        feXpathClick(driver, "//*[@id=\"cocId\"]", "Certificate of Conformity click");
        String selectedArt = selectFromDropdown(driver, "//*[@id=\"art\"]",
                "Årsag dropdown", "Personbil");
        Assert.assertEquals(selectedArt, "Personbil");
        feXpathClick(driver, "//*[@id=\"wizardNextBtn\"]", "Næste 1");

        //Step 4:Følgende skal udfyldes:
        //Anvendelse: Privat personkørsel
        String selectedAnvendelse = selectFromDropdown(driver, "//*[@id=\"anvendelse\"]",
                "Anvendelse dropdown", "Privat personkørsel");
        Assert.assertEquals(selectedAnvendelse, "Privat personkørsel");
        //Thread.sleep(1000);
        //Mærke: BMW
        String selectedMaerke = selectFromDropdown(driver, "//*[@id=\"maerke\"]",
                "Årsag dropdown", "BMW");
        //Thread.sleep(1000);
        Assert.assertEquals(selectedMaerke, "BMW");
        //Model: Frit valg
        String selectedModel = selectFromDropdown(driver, "//*[@id=\"model\"]",
                "Årsag dropdown", "1 SERIE");
        //Thread.sleep(1000);
        Assert.assertEquals(selectedModel, "1 SERIE");
        //Variant: Frit valg
        String selectedVariant = selectFromDropdown(driver, "//*[@id=\"variant\"]",
                "Årsag dropdown", "116I");
        //Thread.sleep(1000);
        Assert.assertEquals(selectedVariant, "116I");
        //Type: Frit valg
        String selectedType = selectFromDropdown(driver, "//*[@id=\"type\"]",
                "Årsag dropdown", "187");
        Thread.sleep(1000);
        Assert.assertEquals(selectedType, "187");

        //EFTypegodkendelsesnr.: e1*2001/116*0304
        feXpathSendkeys(driver, "//*[@id=\"tgNummer\"]", "e1*2001/116*0304", "Handelspris sendkeys");
        //EFTypegodkendt kateegori: M
        feXpathSendkeys(driver, "//*[@id=\"tgKategori\"]", "M", "Handelspris sendkeys");
        //EU-variant: 0
        feXpathSendkeys(driver, "//*[@id=\"euVariant\"]", "0", "Handelspris sendkeys");
        //EU-version: 0
        feXpathSendkeys(driver, "//*[@id=\"euVersion\"]", "0", "Handelspris sendkeys");
        //Fabrikant: 0
        feXpathSendkeys(driver, "//*[@id=\"fabrikant\"]", "0", "Handelspris sendkeys");

        String stelnr = "TEST240TESTW" + numbergenerator();

        //Stelnummer: KT1
            feXpathSendkeys(driver, "//*[@id=\"stelnummer\"]", stelnr,
                    "Handelspris sendkeys");
            //Farve: Blå
            String selectedFarve = selectFromDropdown(driver, "//*[@id=\"farve\"]",
                    "Årsag dropdown", "Blå");
            //Thread.sleep(1000);
            Assert.assertEquals(selectedFarve, "Blå");
            //Ved modelår indtastes 2013
            String selectedAar = selectFromDropdown(driver, "//*[@id=\"modelAar\"]",
                    "Årsag dropdown", "2013");
            //Thread.sleep(1000);
            Assert.assertEquals(selectedAar, "2013");
            //Tilkoblingsanordning = ja
            feXpathClick(driver, "//*[@id=\"tilkoblingMulighed_Yes\"]", "Log på");
            //Fuelmode: Monofuel
            String selectedFuel = selectFromDropdown(driver, "//*[@id=\"fuelmode\"]",
                    "Årsag dropdown", "Monofuel");
            //Thread.sleep(1000);
            Assert.assertEquals(selectedFuel, "Monofuel");
            //Ved "Indkøbspris" indtastes 154.850
            feXpathSendkeys(driver, "//*[@id=\"listePris\"]", "154.850", "Handelspris sendkeys");
            //Ved "Standardpris" indtastes 302.078
            feXpathSendkeys(driver, "//*[@id=\"stdPris\"]", "302.078", "Handelspris sendkeys");
            //Airbags = 6
            feXpathSendkeys(driver, "//*[@id=\"lblAirbags\"]", "6", "Handelspris sendkeys");
            //Markering ved radio
            feXpathClick(driver, "//*[@id=\"chkRadio\"]", "Radio check click");
            //Thread.sleep(1000);
            //Selealarmer = 2
            feXpathSendkeys(driver, "//*[@id=\"lblSelealarm\"]", "2", "Selealarmer sendkeys");
            //Markering ved ESC
            feXpathClick(driver, "//*[@id=\"chkEspElektroniskStabiliseringsProgram\"]", "Markering ved ESC");
            //Thread.sleep(1000);
            //Intergeret barnesæder: 0
            feXpathSendkeys(driver, "//*[@id=\"chkIntegreretBarnesaede\"]", "0", "Intergeret barnesæder sendkeys");
            //Markering ved ABS
            feXpathClick(driver, "//*[@id=\"chkAbsBremser\"]", "Markering ved ABS");
            //Thread.sleep(1000);
            //Klik på næste
            feXpathClick(driver, "//*[@id=\"wizardNextBtn\"]", "Næste 2");
            //Thread.sleep(1000);

            //Step 5: Følgende udfyldes:
            //Tekniks totalvægt:1250
            feXpathSendkeys(driver, "//*[@id=\"tekniskTotalVaegt\"]", "1250", "Teknisk totalvægt sendkeys");
            //Totalvægt:1250
            feXpathSendkeys(driver, "//*[@id=\"totalVaegt\"]", "1250", "Totalvægt sendkeys");
            //Køreklar vægt: 900
            feXpathSendkeys(driver, "//*[@id=\"koereklarVaegtMin\"]", "900", "Køreklar vægt sendkeys");
            //Vogntogsvægt: 2500
            feXpathSendkeys(driver, "//*[@id=\"vognVaegt\"]", "2500", "Vogntogsvægt sendkeys");
            //Med bremser: 1000
            feXpathSendkeys(driver, "//*[@id=\"tilkoblingsvaegtMedBremser\"]", "1000", "Med bremser sendkeys");
            //Uden bremser: 750
            feXpathSendkeys(driver, "//*[@id=\"tilkoblingsvaegtUdenBremser\"]", "750", "Uden bremser sendkeys");
            //
            //Målenorm = NEDC2
            String selectedMaaleNorm = selectFromDropdown(driver, "//*[@id=\"maaleNorm\"]",
                    "Målenorm dropdown", "NEDC-2");
            //Thread.sleep(1000);
            Assert.assertEquals(selectedMaaleNorm, "NEDC-2");

            feXpathSendkeys(driver, "//*[@id=\"motorMaerkning\"]", "A", "Mærkning sendkeys");

            String selectedDrivkraft = selectFromDropdown(driver, "//*[@id=\"drivkraftType\"]",
                    "Drivkraft dropdown", "Benzin");
            //Thread.sleep(1000);
            Assert.assertEquals(selectedDrivkraft, "Benzin");

            //L/100KM = 5,9
            feXpathSendkeys(driver, "//*[@id=\"braendstofForbrugMaalt\"]", "5,9", "Brændstofforbrug målt sendkeys");

            //Makering ved partikelfilter
            feXpathClick(driver, "//*[@id=\"partikelfilter_Yes\"]", "Partikelfilter click");
            //Thread.sleep(1000);
            //Slagvolumen: 1250
            feXpathSendkeys(driver, "//*[@id=\"slagVolumen\"]", "1250", "Slagvolumen sendkeys");
            //Største effekt: 278
            feXpathSendkeys(driver, "//*[@id=\"stoersteEffekt\"]", "278", "Største effekt sendkeys");
            //Maksimal hastighed: 280
            feXpathSendkeys(driver, "//*[@id=\"maksHastighed\"]", "280", "Maksimal hastighe sendkeysd");
            //Antal cyindre: 6
            feXpathSendkeys(driver, "//*[@id=\"cylinderAntal\"]", "6", "Antal cyindre sendkeys");
            //
            //Karosseritype: Sedan
            String selectedKarrosseri = selectFromDropdown(driver, "//*[@id=\"karrosseriType\"]",
                    "Karosseritype dropdown", "Sedan");
            //Thread.sleep(1000);
            Assert.assertEquals(selectedKarrosseri, "Sedan");

            //Fælge/dæk dimensioner:
            // 1. aksel: 205/55 R16 91W / 7Jx16 ET48.
            // 2. aksel: 205/55 R16 91W / 7Jx16 ET48 tommer/mm
            feXpathSendkeys(driver, "//*[@id=\"faelgDaek\"]", "" +
                    "1. aksel: 205/55 R16 91W / 7Jx16 ET48." +
                    "2. aksel: 205/55 R16 91W / 7Jx16 ET48 tommer/mm",
                    "Fælge/Dæk dimensioner sendkeys");
            //Sporvidden
            //Forrest: 1538
            feXpathSendkeys(driver, "//*[@id=\"sporviddenForrest\"]", "1538", "Sporvidde forrest sendkeys");
            //Bagerst: 1508
            feXpathSendkeys(driver, "//*[@id=\"sporviddenBagest\"]", "1508", "Sporvidde bagerst sendkeys");
            //Anbringelse af stelnummer: Højre vange
            feXpathSendkeys(driver, "//*[@id=\"stelNummerAnbringelse\"]", "Højre vange",
                    "Stelnummer anbringelse sendkeys");
            //Antal siddepladser
            //Minimum: 1
            feXpathSendkeys(driver, "//*[@id=\"siddepladserMin\"]", "1",
                    "Antal siddepladser sendkeys");

            //Antal: 2
            feXpathSendkeys(driver, "//*[@id=\"akselAntal\"]", "2", "Antal aksler sendkeys");

            //Antal døre: 4
            feXpathSendkeys(driver, "//*[@id=\"antalDoere\"]", "4", "Antal døre sendkeys");

            //Afstand: 2585 mm
            feXpathSendkeys(driver, "//*[@id=\"akselAfstand\"]", "2585",
                    "Afstand aksler sendkeys");

            //Trækkende aksel nummer: 1
            feXpathSendkeys(driver, "//*[@id=\"akselNummer\"]", "1",
                    "Trækkende aksel sendkeys");
            //Største tryk: 930 kg
            feXpathSendkeys(driver, "//*[@id=\"stoersteAkselTryk\"]", "930",
                    "Tryk aksel sendkeys");
            //
            //CO2udslip: 150,0
            feXpathSendkeys(driver, "//*[@id=\"CO2Udslip\"]", "150",
                    "CO2udslip sendkeys");
            //Emission
            //CO: 376,3
            feXpathSendkeys(driver, "//*[@id=\"coEmission\"]", "376,3",
                    "Emission CO sendkeys");
            //HC+NOX: 0,0
            feXpathSendkeys(driver, "//*[@id=\"hcEmission\"]", "0",
                    "Emission siddepladser sendkeys");
            //NOX: 50,7
            feXpathSendkeys(driver, "//*[@id=\"noxEmission\"]", "50,7",
                    "Emission siddepladser sendkeys");
            //Standstøj: 73,0
            feXpathSendkeys(driver, "//*[@id=\"standStoej\"]", "73",
                    "Standstøj sendkeys");
            //ved: 3750
            feXpathSendkeys(driver, "//*[@id=\"standStoejOmdrejningstal\"]", "3750",
                    "Standstøj ved sendkeys");
            //Kørselsstøj: 72,0
            feXpathSendkeys(driver, "//*[@id=\"koerselStoej\"]", "72",
                    "Kørselsstøj sendkeys");
            //Euronorm: V
            String selectedEuronorm = selectFromDropdown(driver, "//*[@id=\"euronorm\"]",
                    "Euronorm dropdown", "Euro V");
            //Thread.sleep(1000);
            Assert.assertEquals(selectedEuronorm, "Euro V");
            //NCAP markering (mindst 5 stjerner).: Ja
            feXpathClick(driver, "//*[@id=\"ncapTest_Yes\"]", "NCAP click");
            Thread.sleep(1000);

            //Antal aksler: 2
            feXpathSendkeys(driver, "//*[@id=\"akselAntal\"]", "2", "Antal aksler sendkeys");

            //Klik på næste
            feXpathClick(driver, "//*[@id=\"wizardNextBtn\"]", "Næste 3 click");
            Thread.sleep(1000);

            //Step 6: Klik på næste indtil fanen "Godkendelse" vises
            feXpathClick(driver, "//*[@id=\"wizardNextBtn\"]", "Næste 4 click");
            //Thread.sleep(2000);

            //Step 7: Klik på "Vis teknisk information"
            //Marker ved "Erklærer køretøj egnet til registrering"
            //Vælg forhandler, som skal erklære køretøjet cvr. 82050930
            //Klik på "Godkend oprettelse"
            feXpathClick(driver, "//*[@id=\"btnTekniskInfoShow\"]", "Vis teknisk information click");
            //Thread.sleep(1000);
            feXpathClick(driver, "//*[@id=\"importoer\"]", "Erklærer køretøj egnet click");
            //Thread.sleep(1000);
            feXpathClick(driver, "//*[@id=\"nyForhandler\"]", "Opret ny forhandler i listen click");
            //Thread.sleep(1000);

            feXpathSendkeys(driver, "//*[@id=\"forhandlerCVR\"]", "82050930",
                    "CVR sendkeys");
            feXpathClick(driver, "//*[@id=\"btnHentJEOpl\"]", "Hent forhandler click");
            Thread.sleep(1000);
            feXpathClick(driver, "//*[@id=\"createSubmit\"]", "Godkend click");
            //Thread.sleep(1000);

            String resultHeader = driver.findElement(By.tagName("h1")).getText();
            Assert.assertEquals(resultHeader, "Kvittering - Opret køretøj");

            feXpathClick(driver, "//*[@id=\"goToLogout\"]", "Log out click");


        }catch(Exception e){
            System.out.println(e);
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            throw new Exception();
        }

       // ScreenRecorderUtil.stopRecord();

        System.out.println("********************Finished 3356***********************");

    }

    public void feXpathClick(WebDriver driver, String destination, String errorMsg) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(destination)));
        try {
            driver.findElement(By.xpath(destination)).click();
            Thread.sleep(1000);
        } catch (Exception e) {
            throw new NoSuchElementException("Errror finding element to click: " + errorMsg);
        }
    }

    public void feXpathSendkeys(WebDriver driver, String destination, String input, String errorMsg) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(destination)));
        try {
            driver.findElement(By.xpath(destination)).clear();
            driver.findElement(By.xpath(destination)).sendKeys(input);
        } catch (Exception e) {
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
        Thread.sleep(1000);
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
            options.setPageLoadStrategy(PageLoadStrategy.NONE);
            driver = new ChromeDriver(options);
        }else{
        }

        driver.get("https://dmrsit1gateway1.skat.dk/dmr-front/dmr.portal");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //driver.manage().window().maximize();
       // driver.manage().window().setSize(new Dimension(640,360));

    }

    @AfterMethod
    void ProgramTermination() {
        driver.quit();
    }

    @DataProvider(name = "testdata")
    public Object[][] TestDataFeed() {

        ReadExcelFile config = new ReadExcelFile("testdata_dmr_1.xlsx");

        int rows = 1;//config.getRowCount(0);

        Object[][] inputs = new Object[rows][5];

        for (int i = 0; i < rows; i++) {
            inputs[i][0] = config.getData(0, i, 0);
            inputs[i][1] = config.getData(0, i, 1);
            inputs[i][2] = config.getData(0, i, 2);
            inputs[i][3] = config.getData(0, i, 3);
            inputs[i][4] = config.getData(0, i, 4);
        }

        return inputs;
    }

    public String numbergenerator(){
        Random rn = new Random();
        int number = rn.nextInt(99999 - 0 + 1) + 0;
        String result = Integer.toString(number);
        result = String.format("%05d", Integer.parseInt(result));
        return result;
    }

}
