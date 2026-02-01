package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import lombok.Data;

@Data
public class LoginPage extends BasePage {
	
	
	public LoginPage(WebDriver driver) {
		super(driver);
		
	}
	
	
	//-------------------------------- WebElements --------------------------------------------
	
	@FindBy (name = "username")
	private WebElement username;
	
	@FindBy (name = "password")
	private WebElement password;
	
	@FindBy (xpath ="//button[contains(text(),'LogIn')]")
	private WebElement loginBtn;
	
	@FindBy (xpath ="//h1[contains(text(),'Free Plan Dashboard')]")
	private WebElement dashboardPage;
	
	
	
	
	//----------------------------------Methods---------------------------------------------------
	
	public void enterUsernameAndPwd(String name, String pwd) {
		username.sendKeys(name);
		password.sendKeys(pwd);	
	}
	
	public void clickLogin() {
		loginBtn.click();
	}
	
	public void clickLogInWithDetails(String name, String pwd) {
		username.sendKeys(name);
		password.sendKeys(pwd);	
		loginBtn.click();		
	}
	
	public boolean dashboardPageDisplay() {
		return dashboardPage.isDisplayed();
	}
	
	
	
	
	

}
