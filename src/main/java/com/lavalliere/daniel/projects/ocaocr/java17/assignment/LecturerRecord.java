package com.lavalliere.daniel.projects.ocaocr.java17.assignment;

import java.util.Optional;
import java.util.logging.Logger;

public record LecturerRecord(String name, Integer age, Faculty faculty, Department dept) {

    // Note that for record, additional field need to be static
    private static final Logger logger = Logger.getLogger(LecturerRecord.class.getName());

    public LecturerRecord {
        if (name.isBlank() || age <= 0) throw new IllegalArgumentException("""
            Illegal argument passed:
                  "name": %s,
                  "age": %s
            """.formatted(name, age));
    }

    public boolean hasPhd() {
        // Better choice here is:  return
        // return name.length() >= 3 && (name.toUpperCase().startsWith("DR.") || name.toUpperCase().endsWith("PHD"));

        // But for assignment from turtorial, requested
        String prefix = name.length() >= 3 ? name.substring(0,3).toUpperCase() : null;
        String suffix = name.length() >= 3 ? name.substring(name.length()-3 ).toUpperCase() : null;
       /*
          return
            switch(prefix) {
                case "DR." -> true;
                case null, default -> false;
            } ||
            switch(suffix) {
                case "PHD" -> true;
                case null, default -> false;
            }; */

        return
            switch(prefix) {
                case "DR." -> true;
                case null, default -> switch (suffix) {
                    case "PHD" -> true;
                    case null, default -> false;
                };
            };
    }

    public void whichFaculty() {
        switch(faculty) {
            case EngineeringFaculty e -> {
                logger.info(String.format("Faculty of: %s\n",e));
                e.engineering();
            }
            case HumanitiesFaculty h -> {
                logger.info(String.format("Faculty of: %s\n",h));
                h.humanities();
            }
            case BusinessFaculty b -> {
                logger.info(String.format("Faculty of: %s\n",b));
                b.business();
            }
            default -> throw new IllegalArgumentException(String.format("Invalid faculty %s\n",faculty));
        }
    }

    public void whichDept() {
        switch(dept) {
            case ComputerEngineeringDept c -> {
                logger.info(String.format("Dept of: %s\n",c));
                c.compEng();
            }
            case SoftwareEngineeringDept se -> {
                logger.info(String.format("Dept of: %s\n",se));
                se.swEng();
            }
            case AccountingDept a -> {
                logger.info(String.format("Dept of: %s\n",a));
                a.accounting();
            }
            case SocialCareDept sc -> {
                logger.info(String.format("Dept of: %s\n",sc));
                sc.socialCare();
            }
            default -> throw new IllegalArgumentException(String.format("Invalid department %s\n",dept));
        }
    }
}
