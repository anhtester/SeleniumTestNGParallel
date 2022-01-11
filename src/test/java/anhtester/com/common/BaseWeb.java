package anhtester.com.common;

import anhtester.com.helpers.Props;
import anhtester.com.listeners.TestListener;
import anhtester.com.driver.DriverManager;
import anhtester.com.driver.TargetFactory;
import anhtester.com.report.AllureManager;
import anhtester.com.utils.WebUI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;
import org.testng.annotations.*;

import static anhtester.com.config.ConfigurationManager.configuration;

@Listeners({TestListener.class})
public abstract class BaseWeb {

    public WebUI webUI = null;

    @BeforeSuite
    public void beforeSuite() {
        AllureManager.setAllureEnvironmentInformation();
        Props.loadAllFiles();
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void createDriver(@Optional("chrome") String browser) {
        WebDriver driver = ThreadGuard.protect(new TargetFactory().createInstance(browser));
        DriverManager.setDriver(driver);
        DriverManager.getDriver().get(configuration().url());
        webUI = new WebUI();
    }

    @AfterMethod(alwaysRun = true)
    public void closeDriver() {
        DriverManager.quit();
    }
}
