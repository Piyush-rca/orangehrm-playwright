package com.qa.orangeHRM.pages;

import com.microsoft.playwright.Page;

public class DashboardPage {

    private Page page;

    // Locator 
    private String dashboardHeader = "//h6[text()='Dashboard']";

    // Constructor
    public DashboardPage(Page page) {
        this.page = page;
    }

    // actions
    public boolean isDashboardDisplayed() {
        return page.isVisible(dashboardHeader);
    }
}
