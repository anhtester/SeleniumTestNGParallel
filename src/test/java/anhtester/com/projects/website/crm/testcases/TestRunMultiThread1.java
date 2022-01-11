package anhtester.com.projects.website.crm.testcases;

import anhtester.com.manager.BrowserFactory;
import anhtester.com.projects.website.crm.pages.Dashboard.DashboardPage;
import anhtester.com.projects.website.crm.pages.Projects.ProjectPage;
import anhtester.com.projects.website.crm.pages.SignIn.SignInPage;
import anhtester.com.helpers.DatabaseHelpers;
import anhtester.com.utils.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Set;

public class TestRunMultiThread1 {

    WebDriver driver;
    WebUI webUi;
    DatabaseHelpers databaseHelpers;
    SignInPage signInPage;
    DashboardPage dashboardPage;
    ProjectPage projectPage;

    @BeforeMethod
    @Parameters({"browser"}) //Run kiểu TestNG với xml file truyền biến
    public void Setup(String browser) {
        //BaseTest.createDriver(browser); //Cách khởi tạo thứ 1
        driver = new BrowserFactory().createDriver(browser); //Cách khởi tạo thứ 2
        webUi = new WebUI();
        System.out.println("Thread ID is: " + Thread.currentThread().getId());
    }

    @Test
    public void handleTable() {
        signInPage = new SignInPage();
        BrowserFactory.getDriver().get("https://crm.anhtester.com/signin");
        dashboardPage = signInPage.signIn("tld01@mailinator.com", "123456");
        projectPage = dashboardPage.openProjectPage();
        String dataSearch1 = "Project";
        String dataSearch2 = "COM Open Source";
        // Search cột 2 Title
        projectPage.searchByValue(dataSearch1);
        projectPage.checkContainsSearchTableByColumn(2, dataSearch1);
        webUi.sleep(2);
        // Search cột 3 Client
        projectPage.searchByValue(dataSearch2);
        projectPage.checkContainsSearchTableByColumn(3, dataSearch2);
    }

    @Test
    public void handlePrintPopup() throws AWTException {
        BrowserFactory.getDriver().get("https://pos.anhtester.com/login");
        webUi.waitForPageLoaded();
        BrowserFactory.getDriver().findElement(By.xpath("//td[normalize-space()='user01@anhtester.com']")).click();
        BrowserFactory.getDriver().findElement(By.xpath("//button[normalize-space()='Login']")).click();
        webUi.sleep(1);
        webUi.waitForPageLoaded();
        BrowserFactory.getDriver().findElement(By.xpath("//a[@role='button']")).click();
        webUi.sleep(1);
        BrowserFactory.getDriver().findElement(By.xpath("//a[normalize-space()='Manage Sale']")).click();
        BrowserFactory.getDriver().findElement(By.xpath("//span[normalize-space()='Print']")).click();

        webUi.sleep(1);
        webUi.waitForPageLoaded();
        Set<String> windowHandles = BrowserFactory.getDriver().getWindowHandles();
        if (!windowHandles.isEmpty()) {
            BrowserFactory.getDriver().switchTo().window((String) windowHandles.toArray()[windowHandles.size() - 1]);
        }

        //driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
        webUi.sleep(2);
        Robot robotClass = new Robot();
        robotClass.keyPress(KeyEvent.VK_TAB);
        webUi.sleep(1);
        robotClass.keyPress(KeyEvent.VK_ENTER);

        BrowserFactory.getDriver().switchTo().window(BrowserFactory.getDriver().getWindowHandles().toArray()[0].toString());
//        if (!windowHandles.isEmpty()) {
//            driver.switchTo().window((String) windowHandles.toArray()[windowHandles.size() - 1]);
//        }
        webUi.sleep(2);
    }

    @AfterMethod
    public void closeDriver() {
        if (BrowserFactory.getDriver() != null) {
            BrowserFactory.getDriver().quit();
        }
    }

}
