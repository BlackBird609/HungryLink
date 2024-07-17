package com.example.server.prev_v3;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DatabaseManager {

    //private variables
    private List<Restaurant> restaurants;
    private List<Food> foods;


    //constructor
    public DatabaseManager(List<Restaurant> restaurants, List<Food> foods)
    {
        this.restaurants = restaurants;
        this.foods = foods;
    }
    DatabaseManager()
    {

    }

}