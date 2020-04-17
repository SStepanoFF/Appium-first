import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileBrowserType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class MobileWebTest {

    private static WebDriver driver;

    @BeforeAll
    public static void setUp() throws MalformedURLException {
        URL serverUrl = new URL("http://127.0.0.1:4723/wd/hub");

//        driver = getIOSDriver(serverUrl);
         driver = getAndroidDriver(serverUrl);
//        driver = getWebDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterAll
    public static void after(){
        driver.quit();
    }

    @Test
    public void testFBIncorrectLogin() {

        WebDriverWait wait = new WebDriverWait(driver, 30);
        driver.get("https://facebook.com");
        driver.findElement(By.cssSelector("[name='email']")).sendKeys("wrong email");
        driver.findElement(By.cssSelector("[name='pass']")).sendKeys("wrong pass");
        driver.findElement(By.cssSelector("[name='login']")).click();

        new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[data-sigil='m_login_notice']")));

        assertThat("Wrong error message", driver.findElement(By.cssSelector("div[data-sigil='m_login_notice']")).getText(),
                equalTo("Incorrect password. Did you forget your password?"));

    }

    private AppiumDriver getIOSDriver(URL serverUrl) {
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
}
