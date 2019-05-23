package com.demo.selenium;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import com.demo.selenium.mapper.UserMapper;
import com.mysql.cj.jdbc.Driver;

@EnableAspectJAutoProxy
@Configuration
public class PageConfig {

	@Bean
	@Scope("prototype")
	public BaiduPage getBaiduPage() {
		return new BaiduPage();
	}
	
	@Autowired
	private Environment environment;
	@Autowired
	ApplicationContext ac;
	
	@Bean
	public TestEnvironment getTestEnvironment() {

		return new TestEnvironment(environment.getProperty("env","default"));
	}
	
	@Bean
	@Scope("singleton")
    @Lazy
	public WebDriver getWebDriver() {
		String codeBase=environment.getProperty("webDriver");
        
		System.setProperty("webdriver.chrome.driver",codeBase+ "/chromedriver.exe");
        WebDriver driver = new ChromeDriver(); 
        return driver;
	}
	
	@Bean
	public ScreenShotService getScreenShotService() {
		return new ScreenShotService();
	}

	@Bean
	public TestLogger getTestLogger(){
		return new TestLogger();
	}
	
	@Bean
	public MysqlDataService getMysqlDataService() {
		return new MysqlDataService();
	}

	@Bean("getSqlSessionFactory")
	public SqlSessionFactory getSqlSessionFactory() {

		SqlSessionFactory ssf=null;
		try {
			Driver driver= new Driver();
	    	SimpleDriverDataSource ds=new SimpleDriverDataSource();
	    	ds.setUrl(environment.getProperty("dblink"));
	    	ds.setDriver(driver);

			SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
			factoryBean.setDataSource((DataSource) ds);
			ssf = factoryBean.getObject();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ssf;
	}
}
