package com.example.mysqldemo;

public class ServicesInfo {

    private  String name;
    private String image;
    private Integer price;


    public ServicesInfo(String name,String image,Integer price){
        this.image=image;
        this.name=name;
        this.price=price;

    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }




}

