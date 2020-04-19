import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WebDriverManager;

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
        driver= WebDriverManager.getDriver(serverUrl);
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
}
