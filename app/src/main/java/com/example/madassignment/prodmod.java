package com.example.madassignment;

public class prodmod {
    private String name;
    private long price;
    private String description;
    private String image;
    private String id;

    private prodmod(){}

    private prodmod(String name, long price, String description, String image, String id){
        this.name=name;
        this.price=price;
        this.description=description;
        this.image=image;
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
