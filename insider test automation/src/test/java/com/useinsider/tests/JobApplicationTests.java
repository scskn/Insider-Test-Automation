package com.useinsider.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

public class JobApplicationTests extends BaseTest{
	
	@Test
	public void verifyHomePageIsOpened() throws InterruptedException
	{		
		reportUtils.createATestCase("Check if Home Page is opened");
		reportUtils.addTestLog(Status.INFO, "Opening home page...");
		String actualTitle = cmnDriver.getTitleOfThePage();
		String expectedTitle = "Insider personalization engine for seamless customer experiences";
		reportUtils.addTestLog(Status.INFO, "Comparing expected and actual title");
		Assert.assertEquals(actualTitle, expectedTitle);

		homePage.goToCareerPage();
		
		reportUtils.createATestCase("Check if Teams block is opened");
		reportUtils.addTestLog(Status.INFO, "Scrolling to Teams block...");
		String result1 = careersPage.scrollToTeams();
		reportUtils.addTestLog(Status.INFO, "Comparing expected and actual text");
		Assert.assertEquals(result1, "Find your calling");
		
		reportUtils.createATestCase("Check if Locations block is opened");
		reportUtils.addTestLog(Status.INFO, "Scrolling to Locations block...");
		String result2 = careersPage.scrollToLocations();
		reportUtils.addTestLog(Status.INFO, "Comparing expected and actual text");
		Assert.assertEquals(result2, "Our Locations");
		
		reportUtils.createATestCase("Check if Life at Insider block is opened");
		reportUtils.addTestLog(Status.INFO, "Scrolling to Life at Insider block...");
		String result3 = careersPage.scrollToLifeAtInsider();
		reportUtils.addTestLog(Status.INFO, "Comparing expected and actual text");
		Assert.assertEquals(result3, "Life at Insider");
		
		careersPage.seeAllTeams();
		careersPage.selectQA();
		careersPage.seeAllQAJobs();
		careersPage.filter();

		reportUtils.createATestCase("Check presence of jobs");
		reportUtils.addTestLog(Status.INFO, "Scrolling to jobs...");
		int result4 = careersPage.checkPresenceOfJobs();
		reportUtils.addTestLog(Status.INFO, "Checking jobs");
		Assert.assertTrue(result4 > 0);

		reportUtils.createATestCase("Check department and location");
		reportUtils.addTestLog(Status.INFO, "Scrolling to department...");
		Boolean result5 = careersPage.checkDepartmentAndLocation();
		reportUtils.addTestLog(Status.INFO, "Checking department and location");
		Assert.assertTrue(result5);
		
		careersPage.applyNow();
		reportUtils.createATestCase("Check redirection");
		reportUtils.addTestLog(Status.INFO, "Redirecting...");
		Boolean result6 = careersPage.checkRedirection();
		reportUtils.addTestLog(Status.INFO, "Checking redirection");
		Assert.assertTrue(result6);
	}
}