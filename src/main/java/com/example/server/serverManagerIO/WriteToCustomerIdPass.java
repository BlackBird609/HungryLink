package com.example.server.serverManagerIO;

import com.example.server.prev_v3.Food;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WriteToCustomerIdPass implements Runnable{
    Thread thread1;
    HashMap<String,String> hashMap;

    public WriteToCustomerIdPass(HashMap<String,String> hashMap)
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
            BufferedWriter bw_f = new BufferedWriter(new FileWriter("customerIdPass.txt"));
            for(Map.Entry<String,String> entry:hashMap.entrySet())
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
