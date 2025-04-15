package com.lavalliere.daniel.projects.ocaocr.java17.assignment;

import java.util.logging.Logger;

final class SoftwareEngineeringDept extends Department {
    private final Logger logger = Logger.getLogger(SoftwareEngineeringDept.class.getName());

    public void swEng() {
        logger.info("Custom software engineering");
    }

    @Override
    public String toString() {
        return "Software Engineering";
    }
}
