package com.example.madassignment;

public class prodmod {
    private String name;
    private long price;
    private String description;

    private prodmod(){}

    private prodmod(String name, long price, String description){
        this.name=name;
        this.price=price;
        this.description=description;
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
}
