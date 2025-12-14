package com.qa.orangeHRM.base;

import java.util.Properties;

import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeMethod;


import com.microsoft.playwright.Page;
import com.qa.orangeHRM.factory.PlaywrightFactory;


import com.qa.orangeHRM.pages.LoginPage;

public class Basetest {
	
	protected PlaywrightFactory pf;
	protected Page page;
	protected Properties prop;
	
	protected LoginPage loginPage;
	
	
	
	@BeforeMethod
	public void setup() {
		//System.out.println("testbase clss enter");
		pf = new PlaywrightFactory();
		prop = pf.init_prop();
		page =pf.initBrowser(prop);
		
		loginPage = new LoginPage(page);
	}

	@AfterMethod
	public void tearDown() {
		if (PlaywrightFactory.getBrowser() != null) {
            PlaywrightFactory.getBrowser().close();
        }
        if (PlaywrightFactory.getPlaywright() != null) {
            PlaywrightFactory.getPlaywright().close();
        }
	}
}
