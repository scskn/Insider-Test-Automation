package commonLibs.implementation;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class CommonDriver {

	private WebDriver driver;
	private int pageLoadTimeout;
	private int elementDetectionTimeout;
	private String currentWorkingDirectory;
	
	public CommonDriver(String browserType) throws Exception 
	{
		pageLoadTimeout = 10;
		elementDetectionTimeout = 10;
		
		currentWorkingDirectory = System.getProperty("user.dir");
		if(browserType.equalsIgnoreCase("chrome")) 
		{
			ChromeOptions ops = new ChromeOptions();
            ops.addArguments("--disable-notifications");
			System.setProperty("webdriver.chrome.driver", currentWorkingDirectory + "/drivers/chromedriver.exe");
			driver = new ChromeDriver(ops);
		}
		else if(browserType.equalsIgnoreCase("firefox")) 
		{
			FirefoxOptions ops = new FirefoxOptions();
			ops.addArguments("--disable-notifications");
			System.setProperty("webdriver.gecko.driver", currentWorkingDirectory + "/drivers/geckodriver.exe");
			driver = new FirefoxDriver(ops);
		}
		else {throw new Exception("Invalid Browser Type" +browserType);}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}
	
	public void navigateToUrl(String url)
	{
		driver.manage().timeouts().pageLoadTimeout(pageLoadTimeout, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(elementDetectionTimeout, TimeUnit.SECONDS);
		
		driver.get(url);
	}

	public WebDriver getDriver()
	{
		return driver;
	}

	public void setPageLoadTimeout(int pageLoadTimeout)
	{
		this.pageLoadTimeout = pageLoadTimeout;
	}

	public void setElementDetectionTimeout(int elementDetectionTimeout)
	{
		this.elementDetectionTimeout = elementDetectionTimeout;
	}
	
	public void closeAllBrowser()
	{
		driver.quit();
	}
	
	public String getTitleOfThePage()
	{
		return driver.getTitle();
	}
}