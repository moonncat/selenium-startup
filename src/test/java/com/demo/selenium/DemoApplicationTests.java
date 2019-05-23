package com.demo.selenium;

import java.io.File;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.demo.selenium.models.User;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private WebDriver driver;
	@Autowired
	private BaiduPage bp;
	@Autowired
	private ScreenShotService sss;
	@Autowired
	private TestEnvironment te;
	@Autowired
	private Environment environment;
	@Autowired
	private MysqlDataService mds;

	@Test
	public void contextLoads() {
        String url = "http://www.baidu.com";
        this.driver.get(url); 
        
        try {
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            ExtentHtmlReporter hr = new ExtentHtmlReporter(new File(String.format("E:\\%s.html", UUID.randomUUID())));
            ExtentReports er=new ExtentReports(); 
            er.attachReporter(hr);
            ExtentTest et=er.createTest("test1");
            et.info("env: "+te.toString());

            String kw=environment.getProperty("keyword");
			this.bp.getElKeyword().sendKeys(kw);
			this.bp.getElSubmit().click();
			
			// Google's search is rendered dynamically with JavaScript.
	        // Wait for the page to load, timeout after 10 seconds
	        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
	            public Boolean apply(WebDriver d) {
	                return d.getTitle().toLowerCase().startsWith(kw);
	            }
	        });
			
			sss.Capture();
            
			et.pass("automation test");
			er.flush();
			
		
            //driver.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	@Test
	public void printPath() {
		System.out.println(this.environment.getProperty("test"));
	}
	@Test
	public void getUser() {
		try {
			User u=mds.getUser();
			System.out.println(u.name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Test
	public void getUserByID() {
		try {
			User u=mds.getByID(5);
			System.out.println(u.id+u.name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Test
	public void getAll() {
		try {
			List<User> users=mds.getAll();
			for(User u:users) {
				System.out.println(u.id+u.name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Test
	public void createUser() {
		try {
			User u=new User();
			u.name="name1"+UUID.randomUUID();
			u.pwd="pwd1";
			u.createdUser="user1";
			System.out.println(u.id+u.name);
			//System.out.println(mds.create(u));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
