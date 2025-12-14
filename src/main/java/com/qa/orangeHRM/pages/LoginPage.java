package com.qa.orangeHRM.pages;

import com.microsoft.playwright.Page;


public class LoginPage {

	private Page page;
	

	private String email = "//input[@name=\"username\"]";
	private String passwrd = "//input[@name=\"password\"]";
	private String login = "//button[@type=\"submit\"]";
	private String dashboard = "//h6[text()='Dashboard']";
	
	private String requiredFieldError = "span.oxd-input-field-error-message";
	private String invalidCredentialError = "p.oxd-alert-content-text";

	
	//2. constructor of login class
	public LoginPage(Page page){
		this.page=page;
	}

	// Page actions
    public String getLoginPageTitle() {
        return page.title();
    }

    // Valid login → returns DashboardPage
    public DashboardPage doValidLogin(String username, String password) {
        page.fill(email, username);
        page.fill(passwrd, password);
        page.click(login);

        page.waitForSelector(dashboard);
        return new DashboardPage(page);
    }

    // Invalid login → returns error message text
    public String doInvalidLogin(String username, String password) {
    	page.fill(email, username);
        page.fill(passwrd, password);
        page.click(login);

        return page.textContent(invalidCredentialError);
    }

    // Empty credentials → returns true if "Required" error shown
    public boolean isRequiredFieldErrorDisplayed() {
        page.click(login);
		page.waitForSelector(requiredFieldError);
        return page.isVisible(requiredFieldError);
    }

    // Validation helpers
    public boolean isDashboardVisible() {
        return page.isVisible(dashboard);
    }
}
