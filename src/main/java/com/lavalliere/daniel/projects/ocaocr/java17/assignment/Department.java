package com.lavalliere.daniel.projects.ocaocr.java17.assignment;

abstract public sealed  class Department
       permits ComputerEngineeringDept,
               SoftwareEngineeringDept,
               AccountingDept,
               SocialCareDept {
}
