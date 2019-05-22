package com.demo.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

public class BaiduPage {

	@Autowired
	private WebDriver driver;
	public WebElement el_Keyword;
	public WebElement el_Submit;
	
	public BaiduPage() {
	}

	public WebElement getElKeyword() {
        WebElement el_kw = this.driver.findElement(By.id("kw"));
        return el_kw;
	}

	public WebElement getElSubmit() {
        WebElement el_su = this.driver.findElement(By.id("su"));
        return el_su;
	}
}
