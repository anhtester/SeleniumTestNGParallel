package anhtester.com.projects.website.crm.testcases;

import anhtester.com.helpers.ExcelHelpers;
import anhtester.com.helpers.Helpers;
import anhtester.com.manager.BrowserFactory;
import anhtester.com.projects.website.crm.pages.Dashboard.DashboardPage;
import anhtester.com.projects.website.crm.pages.Projects.ProjectPage;
import anhtester.com.projects.website.crm.pages.SignIn.SignInPage;
import anhtester.com.helpers.DatabaseHelpers;
import anhtester.com.utils.WebUI;
import anhtester.com.utils.Log;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Set;

//@Listeners(TestListener.class)
public class TestHandle {

    WebDriver driver;
    WebUI webUi;
    DatabaseHelpers databaseHelpers;
    SignInPage signInPage;
    DashboardPage dashboardPage;
    ProjectPage projectPage;

    @BeforeMethod
    public void Setup() {
        //driver = BaseTest.createDriver("chrome"); //Cách khởi tạo thứ 1
        driver = new BrowserFactory().createDriver("chrome"); //Cách khởi tạo thứ 2
        webUi = new WebUI();
    }

    @Test
    public void handleNotificationsBrowser() {
        WebDriver driver = new ChromeDriver(webUi.notificationsBlock());
        driver.manage().window().maximize();
        driver.get("https://oto.com.vn/mua-ban-xe");
        WebUI.sleep(4);
        driver.close();
    }

    @Test
    public void handleDragAndDrop() {
        driver.get("http://demo.guru99.com/test/drag_drop.html");
        By fromElement1 = By.xpath("//a[normalize-space()='BANK']");
        By toElement1 = By.xpath("(//div[@id='shoppingCart1']//div)[1]");

        By fromElement2 = By.xpath("(//li[@id='fourth'])[2]");
        By toElement2 = By.xpath("(//div[@id='shoppingCart4']//div)[1]");

        //webUi.switchToFrameByElement(toElement);
        //webUi.scrollToElement(toElement);
        webUi.dragAndDrop(fromElement1, toElement1);
        WebUI.sleep(1);
        webUi.dragAndDropElement(fromElement2, toElement2);
        WebUI.sleep(2);
    }

    @Test
    public void handleDragAndDropOffset() {
        driver.get("http://demo.guru99.com/test/drag_drop.html");
        By fromElement1 = By.xpath("//a[normalize-space()='BANK']");
        By toElement1 = By.xpath("(//div[@id='shoppingCart1']//div)[1]");

        int X1 = webUi.findWebElement(fromElement1).getLocation().getX();
        int Y1 = webUi.findWebElement(fromElement1).getLocation().getY();
        webUi.logConsole(X1 + " , " + Y1);

        int X2 = webUi.findWebElement(toElement1).getLocation().getX();
        int Y2 = webUi.findWebElement(toElement1).getLocation().getY();
        webUi.logConsole(X2 + " , " + Y2);

        //webUi.switchToFrameByElement(toElement);
        //webUi.scrollToElement(toElement);
        webUi.dragAndDropOffset(fromElement1, -402, 246); //Nhớ là Tính từ vị trí click chuột đầu tiên
        WebUI.sleep(2);
    }

    @Test
    public void handleHighLightElement() {
        driver.get("https://hrm.anhtester.com/");
        By button = By.xpath("//button[@type='submit']");
        webUi.highLightElement(button); //Tô màu viền đỏ cho Element trên website
        webUi.verifyElementAttributeValue(button, "type", "submit", 10);
        webUi.waitForElementClickable(button, 5);
        WebUI.sleep(2);
    }

    @Test
    public void handleUploadFile() {
        driver.get("https://demoqa.com/upload-download");
        webUi.waitForPageLoaded();
        webUi.sleep(1);

        //Cách 1 sendKeys link từ source
        webUi.uploadFileSendkeys(By.xpath("//input[@id='uploadFile']"), Helpers.getCurrentDir() + "src/test/resources/DOCX_File_01.docx");

        //Cách 2 mở form local máy nên file là trong ổ đĩa máy tính
        webUi.uploadFileForm(By.xpath("//input[@id='uploadFile']"), "D:\\Document.csv");

        WebUI.sleep(3);
    }

    @Test
    public void handleTable1() {
        Log.info("handleTable1");
        driver.get("https://colorlib.com/polygon/notika/data-table.html");
        webUi.waitForPageLoaded();
        System.out.println(webUi.getValueTableByColumn(2));
    }

    @Test
    public void handleTable2() {
        signInPage = new SignInPage();
        driver.get("https://crm.anhtester.com/signin");
        dashboardPage = signInPage.signIn("tld01@mailinator.com", "123456");
        projectPage = dashboardPage.openProjectPage();
        String dataSearch1 = "Project";
        String dataSearch2 = "Test";
        // Search cột 2 Title
        projectPage.searchByValue(dataSearch1);
        projectPage.checkContainsSearchTableByColumn(2, dataSearch1);
        // Search cột 3 Client
        projectPage.searchByValue(dataSearch2);
        projectPage.checkContainsSearchTableByColumn(3, dataSearch2);
    }

    @Test
    public void handlePrintPopup() throws AWTException {
        driver.get("https://pos.anhtester.com/login");
        webUi.waitForPageLoaded();
        driver.findElement(By.xpath("//td[normalize-space()='user01@anhtester.com']")).click();
        driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();
        driver.findElement(By.xpath("//a[@role='button']")).click();
        //driver.findElement(By.xpath("//span[normalize-space()='Sale']")).click();
        webUi.waitForPageLoaded();
        driver.findElement(By.xpath("//a[normalize-space()='Manage Sale']")).click();
        driver.findElement(By.xpath("//span[normalize-space()='Print']")).click();

        webUi.sleep(1);

        Set<String> windowHandles = driver.getWindowHandles();
        if (!windowHandles.isEmpty()) {
            driver.switchTo().window((String) windowHandles.toArray()[windowHandles.size() - 1]);
        }

        //driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
        Robot robotClass = new Robot();
        robotClass.keyPress(KeyEvent.VK_TAB);
        webUi.sleep(1);
        robotClass.keyPress(KeyEvent.VK_ENTER);

        driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());
//        if (!windowHandles.isEmpty()) {
//            driver.switchTo().window((String) windowHandles.toArray()[windowHandles.size() - 1]);
//        }
        webUi.sleep(2);
    }

    @Test(dataProvider = "login")
    public void loginDataProviderExcelArray(String Username, String Password) {
        System.out.println(Username);
        System.out.println(Password);

        driver.get("http://demoqa.com/login");
        driver.findElement(By.id("userName")).sendKeys(Username);
        driver.findElement(By.id("password")).sendKeys(Password);
        driver.findElement(By.id("login")).click();
    }
    @DataProvider
    public Object[][] login() {

        Object[][] testObjArray = ExcelHelpers.getDataArray("src/test/resources/Magento.xlsx", "Login", 2, 3);

        return (testObjArray);
    }

    @AfterMethod
    public void closeDriver() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }

}
