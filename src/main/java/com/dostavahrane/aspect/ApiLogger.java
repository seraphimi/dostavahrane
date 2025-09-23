package com.dostavahrane.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class ApiLogger {
    
    private static final Logger logger = LoggerFactory.getLogger(ApiLogger.class);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    public void logApiCall(String methodName, String className, Object[] args) {
        String timestamp = LocalDateTime.now().format(formatter);
        StringBuilder logMessage = new StringBuilder();
        
        logMessage.append("=== API CALL LOG ===\n");
        logMessage.append("Timestamp: ").append(timestamp).append("\n");
        logMessage.append("Class: ").append(className).append("\n");
        logMessage.append("Method: ").append(methodName).append("\n");
        
        if (args != null && args.length > 0) {
            logMessage.append("Arguments: ");
            for (int i = 0; i < args.length; i++) {
                if (args[i] != null) {
                    logMessage.append(args[i].toString());
                } else {
                    logMessage.append("null");
                }
                if (i < args.length - 1) {
                    logMessage.append(", ");
                }
            }
            logMessage.append("\n");
        }
        
        logMessage.append("===================");
        
        logger.info(logMessage.toString());
    }
    
    public void logApiResult(String methodName, String className, Object result) {
        String timestamp = LocalDateTime.now().format(formatter);
        StringBuilder logMessage = new StringBuilder();
        
        logMessage.append("=== API RESULT LOG ===\n");
        logMessage.append("Timestamp: ").append(timestamp).append("\n");
        logMessage.append("Class: ").append(className).append("\n");
        logMessage.append("Method: ").append(methodName).append("\n");
        logMessage.append("Result: ").append(result != null ? result.toString() : "null").append("\n");
        logMessage.append("=====================");
        
        logger.info(logMessage.toString());
    }
}