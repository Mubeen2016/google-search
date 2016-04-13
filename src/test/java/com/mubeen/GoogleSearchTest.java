package com.mubeen;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class GoogleSearchTest {
	
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
	
	@Ignore("to be added later")
	@Test
	public void shouldSearchKeywordsOnGoogle(){
		System.out.println("Navigating to google.com");
		driver.get("https://google.com");
		
		System.out.println("Verifying google.com page");
		try{
			driver.findElement(By.cssSelector("input[name='btnK'][type='submit']"));
		}catch( Exception e){
			System.out.println("Google page may not be loading....");
		}
		
		
		driver.findElement(By.cssSelector("input[name='q']")).sendKeys("apartments");
		driver.findElement(By.cssSelector("button[name='btnG']")).click();
		
		String results = driver.findElement(By.cssSelector("div[id='resultStats']")).getText();
		
		System.out.println(results);
	}
	
	
	
	@Test
	public void shouldSearchKeywordsFromFile() throws FileNotFoundException, IOException, InterruptedException{
		System.out.println("Navigating to google.com");
		driver.get("https://google.com");
		
		System.out.println("Verifying google.com page");
		try{
			driver.findElement(By.cssSelector("input[name='btnK'][type='submit']"));
		}catch( Exception e){
			System.out.println("Google page may not be loading....");
		}
		
		//step1: read all the keywords from file and make arraylist
		//step2: navigate through the arraylist and perform google search for each item in the list
		ArrayList kwList = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new FileReader("/Users/nmohammed/Workspace/selenium-projects/google-search/src/test/resources/keywords.txt"))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       // process the line.
		       kwList.add(line);
		    }
		}
		
		for(Object k : kwList){
			driver.findElement(By.cssSelector("input[name='q']")).clear();
			driver.findElement(By.cssSelector("input[name='q']")).sendKeys(k.toString());
			driver.findElement(By.cssSelector("button[name='btnG']")).click();
			
			String results = driver.findElement(By.cssSelector("div[id='resultStats']")).getText();
			
			System.out.println(results);
			Thread.sleep(1000);
		}
		
		
	}
	
	
	
	
	
	

}
