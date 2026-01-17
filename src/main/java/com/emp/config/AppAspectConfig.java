package com.emp.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.Duration;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;

@Aspect
@Configuration
public class AppAspectConfig {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final DateTimeFormatter formatter =DateTimeFormatter.ISO_LOCAL_DATE_TIME.withZone(ZoneId.systemDefault());
    
    
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



	public static String spyFields(Object obj) throws IllegalAccessException {
		StringBuilder sb = new StringBuilder();
		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field f : fields) {
			if (!Modifier.isStatic(f.getModifiers())) {
				f.setAccessible(true);
				Object value = f.get(obj);
				sb.append(f.getType().getName());
				sb.append(" ");
				sb.append(f.getName());
				sb.append("=");
				sb.append("" + value);
				sb.append("\n");
			}
		}
		return sb.toString();
	}


	private void logIt(String sql ,Instant start , Instant end ){
		Duration timeElapsed = Duration.between(start, end);

		logger.info(
				String.format("[ SQL  : %s   ] [ START TIME : %s ] [ END TIME : %s ] [ TOTAL EXECUTION TIME IN MILLISECONDS : %s ]",
						sql,formatter.format(start) ,formatter.format( end ),timeElapsed.toMillis()));

	}
    
    
}