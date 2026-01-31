package stepDefinition;

import java.util.ResourceBundle;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

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
        
        String browser = System.getProperty("browser", config.getString("browser"));
        DriverFactory.setBrowser(browser); // to tun mvn test -Dbrowser=firefox
        
        DriverFactory.initializeDriver(); // Initialize driver in a thread-safe manner
    }
    
    @After
    public void tearDown() {
        LoggerLoad.info("Closing WebDriver...");
        DriverFactory.quitDriver(); // Ensure driver is quit properly for parallel tests
    }
    
    
    @AfterStep
    public void afterStep(Scenario scenario) {
        if (scenario.isFailed()) {
            LoggerLoad.error("Step Failed, Taking Screenshot");
            final byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Failure Screenshot");
        }
    }
	
	
	
}
