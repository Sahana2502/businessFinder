package com.rbc.yelp.services.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

public class SearchResult {
    @SerializedName("total")
    private int total;
    @SerializedName("businesses")
    private List<Business> businesses;

    @Override
    public String toString() {
        return "SearchResult{" +
                "total=" + total +
                ", businesses=" + businesses +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchResult that = (SearchResult) o;
        return total == that.total && Objects.equals(businesses, that.businesses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(total, businesses);
    }

    public int getTotal() {
        return total;
    }

    public List<Business> getBusinesses() {
        return businesses;
    }
}
