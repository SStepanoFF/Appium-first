package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.EnumUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static config.SystemVariables.APPIUM_DEFAULT_PORT;
import static config.SystemVariables.APPIUM_HOST;
import static config.SystemVariables.SELENIUM_GRID_HOST;
import static config.SystemVariables.SELENIUM_GRID_PORT;

@Slf4j
public final class WebDriverManager {

    private static final String HUB_PATH = "/wd/hub";

    public static WebDriver getDriver() {
        switch (EnumUtils.getEnumIgnoreCase(WebDriverType.class, System.getProperty("driver"))) {
            case ANDROID:
                return getAndroidDriver();
            case IOS:
                return getIOSDriver();
            case WEB:
                return getWebDriver();
            default:
                throw new RuntimeException("Incorrect WebDriver type");
        }
    }

    public static WebDriver initDriver(DesiredCapabilities capabilities){
        return new RemoteWebDriver(getSeleniumGridServerUrl(), capabilities);
    }

    private static RemoteWebDriver getIOSDriver() {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "13.2.2");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 8");
        capabilities.setCapability(MobileCapabilityType.UDID, "F6901BCA-0392-4701-9DDC-3AAAD1693B9E");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari");
        capabilities.setCapability(MobileCapabilityType.FULL_RESET, "true");

        capabilities.setCapability("showXcodeLog", "true");
        capabilities.setCapability("autoAcceptAlert", "true");

        return new RemoteWebDriver(getSeleniumGridServerUrl(), capabilities);
    }

    private static AppiumDriver getAndroidDriver() {
        log.info("Configuring Android driver....");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");

        //IF start appium with "appium --chromedriver-executable drivers/mac/80/chromedriver"
        capabilities.setCapability("chromedriverExecutable", System.getProperty("user.dir")+"/drivers/mac/74/chromedriver");

        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10"); //chrome 80
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel XL API 29"); //chrome 74
//        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Google Pixel");
//        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Samsung_Galaxy_S10");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
        capabilities.setCapability(MobileCapabilityType.NO_RESET, "true");

        return new AndroidDriver(getSeleniumGridServerUrl(), capabilities);
    }

    private static WebDriver getWebDriver() {
        System.setProperty("webdriver.chrome.driver", "drivers/mac/chromedriver");
        return new ChromeDriver();
    }

    private static URL getAppiumServerUrl() {
        URL url = null;
//        String port
        try {
            url = new URL(PropertiesManager.getProperty(APPIUM_HOST) + ":" + PropertiesManager.getProperty(APPIUM_DEFAULT_PORT) + HUB_PATH);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    private static URL getSeleniumGridServerUrl() {
        URL url = null;
//        String port
        try {
            url = new URL(PropertiesManager.getProperty(SELENIUM_GRID_HOST) + ":" + PropertiesManager.getProperty(SELENIUM_GRID_PORT) + HUB_PATH);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    @Getter
    @AllArgsConstructor
    public enum WebDriverType {
        ANDROID("android"),
        IOS("ios"),
        WEB("web");

        private String type;
    }
}
