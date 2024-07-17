package com.example.server.serverManagerIO;

import com.example.server.prev_v3.Food;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

public class WriteToMenu implements Runnable{
    Thread thread1;
    List<Food> foods;

    public WriteToMenu(List<Food> foods)
    {
        this.foods = foods;
        thread1 = new Thread(this);
        thread1.start();
    }

    @Override
    public void run()
    {

        //writing to menu
        try
        {
            BufferedWriter bw_f = new BufferedWriter(new FileWriter("menu.txt"));
            //System.out.println("to be written in file me");
            for(Food i:foods)
            {
                String s3 = i.getRestId()+","+i.getCategory()+","+i.getName()+","+i.getPrice();
                //System.out.print(s3);
                bw_f.write(s3);
                bw_f.write(System.lineSeparator());
                //System.out.print("\n");
            }
            bw_f.close();

        }
        catch (Exception e)
        {
            System.out.println("got exception while writing to menu");
            System.out.println(e);
        }
    }

    public Thread getThread1()
    {
        return thread1;
    }
}
