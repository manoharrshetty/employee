package com.emp.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;


@Aspect
@Configuration
public class AppAspectConfig {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
  
    
    
    @Around("execution(* com.emp..mapper..*.save*(..)) || execution(* com.emp..mapper..*.update*(..))  || execution(* com.emp..mapper..*.delete*(..)) }} execution(* com.emp..mapper..*.find*(..))")
    public Object aroundSave(ProceedingJoinPoint joinPoint) throws Throwable {
    	long startTime = 0L;
    	try {
    		 startTime = System.currentTimeMillis();
    		 return joinPoint.proceed();    		
    	}finally {
    		long timeTaken = System.currentTimeMillis() - startTime;
    		logger.info("Time Taken by {} is {} ms ", joinPoint, timeTaken);
    	}	
    }
    
    
    
    
}