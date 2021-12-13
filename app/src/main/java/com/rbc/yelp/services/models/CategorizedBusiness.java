package com.rbc.yelp.services.models;

import java.util.List;

public class CategorizedBusiness {

    String categoryName;
    List<Business> businessList;

    public CategorizedBusiness(String categoryName, List<Business> businessList) {
        this.categoryName = categoryName;
        this.businessList = businessList;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Business> getBusinessList() {
        return businessList;
    }

    public void setBusinessList(List<Business> businessList) {
        this.businessList = businessList;
    }
}

