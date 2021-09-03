package com.example.mysqldemo;

public class Product {

    private  String name;
    private String image;
    private Integer price;
    private Integer quantity;

    public Product(String name,String image,Integer price,Integer quantity){
        this.image=image;
        this.name=name;
        this.price=price;
        this.quantity=quantity;
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

    public Integer getQuantity() {
        return quantity;
    }


}



