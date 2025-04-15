package com.lavalliere.daniel.projects.patterns.structural.adapter.objectbased;

public class WordDocumentAdapter implements DocumentUploader {

    private final WordDocumentUploader wordDocumentUploader;

    public WordDocumentAdapter() {
        this.wordDocumentUploader = new WordDocumentUploader();
    }
    @Override
    public void upload() {
        wordDocumentUploader.uploadWordDocument();
    }
}