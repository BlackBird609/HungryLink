package com.example.server.prev_v3;

import java.io.Serializable;

public class Food implements Serializable{

    //private variables
    private final int restId;
    private final String category;
    private final String name;
    private final double price;


    //constructor
    public Food(int restId, String category, String name, double price)
    {
        this.restId = restId;
        this.category = category;
        this.name = name;
        this.price = price;
    }


    //public method
    public void printFood()
    {
        //System.out.println("resId: "+this.restId);
        System.out.println("category: "+this.category);
        System.out.println("name: "+this.name);
        System.out.println("price: "+this.price);
    }


    public int getRestId() {
        return restId;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
