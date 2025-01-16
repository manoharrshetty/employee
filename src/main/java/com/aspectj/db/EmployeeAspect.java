package com.aspectj.db;


import com.emp.util.ConfigurationUtil;
import com.mysql.cj.ClientPreparedQuery;
import com.mysql.cj.jdbc.ClientPreparedStatement;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


@Aspect
@Component
public class EmployeeAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeAspect.class);
    DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME.withZone(ZoneId.systemDefault());

    /**
     * this will log all PreparedStatements execute queries ,update, delete etc.
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution(* java.sql.PreparedStatement.execute* (..))")
    public Object aroundPreparedStatementExecute(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Instant start = null;
        Instant end = null;

        try {
            start = Instant.now();


            return proceedingJoinPoint.proceed();
        } finally {
            end = Instant.now();
            logIt(proceedingJoinPoint, start, end);
        }

    }


    @Around("execution(* java.sql.Statement.executeQuery (..))")
    public Object aroundStatementExecuteQuery(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        StringBuilder sql = new StringBuilder();
        for (Object o : proceedingJoinPoint.getArgs()) {
            sql.append((String) o);

        }


        java.sql.ResultSet resultSet = null;
        Instant start = null;
        Instant end = null;

        try {
            start = Instant.now();

            resultSet = (java.sql.ResultSet) proceedingJoinPoint.proceed();
        } finally {
            end = Instant.now();

            logIt(sql.toString(), start, end);
            return resultSet;
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


    private void logIt(ProceedingJoinPoint proceedingJoinPoint, Instant start, Instant end) {
        Duration timeElapsed = Duration.between(start, end);
        /*
        have not specified during initialization because in future this threshold must be
        dynamic and must come from database and its value can be changed as per requirements and need
         */
        long thresholdMs = Long.parseLong(
                ConfigurationUtil.CONFIGURATION_PROPERTIES_INSTANCE.getProperty("query.threshold.ms"));


        if (timeElapsed.toMillis() > thresholdMs) {
            ClientPreparedStatement cp = (ClientPreparedStatement) proceedingJoinPoint.getTarget();
            ClientPreparedQuery cpq = (ClientPreparedQuery) cp.getQuery();
            LOGGER.error(
                    String.format("[ SQL  : %s   ] [ START TIME : %s ] [ END TIME : %s ] [ TOTAL EXECUTION TIME IN MILLISECONDS : %s ]",
                            cpq.getOriginalSql(), formatter.format(start), formatter.format(end), timeElapsed.toMillis()));

        }


    }

    private void logIt(String sql, Instant start, Instant end) {
        Duration timeElapsed = Duration.between(start, end);
    /*
        have not specified during initialization because in future this threshold must be
        dynamic and must come from database and its value can be changed as per requirements and need
         */
        long thresholdMs = Long.parseLong(
                ConfigurationUtil.CONFIGURATION_PROPERTIES_INSTANCE.getProperty("query.threshold.ms"));


        if (timeElapsed.toMillis() > thresholdMs) {

            LOGGER.error(
                    String.format("[ SQL  : %s   ] [ START TIME : %s ] [ END TIME : %s ] [ TOTAL EXECUTION TIME IN MILLISECONDS : %s ]",
                            sql, formatter.format(start), formatter.format(end), timeElapsed.toMillis()));
        }

    }
}
