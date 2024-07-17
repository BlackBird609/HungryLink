package com.example.server.prev_v3;

import java.io.IOException;
import java.io.Serializable;

public class Restaurant implements Serializable {

    //private variables of string
    private final int id;
    private final String name;
    private final double score;
    private final String price;
    private final String zipcode;
    private final String[] categories;

    //constructor
    public Restaurant(int id, String name, double score, String price, String zipcode, String[] cat)
    {
        this.id = id;
        this.name = name;
        this.score = score;
        this.price = price;
        this.zipcode = zipcode;

        this.categories = new String[cat.length];
        System.arraycopy(cat, 0, this.categories, 0, cat.length);
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

    public void printRestaurantForCustomer(util.SocketWrapper socketWrapper) throws IOException {
        //System.out.println("id: "+this.id);
        socketWrapper.write("name: "+this.name);
        socketWrapper.write("score: "+this.score);
        socketWrapper.write("price: "+this.price);
        socketWrapper.write("zipcode: "+this.zipcode);
        //socketWrapper.write("categories: ");

        String s = "categories: ";
        for(int i =0;i<categories.length;i++)
        {
            s = s + categories[i];
            if(i != (categories.length-1))
            {
                s = s + ",";
            }
        }
        socketWrapper.write(s);
        socketWrapper.write("\n");
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
