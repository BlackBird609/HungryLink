package com.example.server.serverManagerIO;

import com.example.server.prev_v3.Food;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

public class ReadFromMenu implements Runnable{
    List<Food> foods;

    Thread thread1;

    public ReadFromMenu(List<Food> foods)
    {
        this.foods = foods;
        thread1 = new Thread(this);
        thread1.start();
    }

    @Override
    public void run()
    {
        //reading from food menu
        try
        {
            BufferedReader br_f = new BufferedReader(new FileReader("menu.txt"));
            //creating food objects
            String s2;
            while ((s2 = br_f.readLine()) != null) {
                String[] arr = s2.split(",", -1);
                String[] ss = new String[arr.length];
                foods.add(new Food(Integer.valueOf(arr[0]), arr[1], arr[2], Double.valueOf(arr[3])));
            }
            br_f.close();
        }
        catch(Exception e)
        {
            System.out.println("got exception while reading from menu");
            System.out.println(e);
        }
    }

    public Thread getThread1()
    {
        return thread1;
    }
}
