package com.useinsider.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage{
	
	@FindBy(xpath="(//*[@id=\"mega-menu-1\"])[6]")
	private WebElement moreButton;
	
	@FindBy(xpath="(//*[@class=\"d-none d-lg-flex\"])[13]")
	private WebElement careersButton;

	JavascriptExecutor jse = (JavascriptExecutor)driver;

	public HomePage(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public void goToCareerPage() throws InterruptedException
	{
		jse.executeScript("arguments[0].click();", moreButton);
		jse.executeScript("arguments[0].click();", careersButton);
	}
}
