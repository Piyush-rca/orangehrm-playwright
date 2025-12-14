package com.qa.orangeHRM.factory;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Properties;
import java.util.Arrays;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;



public class PlaywrightFactory {
	

	
	private static ThreadLocal<Browser> tlBrowser =new ThreadLocal<>();
	private static ThreadLocal<BrowserContext> tlBrowserContext =new ThreadLocal<>();
	private static ThreadLocal<Page> tlPage =new ThreadLocal<>();
	private static ThreadLocal<Playwright> tlPlaywright =new ThreadLocal<>();
	
	
	public static Playwright getPlaywright() {
		return tlPlaywright.get();
	}
	
	public static Browser getBrowser() {
		return tlBrowser.get();
	}
	
	public static BrowserContext getBrowserContext() {
		return tlBrowserContext.get();
	}
	
	public static Page getPage() {
		return tlPage.get();
	}
	
	
	public Page initBrowser(Properties prop) {
		String browserName = prop.getProperty("browser").trim();
		System.out.println("Browser name is: " + browserName);
		//boolean headmode = Boolean.parseBoolean(prop.getProperty("headless"));
		
		
		
		tlPlaywright.set(Playwright.create());
		
		BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions()
                .setHeadless(true);
		
		switch (browserName.toLowerCase()) {
		case "chromium":
			tlBrowser.set(getPlaywright().chromium().launch(launchOptions));
			break;
		
		case "firefox":
			tlBrowser.set(getPlaywright().firefox().launch(launchOptions));
			break;
			
		case "safari":
			tlBrowser.set(getPlaywright().webkit().launch(launchOptions));
			break;
			
		case "chrome":
			tlBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(true).setArgs(Arrays.asList("--headless=new"))));
			break;
			
			
		default:
			System.out.println("please try with valid browser name");
			throw new RuntimeException("Invalid browser name: " + browserName);
					
		}
		

		
		tlBrowserContext.set(getBrowser().newContext(
				new Browser.NewContextOptions()
                .setIgnoreHTTPSErrors(true)));
		
		tlPage.set(getBrowserContext().newPage());
		getPage().navigate(prop.getProperty("url").trim());
		
		return getPage();
		
	}
		
	//	this method is used to initialise the properties from config file		
		
		public Properties init_prop() {
			Properties prop = new Properties();
			
				try {
					FileInputStream	ip = new FileInputStream("./src/test/resource/config/config.properties");
					prop = new Properties();
					prop.load(ip);
					
				
				} catch (IOException e) {
					throw new RuntimeException("Failed to load config.properties", e);
				}		
				
			return prop;
			
		}
		
		public static String takeScreenshot() {
			String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
			
			
			byte[] buffer = getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
			String base64Path = Base64.getEncoder().encodeToString(buffer);
			
			return base64Path;
		}
		
}


