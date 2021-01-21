package com.example.madassignment;

public class cartq {
    private String name;
    private long price;
    private String image;
    private String id;


    private cartq(){}


    private cartq(String name, long price, String image, String id){
        this.name = name;
        this.price = price;
        this.image = image;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
