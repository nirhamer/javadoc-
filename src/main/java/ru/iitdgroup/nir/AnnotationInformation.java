package ru.iitdgroup.nir;

public class AnnotationInformation {
    private String displayName = "";
    private String description = "";
    private boolean classDoc;

    AnnotationInformation(String displayName) {
        this.displayName = displayName;
    }

    AnnotationInformation(boolean classDoc) {
        this.classDoc = classDoc;
    }

    String getDisplayName() {
        return displayName;
    }

    void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    String getDescription() {
        return description;
    }

    void setDescription(String description) {
        this.description = description;
    }

    public boolean isClassDoc() {
        return classDoc;
    }

    public void setClassDoc(boolean classDoc) {
        this.classDoc = classDoc;
    }
}