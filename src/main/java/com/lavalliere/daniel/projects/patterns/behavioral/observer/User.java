package com.lavalliere.daniel.projects.patterns.behavioral.observer;

import java.beans.PropertyChangeSupport;


// the observable object
public class User {

    private String status;
    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void setStatus(String status) {
        // Notifies the observer when the property in this class changes
        support.firePropertyChange("status",
            this.status,  // Old value
            status // New value
        );

        this.status = status;
    }

    public void addPropertyChangeListener(Newsfeed newsfeed) {
        support.addPropertyChangeListener(newsfeed);
    }
}