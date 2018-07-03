package Assignment5.BingTranslator;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BingTranslator {

   WebDriver driver;
   
   @BeforeClass
   public void SetDriver(){
	   System.setProperty("web.driver.chrome", "/home/qainfotech/workspace/BingTranslator/chromedriver");
	   driver = new ChromeDriver();
	   driver.manage().window().maximize();
   }
   
   @AfterClass
   public void CloseDriver(){
	   driver.close();
   }
   
   @Test
   public void test1_LaunchURL(){
	   driver.get("https://www.bing.com/translator");
   }
   
   @Test
   public void test2_ClickOnText(){
	   driver.findElement(By.cssSelector(".t_navlink.t_navlinkactive")).click();
	   String Expected_URL = "https://www.bing.com/translator";
	   Assert.assertEquals(Expected_URL, driver.getCurrentUrl(), "Page must not have redirected");	   
   }
	
   @Test
   public void test3_ClickOnConversation(){
	   driver.findElement(By.cssSelector("a[title='Conversation']")).click();
	   String Expected_URL = "https://translator.microsoft.com/";
	   Assert.assertEquals(Expected_URL, driver.getCurrentUrl());
	   driver.get("https://www.bing.com/translator");
   }
   
   @Test
   public void test4_ClickOnApps(){
	   driver.findElement(By.cssSelector("a[title='Apps']")).click();
	   String Expected_URL = "https://translator.microsoft.com/apps/";
	   Assert.assertEquals(Expected_URL, driver.getCurrentUrl());
	   driver.get("https://www.bing.com/translator");	   
   }
   
   @Test
   public void test5_ClickOnFor_Buisness(){
	   driver.findElement(By.cssSelector("a[title='For business']")).click();
	   String Expected_URL = "https://www.microsoft.com/en-us/translator/home.aspx";
	   Assert.assertEquals(Expected_URL, driver.getCurrentUrl());
	   driver.get("https://www.bing.com/translator");
   }
   
   @Test
   public void test6_ClickOnHelp(){
	   driver.findElement(By.cssSelector("a[title='Help']")).click();
	   String Expected_URL = "https://translator.microsoft.com/help/bing/";
	   Assert.assertEquals(Expected_URL, driver.getCurrentUrl());
	   driver.get("https://www.bing.com/translator");
   }
   
   @Test
   public void test7_Dropdown() throws InterruptedException
	{
		Select drpDwn=new Select(driver.findElement(By.id("t_sl")));
		drpDwn.selectByVisibleText("Danish");
		Thread.sleep(5000);
		driver.findElement(By.id("t_sl")).click();       
	}
   
   @Test
	public void test8_TextBox() throws InterruptedException
	{
		driver.findElement(By.cssSelector("textarea#t_sv")).sendKeys("Kvalitet");
		Thread.sleep(5000);
	}
	@Test
	public void test9_SwapButton() throws InterruptedException
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;    //Creating the JavascriptExecutor interface object by Type casting
		String Text1=(String) js.executeScript("return document.getElementById('t_sv').value");  //return is used because we want to return a value
		String Text2=(String) js.executeScript("return document.getElementById('t_tv').value");  //return is used because we want to return a value
		Assert.assertEquals("Quality",Text2);
		System.out.println(Text1);
		System.out.println(Text2);
		driver.findElement(By.id("t_revIcon")).click();    //click on reverse button
		Thread.sleep(1000);
		System.out.println(Text1);
		System.out.println(Text2);
		if(Text1==Text2)
		{
			System.out.println("Reverse button not working");
		}
		
	}

   
}
