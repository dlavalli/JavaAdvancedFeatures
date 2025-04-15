package com.lavalliere.daniel.projects.patterns.behavioral.chainofresponsability;

public class ConsoleLogger extends Logger {

    public ConsoleLogger(Logger nextLogger) {
        super(nextLogger);
    }

    @Override
    public void log(LoggerRequest request) {
        switch(request.getLoggerType()) {
            case LoggerRequest.LoggerType.CONSOLE -> System.out.printf("Console Logger: %s\n",request.getMessage());
            case LoggerRequest.LoggerType.FILE -> nextLogger.log(request);
            case null, default -> System.out.printf("No available handler for type %s\n",request.getLoggerType());
        }
    }

}