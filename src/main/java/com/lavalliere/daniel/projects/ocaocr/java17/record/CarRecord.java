package com.lavalliere.daniel.projects.ocaocr.java17.record;

import java.io.Serializable;

public record CarRecord(String regNumber, String owner)
       implements Serializable {  // Can implement interfaces

    // Can have static initializer
    static {
        staticInitialized = 10;
    }

    // Can have static bloc initialized static field
    public static final int staticInitialized;
    public static final long serialVersionUID = -1;

    // Can have pre initialized static field
    public static final String currentYear = "23";

    public boolean isNewCar() {
        return regNumber().startsWith(currentYear);
    }

    // Can have static method
    public static CarRecord createBlankCarRecord() {
        return new CarRecord("    ","");
    }

    /* Identical to compact form below
    public CarRecord(String regNumber, String owner) {
        if (regNumber().length() <= 4) {
            throw new IllegalArgumentException();
        }
        this.regNumber = regNumber;
        this.owner = owner;
    }
     */

    public CarRecord {
        if (regNumber().length() <= 4) {
            throw new IllegalArgumentException();
        }
    }

    // Can add other constructors but must refer to canonical one
    public CarRecord() {
        this("","");
    }

    // Can add other constructors but must refer to canonical one
    public CarRecord(int id) {
        this(Integer.toString(id), "");
    }

    @Override
    public String owner() {
        return owner.toUpperCase();
    }

    @Override
    public String toString() {
        return String.format("CardRecord with regNumber: %s is owned by: %s",regNumber,owner);
    }
}

