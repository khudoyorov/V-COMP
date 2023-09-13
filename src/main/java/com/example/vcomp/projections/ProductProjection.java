package com.example.vcomp.projections;

public interface ProductProjection {
    Integer getId();
    String getName();
    String getDescription();
    Integer getPrice();
    Integer getCategoryId();
    Integer getBrandId();
    Integer getDiscount();
    boolean isLiked();
}