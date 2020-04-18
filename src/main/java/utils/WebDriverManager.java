package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.EnumUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class WebDriverManager {

    public static WebDriver getDriver(URL serverUrl) {
        switch (EnumUtils.getEnumIgnoreCase(WebDriverType.class, System.getProperty("driver"))) {
            case ANDROID:
                return getAndroidDriver(serverUrl);
            case IOS:
                return getIOSDriver(serverUrl);
            case WEB:
                return getWebDriver();
            default:
                    throw new RuntimeException("Incorrect WebDriver type");
        }
    }

    private static AppiumDriver getIOSDriver(URL serverUrl) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "13.2.2");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, " iPhone 8");
        capabilities.setCapability(MobileCapabilityType.UDID, "F6901BCA-0392-4701-9DDC-3AAAD1693B9E");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari");
        capabilities.setCapability(MobileCapabilityType.FULL_RESET, "true");

        capabilities.setCapability("showXcodeLog", "true");
        capabilities.setCapability("autoAcceptAlert", "true");

        return new IOSDriver(serverUrl, capabilities);
    }

    private static AppiumDriver getAndroidDriver(URL serverUrl) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");

        //IF start appium with "appium --chromedriver-executable drivers/mac/80/chromedriver"
//        capabilities.setCapability("chromedriverExecutable", System.getProperty("user.dir")+"/drivers/mac/80/chromedriver");

        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9"); //chrome 80
//        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel XL API 29"); //chrome 74
//        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Google Pixel");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Samsung_Galaxy_S10");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
        capabilities.setCapability(MobileCapabilityType.NO_RESET, "true");

        return new AndroidDriver(serverUrl, capabilities);
    }

    private static WebDriver getWebDriver() {
        System.setProperty("webdriver.chrome.driver", "drivers/mac/chromedriver");
        return new ChromeDriver();
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
