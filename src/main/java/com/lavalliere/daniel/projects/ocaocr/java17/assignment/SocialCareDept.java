package com.lavalliere.daniel.projects.ocaocr.java17.assignment;

import java.util.logging.Logger;

final class SocialCareDept extends Department {
    private final Logger logger = Logger.getLogger(SocialCareDept.class.getName());

    public void socialCare() {
        logger.info("Custom social care");
    }

    @Override
    public String toString() {
        return "Social Care";
    }
}
