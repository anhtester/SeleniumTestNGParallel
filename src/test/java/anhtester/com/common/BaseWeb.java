package anhtester.com.common;

import anhtester.com.helpers.Props;
import anhtester.com.listeners.TestListener;
import anhtester.com.driver.DriverManager;
import anhtester.com.driver.TargetFactory;
import anhtester.com.report.AllureManager;
import anhtester.com.utils.WebUI;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import static anhtester.com.config.ConfigurationManager.configuration;

@Listeners({TestListener.class})
public abstract class BaseWeb {

    @BeforeSuite
    public void beforeSuite() {
        AllureManager.setAllureEnvironmentInformation();
        Props.loadAllFiles();
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void preCondition(@Optional("chrome") String browser) {
        WebDriver driver = new TargetFactory().createInstance(browser);
        DriverManager.setDriver(driver);
        DriverManager.getDriver().get(configuration().url());
    }

    @AfterMethod(alwaysRun = true)
    public void postCondition() {
        DriverManager.quit();
    }
}
