import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WebDriverManager;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

@Slf4j
public class MobileWebTest {

    private static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        log.info("Before test....");
        driver = WebDriverManager.getDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterAll
    public static void after() {
        driver.quit();
    }

    @Test
    public void testFBIncorrectLogin() {

        driver.get("https://facebook.com");
        driver.findElement(By.cssSelector("[name='email']")).sendKeys("wrong email");
        driver.findElement(By.cssSelector("[name='pass']")).sendKeys("wrong pass");
        driver.findElement(By.cssSelector("[name='login']")).click();

        new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[data-sigil='m_login_notice']")));

        assertThat("Wrong error message", driver.findElement(By.cssSelector("div[data-sigil='m_login_notice']")).getText(),
                equalTo("Incorrect password. Did you forget your password?"));

    }
}
