package com.example.restaurant.ReadandWrite;

import com.example.restaurant.HomePageController;
import com.example.restaurant.ReadAndWriteClass.ReadClass;
import com.example.restaurant.prev_v3.Food;
import com.example.restaurant.util.SocketWrapper;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class ReadThread implements Runnable{
    List<String> foodOrders;
    Thread thread;
    SocketWrapper socketWrapper;
    HomePageController homePageController;
    public ReadThread(SocketWrapper socketWrapper)
    {
        this.foodOrders = new ArrayList<>();
        this.socketWrapper = socketWrapper;
        this.thread = new Thread(this);
        //thread.start();
    }
    @Override
    public void run()
    {
        try
        {
            while(true)
            {
                String s = (String) socketWrapper.read();
                System.out.println("received message from server: "+s);

                if(s.contains("order"))
                {
                    Food food = ReadClass.readFood(socketWrapper);
                    System.out.println("food order received from customer ");
                    food.printFood();
                    String username = (String) socketWrapper.read();
                    System.out.println("received from customer " + username);
                    foodOrders.add(food.getName() + "," + username);
                    System.out.println("sending list of strings are:");
                    for (String i : foodOrders) {
                        System.out.println(i);
                    }

                    new Thread(() -> {
                        Platform.runLater(() -> {
                            homePageController.showOrders(foodOrders);
                        });
                    }).start();
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        finally {
            try
            {
                socketWrapper.closeConnection();
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
        }
    }

    public Thread getThread()
    {
        return thread;
    }
    public List<String> getFoodOrders()
    {
        return foodOrders;
    }
    public void setHomePageController(HomePageController homePageController)
    {
        this.homePageController = homePageController;
    }
    public void keepWaiting()
    {
        try
        {
            thread.wait();
            System.out.println("thread waited successfully");
        }
        catch (Exception e)
        {
            System.out.println("exception while waiting thread");
        }
    }
}
