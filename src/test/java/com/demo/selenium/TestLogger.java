package com.demo.selenium;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TestLogger {


    @Before("execution(* com.demo.selenium.BaiduPage.getElKeyword(..))")
    public void logStart(){
        System.out.println("--Start get ElKeyword--");
    }

    @After("execution(* com.demo.selenium.BaiduPage.getElKeyword(..))")
    public void logEnd(){
        System.out.println("--End get ElKeyword--");
    }
    
}
