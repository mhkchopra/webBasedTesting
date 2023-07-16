import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseClass {

    protected static WebDriver driver;

    public static void initializeWebDriver() {
        // Set the path to the chromedriver executable
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\jatin\\Downloads\\chromedriver_win32 (2)\\chromedriver.exe");

        // Create an instance of the WebDriver
        driver = new ChromeDriver();

        //To maximize the window
        driver.manage().window().maximize();

        //To delete cookies
        driver.manage().deleteAllCookies();
    }

    public void closeWebDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}