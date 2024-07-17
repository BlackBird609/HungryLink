package com.example.server.serverManagerIO;

import com.example.server.prev_v3.Restaurant;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

public class ReadFromRest implements Runnable{
    List<Restaurant> restaurants;
    Thread thread1;

    public ReadFromRest(List<Restaurant> restaurants)
    {
        thread1 = new Thread(this);
        this.restaurants = restaurants;
        thread1.start();
    }
    @Override
    public void run()
    {
        //reading from restaurants.txt
        try
        {
            //reading file
            BufferedReader br_r = new BufferedReader(new FileReader("restaurant.txt"));

            //creating restaurant objects
            String s1;
            while ((s1 = br_r.readLine()) != null) {
                String[] arr = s1.split(",", -1);
                String[] ss = new String[arr.length - 5];
                System.arraycopy(arr, 5, ss, 0, arr.length - 5);

                restaurants.add(new Restaurant(Integer.valueOf(arr[0]), arr[1], Double.valueOf(arr[2]), arr[3], arr[4], ss));
            }
            br_r.close();
        }
        catch(Exception e)
        {
            System.out.println("got exception while reading from restaurants");
            System.out.println(e);
        }
    }

    public Thread getThread1()
    {
        return thread1;
    }
}
