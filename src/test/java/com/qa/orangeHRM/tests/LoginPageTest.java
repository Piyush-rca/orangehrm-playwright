package com.qa.orangeHRM.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.orangeHRM.base.Basetest;
import com.qa.orangeHRM.constants.AppConstants;
import com.qa.orangeHRM.pages.DashboardPage;

public class LoginPageTest extends Basetest {

    @Test(priority = 1)
    public void loginPageTitleTest() {
        String actualTitle = loginPage.getLoginPageTitle();
        Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE);
    }

    @Test(priority = 2)
    public void validLoginTest() {
        DashboardPage dashboardPage =
                loginPage.doValidLogin(prop.getProperty("username"),prop.getProperty("password"));

        Assert.assertTrue(dashboardPage.isDashboardDisplayed(),"Dashboard is not displayed after valid login");
    }

    @Test(priority = 3)
    public void invalidLoginTest() {
    	 String errorMsg =
                 loginPage.doInvalidLogin("Admin", "wrongPass");

         Assert.assertEquals(errorMsg, AppConstants.INVALID_LOGIN_ERROR);
    }

    @Test(priority = 4)
    public void emptyCredentialsTest() {
    	Assert.assertTrue(loginPage.isRequiredFieldErrorDisplayed());
    }
}
