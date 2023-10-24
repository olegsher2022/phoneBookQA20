package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);
    static String browser;
    EventFiringWebDriver driver;
    UserHelper userHelper;
    public ApplicationManager() {
        browser = System.getProperty("browser", BrowserType.CHROME);
    }

    public void init() {
       // driver = new EventFiringWebDriver(new ChromeDriver());

        if(browser.equals(BrowserType.CHROME)) {
            driver = new EventFiringWebDriver(new ChromeDriver());
            logger.info("created chrome browser");
        }else if (browser.equals(BrowserType.FIREFOX)) {
            driver = new EventFiringWebDriver(new FirefoxDriver());
            logger.info("started tests in firefox driver");
        }

        driver.navigate().to("https://telranedu.web.app/home");
        logger.info("open page: https://telranedu.web.app/home");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.register(new WDListener());
        userHelper = new UserHelper(driver);
    }

    public UserHelper getUserHelper() {
        return userHelper;
    }

    public void tearDown() {
        driver.quit();
    }

    public void navigateToMainPage() {
        driver.navigate().to("https://telranedu.web.app/home");
    }

    public boolean isPageUrlHome() {
        String url = driver.getCurrentUrl();
        System.out.println(url + "-------------------- url");
        return url.equals("https://telranedu.web.app/home");
    }
}
