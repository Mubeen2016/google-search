package com.mubeen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ScreenCastTest {
	
	private static WebDriver driver;
	
	@Before
	public void setup(){
		System.out.println("Starting browser..");
		driver = new FirefoxDriver();
		
		int timeOut = 5;
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS); //have to wait regarless of condition
		System.out.println("Setting implicit timeout to = " + timeOut);
		
		driver.manage().window().maximize();
		
	}
	
	@After
	public void tearDown(){
		System.out.println("Closing browser");
		driver.quit();
	}
	
	@Test
	public void shouldVerifyLogin() throws IOException{
		
		ArrayList kwList = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader("/Users/nmohammed/Workspace/selenium-projects/google-search/src/test/resources/screencast-users.csv"));
		String line;
		while ((line = br.readLine()) != null) {
		     if (line.contains("email, password")) {
		    	 //if line is the first line then execute next iteration
		    	 continue;
		     }
		     
		     String[] data = line.split(",");
		     //System.out.println(data[0].trim());
		     //System.out.println(data[1].trim());
		     driver.get("https://screencast.com");
		     
		     driver.findElement(By.cssSelector("input[name='emailAddress'][id='emailAddress']")).clear();
		     driver.findElement(By.cssSelector("input[name='emailAddress'][id='emailAddress']")).sendKeys(data[0]);
		     
		     driver.findElement(By.cssSelector("input[name='password'][id='password']")).clear();
		     driver.findElement(By.cssSelector("input[name='password'][id='password']")).sendKeys(data[1]);
		     
		     driver.findElement(By.cssSelector("a[id='signInButton']")).click();
		     
		     //this checks if login page is present
		     try{
		    	 driver.findElement(By.cssSelector("div[id='signinTextss']"));
		     }catch(Exception e){
		    	 System.out.println("Very bod. Element not present");
		     }
		     
		}
	}

}
