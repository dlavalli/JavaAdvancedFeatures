package com.lavalliere.daniel.projects.patterns.behavioral.chainofresponsability;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileLogger extends Logger {

    public FileLogger(Logger nextLogger) {
        super(nextLogger);
    }

    @Override
    public void log(LoggerRequest request) {
        switch(request.getLoggerType()) {
            case LoggerRequest.LoggerType.CONSOLE -> nextLogger.log(request);
            case LoggerRequest.LoggerType.FILE -> {
                try {
                    var writer = new BufferedWriter(new FileWriter("src/main/resources/log.txt", true));
                    writer.write(request.getMessage() + "\n");
                    writer.close();
                    System.out.println("File Logger: written the content to file");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            case null, default ->
                System.out.printf("No available handler for type %s%n", request.getLoggerType().name());
        }
    }
}