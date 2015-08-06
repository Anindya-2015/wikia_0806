package exercise;

import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class MethodRepository {

	public static String configValues = null;
	public static String appURL = null;
	Properties configProperties = new Properties();
	KeyWordLibFunctions kl = new KeyWordLibFunctions();
	static Logger logger = LogManager.getLogger(MethodRepository.class.getName());

	public static WebDriver wd = null;

	//To start the driver
	public void startDriver() {
		try {
			BasicConfigurator.configure();
			logger.info("Starting the Driver");
			InputStream pConfigFile = this.getClass().getResourceAsStream("/resources/Configuration.properties");
			configProperties.load(pConfigFile);
			configValues = configProperties.getProperty("BROWSERDRIVER");

			if (configValues.equalsIgnoreCase("FIREFOX")){
				wd = new FirefoxDriver();
			}
			else if (configValues.equalsIgnoreCase("CHROME")){
				System.setProperty("webdriver.chrome.driver", "chromedriver exe file path here");
				wd = new ChromeDriver();
			}
		} 
		catch(Exception e){
			BasicConfigurator.configure();
			logger.error("Exception occurred during startDriver(): " + e.getMessage());
		}
	}

	// To navigate to Wikia Home Page 
	public void navigateToHomePage()
	{
		try {
			appURL = configProperties.getProperty("BASEURL");
			wd.get(appURL);
			BasicConfigurator.configure();
			logger.info("Wikia homework page");
		}
		catch(Exception e){
			BasicConfigurator.configure();
			logger.error("Exception occurred during navigateToHomePage(): " + e.getMessage());
		}
	}

	// To verify home page address
	public void verifyAddress()
	{
		try {
			String currentURL = wd.getCurrentUrl();
			Assert.assertEquals(currentURL, configProperties.getProperty("URL2"));
			BasicConfigurator.configure();
			logger.info("Verified homepage url");
		}
		catch(Exception e){
			BasicConfigurator.configure();
			logger.error("Exception occurred during verifyAddress(): " + e.getMessage());
		}
	}

	// To hover mouse on Sign In
	public void mouseHoverOnSignIn()
	{
		try {
			kl.klWait();
			Actions actions = new Actions(wd);
			WebElement signInButton = kl.klIsElementPresent(ObjectRepository.SIGNINBUTTON);
			actions.moveToElement(signInButton).build().perform();
			BasicConfigurator.configure();
			logger.info("Mouse hover done");
		}
		catch(Exception e){
			BasicConfigurator.configure();
			logger.error("Exception occurred during mouseHoverOnSignIn(): " + e.getMessage());
		}
	}

	// To verify the login form populated
	public void verifyLoginForm()
	{
		try {
			kl.klWait();
			kl.klIsElementPresent(ObjectRepository.SIGNUPBUTTON);
			kl.klIsElementPresent(ObjectRepository.USERNAME);
			kl.klIsElementPresent(ObjectRepository.PASSWORD);
			kl.klIsElementPresent(ObjectRepository.FORGOTPASSWORDLINK);
			kl.klIsElementPresent(ObjectRepository.STAYSIGNEDINCHECKBOX);
			kl.klIsElementPresent(ObjectRepository.LOGINBUTTON);
			BasicConfigurator.configure();
			logger.info("Verified login form");
		}
		catch(Exception e){
			BasicConfigurator.configure();
			logger.error("Exception occurred during verifyLoginForm(): " + e.getMessage());
		}
	}

	// To login to Wikia web portal
	public void login()
	{
		try {
			kl.klWait();
			WebElement usernameField = kl.klIsElementPresent(ObjectRepository.USERNAME);
			usernameField.sendKeys(configProperties.getProperty("USERNAME"));

			WebElement passwordField =kl.klIsElementPresent(ObjectRepository.PASSWORD);
			passwordField.sendKeys(configProperties.getProperty("PASSWORD"));

			kl.klWebElementClick(ObjectRepository.LOGINBUTTON);
			BasicConfigurator.configure();
			logger.info("Logged in");
		}
		catch(Exception e){
			BasicConfigurator.configure();
			logger.error("Exception occurred during login(): " + e.getMessage());
		}
	}

	// To validate logged in user
	public void validateLogin()
	{
		try {
			kl.klIsElementPresent(ObjectRepository.SIGNEDINUSERNAME);
			BasicConfigurator.configure();
			logger.info("UserName validated");
		}
		catch(Exception e){
			BasicConfigurator.configure();
			logger.error("Exception occurred during validateLogin(): " + e.getMessage());
		}
	}

	// To click on contribute button
	public void clickOnContribute()
	{
		try {
			kl.klWait();
			kl.klWebElementClick(ObjectRepository.CONTRIBUTEBUTTON);
			BasicConfigurator.configure();
			logger.info("Clicked on contribute");
		}
		catch(Exception e){
			BasicConfigurator.configure();
			logger.error("Exception occurred during clickOnContribute(): " + e.getMessage());
		}
	}

	// To select add video option
	public void selectAddVideo()
	{
		try {
			kl.klWait();
			kl.klWebElementClick(ObjectRepository.ADDVIDEOOPTION);
			BasicConfigurator.configure();
			logger.info("Selected contribution method");
		}
		catch(Exception e){
			BasicConfigurator.configure();
			logger.error("Exception occurred during selectAddVideo(): " + e.getMessage());
		}
	}

	// To input video url and add
	public void addVideo()
	{
		try {
			kl.klWait();
			WebElement urlBox = kl.klIsElementPresent(ObjectRepository.URLBOX);
			urlBox.sendKeys(configProperties.getProperty("YOUTUBEURL"));
			kl.klWebElementClick(ObjectRepository.ADDURLBUTTON);
			logger.info("video url added");
		}
		catch(Exception e){
			logger.error("Exception occurred during addVideo(): " + e.getMessage());
		}
	}

	// To confirm video message after addition
	public void videoAdditionMessageConfirmation()
	{
		try {
			kl.klWait();
			String s = kl.klGetTextFromWebElement(ObjectRepository.VIDEOADDMESSAGE);
			kl.klWait();
			Assert.assertTrue(s.contains(configProperties.getProperty("VIDEOFILENAME")));
			BasicConfigurator.configure();
			logger.info("video url confirmed");
		}
		catch(Exception e){
			logger.error("Exception occurred during videoAdditionMessageConfirmation(): " + e.getMessage());
		}
	}

	// To click on file link in message and confirm
	public void clickOnFileLinkAndConfirmUrl()
	{
		try {
			kl.klWebElementClick(ObjectRepository.VIDEOLINK);
			kl.klWait();
			String currentURL = wd.getCurrentUrl();
			if(configValues.equalsIgnoreCase("FIREFOX"))
				Assert.assertEquals(currentURL, configProperties.getProperty("VIDEOURL"));
			else if(configValues.equalsIgnoreCase("CHROME"))
				Assert.assertTrue(currentURL.contains(configProperties.getProperty("BASEURL")));
			BasicConfigurator.configure();
			logger.info("Video link confirmed");
		}
		catch(Exception e){
			BasicConfigurator.configure();
			logger.error("Exception occurred during clickOnFileLinkAndConfirmUrl(): " + e.getMessage());
		}
	}

	// To confirm filename of video added
	public void confirmFilename()
	{
		try {
			kl.klWait();
			String currentURL = wd.getCurrentUrl();
			String modifiedVideoFileName = configProperties.getProperty("VIDEOFILENAME").replace(" ", "_");
			Assert.assertTrue(currentURL.contains(modifiedVideoFileName));
			BasicConfigurator.configure();
			logger.info("Video filename confirmed");
		}
		catch(Exception e){
			BasicConfigurator.configure();
			logger.error("Exception occurred during confirmFilename(): " + e.getMessage());
		}
	}

	// To close the driver
	public void closeDriver() throws Exception {
		logger.info("Closing the Driver");
		if (wd != null) {
			try {
				wd.close();
				wd.quit();
				wd = null;
			}
			catch (Exception e) {
				BasicConfigurator.configure();
				logger.error("Exception occurred during closeDriver(): " + e.getMessage());
			}
		}
	}

}