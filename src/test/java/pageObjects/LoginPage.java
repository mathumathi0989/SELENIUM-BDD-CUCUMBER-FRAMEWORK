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
	
	@FindBy (xpath ="//div[contains(text(),'Logged in successfully')]")
	private WebElement successLoginMsg;
	
	@FindBy (xpath ="//div[contains(text(),'Login Failed')]")
	private WebElement failedLoginMsg;
	
	@FindBy (xpath ="//p[contains(text(),'Email is required')]")
	private WebElement emailReqErrorMsg;
	
	@FindBy (xpath ="//p[contains(text(),'Password is required')]")
	private WebElement pwdReqErrorMsg;
	
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
		username.clear();
		username.sendKeys(name);
		password.clear();
		password.sendKeys(pwd);	
		loginBtn.click();		
	}
	
	public boolean loginSuccessMsgDisplay() {
		webElementVisibleWait(successLoginMsg,10);
		return successLoginMsg.isDisplayed();
	}
	
	public boolean loginFailedMsgDisplay() {
		webElementVisibleWait(failedLoginMsg,10);
		return failedLoginMsg.isDisplayed();
	}
	
	public boolean emailReqErrMsgDisplay() {
		webElementVisibleWait(emailReqErrorMsg,10);
		return emailReqErrorMsg.isDisplayed();
	}
	
	public boolean pwdReqErrMsgDisplay() {
		webElementVisibleWait(pwdReqErrorMsg,10);
		return pwdReqErrorMsg.isDisplayed();
	}	
	
	
	public boolean dashboardPageDisplay() {
		return dashboardPage.isDisplayed();
	}
	
	
	
	
	

}
