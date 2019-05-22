package com.demo.selenium;

public class TestEnvironment {

	public TestEnvironment(String env) {
		this.envName=env;
	}
	private String envName;
	
	@Override
	public String toString() {
		return this.envName;
	}
}
