package com.vinkrish.classification;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by vinkrish on 14/03/16.
 */
public class Classification {
    private long id;
    @SerializedName("name")
    private String classificationName;
    private int points;
    @SerializedName("category")
    private String[] categoryList;
    @SerializedName("theme")
    private Theme theme;
    @SerializedName("items")
    private List<ClassificationItem> classificationItems;

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getClassificationName() {
        return classificationName;
    }

    public void setClassificationName(String classificationName) {
        this.classificationName = classificationName;
    }

    public List<ClassificationItem> getClassificationItems() {
        return classificationItems;
    }

    public void setClassificationItems(List<ClassificationItem> classificationItems) {
        this.classificationItems = classificationItems;
    }

    public String[] getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(String[] categoryList) {
        this.categoryList = categoryList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
