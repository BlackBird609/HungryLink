package com.example.server.serverManagerIO;

import com.example.server.prev_v3.Food;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;

public class ReadFromCustomerIdPass implements Runnable{
    HashMap<String,String> customerIdPass;
    Thread thread1;

    public ReadFromCustomerIdPass(HashMap<String,String> customerIdPass)
    {
        this.customerIdPass = customerIdPass;
        thread1 = new Thread(this);
        thread1.start();
    }

    @Override
    public void run()
    {
        //reading from customerIdPass
        try
        {
            BufferedReader br_f = new BufferedReader(new FileReader("customerIdPass.txt"));
            String s2;
            while ((s2 = br_f.readLine()) != null)
            {
                String[] arr = s2.split(",", -1);
                customerIdPass.put(arr[0],arr[1]);
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
