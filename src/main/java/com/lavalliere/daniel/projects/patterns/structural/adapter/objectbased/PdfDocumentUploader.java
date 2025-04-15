package com.lavalliere.daniel.projects.patterns.structural.adapter.objectbased;

public class PdfDocumentUploader implements DocumentUploader {

    @Override
    public void upload() {
        System.out.println("Preparing PDF document...");
        System.out.println("Uploading PDF document");
    }
}
