package utils;

import io.appium.java_client.remote.MobileCapabilityType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Arrays;

public final class MobileDesiredCapabilities {

    public static DesiredCapabilities getMobileDesireCap(String phoneName) {
        switch (DeviceType.getDeviceTypeByName(phoneName)) {
            case IPHONE_8: return getIPhone8();
            case PIXEL_XL_ASDK: return getPixelXLASDK();
            case GOOGLE_PIXEL: return getGooglePixel();
            case SAMSUNG_GALAXY_X_10: return getSamsungGalaxyS10();
            default: throw new IllegalArgumentException("unknown device name");
        }
    }

    private static DesiredCapabilities getIPhone8() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "13.2.2");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 8");
        capabilities.setCapability(MobileCapabilityType.UDID, "F6901BCA-0392-4701-9DDC-3AAAD1693B9E");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari");
        capabilities.setCapability(MobileCapabilityType.FULL_RESET, "true");

        capabilities.setCapability("showXcodeLog", "true");
        capabilities.setCapability("autoAcceptAlert", "true");
        return capabilities;
    }

    private static DesiredCapabilities getSamsungGalaxyS10() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Samsung_Galaxy_S10");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
        capabilities.setCapability(MobileCapabilityType.NO_RESET, "true");
        //IF start appium with "appium --chromedriver-executable drivers/mac/80/chromedriver"
        capabilities.setCapability("chromedriverExecutable", System.getProperty("user.dir") + "/drivers/mac/80/chromedriver");
        return capabilities;
    }

    private static DesiredCapabilities getPixelXLASDK() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel XL API 29"); //chrome 74
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
        capabilities.setCapability(MobileCapabilityType.NO_RESET, "true");
        capabilities.setCapability("chromedriverExecutable", System.getProperty("user.dir") + "/drivers/mac/74/chromedriver");
        return capabilities;
    }

    private static DesiredCapabilities getGooglePixel() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Google Pixel");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
        capabilities.setCapability(MobileCapabilityType.NO_RESET, "true");
        capabilities.setCapability("chromedriverExecutable", System.getProperty("user.dir") + "/drivers/mac/80/chromedriver");
        return capabilities;
    }

    @Getter
    @AllArgsConstructor
    public enum DeviceType {
        IPHONE_8("iPhone 8"),
        SAMSUNG_GALAXY_X_10("Samsung_Galaxy_S10"),
        PIXEL_XL_ASDK("Pixel XL ASDK"),
        GOOGLE_PIXEL("Google Pixel");

        private String name;

        public static DeviceType getDeviceTypeByName(String name) {
            return Arrays.stream(DeviceType.values()).filter(i-> i.getName().equalsIgnoreCase(name)).findFirst().get();
        }
    }
}
