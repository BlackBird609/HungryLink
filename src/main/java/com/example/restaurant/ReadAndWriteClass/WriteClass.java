package com.example.restaurant.ReadAndWriteClass;

import com.example.restaurant.prev_v3.Food;
import com.example.restaurant.prev_v3.Restaurant;

import java.io.IOException;
import java.util.List;

import com.example.restaurant.util.SocketWrapper;

public class WriteClass {
    public static void writeRestaurant(Restaurant restaurant, SocketWrapper socketWrapper) throws IOException {
        socketWrapper.write(restaurant.getId());
        socketWrapper.write(restaurant.getName());
        socketWrapper.write(restaurant.getScore());
        socketWrapper.write(restaurant.getPrice());
        socketWrapper.write(restaurant.getZipcode());
        socketWrapper.write(restaurant.getCategories());
    }

    public static void writeFood(Food food, SocketWrapper socketWrapper) throws IOException {
        socketWrapper.write(food.getRestId());
        socketWrapper.write(food.getCategory());
        socketWrapper.write(food.getName());
        socketWrapper.write(food.getPrice());
    }

    public static void writeListOfFood(List<Food> foodList, SocketWrapper socketWrapper) throws IOException {
        socketWrapper.write(foodList.size());
        for (Food food : foodList) {
            writeFood(food, socketWrapper);
        }
    }

    public static void writeListOfRestaurant(List<Restaurant> restaurantList, SocketWrapper socketWrapper) throws IOException {
        socketWrapper.write(restaurantList.size());
        for (Restaurant restaurant : restaurantList) {
            writeRestaurant(restaurant, socketWrapper);
        }
    }

    public static void writeHashMapOfRestaurantAndFoodList(java.util.HashMap<Restaurant, List<Food>> hashMap, SocketWrapper socketWrapper) throws IOException {
        socketWrapper.write(hashMap.size());
        for (Restaurant restaurant : hashMap.keySet()) {
            writeRestaurant(restaurant, socketWrapper);
            writeListOfFood(hashMap.get(restaurant), socketWrapper);
        }
    }

}
