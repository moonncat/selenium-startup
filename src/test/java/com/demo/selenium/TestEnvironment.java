package com.demo.selenium;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class TestEnvironment {

	@Value("#{T(java.util.UUID).randomUUID()}")
	public String id;
	
	@Value("${env}")
	private String envName;
	
	@Override
	public String toString() {
		return this.envName;
	}
}
