package stepDefinition;

import java.util.ResourceBundle;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;

import driverSetup.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import testContext.TestContextSetup;
import utils.LoggerLoad;

public class Hooks {

	private TestContextSetup context;
	
	public static ResourceBundle config = ResourceBundle.getBundle("config");
	
    public Hooks(TestContextSetup context) {
        this.context = context;
    }
    
    @Before
    public void setUp(Scenario scenario) {
        LoggerLoad.info("Starting scenario: " + scenario.getName());
        
        String browser = getBrowserFromAnySources();
 //       String browser = System.getProperty("browser", config.getString("browser"));
        DriverFactory.setBrowser(browser); 
        
        DriverFactory.initializeDriver(); 
        context.launchUrl();
        
    }
    
    @After
    public void tearDown() {
        LoggerLoad.info("Closing WebDriver...");
        DriverFactory.quitDriver(); 
    }
    
    
    @AfterStep
    public void afterStep(Scenario scenario) {
        if (scenario.isFailed()) {
            LoggerLoad.error("Step Failed, Taking Screenshot");
            final byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Failure Screenshot");
        }
    }
    
    private String getBrowserFromAnySources() {
    	//From Command line
         String browser = System.getProperty("browser");
         
         //Next try from testng.xml
         if (browser == null || browser.trim().isEmpty()) {
             browser = getTestNGParameter("browser");
         }
        
        //Next try from config.properties
        if (browser == null || browser.trim().isEmpty()) {
               browser = config.getString("browser");
            }
        
        // Or else set with default value
        if (browser == null || browser.trim().isEmpty()) {
            browser = "chrome";
            LoggerLoad.info("Using default browser: chrome");
        }
        
        return browser.trim().toLowerCase();
    }
	
    
    private String getTestNGParameter(String paramName) {
        try {
           return Reporter.getCurrentTestResult()
                .getTestContext()
                .getCurrentXmlTest()
                .getParameter(paramName);
        } catch (Exception e) {
           LoggerLoad.debug("Could not read TestNG parameter: " + paramName);
            return null;
        }
    }
	
	
}
