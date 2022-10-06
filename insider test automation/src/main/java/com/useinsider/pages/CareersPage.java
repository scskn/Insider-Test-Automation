package com.useinsider.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CareersPage extends BasePage{
	
	@FindBy(id="career-find-our-calling")
	private WebElement teams;
	
	@FindBy(xpath="//*[@id=\"career-find-our-calling\"]/div/div/div[1]/h3")
	private WebElement teamsText;
	
	@FindBy(id="career-our-location")
	private WebElement locations;
	
	@FindBy(xpath="//*[@id=\"career-our-location\"]/div/div/div/div[1]/h3")
	private WebElement locationsText;
	
	@FindBy(xpath="(//*[@class=\"elementor-container elementor-column-gap-default\"])[6]")
	private WebElement lifeAtInsider;
	
	@FindBy(xpath="(//*[@class=\"elementor-heading-title elementor-size-default\"])[5]")
	private WebElement lifeAtInsiderText;
	
	@FindBy(xpath="//*[@class=\"btn btn-outline-secondary rounded text-medium mt-5 mx-auto py-3 loadmore\"]")
	private WebElement seeAllTeamsButton;

	@FindBy(xpath="(//*[@class=\"text-center mb-4 mb-xl-5\"])[12]")
	private WebElement qualityAssurance;
	
	@FindBy(xpath="//*[@class=\"btn btn-outline-secondary rounded text-medium mt-2 py-3 px-lg-5 w-100 w-md-50\"]")
	private WebElement seeAllQAJobsButton;
	
	@FindBy(id="filter-by-location")
	private WebElement filterByLocation;
	
	@FindBy(id="resultCounter")
	private WebElement resultCounter;
	
	@FindBy(xpath="(//*[contains(@class, 'btn btn-navy rounded pt-2')])[1]")
	private WebElement applyNowButton;
	
	@FindBy(xpath="(//*[contains(@class, 'position-location')])[1]")
	private WebElement position;
	
	JavascriptExecutor jse = (JavascriptExecutor)driver;
	
	public CareersPage(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public String scrollToTeams() throws InterruptedException
	{
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", teams);
		Thread.sleep(500); 	
		return teamsText.getText();
	}
	
	public String scrollToLocations() throws InterruptedException
	{
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", locations);
		Thread.sleep(500);
		return locationsText.getText();
	}
	
	public String scrollToLifeAtInsider() throws InterruptedException
	{
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", lifeAtInsider);
		Thread.sleep(500);
		return lifeAtInsiderText.getText();
	}
	
	public void seeAllTeams() throws InterruptedException
	{
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", seeAllTeamsButton);
		jse.executeScript("arguments[0].click();", seeAllTeamsButton);
		Thread.sleep(1000);
	}
	
	public void selectQA()
	{
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", qualityAssurance);
		jse.executeScript("arguments[0].click();", qualityAssurance);
	}
	
	public void seeAllQAJobs() throws InterruptedException
	{
		jse.executeScript("arguments[0].click();", seeAllQAJobsButton);
		Thread.sleep(1000);
	}
	
	public void filter() throws InterruptedException
	{
		Select drpLocation = new Select(filterByLocation);
		Thread.sleep(200);
		drpLocation.selectByVisibleText("Istanbul, Turkey");
		Thread.sleep(500);
	}
	
	public int checkPresenceOfJobs()
	{
		String result = resultCounter.getAttribute("innerText");
		
		return Integer.parseInt(result.substring(8,9));
	}
	
	public Boolean checkDepartmentAndLocation() throws InterruptedException
	{
		Thread.sleep(500);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", position);

		List<WebElement> listOfElements1 = driver.findElements(By.xpath("//*[contains(@class, 'position-department')]"));
		boolean departmentFlag = false;
		for(int i = 0; i < listOfElements1.size(); i++)
		{
			if(listOfElements1.get(i).getAttribute("innerText").contains("Quality Assurance"))
			{
				departmentFlag = true;
			}
			else
			{
				departmentFlag = false;
				break;
			}
		}
		
		List<WebElement> listOfElements2 = driver.findElements(By.xpath("//*[contains(@class, 'position-location')]"));
		boolean locationFlag = false;
		for(int i = 0; i < listOfElements2.size(); i++)
		{
			if(listOfElements2.get(i).getAttribute("innerText").contains("Istanbul, Turkey"))
			{
				locationFlag = true;
			}
			else
			{
				locationFlag = false;
				break;
			}
		}
		return departmentFlag & locationFlag;
	}
	
	public void applyNow()
	{
		jse.executeScript("arguments[0].click();", applyNowButton);
	}
	
	public Boolean checkRedirection()
	{
	    ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		return driver.getCurrentUrl().contains("lever");
	}
}