package com.example.officialassignmen2;

public class Flowers {
    private String flowersName;
    private String ImageUrl;
    private String price;


    public Flowers(String flowerName, String imageUrl, String price) {
        this.flowersName = flowerName;
        this.ImageUrl = imageUrl;
        this.price = price;
    }



    public Flowers() {
    }

    public String getFlowersName() {
        return flowersName;
    }

    public void setFlowersName(String flowerName) {
        this.flowersName = flowerName;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
