package com.lavalliere.daniel.projects.ocaocr.java17.assignment;

import java.util.logging.Logger;

public class University {
    private final static Logger logger = Logger.getLogger(University.class.getName());
    public static void main(String[] args) {
        // Exception name is empty
        // LecturerRecord record = new LecturerRecord("",22,new BusinessFaculty(), new SocialCareDept());
        // Exception age < 0
        // LecturerRecord record = new LecturerRecord("Me",-3,new BusinessFaculty(), new SocialCareDept());
        // LecturerRecord record = new LecturerRecord("Jane",24,new BusinessFaculty(), new SocialCareDept());
        // logger.info(STR."Name: \{record.name()} Age: \{record.age()}} Faculty: \{record.faculty()} Department: \{record.dept()}");
        // record.whichFaculty();
        // record.whichDept();
        // logger.info(STR."\{record.name()} has a PhD: \{record.hasPhd()}");

        // LecturerRecord record = new LecturerRecord("Dr. Jane",24,new BusinessFaculty(), new SocialCareDept());
        LecturerRecord record = new LecturerRecord("Jane PhD",24,new BusinessFaculty(), new SocialCareDept());
        logger.info(String.format("%s has a PhD: %b\n",record.name(),record.hasPhd()));
    }
}
