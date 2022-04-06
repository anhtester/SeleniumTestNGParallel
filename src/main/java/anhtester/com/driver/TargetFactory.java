package anhtester.com.driver;

import anhtester.com.enums.Target;
import anhtester.com.exceptions.TargetNotValidException;
import anhtester.com.utils.Log;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

import static anhtester.com.config.ConfigManager.getConfig;

public class TargetFactory {

    public WebDriver createInstance(String browser) {
        Target target = Target.valueOf(getConfig().target().toUpperCase());
        WebDriver webdriver;

        switch (target) {
            case LOCAL:
                webdriver = BrowserFactory.valueOf(browser.toUpperCase()).createDriver();
                break;
            case REMOTE:
                webdriver = createRemoteInstance(BrowserFactory.valueOf(browser.toUpperCase()).getOptions());
                break;
            default:
                throw new TargetNotValidException(target.toString());
        }
        return webdriver;
    }

    private RemoteWebDriver createRemoteInstance(MutableCapabilities capability) {
        RemoteWebDriver remoteWebDriver = null;
        try {
            String gridURL = String.format("http://%s:%s", getConfig().gridUrl(), getConfig().gridPort());

            remoteWebDriver = new RemoteWebDriver(new URL(gridURL), capability);
        } catch (java.net.MalformedURLException e) {
            Log.error("Grid URL is invalid or Grid Port is not available");
            Log.error(String.format("Browser: %s", capability.getBrowserName()), e);
        } catch (IllegalArgumentException e) {
            Log.error(String.format("Browser %s is not valid or recognized", capability.getBrowserName()), e);
        }

        return remoteWebDriver;
    }

}
