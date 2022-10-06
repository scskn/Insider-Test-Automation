package com.useinsider.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.Status;
import com.useinsider.pages.CareersPage;
import com.useinsider.pages.HomePage;

import commonLibs.implementation.CommonDriver;
import commonLibs.utils.ConfigUtils;
import commonLibs.utils.ReportUtils;
import commonLibs.utils.ScreenshotUtils;

public class BaseTest {

	CommonDriver cmnDriver;
	String url;
	WebDriver driver;
	HomePage homePage;
	CareersPage careersPage;
	String currentWorkingDirectory;
	String configFileName;
	Properties configProperty;
	
	String reportFileName;
	ReportUtils reportUtils;
	
	ScreenshotUtils screenshot;
	
	@BeforeSuite
	public void preSetup() throws Exception
	{
		currentWorkingDirectory = System.getProperty("user.dir");
		configFileName = currentWorkingDirectory + "/config/config.properties";
		
		reportFileName = currentWorkingDirectory + "/reports/useinsiderTestReport.html";
		
		configProperty = ConfigUtils.readProperties(configFileName);
		
		reportUtils = new ReportUtils(reportFileName);
	}
	
	@BeforeClass
	public void setup() throws Exception
	{
		url = configProperty.getProperty("baseUrl");
		String browserType = configProperty.getProperty("browserType");
		cmnDriver = new CommonDriver(browserType);
		driver = cmnDriver.getDriver();
		homePage = new HomePage(driver);
		careersPage = new CareersPage(driver);
		
		screenshot = new ScreenshotUtils(driver);
		
		cmnDriver.navigateToUrl(url);
	}
	
	@AfterMethod
	public void postTestAction(ITestResult result) throws Exception
	{
		String testcasename = result.getName();
		long executionTime = System.currentTimeMillis();
		
		String screenshotFilename = currentWorkingDirectory + "/screenshots/" + testcasename + executionTime + ".jpeg";
		
		if(result.getStatus() == ITestResult.FAILURE)
		{
			reportUtils.addTestLog(Status.FAIL, "One or more steps failed");
			screenshot.captureAndSaveScreenshot(screenshotFilename);
			reportUtils.attachScreenshotToReport(screenshotFilename);
		}
	}
	
	@AfterClass
	public void tearDown() {cmnDriver.closeAllBrowser();}
	
	@AfterSuite
	public void postTearDown() {reportUtils.flushReport();}
}