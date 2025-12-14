# OrangeHRM Automation Framework (Playwright + Java)

**Project Overview**
This project is an end-to-end automation framework developed to automate key workflows of the **OrangeHRM** application using **Playwright with Java**.  
The framework is designed as per the given assignment requirements and follows industry-standard best practices such as **Page Object Model (POM)**, **TestNG**, and **CI execution using GitHub Actions**.

---

**Tech Stack**
- **Automation Tool**: Playwright
- **Programming Language**: Java
- **Test Framework**: TestNG
- **Build Tool**: Maven
- **Design Pattern**: Page Object Model (POM)
- **Reporting**: Extent Reports
- **CI/CD**: GitHub Actions (Cron-based execution)
- **Version Control**: GitHub

---

**Application Under Test**
- **URL**: https://opensource-demo.orangehrmlive.com/

---

## 📁 Framework Structure

project-root
│
├── pom.xml
├── testng.xml
│
├── src
│   ├── main
│   │   └── java
│   │       └── com.qa.orangeHRM
│   │           ├── constants
│   │           │   └── AppConstants.java
│   │           └── factory
│   │               └── PlaywrightFactory.java
│   │
│   └── test
│       ├── java
│       │   └── com.qa.orangeHRM
│       │       ├── base
│       │       │   └── BaseTest.java
│       │       ├── pages
│       │       │   ├── LoginPage.java
│       │       │   ├── DashboardPage.java
│       │       │   └── AdminPage.java
│       │       ├── tests
│       │       │   ├── LoginPageTest.java
│       │       │   └── AdminPageTest.java
│       │       └── listeners
│       │           └── ExtentReportListener.java
│       │
│       └── resources
│           ├── config
│           │   └── config.properties
│           └── testrunner
│               └── testng.xml
│
├── screenshot
├── test-output
└── build

**Framework Design Details**
-- **PlaywrightFactory**
   -- Centralized browser initialization
   -- Supports Chromium, Chrome, Firefox, WebKit
   -- Thread-safe execution using ThreadLocal
   -- Handles:
        Browser
        BrowserContext
        Page lifecycle
   -- Provides screenshot capture for reporting


-- **BaseTest**
   -- Common setup and teardown for all tests
   -- Initializes:
        Playwright
        Browser
        Page
        Page Objects
   --Ensures browser and Playwright instances are closed after execution

-- **Page Object Model (POM)**
   -- Each page has a dedicated class
   -- Page classes contain:
        Locators
        Page actions only
   -- No assertions inside page classes (best practice)
   -- Navigation methods return the next page object

-- **Test Classes**
   -- Contain all validations and assertions
   -- Cover both positive and negative scenarios
   -- Use TestNG annotations for execution control

--------------------------

 **Reporting**
   -- Extent Reports integrated using TestNG Listener
   -- Reports are generated:
       During local execution
       During GitHub Actions execution
   -- In CI runs, reports are uploaded as **GitHub Actions artifacts**

------------------

**Test Execution**
-- **Local Execution**
   -- Run via testng.xml from IDE (Eclipse)
   -- Or via Maven:
         mvn clean test

------------------

**CI/CD – GitHub Actions**
-- Tests are executed using GitHub Actions
-- Cron job configured to run every 15 minutes
-- Tests run in headless mode
-- Extent Reports are available as downloadable artifacts

-----------------

**Notes**
-- Utility classes are not added intentionally as current requirements do not involve reusable helpers or data providers.
-- The framework is designed to be extendable for:
-- Data-driven testing
-- Additional OrangeHRM modules
-- Cross-browser execution

