package stepdefination;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObject.AdminPage;
import utilities.ReadConfig;

public class StepDefination extends Base {

	@Before
	public void setup() throws Exception  {
		
		readConfig=new ReadConfig();
		
		System.out.println("Setup method executed");
		// driver = new ChromeDriver();
		 
		 String browser=readConfig.getBrowser();
		 Thread.sleep(2000);
		 
		 if(browser.equalsIgnoreCase("chrome")) {
			 driver=new ChromeDriver();
		 }else if(browser.equalsIgnoreCase("firefox")) {
			 driver=new FirefoxDriver();
		 }else if(browser.equalsIgnoreCase("IE")) {
			 driver=new InternetExplorerDriver();
		 }
	}
	
	@Given("User Lanch Chrome Browser")
	public void user_lanch_chrome_browser() {

		// driver = new ChromeDriver();

		ad = new AdminPage(driver);// create object of AdminPage JAva class
	}

	@When("User open url {string}")
	public void user_open_url(String url) throws Exception {

		driver.get(url);
		Thread.sleep(2000);

	}

	@When("User enter Email as {string} and password as {string}")
	public void user_enter_email_as_and_password_as(String email, String password) throws Exception {

		ad.setUserName(email);
		Thread.sleep(2000);

		ad.setPassword(password);
		Thread.sleep(2000);

	}

	@When("User click on Login button")
	public void user_click_on_login_button() throws Exception {
		ad.clickOnLogin();
		Thread.sleep(2000);

	}

	@Then("User verify page title should be {string}")
	public void user_verify_page_title_should_be(String title) throws Exception {

		Assert.assertEquals(title, driver.getTitle());
		Thread.sleep(2000);
	}

	@Then("close browser")
	public void close_browser() {
		driver.close();
	}
	
	@After
	public void tearDown(Scenario sc) {
		
		System.out.println("Tear down method executed");
		
		if(sc.isFailed()==true) {
			
			String fileWithPath="C:\\Users\\Prashant\\eclipse-workspace\\01May2023CucumberMavenProject\\screeshot\\failedScreenshot.png";
			//convert web driver object into TakesScreeshot
			TakesScreenshot scrshot=((TakesScreenshot)driver);
			//call getScreenshot method to create image file
			File scrFile=scrshot.getScreenshotAs(OutputType.FILE);
			
			File destFile=new File(fileWithPath);
			
			try {
				FileUtils.copyFile(scrFile, destFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		driver.quit();
	}

	

}
