package com.lavalliere.daniel.projects.patterns.behavioral.visitor;

import com.lavalliere.daniel.projects.patterns.behavioral.visitor.File;
import com.lavalliere.daniel.projects.patterns.behavioral.visitor.Directory;

public interface Visitor {
    void visit(File file);
    void visit(Directory directory);
}
