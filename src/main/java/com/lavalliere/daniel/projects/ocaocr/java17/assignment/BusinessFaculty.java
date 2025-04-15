package com.lavalliere.daniel.projects.ocaocr.java17.assignment;

import java.util.logging.Logger;

final class BusinessFaculty extends Faculty{
    private final Logger logger = Logger.getLogger(BusinessFaculty.class.getName());

    public void business() {
        logger.info("We teach accounting. law, economies etc...");
    }

    @Override
    public String toString() {
        return "Business";
    }
}
