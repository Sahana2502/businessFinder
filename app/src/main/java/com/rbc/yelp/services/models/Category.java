package com.rbc.yelp.services.models;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Category {
    @SerializedName("alias")
    private String alias;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(alias, category.alias) && Objects.equals(title, category.title);
    }

    @Override
    public String toString() {
        return "Category{" +
                "alias='" + alias + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(alias, title);
    }

    @SerializedName("title")
    private String title;

    public String getAlias() {
        return alias;
    }

    public String getTitle() {
        return title;
    }
}
