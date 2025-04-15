package com.lavalliere.daniel.projects.patterns.structural.adapter.classbased;

import com.lavalliere.daniel.projects.annotations.Demoable;
import com.lavalliere.daniel.projects.annotations.IsDemoable;
import com.lavalliere.daniel.projects.patterns.structural.adapter.objectbased.OnlineApplication;
import com.lavalliere.daniel.projects.patterns.structural.adapter.objectbased.PdfDocumentUploader;
import com.lavalliere.daniel.projects.patterns.structural.adapter.objectbased.WordDocumentAdapter;

@IsDemoable
public class AdapterClassApp implements Demoable {

    private AdapterClassApp doAdapt() {
        var pdfDocumentUploader = new PdfDocumentUploader();
        var onlineApplication = new OnlineApplication(pdfDocumentUploader);
        onlineApplication.uploadDocument();

        var wordDocumentUploader = new WordDocumentAdapter();
        var onlineApplication2 = new OnlineApplication(wordDocumentUploader);
        onlineApplication2.uploadDocument();

        System.out.println();
        return this;
    }

    public static Demoable newInstance() {
        return new AdapterClassApp();
    }

    public void demo() {
        doAdapt();
    }
}
