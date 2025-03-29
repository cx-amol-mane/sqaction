
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SonarQubeLoginTest {

    private WebDriver driver;
    private static final String BASE_URL = "http://localhost:9000/";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin123";

    @BeforeEach
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox"); 
        options.addArguments("--disable-dev-shm-usage"); 
        options.addArguments("--remote-allow-origins=*"); 
        options.addArguments("--headless=new"); // Use new headless mode
        options.addArguments("--disable-gpu"); // Disable GPU acceleration
        options.addArguments("--disable-popup-blocking"); // Prevent pop-ups
        options.addArguments("--user-data-dir=/tmp/chrome-user-data-" + System.nanoTime()); // Unique user data dir
    
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testSuccessfulLogin() throws InterruptedException {
        driver.get(BASE_URL);
        WebElement username = driver.findElement(By.id("login"));
        WebElement password = driver.findElement(By.id("password"));

        username.sendKeys(USERNAME);
        password.sendKeys(PASSWORD);

        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
        loginButton.click();
        Thread.sleep(5000);

        Assertions.assertTrue(driver.getCurrentUrl().contains("projects"));
    }

    @Test
    public void testInvalidCredentials() {
        driver.get(BASE_URL);
        WebElement username = driver.findElement(By.id("login"));
        WebElement password = driver.findElement(By.id("password"));

        username.sendKeys("wronguser");
        password.sendKeys("wrongpass");

        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
        loginButton.click();

        Assertions.assertFalse(driver.getCurrentUrl().contains("projects"));
    }
}