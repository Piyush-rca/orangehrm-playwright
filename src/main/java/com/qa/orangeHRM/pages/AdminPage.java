package com.qa.orangeHRM.pages;


import com.microsoft.playwright.Page;


public class AdminPage {

    private Page page;

    
    private String adminMenu = "//span[text()='Admin']";
    private String userManagementMenu = "//span[text()='User Management ']";
    private String usersMenu = "//a[text()='Users']";

    
    private String addButton = "//button[normalize-space()='Add']";
    private String userRoleDropdown = "//label[text()='User Role']/../following-sibling::div";
    private String employeeNameInput = "//input[@placeholder='Type for hints...']";
    
    private String usernameInput = "//label[text()='Username']/../following-sibling::div/input";
    private String statusDropdown = "//label[text()='Status']/../following-sibling::div";
    private String passwordInput = "//label[text()='Password']/../following-sibling::div/input";
    private String confirmPasswordInput = "//label[text()='Confirm Password']/../following-sibling::div/input";
    private String saveButton = "//button[normalize-space()='Save']";

    
    private String searchUsernameInput = "//label[text()='Username']/../following-sibling::div/input";
    private String searchButton = "//button[normalize-space()='Search']";

    // Constructor
    public AdminPage(Page page) {
        this.page = page;
    }

    
    public void navigateToUsersPage() {
        page.click(adminMenu);
        page.click(userManagementMenu);
        page.click(usersMenu);
        page.waitForSelector(addButton);
    }

    public void addUser(String employeeName, String username, String password)  {

        page.click(addButton);

        
        page.click(userRoleDropdown);
        page.keyboard().press("ArrowDown");
        page.keyboard().press("Enter");
     
        page.click(userRoleDropdown);
        page.keyboard().press("ArrowDown");
        page.keyboard().press("Enter");

        page.locator(employeeNameInput).fill("Ranga");
        
        page.waitForSelector("div[role='option']:has-text('Ranga')", 
            new Page.WaitForSelectorOptions().setTimeout(10000));
        
        page.locator("div[role='option']:has-text('Ranga')").first().click();
        
        System.out.println("username entered in add: " + username);
        page.fill(usernameInput, username);
        page.click(statusDropdown);
        page.keyboard().press("ArrowDown");
        page.keyboard().press("Enter");

        // Password
        page.fill(passwordInput, password);
        page.fill(confirmPasswordInput, password);

        page.click(saveButton);
        page.waitForSelector(searchButton);
    }


    public boolean searchUser(String username) {
    	
        System.out.println("username entered in search: " + username);
        page.fill(searchUsernameInput, username);
        page.click(searchButton);

        String userCell =
                "//div[contains(text(),'"+ username +"')]";
        System.out.println(userCell);
        page.waitForSelector(userCell);

        return page.isVisible(userCell);
    }
}
