package utilities;

import java.net.MalformedURLException;
import java.net.URI;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserManager {
    private static WebDriver getFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }

    private static WebDriver getFirefoxDriver(String remoteURL) throws MalformedURLException {

        FirefoxOptions fop = new FirefoxOptions();

        return new RemoteWebDriver(URI.create(remoteURL).toURL(), fop);
    }

    private static WebDriver getChromeDriver() {
        WebDriverManager.chromedriver().setup();
        //Specific code for Chrome 111 issue with websocket failure
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        return new ChromeDriver(options);
    }

    private static WebDriver getChromeDriver(String remoteURL) throws MalformedURLException {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(CapabilityType.BROWSER_NAME, "chrome");
        caps.setCapability(ChromeOptions.CAPABILITY, options);
        return new RemoteWebDriver(URI.create(remoteURL).toURL(), caps );
    }

    public static WebDriver createBrowserInstance(String browserName, String isRemote) throws MalformedURLException {
        WebDriver driver = null;
        String remoteURL = "";
        if (isRemote.equalsIgnoreCase("true")) {
            if (browserName.equalsIgnoreCase("chrome")) {
                driver = getChromeDriver(remoteURL);
            } else if (browserName.equalsIgnoreCase("firefox")) {
                driver = getFirefoxDriver(remoteURL);
            }
        } else {
            if (browserName.equalsIgnoreCase("chrome")) {
                driver = getChromeDriver();
            } else if (browserName.equalsIgnoreCase("firefox")) {
                driver = getFirefoxDriver();
            }
        }
        return driver;
    }
}
