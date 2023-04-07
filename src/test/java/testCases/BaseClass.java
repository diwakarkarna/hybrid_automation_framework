package testCases;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import utilities.BrowserManager;

public class BaseClass {
    public WebDriver driver;
    protected static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    @Parameters({"Browser","Remote"})
    @BeforeTest
    public void Setup(String browser, String isRemote) throws MalformedURLException{
        WebDriver driver = BrowserManager.createBrowserInstance(browser, isRemote);
        tlDriver.set(driver);
        System.out.println("Before test Thread ID: "+ Thread.currentThread().threadId());
        getDriver().manage().window().maximize();
    }

    public static WebDriver getDriver(){
        return tlDriver.get();
    }

    @AfterTest
    public void tearDown(){
        getDriver().quit();
        System.out.println("After Test Thread ID: "+Thread.currentThread().threadId());

        tlDriver.remove();
    }
}
