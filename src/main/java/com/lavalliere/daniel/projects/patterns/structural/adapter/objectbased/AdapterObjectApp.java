package com.lavalliere.daniel.projects.patterns.structural.adapter.objectbased;

import com.lavalliere.daniel.projects.annotations.Demoable;
import com.lavalliere.daniel.projects.annotations.IsDemoable;

@IsDemoable
public class AdapterObjectApp implements Demoable {

    private AdapterObjectApp doAdapt() {
        var pdfDocumentUploader = new PdfDocumentUploader();
        var onlineApplication = new OnlineApplication(pdfDocumentUploader);
        onlineApplication.uploadDocument();

        var wordDocumentUploader = new WordDocumentAdapter();
        var onlineApplication2 = new OnlineApplication(wordDocumentUploader);
        onlineApplication2.uploadDocument();

        System.out.println();
        return this;
    }

    public void demo() {
        doAdapt();
    }
}
