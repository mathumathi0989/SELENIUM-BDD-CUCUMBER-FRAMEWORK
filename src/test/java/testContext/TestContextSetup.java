package testContext;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

import org.openqa.selenium.WebDriver;

import driverSetup.DriverFactory;

public class TestContextSetup {

	
	public static ResourceBundle config = ResourceBundle.getBundle("config");

    public WebDriver getDriver() {
        return DriverFactory.getDriver();
    }

    public void launchUrl() {
        getDriver().get(config.getString("url"));
    }

    public String getPropUsername() {
        return config.getString("Username");
    }

    public String getPropPassword() {
        return config.getString("Password");
    }
    
	
	
//	private WebDriver driver;
//  //  private LoginPage loginPage;
//
////    private Properties properties;
////    private DashboardPage dashboardPage;
//
//  //  private LogoutPage logoutPage;
//    private static TestContextSetup instance = null;
//    public static ResourceBundle config = ResourceBundle.getBundle("config"); 
//    public TestContextSetup() {
//	 // No need to call initializeDriver() here
//    this.driver = DriverFactory.getDriver(); // Get the WebDriver instance
//    
//    }
//
//public static TestContextSetup getInstance() {
//    if (instance == null) {
//        instance = new TestContextSetup();
//    }
//    return instance;
//}
//
//public void launchBrowser() {
//    if (this.driver == null) { // Prevent multiple initializations
//        String browser = config.getString("browser"); // Fetch browser from config.properties
//        DriverFactory.setBrowser(browser);
//        DriverFactory.initializeDriver();
//        this.driver = DriverFactory.getDriver();
//    }
//}
//public void launchUrl() {
//    driver.get(config.getString("url")); // Fetch URL from config.properties
//}
//
//public String getPropUsername() {
//    return config.getString("Username");
//}
//
//public String getPropPassword() {
//    return config.getString("Password");
//}
//
////public String getPropRole() {
////    return properties.getProperty("Role");
////}
////public LoginPage getLoginPage() {
////    return (loginPage == null) ? loginPage = new LoginPage(driver) : loginPage;
////}
////public DashboardPage getDashboardPage() {
////    return (dashboardPage == null) ? dashboardPage = new DashboardPage(driver) : dashboardPage;
////}
////public ProgramPage getProgramPage() {
////    return (programPage == null) ? programPage = new ProgramPage(driver) : programPage;
////
////}
////public LogoutPage getLogoutPage() {
////    return (logoutPage == null) ? logoutPage = new LogoutPage(driver) : logoutPage;
////}
////public void quitDriver() {
////	DriverFactory.quitDriver();
////}
////	
	
}
