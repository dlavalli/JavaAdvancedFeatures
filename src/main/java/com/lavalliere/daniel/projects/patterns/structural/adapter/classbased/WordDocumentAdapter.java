package com.lavalliere.daniel.projects.patterns.structural.adapter.classbased;

import com.lavalliere.daniel.projects.patterns.structural.adapter.objectbased.DocumentUploader;
import com.lavalliere.daniel.projects.patterns.structural.adapter.objectbased.WordDocumentUploader;

public class WordDocumentAdapter extends WordDocumentUploader implements DocumentUploader {

    @Override
    public void upload() {
        uploadWordDocument();
    }

    @Override
    public void uploadWordDocument() {
        System.out.println("Preparing Word document...");
        System.out.println("Uploading Word document");
    }
}