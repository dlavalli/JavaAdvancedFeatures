package com.lavalliere.daniel.projects.ocaocr.java17.assignment;

import java.util.logging.Logger;

final class EngineeringFaculty extends Faculty {
    private final Logger logger = Logger.getLogger(EngineeringFaculty.class.getName());

    public void engineering() {
        logger.info("We teach computer science, civil engineering etc...");
    }

    @Override
    public String toString() {
        return "Engineering";
    }
}
