package driverSetup;

import java.time.Duration;
import java.util.ResourceBundle;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {

	
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	private static String browser;
   
   public static ResourceBundle config = ResourceBundle.getBundle("config"); 
   
   
   public static void setBrowser(String browserName) {
	    browser = browserName;
	}

	public static String getBrowser() {
	    return browser != null ? browser : config.getString("browser");
	}
   
   
    public static void initializeDriver() {
      //  loadProperties(); // Ensure properties are loaded before using them
   //     String browserType = browser.get() != null ? browser.get() : config.getString("browser");
       if (getBrowser() == null) {
           throw new RuntimeException("Browser type is not specified in Config.properties or set via setBrowser()");
       }
      System.out.println("browserType data inside initializeDriver method is: "+getBrowser());
        
        boolean isHeadless = Boolean.parseBoolean(config.getString("headless"));
      
       // switch (browserType.toLowerCase()) {
        switch(getBrowser().toLowerCase()) {
            case "chrome":
               // WebDriverManager.chromedriver().setup();
            	
            	ChromeOptions options = new ChromeOptions();
            	if(isHeadless) {
            	options.addArguments("--headless=new");
            	}
                driver.set(new ChromeDriver(options)); 
          
                break;
            case "firefox":
            	FirefoxOptions options1 = new FirefoxOptions();
            	if(isHeadless) {
                	options1.addArguments("--headless");
                	}
                //WebDriverManager.firefoxdriver().setup();
                driver.set(new FirefoxDriver(options1));
                break;
            case "edge":
              //  WebDriverManager.edgedriver().setup();
            	EdgeOptions options2 = new EdgeOptions();
            	if(isHeadless) {
                	options2.addArguments("--headless=new");
                	}
                driver.set(new EdgeDriver(options2));
                break;
            default:
                throw new RuntimeException("Unsupported browser: " + getBrowser());
        }
        // Apply common settings
        WebDriver webDriver = driver.get();
        if (webDriver != null) {
            webDriver.manage().window().maximize();
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
    }
   
   
    public static WebDriver getDriver() {
        return driver.get();
    }
   
    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove(); //Prevents memory leaks in parallel runs
        }
    }
    
	
	
	
	
	
	
}
