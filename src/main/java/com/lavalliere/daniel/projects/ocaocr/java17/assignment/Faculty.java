package com.lavalliere.daniel.projects.ocaocr.java17.assignment;

abstract public sealed class Faculty implements Educational
       permits EngineeringFaculty,
               HumanitiesFaculty,
               BusinessFaculty {
}
