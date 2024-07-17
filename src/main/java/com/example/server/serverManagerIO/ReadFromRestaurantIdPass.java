package com.example.server.serverManagerIO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class ReadFromRestaurantIdPass implements Runnable{
    HashMap<Integer,String> restaurantIdPass;
    Thread thread1;

    public ReadFromRestaurantIdPass(HashMap<Integer,String> restaurantIdPass)
    {
        this.restaurantIdPass = restaurantIdPass;
        thread1 = new Thread(this);
        thread1.start();
    }

    @Override
    public void run()
    {

        try
        {
            BufferedReader br_f = new BufferedReader(new FileReader("RestaurantIdPass.txt"));
            String s2;
            while ((s2 = br_f.readLine()) != null)
            {
                String[] arr = s2.split(",", -1);
                restaurantIdPass.put(Integer.valueOf(arr[0]),arr[1]);
            }
            br_f.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    public Thread getThread1()
    {
        return thread1;
    }
}
