package com.vinkrish.classification;

/**
 * Created by vinkrish on 14/03/16.
 */
public class ClassificationItem {
    private String name;
    private String source;
    private String category;
    private boolean isDragging;

    public boolean isDragging() {
        return isDragging;
    }

    public void setIsDragging(boolean isDragging) {
        this.isDragging = isDragging;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
