package com.example.officialassignmen2;

public class Gifts {
    private String giftsName;
    private String ImageUrl;
    private String price;

    public Gifts(String giftsName, String imageUrl, String price) {
        this.giftsName = giftsName;
        ImageUrl = imageUrl;
        this.price = price;
    }

    public Gifts() {
    }

    public String getGiftsName() {
        return giftsName;
    }

    public void setGiftsName(String giftsName) {
        this.giftsName = giftsName;
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
