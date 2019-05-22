package com.demo.selenium;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class ScreenShotService {

	@Autowired
	public WebDriver driver;
	
	@Value("#{T(java.util.UUID).randomUUID()}")
	public String fileID;

	public void Capture() {
		try {

			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			// The below method will save the screen shot in d drive with name
			// "screenshot.png"
			FileUtils.copyFile(scrFile, new File(String.format("E:\\%s.png",fileID)));
		} catch (Exception ex) {

		}
	}
}
