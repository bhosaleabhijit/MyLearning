package partner.pageObjects.selfMe.orgWeb;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import partner.helper.browserConfig.config.ObjectReader;
import partner.helper.logger.LoggerHelper;
import partner.helper.wait.WaitHelper;
import partner.testBase.*;

public class SignUp {
	private WebDriver driver = null;
	private Logger log = LoggerHelper.getLogger(SignUp.class);
	WaitHelper waitHelper;
	Object[][] signUpData = null;
	

	@FindBy(xpath = "//*[@id='OrgName']")
	WebElement orgName;

	@FindBy(xpath = "//*[@id='FirstName']")
	WebElement firstName;

	@FindBy(xpath = "//*[@id='LastName']")
	WebElement lastName;

	@FindBy(xpath = "//*[@id='EmailAddress']")
	WebElement emailAddress;

	@FindBy(xpath = "//*[@id='PhoneNumber']")
	WebElement phoneNumber;

	@FindBy(xpath = "//*[@id='Password']")
	WebElement password;

	@FindBy(xpath = "//*[@id='ConfirmPassword']")
	WebElement confirmPassword;

	@FindBy(xpath = "//*[@id='TermsAndConditions']")
	WebElement termsAndConditionsCheckbox;

	@FindBy(xpath = "//*[@id='TermsAndConditionsLink']")
	WebElement termsAndConditionsLink;

	@FindBy(xpath = "//*[@id='CreateAccount']")
	WebElement createAccount;

	@FindBy(xpath = "//*[@id='LoginHereLink']")
	WebElement loginHereLink;

	@FindBy(xpath = "//*[@id='logo']")
	WebElement logo;

	@FindBy(xpath = "//*[@id='SignUpPageImage']")
	WebElement signUpPageImage;

	@FindBy(xpath = "//*[@id='showClearTextPassword']")
	WebElement showClearTextPasswordButton;

	@FindBy(xpath = "//*[@id='showClearTextConfirmPassword']")
	WebElement showClearTextConfirmPasswordButton;

	public SignUp(WebDriver driver) {
		log.info("Initializing Sign up page.");
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(createAccount, ObjectReader.reader.getExplicitWait());
		log.info("Sign up page initialized successfully");
		(new TestBase()).getNavigationScreen(driver);
	}
	
	public String getPageTitle()
	{
		String title = driver.getTitle();
		log.info("Page Title is: " + title);
		return title;
	}
	
	public String getPageURL()
	{
		String url =driver.getCurrentUrl();
		log.info("Page URL is: " + url);
		return url;
	}
	
	public void addDataInTextField(WebElement textFieldElement, String data) {
		log.info("Adding " + data + " in the field: " + textFieldElement.toString());
		textFieldElement.clear();
		textFieldElement.sendKeys(data);
	}

	public void checkTermsAndConditionsCheckbox() {
		if (!termsAndConditionsCheckbox.isSelected())
			termsAndConditionsCheckbox.click();
		log.info("Terms And Conditions Checkbox is checked.");
	}

	public void uncheckTermsAndConditionsCheckbox() {
		if (termsAndConditionsCheckbox.isSelected()) {
			termsAndConditionsCheckbox.click();
		}
		log.info("Terms And Conditions Checkbox is unchecked.");
	}

	public void clickTermsAndConditionsLink() {
		termsAndConditionsLink.click();
		log.info("Terms And Conditions link is clicked.");
	}

	public void clickLoginHereLink() {
		loginHereLink.click();
		log.info("Login here link clicked.");
	}

	public void clearAllFormData() {
		orgName.clear();
		firstName.clear();
		lastName.clear();
		emailAddress.clear();
		phoneNumber.clear();
		password.clear();
		confirmPassword.clear();
		uncheckTermsAndConditionsCheckbox();
		log.info("All form fields are cleared");
	}

	public boolean checkShowPasswordButtonWorks() {
		String passwordData = "TestPassword@6839!11LH%";
		addDataInTextField(password, passwordData);
		verifyPasswordFieldShowsDataEnctypted();
		showClearTextPasswordButton.click();
		String clearTextPassword = password.getText();
		if (passwordData.equals(clearTextPassword)) {
			log.info("Password field shows data in clear text on eye button click.");
			return true;
		} else {
			log.info("Password field doesn't shows data in clear text on eye button click.");
			return false;
		}

	}
	
	public boolean checkShowConfirmPasswordButtonWorks() {
		String passwordData = "TestPassword@6839!11LH%";
		addDataInTextField(confirmPassword, passwordData);
		verifyConfirmPasswordFieldShowsDataEnctypted();
		showClearTextConfirmPasswordButton.click();
		String clearTextPassword = confirmPassword.getText();
		if (passwordData.equals(clearTextPassword)) {
			log.info("Password field shows data in clear text on eye button click.");
			return true;
		} else {
			log.info("Confirm Password field doesn't shows data in clear text on eye button click.");
			return false;
		}

	}

	private boolean verifyConfirmPasswordFieldShowsDataEnctypted() {
		String passwordData = "TestPassword@6839!11LH%";
		addDataInTextField(confirmPassword, passwordData);
		String confirmPasswordFieldTest = confirmPassword.getText();
		if (passwordData.equals(confirmPasswordFieldTest)) {
			log.info("Confirm Password field doesn't show data enctypted");
			return true;
		} else {
			log.info("Confirm Password field doesn't show data enctypted");
			return false;
		}
		
	}

	private boolean verifyPasswordFieldShowsDataEnctypted() {
		String passwordData = "TestPassword@6839!11LH%";
		addDataInTextField(password, passwordData);
		String passwordFieldTest = confirmPassword.getText();
		if (passwordData.equals(passwordFieldTest)) {
			log.info("Password field shows data encrypted.");
			return true;
		} else {
			log.info("Password field doesn't show data enctypted");
			return false;
		}

	}

	public String getTextFromField(WebElement element)
	{
		String textData = element.getText();
		log.info("Text in the: " + element.toString() + " field is: " + textData );
		return textData;
	}

	public String getOrganizationFieldText() {
		return getTextFromField(orgName);
	}

	public String getFirstNameFieldText() {
		return getTextFromField(firstName);
	}

	public String getLastNameFieldText() {
		return getTextFromField(lastName);
	}

	public String getEmailAddressFieldText() {
		return getTextFromField(emailAddress);
	}

	public String getPhoneNumberFieldText() {
		return getTextFromField(phoneNumber);
	}

	public String getPasswordFieldText() {
		return getTextFromField(password);
	}

	public String getConfirmPasswordFieldText() {
		return getTextFromField(confirmPassword);
	}

}
