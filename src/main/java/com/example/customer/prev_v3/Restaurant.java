package com.example.customer.prev_v3;

import java.io.Serializable;

public class Restaurant implements Serializable {

    //private variables of string
    private int id;
    private String name;
    private double score;
    private String price;
    private String zipcode;
    private String categories[];

    //constructor
    public Restaurant(int id, String name, double score, String price, String zipcode, String cat[])
    {
        this.id = id;
        this.name = name;
        this.score = score;
        this.price = price;
        this.zipcode = zipcode;

        this.categories = new String[cat.length];
        for(int i=0;i<cat.length;i++)
        {
            this.categories[i] = cat[i];
        }
    }


    //methods of restaurant
    public void printRestaurant()
    {
        //System.out.println("id: "+this.id);
        System.out.println("name: "+this.name);
        System.out.println("score: "+this.score);
        System.out.println("price: "+this.price);
        System.out.println("zipcode: "+this.zipcode);
        System.out.print("categories: ");
        for(int i =0;i<categories.length;i++)
        {
            System.out.print(categories[i]);
            if(i != (categories.length-1))
            {
                System.out.print(",");
            }
        }
        System.out.println("\n");
    }


    //getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getScore() {
        return score;
    }

    public String getPrice() {
        return price;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String[] getCategories() {
        return categories;
    }


}
