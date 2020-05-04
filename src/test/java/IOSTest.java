import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.MobileDesiredCapabilities;
import utils.WebDriverManager;

public class IOSTest extends BaseTest{

    @Execution(ExecutionMode.CONCURRENT)
    @ParameterizedTest
    @ValueSource(strings = {"iPhone 8"})
    public void runAllTests(String deviceName) {
        DesiredCapabilities desireCap = MobileDesiredCapabilities.getMobileDesireCap(deviceName);
        WebDriver driver = WebDriverManager.initDriver(desireCap);
        testFBIncorrectLogin(driver);
        driver.quit();
    }
}
