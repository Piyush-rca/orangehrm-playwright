package com.qa.orangeHRM.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.orangeHRM.base.Basetest;
import com.qa.orangeHRM.pages.AdminPage;

public class AdminPageTest extends Basetest {

    private AdminPage adminPage;
    private String username;

    @BeforeMethod
    public void adminSetup() {
        // Login before each admin test
        loginPage.doValidLogin("Admin", "admin123");

        adminPage = new AdminPage(page);
        adminPage.navigateToUsersPage();        
    }

    @Test(priority = 1)
    public void addUserTest() {
    	username = "autoUser_" + System.currentTimeMillis();
        adminPage.addUser("ranga", username, "Admin@123");
        Assert.assertTrue(true, "User created successfully");
    }

    @Test(priority = 2)
    public void searchUserTest() {
        Assert.assertTrue(adminPage.searchUser(username),"Newly created user not found in search results");
    }
}
