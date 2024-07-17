package com.example.restaurant.ReadAndWriteClass;

import com.example.restaurant.prev_v3.Food;
import com.example.restaurant.prev_v3.Restaurant;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.example.restaurant.util.SocketWrapper;

public class ReadClass {
    public static Restaurant readRestaurant(SocketWrapper socketWrapper) throws IOException, ClassNotFoundException {
        int id = (Integer) socketWrapper.read();
        String name = (String) socketWrapper.read();
        double score = (Double) socketWrapper.read();
        String price = (String) socketWrapper.read();
        String zipcode = (String) socketWrapper.read();
        String cat[] = (String[]) socketWrapper.read();

        return new Restaurant(id, name, score, price, zipcode, cat);
    }

    public static Food readFood(SocketWrapper socketWrapper) throws IOException, ClassNotFoundException {
        int restId = (Integer)socketWrapper.read();
        String category = (String)socketWrapper.read();
        String name = (String)socketWrapper.read();
        double price = (Double)socketWrapper.read();

        return new Food(restId, category, name, price);
    }

    public static List<Food> readListOfFood(SocketWrapper socketWrapper) throws IOException, ClassNotFoundException {
        int n = (Integer) socketWrapper.read();
        List<Food> foodList = new java.util.ArrayList<>();
        for(int i=0;i<n;i++)
        {
            foodList.add(readFood(socketWrapper));
        }
        return foodList;
    }

    public static List<Restaurant> readListOfRestaurant(SocketWrapper socketWrapper) throws IOException, ClassNotFoundException {
        int n = (Integer) socketWrapper.read();
        List<Restaurant> restaurantList = new java.util.ArrayList<>();
        for(int i=0;i<n;i++)
        {
            restaurantList.add(readRestaurant(socketWrapper));
        }
        return restaurantList;
    }

    public static HashMap<Restaurant,List<Food>> readHashMapOfRestaurantAndFoodList(SocketWrapper socketWrapper) throws IOException, ClassNotFoundException {
        int n = (Integer) socketWrapper.read();
        HashMap<Restaurant,List<Food>> hashMap = new HashMap<>();
        for(int i=0;i<n;i++)
        {
            Restaurant restaurant = readRestaurant(socketWrapper);
            List<Food> foodList = readListOfFood(socketWrapper);
            hashMap.put(restaurant,foodList);
        }
        return hashMap;
    }
}
