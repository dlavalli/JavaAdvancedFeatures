package com.lavalliere.daniel.projects.patterns.structural.adapter.objectbased;

public class OnlineApplication {

    private final DocumentUploader documentUploader;

    public OnlineApplication(DocumentUploader documentUploader) {
        this.documentUploader = documentUploader;
    }

    public void uploadDocument() {
        documentUploader.upload();
    }
}