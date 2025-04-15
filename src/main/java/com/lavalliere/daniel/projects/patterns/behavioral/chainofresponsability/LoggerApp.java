package com.lavalliere.daniel.projects.patterns.behavioral.chainofresponsability;

import com.lavalliere.daniel.projects.annotations.Demoable;
import com.lavalliere.daniel.projects.annotations.IsDemoable;

@IsDemoable
public class LoggerApp implements Demoable {

    private static Logger buildLoggerChain() {
        var fileLogger = new FileLogger(null);
        var consoleLogger = new ConsoleLogger(fileLogger);
        return consoleLogger;
    }

    private LoggerApp logIt() {
        var message = "Hello World";
        var request = new LoggerRequest(message, LoggerRequest.LoggerType.CONSOLE);
        Logger logger = buildLoggerChain();
        logger.log(request);
        System.out.println();
        return this;
    }

    public void demo() {
        logIt();
    }
}
