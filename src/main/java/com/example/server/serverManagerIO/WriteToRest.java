package com.example.server.serverManagerIO;

import com.example.server.prev_v3.Restaurant;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

public class WriteToRest implements Runnable{

    List<Restaurant> restaurants;
    Thread thread1;
    public WriteToRest(List<Restaurant> restaurants)
    {
        this.restaurants = restaurants;
        thread1 = new Thread(this);
        thread1.start();
    }
    @Override
    public void run()
    {
        //writing to restaurant
        try
        {
            //writing to file
            BufferedWriter bw_r = new BufferedWriter(new FileWriter("restaurant.txt"));

            //System.out.println("to be written in file rest");
            for(Restaurant i:restaurants)
            {
                String s3 = i.getId()+","+i.getName()+","+i.getScore()+","+i.getPrice()+","+i.getZipcode();
                //System.out.print(s3);
                bw_r.write(s3);
                String[] arr = i.getCategories();
                //arr.
                for(int j=0;j<arr.length;j++)
                {
                    //System.out.print(","+arr[j]);
                    bw_r.write(",");
                    bw_r.write(arr[j]);
                }
                bw_r.write(System.lineSeparator());
            }
            bw_r.close();

        }
        catch (Exception e)
        {
            System.out.println("got exception while writing to restaurant");
            System.out.println(e);
        }
    }

    public Thread getThread1()
    {
        return thread1;
    }
}
