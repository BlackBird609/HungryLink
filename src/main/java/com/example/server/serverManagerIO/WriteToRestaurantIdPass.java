package com.example.server.serverManagerIO;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

public class WriteToRestaurantIdPass implements Runnable{
    Thread thread1;
    HashMap<Integer,String> hashMap;

    public WriteToRestaurantIdPass(HashMap<Integer,String> hashMap)
    {
        this.hashMap = hashMap;
        thread1 = new Thread(this);
        thread1.start();
    }

    @Override
    public void run()
    {
        try
        {
            BufferedWriter bw_f = new BufferedWriter(new FileWriter("RestaurantIdPass.txt"));
            for(Map.Entry<Integer,String> entry:hashMap.entrySet())
            {
                String s3 = entry.getKey()+","+entry.getValue();
                bw_f.write(s3);
                bw_f.write(System.lineSeparator());
            }
            bw_f.close();

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public Thread getThread1()
    {
        return thread1;
    }
}
