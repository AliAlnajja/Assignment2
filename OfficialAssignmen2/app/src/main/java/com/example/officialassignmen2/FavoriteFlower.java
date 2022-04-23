package com.example.officialassignmen2;

public class FavoriteFlower {

    String imageUrl;
    String price;
    String flowersName;


    public FavoriteFlower(String imageUrl, String price, String flowersName) {
        this.imageUrl = imageUrl;
        this.price = price;
        this.flowersName = flowersName;
    }

    public FavoriteFlower() {
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFlowersName() {
        return flowersName;
    }

    public void setFlowersName(String flowersName) {
        this.flowersName = flowersName;
    }
}
