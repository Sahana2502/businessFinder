package com.rbc.yelp.services.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

/**
 * Business model from the Yelp v3 API.
 * Update this file to include any fields you feel are missing.
 *
 * @see <a href=https://www.yelp.ca/developers/documentation/v3/business_search>Yelp API Business Search</a>
 */
public class Business {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("categories")
    private List<Category> categories;

    @SerializedName("image_url")
    private String image_url;

    @SerializedName("rating")
    private float rating;

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Business business = (Business) o;
        return Objects.equals(id, business.id) && Objects.equals(name, business.name) && Objects.equals(categories, business.categories) && Objects.equals(image_url, business.image_url);
    }

    @Override
    public String toString() {
        return "Business{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", categories=" + categories +
                ", image_url='" + image_url + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, categories, image_url);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
