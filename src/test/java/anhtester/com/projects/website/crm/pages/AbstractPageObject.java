package anhtester.com.projects.website.crm.pages;

import anhtester.com.driver.DriverManager;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import static anhtester.com.config.ConfigManager.getConfig;
import static org.openqa.selenium.support.PageFactory.initElements;

public class AbstractPageObject {
    //Này dùng cho PageFactory. Hiện tại chưa dùng.
    protected AbstractPageObject() {
        initElements(new AjaxElementLocatorFactory(DriverManager.getDriver(), getConfig().timeout()), this);
    }
}
