package com.lavalliere.daniel.projects.ocaocr.java17.assignment;

import java.util.logging.Logger;

final class AccountingDept extends Department {
    private final Logger logger = Logger.getLogger(AccountingDept.class.getName());

    public void accounting() {
        logger.info("Custom accounting");
    }

    @Override
    public String toString() {
        return "Accounting";
    }
}
