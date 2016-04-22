package com.vinkrish.classification;

/**
 * Created by vinkrish on 14/03/16.
 */
public class ClassficationResult {
    private String name;
    private String source;
    private String category;
    private String answeredCategory;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAnsweredCategory() {
        return answeredCategory;
    }

    public void setAnsweredCategory(String answeredCategory) {
        this.answeredCategory = answeredCategory;
    }
}
