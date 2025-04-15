package com.lavalliere.daniel.projects.ocaocr.java17.assignment;


import java.util.logging.Logger;

final class ComputerEngineeringDept extends Department {
    private final Logger logger = Logger.getLogger(ComputerEngineeringDept.class.getName());
    public void compEng() {
        logger.info("Custom computer engineering");
    }

    @Override
    public String toString() {
        return "Computer Engineering";
    }
}
