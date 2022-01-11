package anhtester.com.projects.website.crm.testcases;

import anhtester.com.manager.BrowserFactory;
import anhtester.com.helpers.Helpers;
import anhtester.com.utils.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestRunMultiThread2 {

    WebDriver driver;
    WebUI webUi;

    @BeforeMethod
    @Parameters({"browser"}) //Run kiểu TestNG với xml file truyền biến
    public void Setup(String browser) {
        //BaseTest.createDriver(browser); //Cách khởi tạo thứ 1
        driver = new BrowserFactory().createDriver(browser); //Cách khởi tạo thứ 2
        webUi = new WebUI();
        System.out.println("Thread ID is: " + Thread.currentThread().getId());
    }

    @Test
    public void testHighLightElement() {
        driver.get("https://hrm.anhtester.com/");
        By button = By.xpath("//button[@type='submit']");
        webUi.highLightElement(button); //Tô màu viền đỏ cho Element trên website
        webUi.verifyElementAttributeValue(button, "type","submit", 10);
        webUi.waitForElementClickable(button, 5);
        WebUI.sleep(2);
    }

    @Test
    public void handleUploadFile() {
        driver.get("https://www.grammarly.com/plagiarism-checker");
        webUi.uploadFileSendkeys(By.xpath("//input[@name='source_file']"), Helpers.getCurrentDir() + "src/test/resources/DOCX_File_01.docx");
        WebUI.sleep(5);
    }

    @AfterMethod
    public void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

}
